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
        return axios.get('http://localhost:8080/api/v1/posete/dobaviPosete',{ headers: authHeader()})
            .then(response => {
                let poseteSve =response.data
                
                context.commit('postaviPosete',poseteSve)
            })


    },
    dobaviPosetePacijenta (context) {
         return axios.get('http://localhost:8080/api/v1/posete/dobaviPosetePacijenta',{ headers: authHeader()})
            .then(response => {
                let poseteSve =response.data
                console.log(poseteSve);
                context.commit('postaviPosetePacijenta',poseteSve)
            })


    },
    dobaviIstorijuD (context) {
        return axios.get('http://localhost:8080/api/v1/posete/dobaviIstorijuD',{ headers: authHeader()})
           .then(response => {
               let poseteSve =response.data
               console.log(poseteSve);
               context.commit('postaviIstorijuD',poseteSve)
           })


   },
   dobaviIstorijuF (context) {
    return axios.get('http://localhost:8080/api/v1/posete/dobaviIstorijuF',{ headers: authHeader()})
       .then(response => {
           let poseteSve =response.data
           console.log(poseteSve);
           context.commit('postaviIstorijuF',poseteSve)
       })


},
    dobaviPoseteFPacijenta (context) {
        return axios.get('http://localhost:8080/api/v1/posete/dobaviPosetePacijentaF',{ headers: authHeader()})
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

    axios.post('http://localhost:8080/api/v1/posete/otkazi', {"a":state.zakazanePosetePacijenta[index]}, {headers : authHeader()}).then(response => {

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

    axios.post('http://localhost:8080/api/v1/posete/otkaziF', {"a":state.zakazanePosetePacijenta[index]}, {headers : authHeader()}).then(response => {

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
        axios.post('http://localhost:8080/api/v1/posete/zakaziPosetu', podaci, {headers : authHeader()}).then(response => {

            alert("Zakazan pregled");
            return response;
        })
    },

    zabeleziOdsustvo(context, podaci){
        
        axios.post('http://localhost:8080/api/v1/posete/zabeleziOdsustvo', podaci, {headers : authHeader()}).then(response => {

            return response;
        })
    },

    dobaviPoseteAktivne(context){
        return axios.get('http://localhost:8080/api/v1/posete/dobaviPoseteAktivne',{ headers: authHeader()})
            .then(response => {
                let poseteSve =response.data
                
                context.commit('postaviPosete',poseteSve)
            })
    },
    
    dobaviSlobodnePoseteDermatologa(context){

        return axios.get('http://localhost:8080/api/v1/posete/dobaviSlobodnePoseteDermatologa',{ headers: authHeader()})
           .then(response => {
               let poseteSve =response.data
               context.commit('postaviSlobodnePoseteD',poseteSve)
           })
    },
    rezervisiPosetuD(context, id){
        axios.post('http://localhost:8080/api/v1/posete/rezervisiPosetuD',id, {headers : authHeader()}).then(response => {
            
            alert(response.data);
            return response;
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