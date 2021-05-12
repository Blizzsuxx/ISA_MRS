import axios from 'axios'

const state = {
    sviPacijenti :[],
    trenutniPacijent: null,

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

    pacijentZaPrelged(context, pacijent){
        context.commit('postaviPacijentaZaPregled', pacijent);
    }

}

const mutations = {
    postaviPacijente:(state, pacijenti) => (state.sviPacijenti = pacijenti),
    postaviPacijentaZaPregled:(state, pacijentNov) => (state.trenutniPacijent = pacijentNov)
}

export default{
    namespaced: true,
    state,
    getters,
    actions,
    mutations
};