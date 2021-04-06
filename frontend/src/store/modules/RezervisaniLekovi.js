import axios from 'axios'

const state = {    
    rezervisani:[],
    
};
const getters = {
    rezervisani : state => state.rezervisan    
};

const actions = {
    dobaviRezervacije (context) {
        
        axios.get('http://localhost:8080/api/v1/profil/dobaviRezervacije')
        .then(response => {
           console.log(response.data)
        let rezervisani =response.data //dobijam rezervacijaDTO, tj listu njih
         context.commit('postaviRezervacije',rezervisani)
        })
  },
   odustani({commit,state},index,row){ //bio i commit neki ovde 
        console.log( index)
          let rezSve=state.rezervisani.filter(function(el){
              if(row.nazivLeka==el.nazivLeka && row.nazivApoteke==el.nazivApoteke && row.datumVazenja==el.datumVazenja){
                  return false;
              }else{return true;}
          })
          
          commit('postaviRezervacije', rezSve) 
        /*let lekoviAlergija = state.lekoviAlergija.filter(function(el){
            
            if(lekovi.filter(elem=>elem.id===el.id).length!=0){
                
                return false;
            }
            else{
                return true;
            }
        })
        let lekoviBezAlergija = state.lekoviBezAlergija.concat(lekovi)
        commit('postaviAlergije',lekoviAlergija)*/
        
        
    },
    

}

const mutations = {
    postaviRezervacije:(state, rez) => (state.rezervisani = rez),
}

export default{
    namespaced: true,    
    state,
    actions,
    getters,
    mutations
};