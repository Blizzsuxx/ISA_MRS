import axios from 'axios'

const state = {    
    korisnik :null,
  
};

const getters = {
    kor : state => state.profil    
};

const actions = {
    dobaviProfil (context) {
        axios.get('http://localhost:8080/api/v1/profil/dobaviPacijenta')
        .then(response => {
            let profil1 =response.data
            //console.log(profil1[0].adresa.ulica)
            context.commit('postaviProfil',profil1)
        })
    }
}

const mutations = {
    postaviProfil:(state, kor) => (state.korisnik = kor),
}

export default{
    namespaced: true,    
    state,
    getters, 
    actions,
    mutations
};