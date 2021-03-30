import axios from 'axios'

const state = {    
    form :null,
  
};

const getters = {
    kor : state => state.profil    
};

const actions = {
    dobaviProfil (context) {
       
        axios.get('http://localhost:8080/api/v1/profil')
        .then(response => {
            let form =response.data
            console.log(form.ime)
            context.commit('postaviProfil',form)
        })
    },
    izmeniPodatke () {
       
        /*axios.get('http://localhost:8080/api/v1/profil')
        .then(response => {
            let profil1 =response.data
            console.log(profil1.adresa.ulica)
            context.commit('postaviProfil',profil1)

        })*/
    }
}

const mutations = {
    postaviProfil:(state, kor) => (state.form = kor),
}

export default{
    namespaced: true,    
    state,
    getters, 
    actions,
    mutations
};