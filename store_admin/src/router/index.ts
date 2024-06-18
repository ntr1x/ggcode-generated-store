import { createRouter, createWebHistory } from 'vue-router'
import { adminRouter } from './adminRouter.ts'

import PublicView from '../components/views/PublicView.vue'
import AuthCallbackView from '../components/views/AuthCallbackView.vue'

const routes = [
  { path: '/', component: PublicView },
  { path: '/auth/callback', component: AuthCallbackView },
  adminRouter,
]

export const createAppRouter = () => {
  return createRouter({
    history: createWebHistory(),
    routes,
  })
}
