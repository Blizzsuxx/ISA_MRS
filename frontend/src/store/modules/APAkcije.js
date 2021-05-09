import axios from 'axios'
import authHeader from './AuthHeader'

const state = {    
    akcije :[],
    
};
const getters = {
    akcije : state => state.akcije  
};

const actions = {
    dobaviAkcije (context) { 
        return axios.get('http://localhost:8080/api/v1/akcija/dobaviAkcije',{ headers: authHeader()})
        .then(response => {
        let sve =response.data
        context.commit('postaviAkcije',sve)
        console.log(sve)
        })
  }
}

const mutations = {
    postaviAkcije:(state, sve) => (state.akcije= sve),
  
}

export default{
    namespaced: true,    
    state,
    actions,
    getters,
    mutations
};