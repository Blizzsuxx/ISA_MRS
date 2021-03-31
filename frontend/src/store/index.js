import { createStore } from 'vuex'
import APlijekovi from'./modules/APlijekovi';
import APApoteke from'./modules/APApoteke';
import IznemaLicnihInfoKorisnik from'./modules/IzmenaLicnihInfoKorisnik';
import APPacijenti from "./modules/APPacijenti";
import APPosete from "./modules/APPosete";
import APKorisnici from './modules/APKorisnici';

import IzmenaLicnihInfoKorisnik from'./modules/IzmenaLicnihInfoKorisnik';
import Alergije from './modules/Alergije';
import PocetnaStranaPacijenta from './modules/PocetnaStranaPacijenta';
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
        APKorisnici,
        PocetnaStranaPacijenta,
        Alergije
      }
  })