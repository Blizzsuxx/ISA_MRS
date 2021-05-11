import axios from 'axios'
import authHeader from './AuthHeader';
const state = {
    sviOdmori :[],

};

const getters = {
    sviOdmori : state => state.sviOdmori
};

const actions = {
    zakaziGodisnji (context, podaci) {
        axios.post('http://localhost:8080/api/v1/GodisnjiOdmori/zakaziGodisnji', podaci, {headers : authHeader()}).then(response => {

            alert("Zakazan godisnji");
            return response;
        })


    },

    godisnjiOdmoriAdmin(context) {
        axios.get('http://localhost:8080/api/v1/GodisnjiOdmori/admin', {headers : authHeader()}).then(response => {
            console.log(response.data)
            context.commit('postaviOdmore',response.data)
            return response;
        })
    },



    potvrdiOdmor(context, podaci) {
        axios.post('http://localhost:8080/api/v1/godisnjiOdmor/potvrdiGodisnji', podaci, {headers : authHeader()}).then(response => {

            alert("promena izvrsena");
            return response;
        })
    }

}

const mutations = {
    postaviOdmore:(state, odmori) => (state.sviOdmori = odmori),
}

export default{
    namespaced: true,
    state,
    getters,
    actions,
    mutations
};