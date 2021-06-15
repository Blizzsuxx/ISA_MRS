import axios from 'axios'
import authHeader from './AuthHeader'
const state = {
    sviFarmaceuti :[],
    

};



const actions = {
    dobaviFarmaceute (context) {
        axios.get('dermatolog/dobaviDermatologe')
            .then(response => {
                let farmaceuti =response.data
                context.commit('postaviFarmaceute',farmaceuti)
            })


    },
    dobaviFarmaceuteAdmin (context) {
        axios.get('farmaceut/admin', { headers: authHeader()})
            .then(response => {
                let farmaceuti =response.data
                console.log(farmaceuti)
                context.commit('postaviFarmaceute',farmaceuti)
            })


    },
    dobaviNezaposleneFarmaceuteAdmin (context) {
        axios.get('farmaceut/admin/nezaposleni', { headers: authHeader()})
            .then(response => {
                let farmaceuti =response.data
                console.log(farmaceuti)
                context.commit('postaviFarmaceute',farmaceuti)
            })


    },
    otpustiFarmaceuta(context,id){
        return axios.put(`farmaceut/otpustiFarmaceuta/${id}`,{},{ headers: authHeader()})
            .then(() => {
                return context;
            })
    },
    zaposliFarmaceuta(context,id){
        return axios.put(`farmaceut/zaposliFarmaceuta/${id}`,{},{ headers: authHeader()})
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