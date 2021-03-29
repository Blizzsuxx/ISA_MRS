import { createStore } from 'vuex'
import APlijekovi from'./modules/APlijekovi';
import APApoteke from'./modules/APApoteke';
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
        IzmenaLicnihInfoKorisnik,
        PocetnaStranaPacijenta,
        Alergije
      }
  })