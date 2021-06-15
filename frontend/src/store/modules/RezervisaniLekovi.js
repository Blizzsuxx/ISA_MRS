import axios from 'axios'
import authHeader from './AuthHeader'

const state = {    
    rezervisani:[],
    rezervisaniZaId : [],
    
};
const getters = {
    rezervisani : state => state.rezervisan,
    rezervisaniZaId : state => state.rezervisaniZaId    
};

const actions = {
    dobaviRezervacije (context) {
        
        axios.get('profil/dobaviRezervacije',{ headers: authHeader()})
        .then(response => {
            //console.log("a")
           console.log(response.data)
        let rezervisani =response.data //dobijam rezervacijaDTO, tj listu njih
         context.commit('postaviRezervacije',rezervisani)
        })
  },

  postaviRezervacije(context, podaci){
    axios.post('rezervacije/postaviRezervacije', podaci,{ headers: authHeader()}).then(response => {

            alert("Pregled uspesno zavrsen");
            return response;
        })
  },
   odustani(context,index,row){ //bio i commit neki ovde 
        console.log( row)
       // var today = new Date();
        //var dan=state.rezervisani[index].datumVazenja.split(".")
        
        
            console.log(state.rezervisani[index])
          let rezSve=state.rezervisani.filter(function(el){
              if(state.rezervisani[index].id==el.id ){
                  return false;
              }else{return true;}
          })
          
          axios.post("profil/izbaciRezervacije", {"a":state.rezervisani[index].id},{ headers: authHeader()})
        .then(response => {
            if(response){
            alert("Uspesno ste izbacili lek iz rezervacije");
            context.commit('postaviRezervacije', rezSve) }
            else{
                alert("Isteklo je vreme za otkazivanje")  
            }
          return response;
        })
    
          
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

    lekoviPoRezervaciji(context, data){
        // apoteka i id rezervacije su paramtetri
        axios.post('rezervacije/dobaviRezervacijeId', data, {headers : authHeader()}).then(response => {
            context.commit('postaviLekovePoPoseti', response.data);
            if(response.data == ""){
                alert("broj nije ispravan");
            }
            console.log("AAAAAAAAAAAAAAAAAAAAAAA");
            console.log(response.data);
            console.log("BBBBBBBBBBBBBBBBB");
            return response.data;
        })
    },

    IzdajLek(context, data){


        axios.post('http://localhost:8080/api/v1/rezervacije/IzdajLek', data, {headers : authHeader()}).then(response => {
            
            console.log("AAAAAAAAAAAAAAAAAAAAAAA");
            console.log(response.data);
            console.log("BBBBBBBBBBBBBBBBB");
            return response.data;
        })

    }

    

}

const mutations = {
    postaviRezervacije:(state, rez) => (state.rezervisani = rez),
    postaviLekovePoPoseti:(state, rez) => (state.rezervisaniZaId = rez),
    
}

export default{
    namespaced: true,    
    state,
    actions,
    getters,
    mutations
};