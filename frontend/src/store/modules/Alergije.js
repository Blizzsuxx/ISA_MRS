import axios from 'axios'

const state = {    
    lekoviAlergija :[],
    lekoviBezAlergija : [],
};
const getters = {
    lijekovi : state => state.lijek    
};

const actions = {
    dobaviAlergije (context) {
        axios.get('http://localhost:8080/api/v1/profil/dobaviPacijenta')
        .then(response => {
        let lekoviAlergija =response.data.alergije
        console.log(lekoviAlergija)
        axios.get('http://localhost:8080/api/v1/apoteka/dobaviLijekove/1')
        .then(response => {
        let lekoviBezAlergija = response.data.filter(lek=>lekoviAlergija.indexOf(lek)==-1)//todo neko ko ce da vrati sve lekove
        context.commit('postaviAlergije',lekoviAlergija)
        console.log("lek")
        context.commit('postaviLekove',lekoviBezAlergija)
    })
        })
  },
    izbaci({commit,state},lekovi){ //bio i commit neki ovde
        
        let lekoviAlergija = state.lekoviAlergija.filter(function(el){
            
            if(lekovi.filter(elem=>elem.id===el.id).length!=0){
                
                return false;
            }
            else{
                return true;
            }
        })
        let lekoviBezAlergija = state.lekoviBezAlergija.concat(lekovi)
        commit('postaviAlergije',lekoviAlergija)
        commit('postaviLekove',lekoviBezAlergija)
        
    },
    premesti({commit,state},lekovi){
        
        let lekoviBezAlergija = state.lekoviBezAlergija.filter(function(el){
            
            if(lekovi.filter(elem=>elem.id===el.id).length!=0){
                
                return false;
            }
            else{
                return true;
            }
        })
        let lekoviAlergija = state.lekoviAlergija.concat(lekovi)
        commit('postaviAlergije',lekoviAlergija)
        commit('postaviLekove',lekoviBezAlergija)
        
    }

}

const mutations = {
    postaviAlergije:(state, lekovi) => (state.lekoviAlergija = lekovi),
    postaviLekove:(state, lekovi) => (state.lekoviBezAlergija = lekovi),
}

export default{
    namespaced: true,    
    state,
    actions,
    getters,
    mutations
};