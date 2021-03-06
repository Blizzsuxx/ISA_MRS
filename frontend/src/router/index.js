import { createRouter, createWebHistory } from 'vue-router'

import ApotekeSve from '../components/ApotekeSve'
import APLijekovi from '../components/APLijekovi'
import APPostojeceApoteke from '../components/APPostojeceApoteke'
import IzmenaLicnihInfoKorisnik from '../components/IzmenaLicnihInfoKorisnik'
import APFarmaceut from '../components/APFarmaceut'
import APDermatolog from '../components/APDermatolog'
import IzmenaPodatakaRadnik from '../components/IzmenaPodatakaRadnik'
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
import Dobavljac from '../components/Dobavljac'

import BrisanjeDermatologa  from  "../components/BrisanjeDermatologa"
import FarmaceutiBrisanje from "../components/FarmaceutiBrisanje"
import Penali from "../components/Penali"
import PocetnaStrana from "../components/PocetnaStrana"
import Registracija from "../components/Registracija"
import RezervisanjeLekova from "../components/FormaRezervisanjLekova"
import PregledKategorije from "../components/PregledKategorije"
import AkcijeApoteke from "../components/AkcijeApoteke"
import PretplataNaApoteke from "../components/PretplataNaApoteke"

import FormaZakazivanjaKodDermatologa from "../components/FormaZakazivanjaKodDermatologa"
import LekoviNeautentifikovanKorisnik from "../components/LekoviNeautentifikovanKorisnik"
import FormaZakazivanjeFarmaceut from "../components/FormaZakazivanjeFarmaceut"

import PrihvatanjeGodisnjihOdmora from "../components/PrihvatanjeGodisnjihOdmora"
import DermatologZaposljavanje from "../components/DermatologZaposljavanje"
import FarmaceutZaposljavanje from "../components/FarmaceutZaposljavanje"
import PrikazNarudzbenica from "../components/PrikazNarudzbenica"
import NarudzbenicaProzor from "../components/NarudzbenicaProzor"
import PrvaPrijavaAdminApoteke from "../components/PrvaPrijavaAdminApoteke"
import DodavanjeAkcije from "../components/DodavanjeAkcije"
import Zalbe from "../components/Zalbe"
import ZalbeIstorija from '../components/ZalbeIstorija'
import PoruceniNepostojeci from '../components/PoruceniNepostojeci'
import FormaZaOcenjivanje from '../components/FormaZaOcenjivanje'
import ProfilApotekeKorisnik from '../components/ProfilApotekeKorisnik'
import PromjenaLozinke from "../components/PromjenaLozinke"
import QRKod from '../components/QRKod'
import Izvjestaji from "../components/Izvjestaji"

const routes = [
  {
    path: '/ap/apotekeSve',
    name: 'ApotekeSve',
    component: ApotekeSve,
  },
  {
    path: '/ap/lijekovi',
    name: 'APLijekovi',
    component: APLijekovi,
  },
  {
    path: '/izvjestaji',
    name: 'Izvjestaji',
    component: Izvjestaji,
  },
  {
    path: '/poruceni',
    name: 'PoruceniNepostojeci',
    component: PoruceniNepostojeci,
  },
  {
    path: '/adminApoteke/promjenaLozinke',
    name: 'PromjenaLozinke',
    component: PromjenaLozinke,
    meta:{title: 'Promjena lozinke'}
  },
  {
    path: '/prikazNarudzbenica',
    name: 'PrikazNarudzbenica',
    component: PrikazNarudzbenica,
    meta:{title: 'Istorija narudzbenica'}
  },
  {
    path: '/adminApoteke/potvrda',
    name: 'PrvaPrijavaAdminApoteke',
    component: PrvaPrijavaAdminApoteke,
    meta:{title: 'Resetovanje lozinke'}
  },
  {
    path: '/prikazNarudzbenice/:id',
    name: 'NarudzbenicaProzor',
    component: NarudzbenicaProzor,
    meta:{title: 'Narudzbenica'}
  },
  {
    path: '/apoteka/:id',
    name: 'ProfilApotekeKorisnik',
    component: ProfilApotekeKorisnik,
    meta:{title: 'Apoteka'}
  },
  {
    path: '/dodavanjeAkcije/:id',
    name: 'DodavanjeAkcije',
    component: DodavanjeAkcije,
    meta:{title: 'Dodavanje akcije'}
  },
  {
    path: '/godisnjiOdmori',
    name: 'PrihvatanjeGodisnjihOdmora',
    component: PrihvatanjeGodisnjihOdmora,

  },
  {
    path: '/ap/farmaceut/izdavanje',
    name: 'APIzdavanje',
    component: APIzdavanje
  },
  {
    path: '/pregledDermatologa',
    name: 'BrisanjeDermatologa',
    component: BrisanjeDermatologa,
    meta:{title: 'Zaposleni dermatolozi'}
  },
  {
    path: '/zaposljavanjeDermatologa',
    name: 'DermatologZaposljavanje',
    component: DermatologZaposljavanje,
    meta:{title: 'Dermatolozi'}
  },
  {
    path: '/zaposljavanjeFarmaceuta',
    name: 'FarmaceutZaposljavanje',
    component: FarmaceutZaposljavanje,
    meta:{title: 'Farmaceuti'}
  },
  {
    path: '/pregledFarmaceuta',
    name: 'FarmaceutiBrisanje',
    component: FarmaceutiBrisanje,
    meta:{title: 'Zaposleni farmaceuti'}
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
    path: '/ap/izmena',
    name: 'IzmenaPodatakaRadnik',
    component: IzmenaPodatakaRadnik,
  }
  ,
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
    path: '/ap/Pregled/:pacijentID+:pregledID',
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
  },
  {
    path: '/ap/penali',
    name: 'Penali',
    component: Penali,
  },
  {
    path: '/ap/registracija',
    name: 'Registracija',
    component: Registracija,
  },
  {
    path: '/ap/rezervisanjeLekova/:id?',
    name: 'RezervisanjeLekova',
    component: RezervisanjeLekova,
  },
  {
    path: '/ap/pregledKategorije',
    name: 'pregledKategorije',
    component: PregledKategorije,
  },
  {
    path: '/ap/akcijeApoteke',
    name: 'akcijeApoteke',
    component: AkcijeApoteke,
  },
  {
    path: '/ap/pretplata',
    name: 'pretplataNaApoteke',
    component: PretplataNaApoteke,
  },
  {
    path: '/ap/Dobavljac',
    name: 'Dobavljac',
    component: Dobavljac
  },
  {
    path: '/ap/kreirajZalbu',
    name: 'Zalbe',
    component: Zalbe
  },
  {
    path: '/ap/zalbeIstorija',
    name: 'ZalbeIstorija',
    component: ZalbeIstorija
  }
  ,
  {
    path: '/ap/zakaziD',
    name: 'FormaZakazivanjaKodDermatologa',
    component: FormaZakazivanjaKodDermatologa,
  },
  {
    path: '/ap/sviLekovi',
    name: 'LekoviNeautentifiKovanKorisnik',
    component: LekoviNeautentifikovanKorisnik,
  },
  {
    path: '/ap/zakaziF',
    name: 'FormaZakazivanjeFarmaceut',
    component: FormaZakazivanjeFarmaceut,
  },
  {
    path: '/ap/oceni',
    name: 'FormaZaOcenjivanje',
    component: FormaZaOcenjivanje,
  },
  {
    path: '/ap/qrkod',
    name: 'QRKod',
    component: QRKod
  }

]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
})
const DEFAULT_TITLE = 'Apoteka'
router.beforeEach((to, from, next)=>{

  const publicPages = ['/ap/prijava', '/ap/FormaKorisnika', '/ap/pocetnaStrana'];

  const authRequired = !publicPages.includes(to.path);
  const loggedIn = localStorage.getItem('user');
  document.title = to.meta.title || DEFAULT_TITLE
  if (authRequired && !loggedIn){
    console.log('provjera');
    return next('/ap/prijava');
  }
  next();
})

export default router