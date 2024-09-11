import axios from 'axios'

export const structureRemote = axios.create({
  baseURL: window.__APP_CONFIG__?.remote?.structure?.baseURL
    || import.meta.env.VITE_REMOTE_STRUCTURE_BASE_URL
    || 'http://api.local.example.com/api/assembly_web'
})
