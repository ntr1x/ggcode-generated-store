import axios from 'axios'

export const paymentsRemote = axios.create({
  baseURL: 'http://api.local.example.com/api/assembly_web'
})
