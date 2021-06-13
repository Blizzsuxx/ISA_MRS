import axios from 'axios'
import authHeader from './AuthHeader';


const actions = {
    posaljiMail(context, podaci){
        axios.post('mail/sendemail', podaci, {headers : authHeader()}).then(response => {

            alert("poslato obavestenje!");
            return response;
        })
    },

    posaljiMailRegistracija(){
        axios.post('mail/sendemailRegistration',{} ,{headers : authHeader()})
        .then(response => {
            alert("poslato obavestenje!");
            return response;
        })
    }

}


export default{
    namespaced: true,
    actions,
};