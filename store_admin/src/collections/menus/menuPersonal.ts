import { type Router } from "vue-router";
import { type Menu } from "../menus.d";

export const menuPersonal = (router: Router): Menu => ({
  title: 'Personal',
  items: [
    { type: 'page', label: 'Dashboard', description: 'View Personal → Dashboard page', execute: () => router.push('/personal/dashboard') },
  ]
})
