import axios from 'axios'

const state = {
    sviPacijenti :[],

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


    }

}

const mutations = {
    postaviPacijente:(state, pacijenti) => (state.sviPacijenti = pacijenti),
}

export default{
    namespaced: true,
    state,
    getters,
    actions,
    mutations
};