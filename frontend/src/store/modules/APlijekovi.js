import axios from 'axios'

const state = {    
    lijekoviMagacin :[],
    lijekoviProdaja : [],
};

const getters = {
    lijekovi : state => state.lijek    
};

const actions = {
    dobaviLijekove (contex) {
        axios.get('http://localhost:8080/api/v1/apoteka/dobaviLijekove/1')
        .then(response => {
        let lijekoviMagacin =response.data.filter(lijek=>lijek.prodaja===false)
        let lijekoviProdaja = response.data.filter(lijek=>lijek.prodaja===true)
        contex.commit('postaviLijekProdaja',lijekoviProdaja)
        contex.commit('postaviLijekMagacin',lijekoviMagacin)
        })
        
        
    },
    izbaciIzProizvodnje({commit,state},lijekovi){
        
        let lijekoviProdaja = state.lijekoviProdaja.filter(function(el){
            
            if(lijekovi.filter(elem=>elem.id===el.id).length!=0){
                
                return false;
            }
            else{
                return true;
            }
        })
        let lijekoviMagacin = state.lijekoviMagacin.concat(lijekovi)
        commit('postaviLijekProdaja',lijekoviProdaja)
        commit('postaviLijekMagacin',lijekoviMagacin)
        
    },
    ubaciUProizvodnju({commit,state},lijekovi){
        
        let lijekoviMagacin = state.lijekoviMagacin.filter(function(el){
            
            if(lijekovi.filter(elem=>elem.id===el.id).length!=0){
                
                return false;
            }
            else{
                return true;
            }
        })
        let lijekoviProdaja = state.lijekoviProdaja.concat(lijekovi)
        commit('postaviLijekProdaja',lijekoviProdaja)
        commit('postaviLijekMagacin',lijekoviMagacin)
        
    }

}

const mutations = {
    postaviLijekProdaja:(state, lijekovi) => (state.lijekoviProdaja = lijekovi),
    postaviLijekMagacin:(state, lijekovi) => (state.lijekoviMagacin = lijekovi),
}

export default{
    namespaced: true,    
    state,
    getters, 
    actions,
    mutations
};