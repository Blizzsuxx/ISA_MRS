import axios from 'axios'

const actions = {
    dodajKorisnika (context, korisnik){
        if (korisnik.uloga === "Dermatolog"){
            axios.post("http://localhost:8080/api/v1/administratorApoteke/sacuvajDermatologa", korisnik)
            .then(response => {
                alert("Dodat Dermatolog");
                return response;
            })
        } else if (korisnik.uloga === "Dobavljac"){
            axios.post("http://localhost:8080/api/v1/administratorApoteke/sacuvajDobavljaca", korisnik)
            .then(response => {
                alert("Dodat Dobavljac");
                return response;
            })
        } else if (korisnik.uloga === "Administrator Sistema"){
            axios.post("http://localhost:8080/api/v1/administratorApoteke/sacuvajAdministratoraSistema", korisnik)
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

export default{
    namespaced: true,    
    actions
};