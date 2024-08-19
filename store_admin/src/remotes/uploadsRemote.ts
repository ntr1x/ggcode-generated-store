import axios from 'axios'

export const uploadsRemote = axios.create({
  baseURL: window.__APP_CONFIG__?.remote?.uploads?.baseURL
    || import.meta.env.VITE_REMOTE_UPLOADS_BASE_URL
    || 'http://api.local.example.com/api/assembly_web'
})
