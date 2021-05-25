import axios from 'axios'
import authHeader from './AuthHeader'

const state = {
    svePonude :[],

};

const getters = {
    svePonude : state => state.svePonude
};

const actions = {
    dobaviPonudeDobavljaca(){
        return axios.get('http://localhost:8080/api/v1/ponuda/dobaviPonudeDobavljaca', { headers : authHeader()});
    },
    dobaviPonude (context,id) {
        axios.get(`http://localhost:8080/api/v1/ponuda/narudzbenica/${id}/admin`, { headers: authHeader()})
            .then(response => {
                let ponude =response.data
                context.commit('postaviPonude',ponude)
            })
    },
    prihvatiPonudu(context,params) {
        return axios.put(`http://localhost:8080/api/v1/ponuda/narudzbenica/${params.nId}/prihvati/${params.id}`,{},{ headers: authHeader()})
            .then(() => {
            })
    },
    odbijPonudu(context,id) {
        return axios.put(`http://localhost:8080/api/v1/ponuda/${id}/odbij`,{}, { headers: authHeader()})
            .then(() => {
                
            })
    },
}

const mutations = {
    postaviPonude:(state, ponude) => (state.svePonude = ponude),
   
}

export default{
    namespaced: true,
    state,
    getters,
    actions,
    mutations
};