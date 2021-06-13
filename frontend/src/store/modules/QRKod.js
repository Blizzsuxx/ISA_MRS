import axios from 'axios'
import authHeader from './AuthHeader'

const actions = {
    posaljiKod(context, kod){
        return axios.post('qrkod/posaljiKod', kod, {headers: authHeader()});
    },
    rezervisiERecept(context, erecept){
        return axios.post('qrkod/kreirajErecept', erecept, {headers: authHeader()});
    }

}

export default{
    namespaced: true,
    actions,
};