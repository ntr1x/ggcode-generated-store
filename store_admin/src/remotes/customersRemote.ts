import axios from 'axios'

export const customersRemote = axios.create({
  baseURL: window.__APP_CONFIG__?.remote?.customers?.baseURL
    || import.meta.env.VITE_REMOTE_CUSTOMERS_BASE_URL
    || 'http://api.local.example.com/api/assembly_web'
})
