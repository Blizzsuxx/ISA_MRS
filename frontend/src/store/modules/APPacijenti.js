import axios from 'axios'
import authHeader from './AuthHeader'
const state = {
    sviPacijenti :[],
    kategorija : "",
    pretplata : [],
    trenutniPacijent: null,

};

const getters = {
    pacijenti : state => state.pacijent
};

const actions = {
    dobaviPacijente (context) {
        axios.get('http://localhost:8080/api/v1/apoteka/dobaviPacijente')
            .then(response => {
                let pacijentiSvi =response.data
                context.commit('postaviPacijente',pacijentiSvi)
            })


    },
    dobaviKategorijuPacijenta(context){
        return axios.get('http://localhost:8080/api/v1/profil/dobaviKategoriju',{ headers: authHeader()}).then(response=>{
            let kategorija=response.data
            console.log(kategorija)
            context.commit('postaviKategoriju',kategorija)
        })
    },
    dobaviPretplaceneApoteke(context){
        return axios.get('http://localhost:8080/api/v1/profil/pretplata',{ headers: authHeader()}).then(response=>{
            let apoteke=response.data
            console.log(apoteke)
            context.commit('postaviApoteke',apoteke)
        })
    },
    otkaziPretplatu(context,id){
       
        return axios.put('http://localhost:8080/api/v1/profil/otkaziPretplatu',id,{ headers: authHeader()}).then(response=>{
            let da=response.data
            if(da){
            let preostaleApoteke = state.pretplata.filter(function(el){
                    if(el.id===id){
                        
                        return false;
                    }
                    else{
                        return true;
                    }
            })
            console.log(preostaleApoteke)
            context.commit('postaviApoteke',preostaleApoteke)
            
        }
            else{
                console.log("greska");
                confirm("Doslo je do greske.")
            }
            console.log(state.pretplata)
        })
    },

    pacijentZaPrelged(context, pacijent){
        context.commit('postaviPacijentaZaPregled', pacijent);
    }

}

const mutations = {
    postaviPacijente:(state, pacijenti) => (state.sviPacijenti = pacijenti),
    postaviKategoriju:(state, kat) => (state.kategorija = kat),
    postaviApoteke:(state, apo) => (state.pretplata = apo),
    postaviPacijentaZaPregled:(state, pacijentNov) => (state.trenutniPacijent = pacijentNov)
}

export default{
    namespaced: true,
    state,
    getters,
    actions,
    mutations
};