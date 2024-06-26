import ViewPersonal from '../components/views/ViewPersonal.vue'
import PageDashboard from '../components/pages/PageDashboard.vue'

export const personalRouter = {
  path: '/personal',
  component: ViewPersonal,
  children: [
    {
      path: 'dashboard',
      component: PageDashboard,
      meta: {
        requiresAuth: true,
        roles: []
      }
    },
    {
      path: '',
      redirect: '/personal/dashboard'
    }
  ]
}
