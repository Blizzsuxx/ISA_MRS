import axios from 'axios'
import authHeader from './AuthHeader'


const actions = {
    sacuvajPopust(context, popust) {
        console.log(popust);
        return axios.post('http://localhost:8080/api/popust/sacuvajPopust', popust, { headers: authHeader()})
        .then(response => {
            return response.data;
        })
    },
    dobaviPopust() {
        return axios.get('http://localhost:8080/api/popust/dobaviPopust', { headers: authHeader()})
        .then(response => {
            return response.data;
        })
    },
}

export default{
    namespaced: true, 
    actions
};