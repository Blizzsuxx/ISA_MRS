import axios from 'axios'
import authHeader from './AuthHeader'

const user =  JSON.parse(localStorage.getItem('user'));
const initialState = user
    ? { status: { loggedIn: true }, user}
    : { status: { loggedIn: false}, user: null};

const state = {
    dermatolozi: [],
    dobavljaci: [],
    administratoriSistema: [],
    administratoriApoteke: [],
    farmaceuti: [],
    stanje: initialState
};

const API_URL = 'http://localhost:8080/api/v1/';

const actions = {
    login(kredecijali) {
        return axios.post('http://localhost:8080/api/korisnici/login', kredecijali)
        .then(response => {
            if (response.data.accessToken){
                localStorage.setItem('user', JSON.stringify(response.data));
            }
            return response.data;
        })
    },
    validateLogin({ commit }, user) {
        return actions.login(user).then(
          user => {
            commit('loginSuccess', user);
            return Promise.resolve(user);
          },
          error => {
            commit('loginFailure');
            return Promise.reject(error);
          }
        );
    },
    
    logout({ commit }) {
        localStorage.removeItem('user');
        commit('logout');
    },
    
    register(user) {
        return axios.post(API_URL + 'signup', {
          username: user.username,
          email: user.email,
          password: user.password
        });
    },

    dobaviAdministratoreApoteka (context){
        return axios.get('http://localhost:8080/api/v1/administratorApoteke/sviAdministratoriApoteke', { headers: authHeader()})
        .then(response => {
            context.commit('postaviAdministratoreApoteke', response.data);
        })
        .catch(error => {
            if (error.response.status == 403 || error.response.status == 401){
                alert('Zabranjen pristup!');
            }
        })
    },

    dobaviFarmaceute (context){
        return axios.get('http://localhost:8080/api/v1/farmaceut/dobaviFarmaceute')
        .then(response => {
            context.commit('postaviAdministratoreApoteke', response.data);
        })
    },

    dobaviDermatologe (context){
        return axios.get('http://localhost:8080/api/v1/dermatolog/dobaviDermatologe', { headers: authHeader()})
        .then(response => {
            context.commit('postaviDermatologe', response.data);
        })
        .catch(error => {
            if (error.response.status == 403 || error.response.status == 401){
                alert('Zabranjen pristup!');
            }
        })
    },

    dobaviDobavljace (context){
        return axios.get('http://localhost:8080/api/v1/dobavljac/sviDobavljaci', { headers: authHeader()})
        .then(response => {
            context.commit('postaviDobavljace', response.data);
        })
        .catch(error => {
            if (error.response.status == 403 || error.response.status == 401){
                alert('Zabranjen pristup!');
            }
        })
    },

    dobaviAdministratoreSistema (context){
        return axios.get('http://localhost:8080/api/v1/administratorSistema/sviAdministratoriSistema', { headers: authHeader()})
        .then(response => {
            context.commit('postaviAdministratoreSistema', response.data);
        })
        .catch(error => {
            if (error.response.status == 403 || error.response.status == 401){
                alert('Zabranjen pristup!');
            }
        })
    },

    dodajKorisnika (context, korisnik){
        alert(korisnik.uloga);
        if (korisnik.uloga === "ROLE_DERMATOLOG"){
            axios.post("http://localhost:8080/api/v1/dermatolog/sacuvajDermatologa", korisnik)
            .then(response => {
                alert("Dodat Dermatolog");
                return response;
            })
        } else if (korisnik.uloga === "ROLE_DOBAVLJAC"){
            axios.post("http://localhost:8080/api/v1/dobavljac/sacuvajDobavljaca", korisnik)
            .then(response => {
                alert("Dodat Dobavljac");
                return response;
            })
        } else if (korisnik.uloga === "ROLE_FARMACEUT"){
            axios.post("http://localhost:8080/api/v1/dobavljac/sacuvajFarmaceuta", korisnik)
            .then(response => {
                alert("Dodat Farmaceut");
                return response;
            })
        }
        else if (korisnik.uloga === "ROLE_ADMIN_SISTEMA"){
            axios.post("http://localhost:8080/api/v1/administratorSistema/sacuvajAdministratoraSistema", korisnik)
            .then(response => {
                alert("Dodat Administrator Sistema");
                return response;
            })
        } else if (korisnik.uloga === "ROLE_ADMIN_APOTEKA"){
            axios.post("http://localhost:8080/api/v1/administratorApoteke/sacuvajAdministratoraApoteke", korisnik)
            .then(response => {
                alert("Dodat Administrator Apoteke");
                return response;
            })
        } else if (korisnik.uloga === "ROLE_PACIJENT"){
            axios.post("http://localhost:8080/api/v1/profil/registracija", korisnik)
            .then(response => {
                alert("Dodat Pacijent");
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
    loginSuccess(state, user) {
        state.stanje.status.loggedIn = true;
        state.stanje.user = user;
    },
    loginFailure(state) {
        state.stanje.status.loggedIn = false;
        state.stanje.user = null;
    },
    logout(state) {
        state.stanje.status.loggedIn = false;
        state.stanje.user = null;
    },
    registerSuccess(state) {
        state.stanje.status.loggedIn = false;
    },
    registerFailure(state) {
        state.stanje.status.loggedIn = false;
    }
}

export default{
    namespaced: true,
    user,
    initialState,
    state,  
    API_URL,  
    actions,
    mutations
};