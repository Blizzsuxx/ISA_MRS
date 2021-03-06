import axios from 'axios'
import authHeader from './AuthHeader'

const state = {    
    korisnik: null,
   loading: true,
   radnik: null,

};

const getters = {//pazi ovo je nebitno
    kor : state => state.profil    
};

const actions = {
     dobaviProfil (context) {
      
       return axios.get('profil/dobaviPacijenta',{ headers: authHeader()})
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
        arr.push(input.email)
        arr.push(input.date1)
        
        axios.put('profil/izmeni',arr,{ headers: authHeader()})
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


    izmeniPodatkeRadnik (context,input) {
        
        console.log(input);
        var info = {"ime" : input.ime, "prezime" : input.prezime, "email" : input.email, "sifra" : input.sifra};

        axios.post('korisnici/izmeni', info, {headers : authHeader()})
        .then(response => {
            context.commit('postaviProfilRadnik',input)
            console.log("da")
        
            return response
        })  
        
    },



}

const mutations = {
    postaviProfil:(state, korisnik1) => (state.korisnik = korisnik1),
    postaviProfilRadnik:(state, radnik1) => (state.radnik = radnik1),
    postaviLoading:(state, loading) => (state.loading = loading),
}

export default{
    namespaced: true,     
    state,
    getters, 
    actions,
    mutations
};