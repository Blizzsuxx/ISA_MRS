import { createRouter, createWebHistory } from 'vue-router'


import APLijekovi from '../components/APLijekovi'
import APApoteke from '../components/APApoteke'
import IznemaLicnihInfoKorisnik from '../components/IznemaLicnihInfoKorisnik'
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
    name: 'IznemaLicnihInfoKorisnik',
    component: IznemaLicnihInfoKorisnik,
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
})

export default router