import axios from 'axios'

const state = {    
    korisnik: null,
   loading: true
};

const getters = {//pazi ovo je nebitno
    kor : state => state.profil    
};

const actions = {
     dobaviProfil (context) {
      
       return axios.get('http://localhost:8080/api/v1/profil/dobaviPacijenta')
        .then(response => {
           
            context.commit('postaviProfil',response.data)
            context.commit("postaviLoading", false)

        })
    },
    izmeniPodatke ({commit},input) {
        let arr = new Array();
        arr.push(input.ime)
        arr.push(input.prezime)
        arr.push(input.mesto)
        arr.push(input.ulica)
        arr.push(input.broj)

        arr.push(input.date1)
        
        axios.put('http://localhost:8080/api/v1/profil/izmeni',arr)
        .then(response => {
            let tf = response.data
            if(tf){
            commit('postaviProfil',input)
            console.log("da")
        }
            else{
                //todo neki ispis
                console.log("Nije dobro")
            }
            return response
        })  
        
    },
}

const mutations = {
    postaviProfil:(state, korisnik1) => (state.korisnik = korisnik1),
    postaviLoading:(state, loading) => (state.loading = loading),
}

export default{
    namespaced: true,     
    state,
    getters, 
    actions,
    mutations
};