import { createRouter, createWebHistory } from 'vue-router'
import { useAuthStore } from '../store/authStore.ts'
import { adminRouter } from './adminRouter.ts'

import PublicView from '../components/views/PublicView.vue'
import AuthCallbackView from '../components/views/AuthCallbackView.vue'
import SignInView from '../components/views/SignInView.vue'
import SignUpView from '../components/views/SignUpView.vue'

const routes = [
  { path: '/', component: PublicView },
  { path: '/sign-in', component: SignInView },
  { path: '/sign-up', component: SignUpView },
  { path: '/auth/callback', component: AuthCallbackView },
  adminRouter,
]

export const createAppRouter = () => {
  const router = createRouter({
    history: createWebHistory(),
    routes,
  })

  router.beforeEach(async (to, _, next) => {

    const store = useAuthStore()

    try {
      if (to.meta.requiresAuth) {
        const roles = to.meta.roles || []
        const { principal } = await store.requireAuth()
        console.log(principal, roles)
      }
      next()
    } catch {
      next({ path: '/sign-in' })
    }
  })

  return router
}
