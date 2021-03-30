import { createStore } from 'vuex'
import APlijekovi from'./modules/APlijekovi';
import APApoteke from'./modules/APApoteke';
import IznemaLicnihInfoKorisnik from'./modules/IzmenaLicnihInfoKorisnik';
import APPacijenti from "./modules/APPacijenti";
import APPosete from "./modules/APPosete";
import APKorisnici from './modules/APKorisnici';

export default createStore({
    state () {
        return {
          
        }
      },
      
      modules:{
        APlijekovi,
        APApoteke,
        IznemaLicnihInfoKorisnik,
        APPacijenti,
          APPosete,
        APKorisnici
      }
  })