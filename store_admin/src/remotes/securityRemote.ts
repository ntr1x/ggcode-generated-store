import axios from 'axios'

export const securityRemote = axios.create({
  baseURL: window.__APP_CONFIG__?.remote?.security?.baseURL
    || import.meta.env.VITE_REMOTE_SECURITY_BASE_URL
    || 'http://api.local.example.com/api/assembly_web'
})
