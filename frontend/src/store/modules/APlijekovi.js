import axios from 'axios'
import moment from 'moment'
import authHeader from './AuthHeader'

const state = {    
    
    sviLijekovi : [],
    zabranjeni : [],
    dtoLijekovi: [],
    lijekoviZaPorucivanje:[],
    apotekaLijekovi: [],
    dostupniLekovi: [],
    poruceniNepostojeci: [],
    greska: false,

};



const actions = {
    dobaviLijekoveDobavljaca(){
        return axios.get('http://localhost:8080/api/v1/lijekovi/dobaviStanjeLijekovaDobavljaca', { headers: authHeader() });
    },

    dodajLijek (context, lijek){
        return axios.post("http://localhost:8080/api/v1/lijekovi/sacuvajLijek", lijek, { headers: authHeader()});

    },

    dobaviDTOLijek(context, naziv){
        return axios.get(`http://localhost:8080/api/v1/lijekovi/dobaviDTOLijek/${naziv}`, { headers: authHeader()});
    },

    azurirajDTOLijek(context, lijek){
        return axios.put('http://localhost:8080/api/v1/lijekovi/azurirajDTOLijek', 
        lijek, { headers: authHeader()});
    },

    obrisiDTOLijek(context, naziv){
        return axios.delete(`http://localhost:8080/api/v1/lijekovi/obrisiDTOLijek/${naziv}`, { headers: authHeader()});
    },

    dobaviDTOLijekove (context) {
        return axios.get('http://localhost:8080/api/v1/lijekovi/DTOlijekovi',{ headers: authHeader()})
        .then(response => {
            context.commit('postaviDTOLijekove', response.data);
        })

    },

    dobaviLijekove (context) {
        return axios.get('http://localhost:8080/api/v1/apoteka/dobaviLijekove/1',{ headers: authHeader()})
        .then(response => {
            context.commit('postaviSveLijekove',response.data)
            return response
            
        
        })
        
        
    },
    dobaviLijekoveAdmin(context){
        return axios.get('http://localhost:8080/api/v1/apoteka/lijekovi/admin',{ headers: authHeader()})
        .then(response => {
            context.commit('postaviSveLijekove',response.data)
            return response
            
        
        })
    },
    dobaviLijekoveKorisnik(context,id){
        return axios.get(`http://localhost:8080/api/v1/apoteka/${id}/lijekovi/profil`,{ headers: authHeader()})
        .then(response => {
            context.commit('postaviSveLijekove',response.data)
            return response
            
        
        })
    },
    dobaviLijekoveN(context){
        
        return axios.get('http://localhost:8080/api/v1/apoteka/dobaviLijekoveN',{ headers: authHeader()})
        .then(response => {
            context.commit('postaviSveLijekove',response.data)
            return response
        })
    },
    dobaviSveDostupneLijekove (context) {
        return axios.get('http://localhost:8080/api/v1/apoteka/dobaviSveDostupneLijekove',{ headers: authHeader()})
        .then(response => {
            context.commit('postaviDostupne',response.data)
            return response
            
        
        })    
    },
    rezervisiLek (context, lek, apoteka, na) {//poslati datum i kolicinu
        console.log(apoteka)
        console.log(na)
        let nov=lek+" "+apoteka;
        return axios.post('http://localhost:8080/api/v1/apoteka/rezervisiLek',nov,{ headers: authHeader()})
        .then(response => {
            let tf = response.data
            if(tf){
            
            console.log("rezervisali ste lek")
        }else{   
                console.log("Nije dobro")
            }
        })  
    },

    proveriAlergije (context, data){
        console.log("QQQ");
        var korisnik = data.a;
        var lijekovi = data.s;
        console.log(korisnik);
        axios.post('http://localhost:8080/api/v1/profil/proveriAlergije',{"lijekovi" : lijekovi, "korisnik" : korisnik}, {headers : authHeader()})
        .then(response => {
            context.commit('postaviGresku',response.data)
            return response

        })
    },


    proveriDostupnost (context, lijekovi, pregledID){
        axios.post('http://localhost:8080/api/v1/posete/proveriDostupnost',{"lijekovi" : lijekovi, "pregledID" : pregledID}, {headers : authHeader()})
        .then(response => {
            context.commit('postaviGresku',response.data)
            return response

        })
    },


    dobaviLijekoveApoteke (context, radnik, korisnik) {
        axios.post('http://localhost:8080/api/v1/apoteka/dobaviLijekoveApoteke',{"radnik" : radnik, "korisnik" : korisnik}, {headers : authHeader()})
        .then(response => {
            context.commit('dobaviLijekoveApoteke',response.data)
            return response

        })
        
        
    },
    dodajLijekUNarudzbinu(context,lijek){
        let sviLijekovi  = state.dtoLijekovi
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
        let datum1 = moment(String(datum)).format('YYYY-MM-DD hh:mm').split(" ")[0]+" 23:59";
       
        axios.post("http://localhost:8080/api/v1/narudzbenice/kreirajNarudzbenicu", {lijekovi:lijekovi,
                                                datum: datum1,
                                                                                    apoteka:1},{ headers: authHeader()})
        .then(response => {
            context.commit('resetujLijekoveZaPorucivanje',[])
          return response;
        })
        
    },

    dobaviPoruceneNepostojece(context){

        axios.get('http://localhost:8080/api/v1/stanjeLijeka/dobaviZatrazene',{ headers: authHeader()}).then(response=>{
            context.commit('postaviPoruceneNepostojece',response.data)
        })
    },

    oznaciPregledano(context,id){

        axios.put(`http://localhost:8080/api/v1/stanjeLijeka/ocistiZatrazeni/${id}`,{},{ headers: authHeader()}).then(response=>{
            context.commit('postaviPoruceneNepostojece',response.data)
        })
    },

    

    promjeniCijenu (contex,lijek){
       

        return axios.put('http://localhost:8080/api/v1/stanjeLijeka/promjeniCijenu',{},{ headers: authHeader(),params:{id:lijek.id,cijena:lijek.cijena,datumIstekaCijene:lijek.value}})
        .then(() => {

            return contex;

        })
        
    },
    izbrisiLijekove (context,id){
        return axios.delete(`http://localhost:8080/api/v1/stanjeLijeka/izbrisiLijekove/${id.id}`,{ headers: authHeader()})
        .then(response => {
            console.log(context)
            return response

        })
        
        
    },
   
    

}

const mutations = {
    
    postaviSveLijekove:(state,lijekovi)=>(state.sviLijekovi = lijekovi),
    postaviDostupne:(state,lijekovi)=>(state.dostupniLekovi = lijekovi),
    postaviZabranjene:(state,zabranjeni)=>(state.zabranjeni  =zabranjeni),
    postaviDTOLijekove:(state, dtoLijekovi)=>(state.dtoLijekovi = dtoLijekovi),
    postaviLijekoviPorucivanje:(state,lijekovi)=>(state.lijekoviZaPorucivanje.push(lijekovi)),
    resetujLijekoveZaPorucivanje:(state,lijekovi)=>(state.lijekoviZaPorucivanje = lijekovi),
    dobaviLijekoveApoteke:(state, lijekovi)=>(state.apotekaLijekovi = lijekovi),
    postaviPoruceneNepostojece:(state,poruceniNepostojeci)=>(state.poruceniNepostojeci = poruceniNepostojeci),
    postaviGresku:(state, er)=>(state.greska = er),
}

export default{
    namespaced: true,    
    state,
    
    actions,
    mutations
};