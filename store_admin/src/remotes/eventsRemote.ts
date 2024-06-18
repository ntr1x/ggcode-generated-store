import axios from 'axios'

export const eventsRemote = axios.create({
  baseURL: 'http://api.local.example.com/api/assembly_web'
})
