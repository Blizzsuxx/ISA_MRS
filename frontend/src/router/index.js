import { createRouter, createWebHistory } from 'vue-router'


import APLijekovi from '../components/APLijekovi'
const routes = [
  
  
  {
    path: '/ap/lijekovi',
    name: 'APLijekovi',
    component: APLijekovi,
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
})

export default router