import axios from 'axios'
import authHeader from './AuthHeader'

const state = {    
    oceneSve :[],
    
};
const getters = {
    ocene : state => state.oceneSve    
};

const actions = {
    dobaviSvojeDermatologe (context) {
        axios.get('http://localhost:8080/api/v1/profil/dobaviSvojeDermatologe',{ headers: authHeader()})
        .then(response => {
        let ocene =response.data
        console.log(ocene)
        context.commit('postaviOcene',ocene)
        })
  },
  dobaviSvojeFarmaceute (context) {
    axios.get('http://localhost:8080/api/v1/profil/dobaviSvojeFarmaceute',{ headers: authHeader()})
    .then(response => {
    let ocene =response.data
    console.log(ocene)
    context.commit('postaviOcene',ocene)
    })
},
dobaviSvojeApoteke (context) {
    axios.get('http://localhost:8080/api/v1/profil/dobaviSvojeApoteke',{ headers: authHeader()})
    .then(response => {
    let ocene =response.data
    console.log(ocene)
    context.commit('postaviOcene',ocene)
    })
},
dobaviSvojeLekove (context) {
    axios.get('http://localhost:8080/api/v1/profil/dobaviSvojeLekove',{ headers: authHeader()})
    .then(response => {
    let ocene =response.data
    console.log(ocene)
    context.commit('postaviOcene',ocene)
    })
},  
    posalji( context,idOcena){
       console.log(context)
        console.log(idOcena)
        axios.put(`http://localhost:8080/api/v1/profil/posaljiOcenu/${idOcena}`,{},{ headers: authHeader()})
        .then(response => {
            console.log("baba")
            return response
        }
            //return response
        )  
    }

}

const mutations = {
    postaviOcene:(state, o) => (state.oceneSve = o),
   
}

export default{
    namespaced: true,    
    state,
    actions,
    getters,
    mutations
};