import { type Router } from "vue-router";
import { type Menu } from "../menus.d";

export const menuProfiles = (router: Router): Menu => ({
  title: 'Profiles',
  items: [
    { type: 'page', label: 'Customers', description: 'View Profiles → Customers page', execute: () => router.push('/admin/customers') },
    { type: 'page', label: 'Agents', description: 'View Profiles → Agents page', execute: () => router.push('/admin/agents') },
  ]
})
