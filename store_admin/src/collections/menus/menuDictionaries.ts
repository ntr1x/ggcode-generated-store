import { type Router } from "vue-router";
import { type Menu } from "../menus.d";

export const menuDictionaries = (router: Router): Menu => ({
  title: 'Dictionaries',
  items: [
    { type: 'page', label: 'Order Types', description: 'View Dictionaries → Order Types page', execute: () => router.push('/admin/order_types') },
    { type: 'page', label: 'Order Statuses', description: 'View Dictionaries → Order Statuses page', execute: () => router.push('/admin/order_statuses') },
    { type: 'page', label: 'Payment Types', description: 'View Dictionaries → Payment Types page', execute: () => router.push('/admin/payment_types') },
    { type: 'page', label: 'Payment Statuses', description: 'View Dictionaries → Payment Statuses page', execute: () => router.push('/admin/payment_statuses') },
    { type: 'page', label: 'Promotion Types', description: 'View Dictionaries → Promotion Types page', execute: () => router.push('/admin/promotion_types') },
    { type: 'page', label: 'Source Types', description: 'View Dictionaries → Source Types page', execute: () => router.push('/admin/source_types') },
    { type: 'page', label: 'Subscription Types', description: 'View Dictionaries → Subscription Types page', execute: () => router.push('/admin/subscription_types') },
    { type: 'page', label: 'Dispatch Types', description: 'View Dictionaries → Dispatch Types page', execute: () => router.push('/admin/dispatch_types') },
    { type: 'page', label: 'Dispatch Statuses', description: 'View Dictionaries → Dispatch Statuses page', execute: () => router.push('/admin/dispatch_statuses') },
    { type: 'page', label: 'Event Sources', description: 'View Dictionaries → Event Sources page', execute: () => router.push('/admin/event_sources') },
    { type: 'page', label: 'Event Topics', description: 'View Dictionaries → Event Topics page', execute: () => router.push('/admin/event_topics') },
    { type: 'page', label: 'Event Types', description: 'View Dictionaries → Event Types page', execute: () => router.push('/admin/event_types') },
    { type: 'page', label: 'Template Types', description: 'View Dictionaries → Template Types page', execute: () => router.push('/admin/template_types') },
    { type: 'page', label: 'Template Shapes', description: 'View Dictionaries → Template Shapes page', execute: () => router.push('/admin/template_shapes') },
  ]
})
