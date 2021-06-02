import axios from 'axios'
import authHeader from './AuthHeader'
import moment from 'moment'

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
    trenutniRadnik: null,
    stanje: initialState,
    trenutnaRedirekcija: "null"
};

const API_URL = 'http://localhost:8080/api/v1/';

const actions = {

    promeniRedirekciju(context, novaRedirekcija){
        context.commit("promeniRedirekciju", novaRedirekcija);
        return novaRedirekcija;
    },

    dobaviRedirekciju(context){
        context;
        return state.trenutnaRedirekcija;
    },

    kreirajOdgovor(context, sadrzaj){
        sadrzaj.datumVrijeme = moment(String(Date())).format('YYYY-MM-DD hh:mm');
        return axios.post('http://localhost:8080/api/odgovori/kreirajOdgovor', sadrzaj, {headers: authHeader()})
    },
    dobaviSveZalbe(){
        return axios.get('http://localhost:8080/api/zalbe/dobaviSveZalbe',{headers: authHeader()});
    },
    dobaviOdgovore(context, id){
        return axios.get(`http://localhost:8080/api/odgovori/dobaviOdgovoreZalbe/${id}`, {headers: authHeader()});
    },
    dobaviZalbe(){
        return axios.get('http://localhost:8080/api/zalbe/dobaviZalbe', {headers: authHeader()});
    },
    kreirajZalbu(context, zalba){
        zalba.datumVrijeme = moment(String(Date())).format('YYYY-MM-DD hh:mm');
        return axios.post('http://localhost:8080/api/zalbe/sacuvajZalbu', zalba, { headers: authHeader()})
    },
    dobaviTrenutnogKorisnika(){
        return axios.get('http://localhost:8080/api/korisnici/dobaviTrenutnogKorisnika', { headers: authHeader()})
        .then(response => {
            return response.data;
        })
    },
    azurirajNalog(context, korisnik){
        return axios.put('http://localhost:8080/api/korisnici/azurirajNalog', 
        {korisnickoIme:korisnik.korisnickoIme, ime: korisnik.ime, prezime: korisnik.prezime,
        email: korisnik.email, uloga: korisnik.uloga, sifra: korisnik.sifra,
        rodjendan:moment(String(korisnik.rodjendan)).format('YYYY-MM-DD hh:mm')}, { headers: authHeader()});
    },
    promjenaLozinke(context, lozinka){
        return axios.get(`http://localhost:8080/api/korisnici/promjenaLozinke/${lozinka}`, { headers: authHeader()})
        .then(response => {
            return response.data;
        })
    },

    provjeraLozinke(context, lozinka){
        return axios.get(`http://localhost:8080/api/korisnici/provjeraLozinke/${lozinka}`, { headers: authHeader()})
        .then(response => {
            return response.data;
        })
    },
    potvrdaLozinke(){
        return axios.get('http://localhost:8080/api/korisnici/potvrdaPrijave', { headers: authHeader()})
        .then(response => {
            console.log(response.data);
            return response.data;
        })
    },
    login(kredecijali) {
        return axios.post('http://localhost:8080/api/korisnici/login', kredecijali)
        .then(response => {
            if (response.data.accessToken && response.data.uloga !== "NEMA"){
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

    dobaviKorisnika(context, korIme){
        return axios.get(`http://localhost:8080/api/korisnici/dobaviKorisnika/${korIme}`, { headers: authHeader()});
    },

    dobaviAdministratoreApoteka (context){
        return axios.get('http://localhost:8080/api/v1/administratorApoteke/sviAdministratoriApoteke', { headers: authHeader()})
        .then(response => {
            console.log(response.data);
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


    trenutniRadnik(context){



        return axios.get('http://localhost:8080/api/korisnici/trenutniRadnik', { headers: authHeader()})
        .then(response => {
            context.commit('postaviTrenutnogRadnika', response.data);
        })
    },

    dobaviDermatologeAdminSistema (context){
        return axios.get('http://localhost:8080/api/v1/dermatolog/sviDermatolozi', { headers: authHeader()})
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

    azurirajKorisnika(context, korisnik){
        return axios.put('http://localhost:8080/api/korisnici/azurirajKorisnika', 
        {korisnickoIme:korisnik.korisnickoIme, ime: korisnik.ime, prezime: korisnik.prezime,
        email: korisnik.email, uloga: korisnik.uloga, 
        rodjendan:moment(String(korisnik.rodjendan)).format('YYYY-MM-DD hh:mm')}, { headers: authHeader()});
    },

    obrisiKorisnika(context, korIme){
        return axios.delete(`http://localhost:8080/api/korisnici/obrisiKorisnika/${korIme}`, { headers: authHeader()});
    },

    dodajKorisnika (context, korisnik){
        if (korisnik.uloga === "ROLE_DERMATOLOG"){
            return axios.post("http://localhost:8080/api/v1/dermatolog/sacuvajDermatologa", korisnik, 
            { headers: authHeader()})
            .then(response => {
                console.log(response);
                return true;
            })
        } else if (korisnik.uloga === "ROLE_DOBAVLJAC"){
            return axios.post("http://localhost:8080/api/v1/dobavljac/sacuvajDobavljaca", korisnik, 
            { headers: authHeader()})
            .then(response => {
                console.log(response);
                return true;
            })
        } else if (korisnik.uloga === "ROLE_FARMACEUT"){
            return axios.post("http://localhost:8080/api/v1/dobavljac/sacuvajFarmaceuta", korisnik, 
            { headers: authHeader()})
            .then(response => {
                console.log(response);
                return true;
            })
        }
        else if (korisnik.uloga === "ROLE_ADMIN_SISTEMA"){
            return axios.post("http://localhost:8080/api/v1/administratorSistema/sacuvajAdministratoraSistema", korisnik,
            { headers: authHeader()})
            .then(response => {
                console.log(response);
                return true;
            })
        } else if (korisnik.uloga === "ROLE_ADMIN_APOTEKA"
        || korisnik.uloga === "ROLE_ADMIN_APOTEKE"){
            return axios.post("http://localhost:8080/api/v1/administratorApoteke/sacuvajAdministratoraApoteke", korisnik,
            { headers: authHeader()})
            .then(response => {
                console.log(response);
                return true;
            })
        } else if (korisnik.uloga === "ROLE_PACIJENT"){
            return axios.post("http://localhost:8080/api/v1/profil/registracija", korisnik, 
            { headers: authHeader()})
            .then(response => {
                console.log(response);
                return true;
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
    promeniRedirekciju:(state, novaRedirekcija)=>(state.trenutnaRedirekcija = novaRedirekcija),
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
    },
    postaviTrenutnogRadnika:(state, radnik)=>(state.trenutniRadnik = radnik)
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