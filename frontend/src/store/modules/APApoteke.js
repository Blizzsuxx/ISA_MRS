import axios from 'axios'
import authHeader from './AuthHeader'
import moment from 'moment'
const state = {    
    sveApoteke :[],
    apoteka : null,
    slobodniTermini : [],
    farmaceuti : [],
    slobodneApoteke : [],
    oznaka: false,

  
};

const getters = {
    apoteke : state => state.apoteka,
    apoteka :state => state.apoteka,
    slobodniTermini: state =>state.slobodniTermini,
};

const actions = {
    pretplataNaApoteku(context, idApoteke){
        return axios.get(`apoteka/pretplataApoteke/${idApoteke}`,{ headers: authHeader()});
    },

    dobaviApotekePacijenta(context){
        return axios.get('apoteka/dobaviApotekePacijenta',{ headers: authHeader()})
        .then(response => {
            let apotekeSve =response.data
            //console.log(apotekeSve[0].adresa.ulica)
            context.commit('postaviApoteke',apotekeSve)
        })
    },

    dobaviApoteke (context) {

        return axios.get('apoteka/dobaviApoteke',{ headers: authHeader()})
        .then(response => {
            let apotekeSve =response.data
            //console.log(apotekeSve[0].adresa.ulica)
            context.commit('postaviApoteke',apotekeSve)
        })
    },
    dobaviBasSveApoteke(){
        return axios.get('apoteka/dobaviBasSveApoteke',{ headers: authHeader()});
    },

    dodajApoteku (context, apoteka){
        return axios.post("apoteka/sacuvajApoteku", apoteka, { headers: authHeader()});
    },


    dobaviApotekuAdmin(context){
         return axios.get("apoteka/admin", { headers: authHeader()})
        .then(response => {

            context.commit('postaviApoteku',response.data)

        })
    },


    azurirajApotekuAdmin(context,apoteka){
        return axios.put("apoteka/admin",apoteka, { headers: authHeader()})
       .then(response => {

           context.commit('postaviApoteku',response.data)

       })
   },

    dobaviApoteku(context, id){
        return axios.get(`apoteka/${id}`,{ headers: authHeader()})
        .then(response => {
            context.commit('postaviApoteku',response.data)
         
        })
    },
    izvjestaj(){
        return axios.get(`apoteka/izvjestaj`,{ headers: authHeader()})
        .then(response => {
            return response.data;
         
        })
        
    },

    izvjestajPeriod(context,data){
        console.log(context)
        
        let datumOd = moment(data.datumOd).format('YYYY-MM-DD hh-mm').split(" ")[0]+"-23-59";
        let datumDo = moment(data.datumDo).format('YYYY-MM-DD hh-mm').split(" ")[0]+"-23-59";
        console.log(datumOd)
        console.log(datumDo)
        return axios.get(`apoteka/izvjestaj/${datumOd}/${datumDo}`,{ headers: authHeader()})
        .then(response => {
            console.log(response.data)
            return response.data;
         
        })
        
        
    },

    // dobaviApotekuKorisnik(context, id){
    //     return axios.get(`http://localhost:8080/api/v1/apoteka/${id}/profil/korisnik`,{ headers: authHeader()})
    //     .then(response => {
    //         context.commit('postaviApoteku',response.data)
         
    //     })
    // },
    dobaviSlobodneTermine( context,id){
        return axios.get( `slobodanTermin/apoteka/${id}`,{ headers: authHeader()})
            .then(response => {
                let slobodniTermini =response.data
                context.commit('postaviSlobodneTermine',slobodniTermini)
                
            })
            .catch(error=>console.log(error))
    },
    dobaviSlobodneTermineFarmaceut( context,id){//ovo treba da vrati sve apoteke koje imaju slobodne termine u zadatom periodu
        console.log(id)
        return axios.get( `slobodanTermin/slobodanTerminApoteke/${id}`,{ headers: authHeader()})
            .then(response => {
                let slobodniTermini =response.data
                let listaApoteka=[]
                console.log(response.data)
                let i=0;
                for ( i = 0; i < response.data.length; i++) {
                    console.log("a")
                   listaApoteka[i]=response.data[i].apoteka
                  }
                console.log(listaApoteka)
                context.commit('postaviSlobodneApoteke',listaApoteka)
                context.commit('postaviSlobodneTermine',slobodniTermini)
                console.log()
            })
            .catch(error=>console.log(error))
    },
    dobaviFarmaceute({commit,state}, ime){
        let farmaceuti = []
        let i=0;
        
        for ( i = 0; i < state.slobodniTermini.length; i++) {
            if(state.slobodniTermini[i].apoteka.ime==ime){
                commit('postaviApoteku',state.slobodniTermini[i].apoteka)
                farmaceuti[i]={"ime":state.slobodniTermini[i].imeRadnika,"prezime":state.slobodniTermini[i].prezimeRadnika,"ocena":state.slobodniTermini[i].ocena }
          }}
          
          commit('postaviFarmaceute',farmaceuti)
    },
    zakaziPosetu( {commit,state},id){//u fji dobavi farmaceuta sam stavila i apoteku koja je izabrana, pa ako budem htela
        //da saljem ceo termin, mogu pronaci preko toga koji je to ceo termin, ovakoo saljem samo farmaceuta
        var naziv=id.split(" ")
        let i=0;
        let idPosete=""
        for ( i = 0; i < state.slobodniTermini.length; i++) {
            if(state.slobodniTermini[i].apoteka.ime==state.apoteka.ime && state.slobodniTermini[i].imeRadnika==naziv[0] && state.slobodniTermini[i].prezimeRadnika==naziv[1]){
            idPosete=state.slobodniTermini[i].id   
            }}
        return axios.put( `slobodanTermin/zakaziFarmaceuta`,{"a":idPosete},{ headers: authHeader()})
            .then(response => {
                if(response.data){
                    commit("setujOznaku",true)
                }else{
                    commit("setujOznaku",false)
                }
            })
            .catch(error=>console.log(error))
    }

}
const mutations = {
    postaviApoteke:(state, apoteke) => (state.sveApoteke = apoteke),
    postaviApoteku:(state,apoteka)=> (state.apoteka = apoteka),
    postaviSlobodneTermine :(state,slobodniTermini)=>(state.slobodniTermini = slobodniTermini),
    postaviFarmaceute:(state,f)=>(state.farmaceuti = f),
    postaviSlobodneApoteke:(state,apoteka)=> (state.slobodneApoteke = apoteka),
    setujOznaku:(state, oz)=>(state.oznaka=oz)
}

export default{
    namespaced: true,    
    state,
    getters, 
    actions,
    mutations
};