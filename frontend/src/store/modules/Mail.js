import axios from 'axios'
import authHeader from './AuthHeader';


const actions = {
    posaljiMail(context, podaci){
        axios.post('http://localhost:8080/api/v1/mail/sendemail', podaci, {headers : authHeader()}).then(response => {

            alert("poslato obavestenje!");
            return response;
        })
    }

}


export default{
    namespaced: true,
    actions,
};