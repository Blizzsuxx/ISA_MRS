import axios from 'axios'
import moment from 'moment'
const state = {    
    
    sviLijekovi : [],
    zabranjeni : [],
    dtoLijekovi: [],
    lijekoviZaPorucivanje:[],
};



const actions = {
    dodajLijek (context, lijek){
        axios.post("http://localhost:8080/api/v1/lijekovi/sacuvajLijek", lijek)
        .then(response => {
            alert("Dodat lijek");
          return response;
        })
    },

    dobaviDTOLijekove (context) {
        axios.get('http://localhost:8080/api/v1/lijekovi/DTOlijekovi')
        .then(response => {
            context.commit('postaviDTOLijekove', response.data);
        })

    },

    dobaviLijekove (context) {
        return axios.get('http://localhost:8080/api/v1/apoteka/dobaviLijekove/1')
        .then(response => {
            context.commit('postaviSveLijekove',response.data)
            return response
            
        
        })
        
        
    },
    dodajLijekUNarudzbinu(context,lijek){
        let sviLijekovi  = state.dtoLijekovi
        //let naruceni  = state.lijekoviZaPorucivanje
        
        sviLijekovi = sviLijekovi.map(element=>{
            
            if(element.id ===lijek.id){
                let noviLijek = {...element};
                noviLijek.kolicina = lijek.kolicina
                context.commit('postaviLijekoviPorucivanje',noviLijek)
                element.kolicina =element.kolicina- lijek.kolicina
                
                return element
            }
            return element
        })
       
       
        context.commit('postaviSveLijekove',sviLijekovi)
    },
    zavrsiNarucivanje(context,datum){
      
        let lijekovi = state.lijekoviZaPorucivanje.map(elem=>{
            let new_elem = JSON.parse(JSON.stringify(elem))
            return new_elem;
        })
        axios.post("http://localhost:8080/api/v1/narudzbenice/kreirajNarudzbenicu", {lijekovi:lijekovi,
                                                datum: moment(String(datum)).format('YYYY-MM-DD hh:mm'),
                                                                                    apoteka:1})
        .then(response => {
            context.commit('resetujLijekoveZaPorucivanje',[])
          return response;
        })
        
    },

    promjeniCijenu (contex,lijek){
        console.log(lijek)
        

        axios.put('http://localhost:8080/api/v1/stanjeLijeka/promjeniCijenu',{},{params:{id:lijek.id,cijena:lijek.cijena}})
        .then(response => {

            return response

        })
        return contex;
    },
    izbrisiLijekove ({commit,state},lijekovi){
        let arr = new Array();
        lijekovi.forEach(element => {
            arr.push(element.id)
        });
        let lijekoviNovi = state.sviLijekovi.filter(element=>{
            if(lijekovi.filter(elem=>elem.id===element.id).length!=0){
                return false;
            }
            return true;
        });
        
        axios.put('http://localhost:8080/api/v1/stanjeLijeka/izbrisiLijekove',arr)
        .then(response => {
            commit('postaviSveLijekove',lijekoviNovi)
            return response

        })
        
    },
   
    promjeniStanje({commit,state},lijekovi){
        let arr = new Array();
        lijekovi.forEach(element => {
            arr.push(element.id)
        });
        
        axios.put('http://localhost:8080/api/v1/stanjeLijeka/promjenaStatusaProdaje',arr)
        .then(response => {
            let arry = response.data
            commit('postaviZabranjene',response.data)
            let odobrenilijekovi = lijekovi.filter(function(el){
                if(arry.filter(elem=>elem===el.id).length!=0){
                    
                    return false;
                }
                else{
                    return true;
                }
            })
            let sviLijekoviStari = state.sviLijekovi.filter(function(el){
                
                if(odobrenilijekovi.filter(elem=>elem.id===el.id).length!=0){
                    
                    return false;
                }
                else{
                    return true;
                }
            })
            
            let sviLijekoviNovi = odobrenilijekovi.map(function(elem){
                    elem.prodaja = ! elem.prodaja
                    return elem
            })
           
            let sviLijekovi = sviLijekoviNovi.concat(sviLijekoviStari)
            
            commit('postaviSveLijekove',sviLijekovi)
            return response
        })

    }

}

const mutations = {
    
    postaviSveLijekove:(state,lijekovi)=>(state.sviLijekovi = lijekovi),
    postaviZabranjene:(state,zabranjeni)=>(state.zabranjeni  =zabranjeni),
    postaviDTOLijekove:(state, dtoLijekovi)=>(state.dtoLijekovi = dtoLijekovi),
    postaviLijekoviPorucivanje:(state,lijekovi)=>(state.lijekoviZaPorucivanje.push(lijekovi)),
    resetujLijekoveZaPorucivanje:(state,lijekovi)=>(state.lijekoviZaPorucivanje = lijekovi)
}

export default{
    namespaced: true,    
    state,
    
    actions,
    mutations
};