import axios from 'axios'

const state = {    
    
    sviLijekovi : [],
    zabranjeni : []
};



const actions = {
    dobaviLijekove (context) {
        axios.get('http://localhost:8080/api/v1/apoteka/dobaviLijekove/1')
        .then(response => {
       
        context.commit('postaviSveLijekove',response.data)
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
                    console.log("Usao ovde")
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
    postaviZabranjene:(state,zabranjeni)=>(state.zabranjeni  =zabranjeni)
}

export default{
    namespaced: true,    
    state,
    
    actions,
    mutations
};