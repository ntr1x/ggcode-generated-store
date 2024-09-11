import axios from 'axios'

export const productsRemote = axios.create({
  baseURL: window.__APP_CONFIG__?.remote?.products?.baseURL
    || import.meta.env.VITE_REMOTE_PRODUCTS_BASE_URL
    || 'http://api.local.example.com/api/assembly_web'
})
