import { createStore } from 'vuex'
import APlijekovi from'./modules/APlijekovi';
import APApoteke from'./modules/APApoteke';
import APPacijenti from "./modules/APPacijenti";
import APPosete from "./modules/APPosete";
import APKorisnici from './modules/APKorisnici';
import Dermatolozi from './modules/Dermatolozi';
import IzmenaLicnihInfoKorisnik from'./modules/IzmenaLicnihInfoKorisnik';
import Alergije from './modules/Alergije';
//import PocetnaStranaPacijenta from './modules/PocetnaStranaPacijenta';
import RezervisaniLekovi from './modules/RezervisaniLekovi';
import ERecepti from './modules/ERecepti';
import Farmaceuti from './modules/Farmaceuti';


export default createStore({
    state () {
        return {
          
        }
      },
      
      modules:{
        APlijekovi,
        APApoteke,
        IzmenaLicnihInfoKorisnik,
        APPacijenti,
          APPosete,
        APKorisnici,
        //PocetnaStranaPacijenta,
        RezervisaniLekovi,
        ERecepti,
        Alergije,
        Dermatolozi,
        Farmaceuti
      }
  })