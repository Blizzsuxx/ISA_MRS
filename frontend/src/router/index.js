import { createRouter, createWebHistory } from 'vue-router'


import APLijekovi from '../components/APLijekovi'
import APPostojeceApoteke from '../components/APPostojeceApoteke'
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

import ListaRezervacija from "../components/ListaRezervacija";
import ListaRecepata from "../components/ListaRecepata";
import IstorijaLekova from "../components/IstorijaLekova";
import APIzdavanje from "../components/APIzdavanje";


import SlobodanTermin from '../components/SlobodanTermin';
import ProfilApoteke from '../components/ProfilApoteke';
import APZakazanePoseteDermatologu from '../components/APZakazanePoseteDermatologu';
import APZakazanePoseteFarmaceutu from '../components/APZakazanePoseteFarmaceutu';
import IstorijaPosetaDermatologu from '../components/IstorijaPosetaDeramatologu';
import IstorijaPosetaFarmaceut from '../components/IstorijaPosetaFarmaceut'
import APPregled from "../components/APPregled"
import AzuriranjeApoteka from "../components/AzuriranjeApoteka"
import Prijava from "../components/Prijava"

import AdministratorSistema from '../components/AdministratorSistema';

import BrisanjeDermatologa  from  "../components/BrisanjeDermatologa"

import PocetnaStrana from "../components/PocetnaStrana"

const routes = [
  
  
  {
    path: '/ap/lijekovi',
    name: 'APLijekovi',
    component: APLijekovi,
  },
  {
    path: '/ap/farmaceut/izdavanje',
    name: 'APIzdavanje',
    component: APIzdavanje
  },

  {
    path: '/brisanjeDermatologa',
    name: 'BrisanjeDermatologa',
    component: BrisanjeDermatologa,
  },
  {
    path: '/profilApoteke',
    name: 'ProfilApoteke',
    component: ProfilApoteke,
  },
  {
    path: '/ap/apoteke',
    name: 'APPostojeceApoteke',
    component: APPostojeceApoteke,
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
    path: '/azuriranjeApoteke',
    name: 'AzuriranjeApoteka',
    component: AzuriranjeApoteka,
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
  },
  {
    path: '/ap/prijava',
    name: 'Prijava',
    component: Prijava
  },
  {
    path: '/ap/rezervacije',
    name: 'ListaRezervacija',
    component: ListaRezervacija,
  },
  {
    path: '/ap/recepti',
    name: 'ListaRecepata',
    component: ListaRecepata,
  },
  {
    path: '/ap/istorijaLekova',
    name: 'IstorijaLekova',
    component: IstorijaLekova,
  },
  {
    path: '/ap/zakazanePoseteDermatologuPacijent',
    name: 'APZakazanePoseteDermatologu',
    component: APZakazanePoseteDermatologu,
  },
  {
    path: '/ap/zakazanePoseteFarmaceutuPacijent',
    name: 'APZakazanePoseteFarmaceutu',
    component: APZakazanePoseteFarmaceutu,
  },
  {
    path: '/ap/istorijaPosetaDermatologu',
    name: 'IstorijaPosetaDermatologu',
    component: IstorijaPosetaDermatologu,
  },
  {
    path: '/ap/istorijaPosetaFarmaceut',
    name: 'IstorijaPosetaFarmaceut',
    component: IstorijaPosetaFarmaceut,
  },
  {
    path: '/ap/AdministratorSistema',
    name: 'AdministratorSistema',
    component: AdministratorSistema,
  },
  {
    path: '/ap/pocetnaStrana',
    name: 'PocetnaStrana',
    component: PocetnaStrana,
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
})

router.beforeEach((to, from, next)=>{
  const publicPages = ['/ap/prijava', '/ap/FormaKorisnika'];
  const authRequired = !publicPages.includes(to.path);
  const loggedIn = localStorage.getItem('user');

  if (authRequired && !loggedIn){
    console.log('provjera');
    return next('/ap/prijava');
  }
  next();
})

export default router