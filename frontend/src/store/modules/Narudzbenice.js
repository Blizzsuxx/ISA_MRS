import axios from 'axios'
import authHeader from './AuthHeader'

const state = {
    sveNarudzbenice :[]

};

const getters = {
    sveNarudzbenice : state => state.sveNarudzbenice
};

const actions = {
    dobaviNarudzbenice (context) {
        axios.get('http://localhost:8080/api/v1/narudzbenice/admin', { headers: authHeader()})
            .then(response => {
                let narudzbenice =response.data
                console.log(narudzbenice)
                context.commit('postaviNarudzbenice',narudzbenice)
            })


    },
    

}

const mutations = {
    postaviNarudzbenice:(state, narudzbenice) => (state.sveNarudzbenice = narudzbenice),
    
}

export default{
    namespaced: true,
    state,
    getters,
    actions,
    mutations
};