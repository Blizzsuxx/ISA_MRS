import axios from 'axios'
import authHeader from './AuthHeader'

const actions = {
    posaljiKod(context, kod){
        return axios.post('http://localhost:8080/api/qrkod/posaljiKod', kod, {headers: authHeader()});
    }
}

export default{
    namespaced: true,
    actions,
};