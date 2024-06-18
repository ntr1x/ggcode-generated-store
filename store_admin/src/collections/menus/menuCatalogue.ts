import { type Router } from "vue-router";
import { type Menu } from "../menus.d";

export const menuCatalogue = (router: Router): Menu => ({
  title: 'Catalogue',
  items: [
    { type: 'page', label: 'Categories', description: 'View Catalogue → Categories page', execute: () => router.push('/admin/categories') },
    { type: 'page', label: 'Products', description: 'View Catalogue → Products page', execute: () => router.push('/admin/products') },
    { type: 'page', label: 'Items', description: 'View Catalogue → Items page', execute: () => router.push('/admin/items') },
    { type: 'page', label: 'Promotions', description: 'View Catalogue → Promotions page', execute: () => router.push('/admin/promotions') },
    { type: 'page', label: 'Regions', description: 'View Catalogue → Regions page', execute: () => router.push('/admin/regions') },
    { type: 'page', label: 'Shops', description: 'View Catalogue → Shops page', execute: () => router.push('/admin/shops') },
  ]
})
