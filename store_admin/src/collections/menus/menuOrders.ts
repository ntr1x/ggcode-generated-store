import { type Router } from "vue-router";
import { type Menu } from "../menus.d";

export const menuOrders = (router: Router): Menu => ({
  title: 'Orders',
  items: [
    { type: 'page', label: 'Orders', description: 'View Orders → Orders page', execute: () => router.push('/admin/orders') },
    { type: 'page', label: 'Payments', description: 'View Orders → Payments page', execute: () => router.push('/admin/payments') },
  ]
})
