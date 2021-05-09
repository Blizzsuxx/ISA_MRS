import axios from 'axios'
import authHeader from './AuthHeader'
const state = {
    sviPacijenti :[],
    kategorija : [],
    pretplata : [],

};

const getters = {
    pacijenti : state => state.pacijent
};

const actions = {
    dobaviPacijente (context) {
        axios.get('http://localhost:8080/api/v1/apoteka/dobaviPacijente')
            .then(response => {
                let pacijentiSvi =response.data
                context.commit('postaviPacijente',pacijentiSvi)
            })


    },
    dobaviKategorijuPacijenta(context){
        return axios.get('http://localhost:8080/api/v1/profil/dobaviKategoriju',{ headers: authHeader()}).then(response=>{
            let kategorija=response.data
            context.commit('postaviKategoriju',kategorija)
        })
    },
    dobaviPretplaceneApoteke(context){
        return axios.get('http://localhost:8080/api/v1/profil/pretplata',{ headers: authHeader()}).then(response=>{
            let apoteke=response.data
            console.log(apoteke)
            context.commit('postaviApoteke',apoteke)
        })
    }

}

const mutations = {
    postaviPacijente:(state, pacijenti) => (state.sviPacijenti = pacijenti),
    postaviKategoriju:(state, kat) => (state.kategorija = kat),
    postaviApoteke:(state, apo) => (state.pretplata = apo),
}

export default{
    namespaced: true,
    state,
    getters,
    actions,
    mutations
};