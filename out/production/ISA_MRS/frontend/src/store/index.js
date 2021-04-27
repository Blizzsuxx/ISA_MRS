import { createStore } from 'vuex'
import APlijekovi from'./modules/APlijekovi';
import APApoteke from'./modules/APApoteke';
import IznemaLicnihInfoKorisnik from'./modules/IzmenaLicnihInfoKorisnik';

export default createStore({
    state () {
        return {
          
        }
      },
      
      modules:{
        APlijekovi,
        APApoteke,
        IznemaLicnihInfoKorisnik
      }
  })