import axios from 'axios'
import authHeader from './AuthHeader'

const state = {
    svePosete :[],
    zakazanePosetePacijenta : [],
    istorijaDermatolog : [],
    istorijaFarmaceut : [],
    slobodnePoseteD: [],
};

const getters = {
    posete : state => state.poseta
};

const actions = {
    dobaviPosete (context) {
        return axios.get('posete/dobaviPosete',{ headers: authHeader()})
            .then(response => {
                let poseteSve =response.data
                
                context.commit('postaviPosete',poseteSve)
            })


    },
    dobaviPosetePacijenta (context) {
         return axios.get('posete/dobaviPosetePacijenta',{ headers: authHeader()})
            .then(response => {
                let poseteSve =response.data
                console.log(poseteSve);
                context.commit('postaviPosetePacijenta',poseteSve)
            })


    },
    dobaviIstorijuD (context) {
        return axios.get('posete/dobaviIstorijuD',{ headers: authHeader()})
           .then(response => {
               let poseteSve =response.data
               console.log(poseteSve);
               context.commit('postaviIstorijuD',poseteSve)
           })


   },
   dobaviIstorijuF (context) {
    return axios.get('posete/dobaviIstorijuF',{ headers: authHeader()})
       .then(response => {
           let poseteSve =response.data
           console.log(poseteSve);
           context.commit('postaviIstorijuF',poseteSve)
       })


},
    dobaviPoseteFPacijenta (context) {
        return axios.get('posete/dobaviPosetePacijentaF',{ headers: authHeader()})
           .then(response => {
               let poseteSve =response.data
               console.log(poseteSve);
               context.commit('postaviPosetePacijenta',poseteSve)
           })


   },
   otkazi(context,index){

    let rezSve=state.zakazanePosetePacijenta.filter(function(el){
        if(state.zakazanePosetePacijenta[index].pocetak==el.pocetak){
            return false;
        }else{return true;}
    })

    axios.post('posete/otkazi', {"a":state.zakazanePosetePacijenta[index]}, {headers : authHeader()}).then(response => {

        if(response){
            alert("Uspesno ste otkazali pregled");
            context.commit('postaviPosetePacijenta', rezSve) }
            else{
                alert("Isteklo je vreme za otkazivanje")  
            }
          return response;
    })
   },
   otkaziF(context,index){

    let rezSve=state.zakazanePosetePacijenta.filter(function(el){
        if(state.zakazanePosetePacijenta[index].pocetak==el.pocetak){
            console.log("na")
            return false;
        }else{return true;}
    })

    axios.post('posete/otkaziF', {"a":state.zakazanePosetePacijenta[index]}, {headers : authHeader()}).then(response => {

        if(response){
            alert("Uspesno ste otkazali pregled.");
            console.log(rezSve)
            context.commit('postaviPosetePacijenta', rezSve) }
            else{
                alert("Isteklo je vreme za otkazivanje")  
            }
          return response;
    })
   },
    zakaziPosetu(context, podaci){
        axios.post('posete/zakaziPosetu', podaci, {headers : authHeader()}).then(response => {

            alert("Zakazan pregled");
            return response;
        })
    },

    zabeleziOdsustvo(context, podaci){
        
        axios.post('posete/zabeleziOdsustvo', podaci, {headers : authHeader()}).then(response => {

            return response;
        })
    },

    dobaviPoseteAktivne(context){
        return axios.get('posete/dobaviPoseteAktivne',{ headers: authHeader()})
            .then(response => {
                let poseteSve =response.data
                
                context.commit('postaviPosete',poseteSve)
            })
    },
    
    dobaviSlobodnePoseteDermatologa(context){

        return axios.get('posete/dobaviSlobodnePoseteDermatologa',{ headers: authHeader()})
           .then(response => {
               let poseteSve =response.data
               context.commit('postaviSlobodnePoseteD',poseteSve)
           })
    },
    rezervisiPosetuD(context, id){
        axios.post('posete/rezervisiPosetuD',id, {headers : authHeader()}).then(response => {
            if(response.data=="Uspesno ste zakazali posetu kod dermatologa Sime."){

            alert(response.data);
            return true;}else{return false;}
        })
    }
}

const mutations = {
    postaviPosete:(state, posete) => (state.svePosete = posete),
    postaviPosetePacijenta:(state, posete1)=>(state.zakazanePosetePacijenta=posete1),
    postaviIstorijuD:(state,posete2)=>(state.istorijaDermatolog=posete2),
    postaviIstorijuF:(state,posete2)=>(state.istorijaFarmaceut=posete2),
    postaviSlobodnePoseteD:(state, slob)=>(state.slobodnePoseteD=slob)
}

export default{
    namespaced: true,
    state,
    getters,
    actions,
    mutations
};