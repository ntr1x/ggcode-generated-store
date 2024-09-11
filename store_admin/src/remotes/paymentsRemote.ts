import axios from 'axios'

export const paymentsRemote = axios.create({
  baseURL: window.__APP_CONFIG__?.remote?.payments?.baseURL
    || import.meta.env.VITE_REMOTE_PAYMENTS_BASE_URL
    || 'http://api.local.example.com/api/assembly_web'
})
