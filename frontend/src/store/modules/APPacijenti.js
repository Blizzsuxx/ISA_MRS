import axios from 'axios'
import authHeader from './AuthHeader'
const state = {
    sviPacijenti :[],
    kategorija : "",
    pretplata : [],
    trenutniPacijent: null,
    dozvola: false,

};

const getters = {
    pacijenti : state => state.pacijent
};

const actions = {
    dobaviPacijente (context) {
        axios.get('apoteka/dobaviPacijente')
            .then(response => {
                let pacijentiSvi =response.data
                context.commit('postaviPacijente',pacijentiSvi)
            })


    },
    dobaviKategorijuPacijenta(context){
        return axios.get('profil/dobaviKategoriju',{ headers: authHeader()}).then(response=>{
            let kategorija=response.data
            console.log(kategorija)
            context.commit('postaviKategoriju',kategorija)
        })
    },
    dobaviPretplaceneApoteke(context){
        return axios.get('profil/pretplata',{ headers: authHeader()}).then(response=>{
            let apoteke=response.data
            console.log(apoteke)
            context.commit('postaviApoteke',apoteke)
        })
    },
    otkaziPretplatu(context,id){
       
        return axios.put('profil/otkaziPretplatu',id,{ headers: authHeader()}).then(response=>{
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
    },
    proveriPenale(context){
        return axios.get('profil/provariPenale',{ headers: authHeader()}).then(response=>{
            console.log(response.data)
            context.commit('postaviDozvolu',response.data)
            return response.data;
        })
    }

}

const mutations = {
    postaviPacijente:(state, pacijenti) => (state.sviPacijenti = pacijenti),
    postaviKategoriju:(state, kat) => (state.kategorija = kat),
    postaviApoteke:(state, apo) => (state.pretplata = apo),
    postaviPacijentaZaPregled:(state, pacijentNov) => (state.trenutniPacijent = pacijentNov),
    postaviDozvolu:(state, doz) => (state.dozvola = doz)
}

export default{
    namespaced: true,
    state,
    getters,
    actions,
    mutations
};