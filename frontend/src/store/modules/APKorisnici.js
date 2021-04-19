import axios from 'axios'

const state = {
    dermatolozi: [],
    dobavljaci: [],
    administratoriSistema: [],
    administratoriApoteke: [],
    farmaceuti: [],
};

const actions = {
    login(context, kredecijali) {
        axios.post('http://localhost:8080/api/korisnici/login', kredecijali)
        .then(response => {
            console.log(response.data.accessToken)
        })
    },

    dobaviAdministratoreApoteka (context){
        return axios.get('http://localhost:8080/api/v1/administratorApoteke/sviAdministratoriApoteke')
        .then(response => {
            context.commit('postaviAdministratoreApoteke', response.data);
        })
    },

    dobaviFarmaceute (context){
        return axios.get('http://localhost:8080/api/v1/farmaceut/dobaviFarmaceute')
        .then(response => {
            context.commit('postaviAdministratoreApoteke', response.data);
        })
    },

    dobaviDermatologe (context){
        return axios.get('http://localhost:8080/api/v1/dermatolog/dobaviDermatologe')
        .then(response => {
            context.commit('postaviDermatologe', response.data);
        })
    },

    dobaviDobavljace (context){
        return axios.get('http://localhost:8080/api/v1/dobavljac/sviDobavljaci')
        .then(response => {
            context.commit('postaviDobavljace', response.data);
        })
    },

    dobaviAdministratoreSistema (context){
        return axios.get('http://localhost:8080/api/v1/administratorSistema/sviAdministratoriSistema')
        .then(response => {
            context.commit('postaviAdministratoreSistema', response.data);
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
        } else if (korisnik.uloga === "Farmaceut"){
            axios.post("http://localhost:8080/api/v1/dobavljac/sacuvajFarmaceuta", korisnik)
            .then(response => {
                alert("Dodat Farmaceut");
                return response;
            })
        }
        else if (korisnik.uloga === "Administrator Sistema"){
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
    postaviDobavljace:(state, dobavljaci)=>(state.dobavljaci = dobavljaci),
    postaviAdministratoreSistema:(state, administratoriSistema)=>(state.administratoriSistema = administratoriSistema),
    postaviAdministratoreApoteke:(state, administratoriApoteke)=>(state.administratoriApoteke = administratoriApoteke),
    postaviFarmaceute:(state, farmaceuti)=>(state.farmaceuti = farmaceuti),
}

export default{
    namespaced: true,
    state,    
    actions,
    mutations
};