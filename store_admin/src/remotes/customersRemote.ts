import axios from 'axios'

export const customersRemote = axios.create({
  baseURL: 'http://api.local.example.com/api/assembly_web'
})
