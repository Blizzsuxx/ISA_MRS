import axios from 'axios'
import authHeader from './AuthHeader'

const state = {    
    lekoviAlergija :[],
    lekoviBezAlergija : [],
};
const getters = {
    lijekovi : state => state.lijek    
};

const actions = {
    dobaviAlergije (context) {
        axios.get('http://localhost:8080/api/v1/profil/dobaviPacijenta',{ headers: authHeader()})
        .then(response => {
        let lekoviAlergija =response.data.alergije
        console.log(lekoviAlergija)
        axios.get('http://localhost:8080/api/v1/apoteka/dobaviLijekoveA/1',{ headers: authHeader()})
        .then(response => {
        let lekoviBezAlergija = response.data.filter(lek=>lekoviAlergija.indexOf(lek)==-1)//todo neko ko ce da vrati sve lekove
        context.commit('postaviAlergije',lekoviAlergija)
        console.log("lek")
        console.log(lekoviBezAlergija)
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
        axios.post("http://localhost:8080/api/v1/profil/izbaciAlergije", lekovi,{ headers: authHeader()})
        .then(response => {
            alert("Promenjeno je");
          return response;
        })
        
    },
    premesti({commit,state},lekovi){ //todo treba ovo poslati nekako, najbolje preko id, itreba dobaviti lekove koji imaju id, i ne dobavljati stanja leka
        
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
        //posalji()
        
    },
    posalji(){
        let arr = new Array();
        arr=this.lekoviAlergija
        axios.put('http://localhost:8080/api/v1/profil/promeniAlergije',arr,{ headers: authHeader()})
        .then(response => {
            let tf = response.data
            if(tf){
            
            console.log("da")
        }
            else{
                //todo neki ispis
                console.log("Nije dobro")
            }
            return response
        })  
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