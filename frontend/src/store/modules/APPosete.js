import axios from 'axios'


const state = {
    svePosete :[],

};

const getters = {
    posete : state => state.poseta
};

const actions = {
    dobaviPosete (context) {
        axios.get('http://localhost:8080/api/v1/posete/dobaviPosete')
            .then(response => {
                let poseteSve =response.data

                context.commit('postaviPosete',poseteSve)
            })


    }

}

const mutations = {
    postaviPosete:(state, posete) => (state.svePosete = posete),
}

export default{
    namespaced: true,
    state,
    getters,
    actions,
    mutations
};