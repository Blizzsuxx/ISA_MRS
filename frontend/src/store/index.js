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
import GodisnjiOdmori from './modules/GodisnjiOdmori'
import Mail from './modules/Mail'
import APenali from './modules/APenali'
import APAkcije from './modules/APAkcije'
import LoyaltyPrograma from './modules/LoyaltyPrograma'
import Narudzbenice from './modules/Narudzbenice'

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
        APenali,
        RezervisaniLekovi,
        ERecepti,
        Alergije,
        Dermatolozi,
        Farmaceuti,
        GodisnjiOdmori,
        Mail,
        APAkcije,
        LoyaltyPrograma,
        Narudzbenice
      }
  })