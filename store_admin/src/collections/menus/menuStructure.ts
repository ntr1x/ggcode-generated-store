import { type Router } from "vue-router";
import { type Menu } from "../menus.d";

export const menuStructure = (router: Router): Menu => ({
  title: 'Structure',
  items: [
    { type: 'page', label: 'Networks', description: 'View Structure → Networks page', execute: () => router.push('/admin/networks') },
    { type: 'page', label: 'Guides', description: 'View Structure → Guides page', execute: () => router.push('/admin/guides') },
    { type: 'page', label: 'Guide Subjects', description: 'View Structure → Guide Subjects page', execute: () => router.push('/admin/guide_subjects') },
  ]
})
