import axios from 'axios'

export const eventsRemote = axios.create({
  baseURL: window.__APP_CONFIG__?.remote?.events?.baseURL
    || import.meta.env.VITE_REMOTE_EVENTS_BASE_URL
    || 'http://api.local.example.com/api/assembly_web'
})
