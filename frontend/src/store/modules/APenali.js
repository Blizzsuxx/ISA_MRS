import axios from 'axios'
import authHeader from './AuthHeader'

const state = {    
    sviPenali :[],
    
  
};

const getters = {
    sviPenali : state => state.penal   
};

const actions = {
    dobaviPenale (context) {
        return axios.get('http://localhost:8080/api/v1/profil/dobaviPenale',{ headers: authHeader()})
        .then(response => {
            let rSvi =response.data
            context.commit('postaviPenale',rSvi)
            
        })
    },

}

const mutations = {
    postaviPenale:(state, penali) => (state.sviPenali = penali),
}

export default{
    namespaced: true,    
    state,
    getters, 
    actions,
    mutations
};