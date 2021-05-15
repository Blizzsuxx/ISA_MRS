import axios from 'axios'
import authHeader from './AuthHeader'
const state = {
    sviFarmaceuti :[],
    

};



const actions = {
    dobaviFarmaceute (context) {
        axios.get('http://localhost:8080/api/v1/dermatolog/dobaviDermatologe')
            .then(response => {
                let farmaceuti =response.data
                context.commit('postaviFarmaceute',farmaceuti)
            })


    },
    dobaviFarmaceuteAdmin (context) {
        axios.get('http://localhost:8080/api/v1/farmaceut/admin', { headers: authHeader()})
            .then(response => {
                let farmaceuti =response.data
                console.log(farmaceuti)
                context.commit('postaviFarmaceute',farmaceuti)
            })


    },
    dobaviNezaposleneFarmaceuteAdmin (context) {
        axios.get('http://localhost:8080/api/v1/farmaceut/admin/nezaposleni', { headers: authHeader()})
            .then(response => {
                let farmaceuti =response.data
                console.log(farmaceuti)
                context.commit('postaviFarmaceute',farmaceuti)
            })


    },
    otpustiFarmaceuta(context,id){
        return axios.put(`http://localhost:8080/api/v1/farmaceut/otpustiFarmaceuta/${id}`,{},{ headers: authHeader()})
            .then(() => {
                return context;
            })
    },
    zaposliFarmaceuta(context,id){
        return axios.put(`http://localhost:8080/api/v1/farmaceut/zaposliFarmaceuta/${id}`,{},{ headers: authHeader()})
            .then(() => {
                return context;
            })
    },
}

const mutations = {
    postaviFarmaceute:(state, farmaceuti) => (state.sviFarmaceuti = farmaceuti),
   
}

export default{
    namespaced: true,
    state,
    actions,
    mutations
};