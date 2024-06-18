import axios from 'axios'

export const securityRemote = axios.create({
  baseURL: 'http://api.local.example.com/api/assembly_web'
})
