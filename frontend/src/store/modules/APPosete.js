import axios from 'axios'
import authHeader from './AuthHeader'

const state = {
    svePosete :[],
    zakazanePosetePacijenta : [],
    istorijaDermatolog : [],
    istorijaFarmaceut : [],
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

    zakaziPosetu(context, podaci){
        axios.post('http://localhost:8080/api/v1/posete/zakaziPosetu', podaci).then(response => {

            alert("Zakazan pregled");
            return response;
        })
    }

}

const mutations = {
    postaviPosete:(state, posete) => (state.svePosete = posete),
    postaviPosetePacijenta:(state, posete1)=>(state.zakazanePosetePacijenta=posete1),
    postaviIstorijuD:(state,posete2)=>(state.istorijaDermatolog=posete2),
    postaviIstorijuF:(state,posete2)=>(state.istorijaFarmaceut=posete2)

}

export default{
    namespaced: true,
    state,
    getters,
    actions,
    mutations
};