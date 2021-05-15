import axios from 'axios'
import authHeader from './AuthHeader'
const state = {    
    sveApoteke :[],
    apoteka : null,
    slobodniTermini : []
  
};

const getters = {
    apoteke : state => state.apoteka,
    apoteka :state => state.apoteka,
    slobodniTermini: state =>state.slobodniTermini,
};

const actions = {
    dobaviApoteke (context) {

        return axios.get('http://localhost:8080/api/v1/apoteka/dobaviApoteke',{ headers: authHeader()})
        .then(response => {
            let apotekeSve =response.data
            context.commit('postaviApoteke',apotekeSve)
        })
    },




    dodajApoteku (context, apoteka){
        return axios.post("http://localhost:8080/api/v1/apoteka/sacuvajApoteku", apoteka, { headers: authHeader()});
    },


    dobaviApotekuAdmin(context){
         return axios.get("http://localhost:8080/api/v1/apoteka/admin", { headers: authHeader()})
        .then(response => {

            context.commit('postaviApoteku',response.data)

        })
    },


    azurirajApotekuAdmin(context,apoteka){
        console.log(apoteka)
        return axios.put("http://localhost:8080/api/v1/apoteka/admin",apoteka, { headers: authHeader()})
       .then(response => {

           context.commit('postaviApoteku',response.data)

       })
   },

    dobaviApoteku(context, id){
        return axios.get(`http://localhost:8080/api/v1/apoteka/${id}`,{ headers: authHeader()})
        .then(response => {
            context.commit('postaviApoteku',response.data)
         
        })
    },
    dobaviSlobodneTermine( context,id){
        return axios.get( `http://localhost:8080/api/v1/slobodanTermin/apoteka/${id}`,{ headers: authHeader()})
            .then(response => {
                let slobodniTermini =response.data
                context.commit('postaviSlobodneTermine',slobodniTermini)
                
            })
            .catch(error=>console.log(error))
    },

}

const mutations = {
    postaviApoteke:(state, apoteke) => (state.sveApoteke = apoteke),
    postaviApoteku:(state,apoteka)=> (state.apoteka = apoteka),
    postaviSlobodneTermine :(state,slobodniTermini)=>(state.slobodniTermini = slobodniTermini)
}

export default{
    namespaced: true,    
    state,
    getters, 
    actions,
    mutations
};