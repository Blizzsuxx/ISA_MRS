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
        axios.post('GodisnjiOdmori/zakaziGodisnji', podaci, {headers : authHeader()}).then(response => {

            alert("Zakazan godisnji");
            return response;
        })


    },

    godisnjiOdmoriAdmin(context) {
        axios.get('GodisnjiOdmori/admin', {headers : authHeader()}).then(response => {
            console.log(response.data)
            context.commit('postaviOdmore',response.data)
            return response;
        })
    },

    odobriGodisnjiOdmor(contex,id){
        return axios.put(`GodisnjiOdmori/admin/odobriZahtjev/${id}`,{}, {headers : authHeader()}).then(() => {
            return contex;
        })
        
    },
    odbijGodisnjiOdmor(contex,id){
        return axios.put(`GodisnjiOdmori/admin/odbijZahtjev/${id}`,{}, {headers : authHeader()}).then(() => {
            return contex;
        })

    },



    potvrdiOdmor(context, podaci) {
        axios.post('godisnjiOdmor/potvrdiGodisnji', podaci, {headers : authHeader()}).then(response => {

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