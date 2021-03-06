import axios from 'axios'
import moment from 'moment'
import authHeader from './AuthHeader'

const state = {    
    akcije :[],
    
};
const getters = {
    akcije : state => state.akcije  
};

const actions = {
    dobaviAkcije (context,id) { 
        return axios.put(`akcija/dobaviAkcije/${id}`,{},{ headers: authHeader()})
        .then(response => {
        let sve =response.data
        context.commit('postaviAkcije',sve)
        
        })
    },
    kreirajAkciju(contex,forma){
        let datumOd =  moment(String(forma.datumOd)).format('YYYY-MM-DD hh:mm').split(" ")[0]+" 00:00";
        let datumDo =  moment(String(forma.datumDo)).format('YYYY-MM-DD hh:mm').split(" ")[0]+" 23:59";
        return axios.post("akcija/", {popust:parseInt(forma.popust),
                                                datumOd: datumOd,
                                                datumDo: datumDo,
                                                id:parseInt(forma.id)},{ headers: authHeader()})
        
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