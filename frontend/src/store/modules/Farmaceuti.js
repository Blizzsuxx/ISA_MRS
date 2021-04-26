import axios from 'axios'

const state = {
    sviFarmaceuti :[],
    

};



const actions = {
    dobaviFarmaceute (context) {
        axios.get('http://localhost:8080/api/v1/dermatolog/dobaviDermatologe')
            .then(response => {
                let farmaceuti =response.data
                context.commit('postaviFarmaceute',farmaceuti)
            })


    },
  

}

const mutations = {
    postaviFarmaceute:(state, farmaceuti) => (state.sviFarmaceuti = farmaceuti),
   
}

export default{
    namespaced: true,
    state,
    actions,
    mutations
};