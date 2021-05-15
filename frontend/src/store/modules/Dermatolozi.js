import axios from 'axios'
import authHeader from './AuthHeader'

const state = {
    sviDermatolozi :[],
    radnoVrijeme : null,
    slobodniTermini : null,

};

const getters = {
    sviDermatolozi : state => state.sviDermatolozi
};

const actions = {
    dobaviDermatologe (context) {
        axios.get('http://localhost:8080/api/v1/dermatolog/dobaviDermatologe', { headers: authHeader()})
            .then(response => {
                let dermatolozi =response.data
                context.commit('postaviDermatologe',dermatolozi)
            })


    },
    dobaviDermatologeAdmin (context) {
        axios.get('http://localhost:8080/api/v1/dermatolog/admin', { headers: authHeader()})
            .then(response => {
                let dermatolozi =response.data
                context.commit('postaviDermatologe',dermatolozi)
            })


    },
    dobaviNezaposleneDermatologe (context) {
        axios.get('http://localhost:8080/api/v1/dermatolog/admin/nezaposleni', { headers: authHeader()})
            .then(response => {
                let dermatolozi =response.data
                context.commit('postaviDermatologe',dermatolozi)
            })
    },
    otpustiDermatologa(context,id){
        return axios.put(`http://localhost:8080/api/v1/dermatolog/otpustiDermatologa/${id}`, {},{ headers: authHeader()})
            .then(() => {
                
                return context;
                
            })
    },
    zaposliDermatologa(context,id){
        return axios.put(`http://localhost:8080/api/v1/dermatolog/zaposliDermatologa/${id}`, {},{ headers: authHeader()})
            .then(() => {   
                return context;  
            })
    },
    dobaviRadnoVrijeme( context,id){
        return axios.get( `http://localhost:8080/api/v1/radnoVrijeme/${id}`,{ headers: authHeader()})
            .then(response => {
                let radnoVrijeme =response.data
                
                context.commit('postaviRadnoVrijeme',radnoVrijeme)
                
            })
            .catch(error=>console.log(error))
    },
    dobaviSlobodneTermine( context,id){
        return axios.get( `http://localhost:8080/api/v1/slobodanTermin/dermatolog/${id}`,{ headers: authHeader()})
            .then(response => {
                let slobodniTermini =response.data
                context.commit('postaviSlobodneTermine',slobodniTermini)
                
            })
            .catch(error=>console.log(error))
    },
    kreirajSlobodanTermin(context,objekat){
        return axios.post( `http://localhost:8080/api/v1/slobodanTermin/`,objekat,{ headers: authHeader()}).then(()=>{
            context.commit('dodajSlobodanTermin',objekat)
            return 1
        }).catch(()=>(-1))
        
    }

}

const mutations = {
    postaviDermatologe:(state, dermatolozi) => (state.sviDermatolozi = dermatolozi),
    postaviRadnoVrijeme :(state,radnoVrijeme)=> (state.radnoVrijeme =  radnoVrijeme),
    postaviSlobodneTermine: (state,slobodniTermini)=>(state.slobodniTermini = slobodniTermini),
    dodajSlobodanTermin:(state,slobodanTermin)=>(state.slobodniTermini.push(slobodanTermin))
}

export default{
    namespaced: true,
    state,
    getters,
    actions,
    mutations
};