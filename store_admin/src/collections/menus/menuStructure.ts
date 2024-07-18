import { type Router } from "vue-router";
import { type Menu } from "../menus.d";

export const menuStructure = (router: Router): Menu => ({
  title: 'Structure',
  items: [
    { type: 'page', label: 'Networks', description: 'View Structure → Networks page', execute: () => router.push('/admin/networks') },
  ]
})
