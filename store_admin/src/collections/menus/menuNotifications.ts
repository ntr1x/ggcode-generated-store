import { type Router } from "vue-router";
import { type Menu } from "../menus.d";

export const menuNotifications = (router: Router): Menu => ({
  title: 'Notifications',
  items: [
    { type: 'page', label: 'Dispatches', description: 'View Notifications → Dispatches page', execute: () => router.push('/admin/dispatches') },
    { type: 'page', label: 'Subscriptions', description: 'View Notifications → Subscriptions page', execute: () => router.push('/admin/subscriptions') },
    { type: 'page', label: 'Events', description: 'View Notifications → Events page', execute: () => router.push('/admin/events') },
    { type: 'page', label: 'Templates', description: 'View Notifications → Templates page', execute: () => router.push('/admin/templates') },
  ]
})
