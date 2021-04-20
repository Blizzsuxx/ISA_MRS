import { createRouter, createWebHistory } from 'vue-router'


import APLijekovi from '../components/APLijekovi'
import APApoteke from '../components/APApoteke'
import IzmenaLicnihInfoKorisnik from '../components/IzmenaLicnihInfoKorisnik'
import APFarmaceut from '../components/APFarmaceut'
import APDermatolog from '../components/APDermatolog'
import PocetnaStranaPacijenta from '../components/PocetnaStranaPacijenta'

import ASLijekovi from '../components/ASLijekovi'
import ASKorisnici from '../components/ASKorisnici'
import FormaApoteke from '../components/FormaApoteke'
import FormaKorisnika from '../components/FormaKorisnika'
import FormaLijekova from '../components/FormaLijekova'

import APPregledPacijenataDermatolog from "../components/APPregledPacijenataDermatolog";
import APPregledPacijenataFarmaceut from "../components/APPregledPacijenataFarmaceut";
import KreiranjeNarudzbenica from "../components/KreiranjeNarudzbenica"
import APPregled from "../components/APPregled"
import SlobodanTermin from '../components/SlobodanTermin'
import ProfilApoteke from '../components/ProfilApoteke'
const routes = [
  
  
  {
    path: '/ap/lijekovi',
    name: 'APLijekovi',
    component: APLijekovi,
  },
  {
    path: '/profilApoteke',
    name: 'ProfilApoteke',
    component: ProfilApoteke,
  },
  {
    path: '/ap/apoteke',
    name: 'APApoteke',
    component: APApoteke,
  },
  {
    path: '/ap/profil',
    name: 'IzmenaLicnihInfoKorisnik',
    component: IzmenaLicnihInfoKorisnik,
  },
  {
    path: '/ap/farmaceut',
    name: 'APFarmaceut',
    component: APFarmaceut,
  },
  {
    path :'/slobodanTermin',
    name : 'SlobodanTermin',
    component: SlobodanTermin
  },
  {
    path: '/ap/dermatolog',
    name: 'APDermatolog',
    component: APDermatolog,
  },
  {
    path: '/ap/DTOLijekovi',
    name: 'ASLijekovi',
    component: ASLijekovi,
  },{
    path: '/ap/dermatolog/pacijenti',
    name: 'APPregledPacijenata',
    component: APPregledPacijenataDermatolog,
  },
  {

    path: '/ap/farmaceut/pacijenti',
    name: 'APPregledPacijenataFarmaceut',
    component: APPregledPacijenataFarmaceut,
  },
  {
    path: '/ap/FormaApoteke',
    name: 'FormaApoteke',
    component: FormaApoteke,
  },
  {
    path: '/ap/FormaKorisnika',
    name: 'FormaKorisnika',
    component: FormaKorisnika,
  },
  {
    path: '/ap/pacijent',
    name: 'PocetnaStranaPacijenta',
    component: PocetnaStranaPacijenta,
  },
  {
    path: '/ap/korisnici',
    name: 'ASKorisnici',
    component: ASKorisnici,
  },
  {
    path: '/ap/FormaLijekova',
    name: 'FormaLijekova',
    component: FormaLijekova,
  },
  {
    path: '/kreiranjeNarudzbenice',
    name: 'KreiranjeNarudzbenice',
    component: KreiranjeNarudzbenica

  },
  {
    path: '/ap/Pregled',
    name: 'APPregled',
    component: APPregled
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
})

export default router