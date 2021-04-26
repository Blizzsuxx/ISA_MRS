import axios from 'axios'


const state = {
    svePosete :[],
    zakazanePosetePacijenta : [],
    istorijaDermatolog : [],
    istorijaFarmaceut : [],
};

const getters = {
    posete : state => state.poseta
};

const actions = {
    dobaviPosete (context) {
        return axios.get('http://localhost:8080/api/v1/posete/dobaviPosete')
            .then(response => {
                let poseteSve =response.data

                context.commit('postaviPosete',poseteSve)
            })


    },
    dobaviPosetePacijenta (context) {
         return axios.get('http://localhost:8080/api/v1/posete/dobaviPosetePacijenta')
            .then(response => {
                let poseteSve =response.data
                console.log(poseteSve);
                context.commit('postaviPosetePacijenta',poseteSve)
            })


    },
    dobaviIstorijuD (context) {
        return axios.get('http://localhost:8080/api/v1/posete/dobaviIstorijuD')
           .then(response => {
               let poseteSve =response.data
               console.log(poseteSve);
               context.commit('postaviIstorijuD',poseteSve)
           })


   },
   dobaviIstorijuF (context) {
    return axios.get('http://localhost:8080/api/v1/posete/dobaviIstorijuF')
       .then(response => {
           let poseteSve =response.data
           console.log(poseteSve);
           context.commit('postaviIstorijuF',poseteSve)
       })


},
    dobaviPoseteFPacijenta (context) {
        return axios.get('http://localhost:8080/api/v1/posete/dobaviPosetePacijentaF')
           .then(response => {
               let poseteSve =response.data
               console.log(poseteSve);
               context.commit('postaviPosetePacijenta',poseteSve)
           })


   },

    zakaziPosetu(context, podaci){
        axios.post('http://localhost:8080/api/v1/posete/zakaziPosetu', podaci).then(response => {

            alert("Zakazan pregled");
            return response;
        })
    }

}

const mutations = {
    postaviPosete:(state, posete) => (state.svePosete = posete),
    postaviPosetePacijenta:(state, posete1)=>(state.zakazanePosetePacijenta=posete1),
    postaviIstorijuD:(state,posete2)=>(state.istoijaDermatolog=posete2),
    postaviIstorijuF:(state,posete2)=>(state.istoijaFarmaceut=posete2)

}

export default{
    namespaced: true,
    state,
    getters,
    actions,
    mutations
};