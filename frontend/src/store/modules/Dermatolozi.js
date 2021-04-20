import axios from 'axios'

const state = {
    sviDermatolozi :[],
    radnoVrijeme : null,
    slobodniTermini : null,

};

const getters = {
    sviDermatolozi : state => state.sviDermatolozi
};

const actions = {
    dobaviDermatologe (context) {
        axios.get('http://localhost:8080/api/v1/dermatolog/dobaviDermatologe')
            .then(response => {
                let dermatolozi =response.data
                context.commit('postaviDermatologe',dermatolozi)
            })


    },
    dobaviRadnoVrijeme( context,id){
        return axios.get( `http://localhost:8080/api/v1/radnoVrijeme/${id}`)
            .then(response => {
                let radnoVrijeme =response.data
                context.commit('postaviRadnoVrijeme',radnoVrijeme)
                
            })
            .catch(error=>console.log(error))
    },
    dobaviSlobodneTermine( context,id){
        return axios.get( `http://localhost:8080/api/v1/slobodanTermin/dermatolog/${id}`)
            .then(response => {
                let slobodniTermini =response.data
                context.commit('postaviSlobodneTermine',slobodniTermini)
                
            })
            .catch(error=>console.log(error))
    },
    kreirajSlobodanTermin(context,objekat){
        return axios.post( `http://localhost:8080/api/v1/slobodanTermin/`,objekat).then(()=>{
            context.commit('dodajSlobodanTermin',objekat)
            return 1
        }).catch(()=>(-1))
        
    }

}

const mutations = {
    postaviDermatologe:(state, dermatolozi) => (state.sviDermatolozi = dermatolozi),
    postaviRadnoVrijeme :(state,radnoVrijeme)=> (state.radnoVrijeme =  radnoVrijeme),
    postaviSlobodneTermine: (state,slobodniTermini)=>(state.slobodniTermini = slobodniTermini),
    dodajSlobodanTermin:(state,slobodanTermin)=>(state.slobodniTermini.push(slobodanTermin))
}

export default{
    namespaced: true,
    state,
    getters,
    actions,
    mutations
};