import axios from 'axios'

const state = {
    dermatolozi: [],
    dobavljaci: [],
    administratoriSistema: [],
    administratoriApoteke: []
};

const actions = {
    dobaviAdministratoreApoteka (context){
        return axios.get('http://localhost:8080/api/v1/administratorApoteke/sviAdministratoriApoteke')
        .then(response => {
            context.commit('postaviAdministratoreApoteke', response.data);
        })
    },

    dobaviDermatologe (context){
        return axios.get('http://localhost:8080/api/v1/administratorApoteke/sviAdministratoriApoteke')
        .then(response => {
            context.commit('postaviAdministratoreApoteke', response.data);
        })
    },

    dobaviDobavljace (context){
        return axios.get('http://localhost:8080/api/v1/administratorApoteke/sviAdministratoriApoteke')
        .then(response => {
            context.commit('postaviAdministratoreApoteke', response.data);
        })
    },

    dobaviAdministratoreSistema (context){
        return axios.get('http://localhost:8080/api/v1/administratorApoteke/sviAdministratoriApoteke')
        .then(response => {
            context.commit('postaviAdministratoreApoteke', response.data);
        })
    },

    dodajKorisnika (context, korisnik){
        alert(korisnik.uloga);
        if (korisnik.uloga === "Dermatolog"){
            axios.post("http://localhost:8080/api/v1/dermatolog/sacuvajDermatologa", korisnik)
            .then(response => {
                alert("Dodat Dermatolog");
                return response;
            })
        } else if (korisnik.uloga === "Dobavljac"){
            axios.post("http://localhost:8080/api/v1/dobavljac/sacuvajDobavljaca", korisnik)
            .then(response => {
                alert("Dodat Dobavljac");
                return response;
            })
        } else if (korisnik.uloga === "Administrator Sistema"){
            axios.post("http://localhost:8080/api/v1/administratorSistema/sacuvajAdministratoraSistema", korisnik)
            .then(response => {
                alert("Dodat Administrator Sistema");
                return response;
            })
        } else if (korisnik.uloga === "Administrator Apoteke"){
            axios.post("http://localhost:8080/api/v1/administratorApoteke/sacuvajAdministratoraApoteke", korisnik)
            .then(response => {
                alert("Dodat Administrator Apoteke");
                return response;
            })
        }
        
    }
}

const mutations = {
    postaviDermatologe:(state, dermatolozi)=>(state.dermatolozi = dermatolozi),
    postaviDostavljace:(state, dobavljaci)=>(state.dobavljaci = dobavljaci),
    postaviAdministratoreSistema:(state, administratoriSistema)=>(state.administratoriSistema = administratoriSistema),
    postaviAdministratoreApoteke:(state, administratoriApoteke)=>(state.administratoriApoteke = administratoriApoteke)
}

export default{
    namespaced: true,
    state,    
    actions,
    mutations
};