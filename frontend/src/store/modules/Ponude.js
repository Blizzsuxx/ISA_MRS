import axios from 'axios'
import authHeader from './AuthHeader'

const state = {
    svePonude :[],
    noviLijekovi :[],

};

const getters = {
    svePonude : state => state.svePonude
};

const actions = {
    azurirajPonudu(context, ponuda){
        return axios.post('http://localhost:8080/api/v1/ponuda/azurirajPonudu', ponuda, {headers : authHeader()});
    },
    kreirajPonudu(context, ponuda){
        
        return axios.post('http://localhost:8080/api/v1/ponuda/kreirajPonudu', ponuda, {headers : authHeader()})
    },
    dobaviPonudeDobavljaca(){
        return axios.get('http://localhost:8080/api/v1/ponuda/dobaviPonudeDobavljaca', { headers : authHeader()});
    },
    dobaviNarudzbenicuPonude(context, id){
        return axios.get(`http://localhost:8080/api/v1/ponuda/dobaviNarudzbenicuPonude/${id}`, { headers : authHeader()});
    },
    dobaviPonude (context,id) {
        axios.get(`http://localhost:8080/api/v1/ponuda/narudzbenica/${id}/admin`, { headers: authHeader()})
            .then(response => {
                let ponude =response.data
                context.commit('postaviPonude',ponude)
            })
    },
    prihvatiPonudu(context,params) {
        return axios.put(`http://localhost:8080/api/v1/ponuda/narudzbenica/${params.nId}/prihvati/${params.id}`,params.lijekovi,{ headers: authHeader()})
            .then(() => {
            })
    },
    pripremiPonudu(context,params) {
        return axios.get(`http://localhost:8080/api/v1/ponuda/narudzbenica/${params.nId}/pripremi/${params.id}`,{ headers: authHeader()})
            .then((response) => {
                context.commit('postaviNoveLijekove',response.data)
            })
    },
    odbijPonudu(context,id) {
        return axios.put(`http://localhost:8080/api/v1/ponuda/${id}/odbij`,{}, { headers: authHeader()})
            .then(() => {
                
            })
    },
}

const mutations = {
    postaviPonude:(state, ponude) => (state.svePonude = ponude),
    postaviNoveLijekove:(state,noviLijekovi)=>(state.noviLijekovi = noviLijekovi)
   
}

export default{
    namespaced: true,
    state,
    getters,
    actions,
    mutations
};