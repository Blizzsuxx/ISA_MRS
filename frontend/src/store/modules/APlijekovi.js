

const state = {    
    lijek: "Bromazepan"
};

const getters = {
    dobaviLijek : state => state.lijek    
};



const mutations = {
    postaviLijek:(state, lijek) => (state.lijek = lijek),
}

export default{
    namespaced: true,    
    state,
    getters, 
    mutations
};