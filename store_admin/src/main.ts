import { createApp, markRaw } from 'vue'
import { createPinia } from 'pinia'
import PrimeVue from 'primevue/config'
import Ripple from 'primevue/ripple';
import StyleClass from 'primevue/styleclass';

// @ts-ignore
import Lara from './presets/lara';

import "primeicons/primeicons.css";
import './style.css'
import App from './App.vue'
import { createAppRouter } from './router';

const app = createApp(App)
const pinia = createPinia()
const router = createAppRouter()

pinia.use(({ store }) => {
  store.$router = markRaw(router)
})

app.use(router)
app.use(pinia)
app.use(PrimeVue, {
  unstyled: true,
  pt: Lara,
  // ripple: true
})

app.directive('ripple', Ripple)
app.directive('styleclass', StyleClass)

app.mount('#app')
