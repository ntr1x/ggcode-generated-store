<template>
  <div class="mb-3">
    <div
      v-styleclass="{
        selector: '@next',
        enterClass: 'hidden',
        enterActiveClass: 'slidedown',
        leaveToClass: 'hidden',
        leaveActiveClass: 'slideup'
      }"
      class="p-1 flex items-center justify-between text-surface-600 dark:text-surface-400 cursor-pointer rounded-md"
    >
      <span class="text-sm">Orders</span>
      <i class="pi pi-chevron-down"></i>
    </div>
    <ul class="list-none p-0 m-0 overflow-hidden">
      <li>
        <router-link to="/admin/orders" class="flex items-center cursor-pointer p-1 rounded-md text-surface-700 dark:text-surface-0/80 hover:bg-surface-100 dark:hover:bg-surface-700 duration-200 transition-colors">
          <i class="pi pi-shopping-bag mr-2"></i>
          <span class="font-medium">Orders</span>
        </router-link>
      </li>
      <li>
        <router-link to="/admin/payments" class="flex items-center cursor-pointer p-1 rounded-md text-surface-700 dark:text-surface-0/80 hover:bg-surface-100 dark:hover:bg-surface-700 duration-200 transition-colors">
          <i class="pi pi-money-bill mr-2"></i>
          <span class="font-medium">Payments</span>
        </router-link>
      </li>
    </ul>
  </div>
</template>