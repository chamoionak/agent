import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home.vue'
import LoveAppChat from '../views/LoveAppChat.vue'
import ManusChat from '../views/ManusChat.vue'

const routes = [
  { path: '/', name: 'home', component: Home },
  { path: '/love-app', name: 'love-app', component: LoveAppChat },
  { path: '/manus', name: 'manus', component: ManusChat },
]

const router = createRouter({
  history: createWebHistory(),
  routes,
})

export default router
