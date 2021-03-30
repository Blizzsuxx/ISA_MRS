import axios from 'axios'

const actions = {
    dodajKorisnika (context, korisnik){
        axios.post("http://localhost:8080/api/v1/administratorApoteke/sacuvajAdministratoraApoteke", korisnik)
        .then(response => {
            alert("Dodat korisnik");
          return response;
        })
    }
}

export default{
    namespaced: true,    
    actions
};