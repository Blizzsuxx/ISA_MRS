import axios from 'axios'

const state = {    
    sveApoteke :[],
  
};

const getters = {
    apoteke : state => state.apoteka    
};

const actions = {
    dobaviApoteke (context) {
        axios.get('http://localhost:8080/api/v1/apoteka/dobaviApoteke')
        .then(response => {
            let apotekeSve =response.data
            console.log(apotekeSve[0].adresa.ulica)
            context.commit('postaviApoteke',apotekeSve)
        })
    },

    dodajApoteku (context, apoteka){
        axios.post("http://localhost:8080/api/v1/apoteka/sacuvajApoteku", apoteka)
        .then(response => {
            alert("Dodata apoteka");
          return response;
        })
    }

}

const mutations = {
    postaviApoteke:(state, apoteke) => (state.sveApoteke = apoteke),
}

export default{
    namespaced: true,    
    state,
    getters, 
    actions,
    mutations
};