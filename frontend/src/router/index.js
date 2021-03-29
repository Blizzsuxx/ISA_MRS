import { createRouter, createWebHistory } from 'vue-router'


import APLijekovi from '../components/APLijekovi'
import APApoteke from '../components/APApoteke'
import IzmenaLicnihInfoKorisnik from '../components/IzmenaLicnihInfoKorisnik'
import APFarmaceut from '../components/APFarmaceut'
import APDermatolog from '../components/APDermatolog'
import PocetnaStranaPacijenta from '../components/PocetnaStranaPacijenta'

const routes = [
  
  
  {
    path: '/ap/lijekovi',
    name: 'APLijekovi',
    component: APLijekovi,
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
    path: '/ap/dermatolog',
    name: 'APDermatolog',
    component: APDermatolog,
  },
   {
    path: '/ap/pacijent',
    name: 'PocetnaStranaPacijenta',
    component: PocetnaStranaPacijenta,
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
})

export default router