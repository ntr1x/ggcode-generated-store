import axios from 'axios'

export const minioRemote = axios.create({
  baseURL: window.__APP_CONFIG__?.remote?.minio?.baseURL
    || import.meta.env.VITE_REMOTE_MINIO_BASE_URL
    || 'http://minio.local.example.com'
})
