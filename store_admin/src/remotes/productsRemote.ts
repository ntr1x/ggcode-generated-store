import axios from 'axios'

export const productsRemote = axios.create({
  baseURL: 'http://api.local.example.com/api/assembly_web'
})
