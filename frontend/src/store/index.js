import { createStore } from 'vuex'
import lijekovi from'./modules/APlijekovi';


export default createStore({
    state () {
        return {
          count: 0
        }
      },
      mutations: {
        increment (state) {
          state.count++
        }
      },
      modules:{
        lijekovi
      }
  })