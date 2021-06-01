import axios from 'axios'
import authHeader from './AuthHeader'

const state = {
    sveNarudzbenice :[],
    narudzbenica :null

};

const getters = {
    sveNarudzbenice : state => state.sveNarudzbenice
};

const actions = {
    dobaviSveNarudzbenice(){
        return axios.get('http://localhost:8080/api/v1/narudzbenice/dobaviSveNarudzbenice', {headers: authHeader()});
    },
    
    dobaviNarudzbenice (context) {
        axios.get('http://localhost:8080/api/v1/narudzbenice/admin', { headers: authHeader()})
            .then(response => {
                let narudzbenice =response.data
                context.commit('postaviNarudzbenice',narudzbenice)
            })
    },

    dobaviNarudzbenicu (context,id) {
        return axios.get(`http://localhost:8080/api/v1/narudzbenice/${id}/admin`, { headers: authHeader()})
            .then(response => {
                let narudzbenice =response.data
                console.log(narudzbenice)
                context.commit('postaviNarudzbenicu',narudzbenice)
            })
    },
    izbrisiNarudzbenicu(context,id){
        return axios.delete(`http://localhost:8080/api/v1/narudzbenice/${id}/admin`, { headers: authHeader()})
            .then(() => {
                
            })
    }
    

}

const mutations = {
    postaviNarudzbenice:(state, narudzbenice) => (state.sveNarudzbenice = narudzbenice),
    postaviNarudzbenicu:(state,narudzbenica)=>(state.narudzbenica = narudzbenica)
}

export default{
    namespaced: true,
    state,
    getters,
    actions,
    mutations
};