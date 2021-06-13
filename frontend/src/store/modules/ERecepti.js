import axios from 'axios'
import authHeader from './AuthHeader'

const state = {    
    sviRecepti :[],
    trazeniLekovi: [],
  
};

const getters = {
    recepti : state => state.recepat    
};

const actions = {
    dobaviRecepte (context) {
        return axios.get('profil/dobaviERecepte',{ headers: authHeader()})
        .then(response => {
            
            let rSvi =response.data
            //console.log(reSvi[0])
            context.commit('postaviRecepte',rSvi)
            
        })
    },




    postaviRezervaciju(context, podaci){
        axios.post('rezervacije/postaviRezervaciju', podaci,{ headers: authHeader()}).then(response => {
    
                alert("Pregled uspesno zavrsen");
                return response;
            })
      },





    dobaviPreuzeteRecepte (context) {
        return axios.get('profil/dobaviIzdateERecepte',{ headers: authHeader()})
        .then(response => {
            
            let rSvi =response.data
            //console.log(reSvi[0])
            context.commit('postaviRecepte',rSvi)
            
        })
    },

    pronadji({commit,state},sifra){ //TODO, ako ne radi kad se sortira, probaj preko ovog, problem je sto ovo salji ne radi, pre toga postoji
        //i posle salji postoji salji, ali trazenilekovi ostaje undefined??
        
        let trazeni = state.sviRecepti.filter(function(el){
            
            if(el.sifra===sifra){ 
                return true;
            }
            else{
                return false;
            }
        })
        
        var salji=[]
        for (let pr in trazeni[0].prepisaniLijekovi){
            salji.push(trazeni[0].prepisaniLijekovi[pr])
        }
        commit('postaviLekove',salji)
        console.log(this.trazeniLekovi)
        
    }
    

}

const mutations = {
    postaviRecepte:(state, recepti) => (state.sviRecepti = recepti),
    postaviLekove:(state, lekovi) => (state.trazeniLekovi = lekovi),
}

export default{
    namespaced: true,    
    state,
    getters, 
    actions,
    mutations
};