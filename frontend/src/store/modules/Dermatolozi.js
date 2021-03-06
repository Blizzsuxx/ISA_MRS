import axios from 'axios'
import authHeader from './AuthHeader'
import moment from 'moment'
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
        axios.get('dermatolog/dobaviDermatologe', { headers: authHeader()})
            .then(response => {
                let dermatolozi =response.data
                context.commit('postaviDermatologe',dermatolozi)
            })


    },
    dobaviDermatologeKorisnik (context,id) {
        axios.get(`dermatolog/korisnik/${id}`, { headers: authHeader()})
            .then(response => {
                let dermatolozi =response.data
                context.commit('postaviDermatologe',dermatolozi)
            })


    },
    dobaviDermatologeAdmin (context) {
        axios.get('dermatolog/admin', { headers: authHeader()})
            .then(response => {
                let dermatolozi =response.data
                context.commit('postaviDermatologe',dermatolozi)
            })


    },
    dobaviNezaposleneDermatologe (context) {
        axios.get('dermatolog/admin/nezaposleni', { headers: authHeader()})
            .then(response => {
                let dermatolozi =response.data
                context.commit('postaviDermatologe',dermatolozi)
            })
    },
    otpustiDermatologa(context,id){
        return axios.put(`dermatolog/otpustiDermatologa/${id}`, {},{ headers: authHeader()})
            .then(() => {
                
                return context;
                
            })
    },
    zaposliDermatologa(context,radnoVrijeme){
        console.log(String(radnoVrijeme.pocetakRadnogVremena))
        radnoVrijeme.pocetakRadnogVremena = moment(String(radnoVrijeme.pocetakRadnogVremena)).format('DD-MM-YYYY HH:mm')
        radnoVrijeme.krajRadnogVremena = moment(String(radnoVrijeme.krajRadnogVremena)).format('DD-MM-YYYY HH:mm')
        console.log(radnoVrijeme)
        return axios.post(`dermatolog/zaposliDermatologa`, radnoVrijeme,{ headers: authHeader()})
            .then(() => {   
                return context;  
            })
    },
    dobaviRadnoVrijeme( context,id){
        return axios.get( `radnoVrijeme/${id}`,{ headers: authHeader()})
            .then(response => {
                let radnoVrijeme =response.data
                
                context.commit('postaviRadnoVrijeme',radnoVrijeme)
                
            })
            .catch(error=>console.log(error))
    },
    dobaviSlobodneTermine( context,id){
        return axios.get( `slobodanTermin/dermatolog/${id}`,{ headers: authHeader()})
            .then(response => {
                let slobodniTermini =response.data
                context.commit('postaviSlobodneTermine',slobodniTermini)
                
            })
            .catch(error=>console.log(error))
    },

    
    kreirajSlobodanTermin(context,objekat){
        return axios.post( `slobodanTermin/`,objekat,{ headers: authHeader()}).then(()=>{
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