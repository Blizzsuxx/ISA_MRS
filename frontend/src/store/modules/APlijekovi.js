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
    zamenaLekovi: [],

};



const actions = {
    dobaviLijekoveDobavljaca(){
        return axios.get('lijekovi/dobaviStanjeLijekovaDobavljaca', { headers: authHeader() });
    },

    dodajLijek (context, lijek){
        return axios.post("lijekovi/sacuvajLijek", lijek, { headers: authHeader()});

    },

    dobaviDTOLijek(context, naziv){
        return axios.get(`lijekovi/dobaviDTOLijek/${naziv}`, { headers: authHeader()});
    },

    azurirajDTOLijek(context, lijek){
        return axios.put('lijekovi/azurirajDTOLijek', 
        lijek, { headers: authHeader()});
    },

    obrisiDTOLijek(context, naziv){
        return axios.delete(`lijekovi/obrisiDTOLijek/${naziv}`, { headers: authHeader()});
    },

    dobaviDTOLijekove (context) {
        return axios.get('lijekovi/DTOlijekovi',{ headers: authHeader()})
        .then(response => {
            context.commit('postaviDTOLijekove', response.data);
        })

    },

    dobaviLijekove (context) {
        return axios.get('apoteka/dobaviLijekove/1',{ headers: authHeader()})
        .then(response => {
            context.commit('postaviSveLijekove',response.data)
        })
        
        
    },
    dobaviLijekoveAdmin(context){
        return axios.get('apoteka/lijekovi/admin',{ headers: authHeader()})
        .then(response => {
            context.commit('postaviSveLijekove',response.data)
            console.log(response.data)
        })
    },
    dobaviLijekoveKorisnik(context,id){
        return axios.get(`apoteka/${id}/lijekovi/profil`,{ headers: authHeader()})
        .then(response => {
            context.commit('postaviSveLijekove',response.data)
            return response
            
        
        })
    },
    dobaviLijekoveN(context){
        
        return axios.get('apoteka/dobaviLijekoveN',{ headers: authHeader()})
        .then(response => {
            context.commit('postaviSveLijekove',response.data)
            return response
        })
    },
    dobaviSveDostupneLijekove (context) {
        return axios.get('apoteka/dobaviSveDostupneLijekove',{ headers: authHeader()})
        .then(response => {
            context.commit('postaviDostupne',response.data)
            return response
            
        
        })    
    },
    rezervisiLek (context, lek) {//poslati datum i kolicinu
        
        return axios.post('apoteka/rezervisiLek',lek,{ headers: authHeader()})
        .then(response => {
            let tf = response.data
            if(tf){
            
            console.log("rezervisali ste lek")
            return true;
        }else{   
                console.log("Nije dobro")
                return false;
            }
        })  
    },

    async proveriAlergije (context, data){
        axios.post('profil/proveriAlergije',data, {headers : authHeader()})
        .then(response => {
            context.commit('postaviGresku',response.data)
            return Promise.resolve(1);

        })
    },


    async proveriDostupnost (context, params){
        console.log(params);
        axios.post('posete/proveriDostupnost',params, {headers : authHeader()})
        .then(response => {
            context.commit('postaviGresku',response.data)

        });

        if(state.greska){
            axios.post('posete/traziZamenu',params, {headers : authHeader()})
            .then(response => {
                context.commit('postaviZamenuLekove',response.data)

        });
        }
        return Promise.resolve(1);
    },


    dobaviLijekoveApoteke (context, radnik, korisnik) {
        axios.post('apoteka/dobaviLijekoveApoteke',{"radnik" : radnik, "korisnik" : korisnik}, {headers : authHeader()})
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
       
        axios.post("narudzbenice/kreirajNarudzbenicu", {lijekovi:lijekovi,
                                                datum: datum1},{ headers: authHeader()})
        .then(response => {
            context.commit('resetujLijekoveZaPorucivanje',[])
          return response;
        })
        
    },

    dobaviPoruceneNepostojece(context){

        axios.get('stanjeLijeka/dobaviZatrazene',{ headers: authHeader()}).then(response=>{
            context.commit('postaviPoruceneNepostojece',response.data)
        })
    },

    oznaciPregledano(context,id){

        axios.put(`stanjeLijeka/ocistiZatrazeni/${id}`,{},{ headers: authHeader()}).then(response=>{
            context.commit('postaviPoruceneNepostojece',response.data)
        })
    },
    setujPotraznju(context,id){
        axios.put(`stanjeLijeka/setujPotraznju/${id}`,{},{ headers: authHeader()})
        console.log(context)
    },

    

    promjeniCijenu (contex,lijek){
       

        return axios.put('stanjeLijeka/promjeniCijenu',{},{ headers: authHeader(),params:{id:lijek.id,cijena:lijek.cijena,datumIstekaCijene:lijek.value}})
        .then(() => {

            return contex;

        })
        
    },
    izbrisiLijekove (context,id){
        return axios.delete(`stanjeLijeka/izbrisiLijekove/${id.id}`,{ headers: authHeader()})
        .then(response => {
            console.log(context)
            return response

        })
        
        
    },
   
    

}

const mutations = {
    
    postaviSveLijekove:(state,lijekovi)=>(state.sviLijekovi = lijekovi),
    postaviZamenuLekove:(state, newZamena)=>(state.zamenaLekovi = newZamena),
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