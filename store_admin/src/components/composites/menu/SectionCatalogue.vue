<script lang="ts" setup>
import { Ref, inject, computed } from 'vue';
import { PrincipalResponse } from '../../../store/profileStore';

const principal = inject<Ref<PrincipalResponse | null>>('principal')

const hasAnyRole = computed(() => (roles: string[]) => {
  return roles.length == 0 || (principal?.value?.authorities || []).filter(value => roles.includes(value)).length > 0
})
</script>

<template>
  <div class="mb-3" v-if='hasAnyRole(["realm:developer","realm:admin"])'>
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
      <span class="text-sm">Catalogue</span>
      <i class="pi pi-chevron-down"></i>
    </div>
    <ul class="list-none p-0 m-0 overflow-hidden">
      <li v-if='hasAnyRole(["realm:developer","realm:admin"])'>
        <router-link to="/admin/categories" class="flex items-center cursor-pointer p-1 rounded-md text-surface-700 dark:text-surface-0/80 hover:bg-surface-100 dark:hover:bg-surface-700 duration-200 transition-colors">
          <i class="pi pi-tags mr-2"></i>
          <span class="font-medium">Categories</span>
        </router-link>
      </li>
      <li v-if='hasAnyRole(["realm:developer","realm:admin"])'>
        <router-link to="/admin/products" class="flex items-center cursor-pointer p-1 rounded-md text-surface-700 dark:text-surface-0/80 hover:bg-surface-100 dark:hover:bg-surface-700 duration-200 transition-colors">
          <i class="pi pi-table mr-2"></i>
          <span class="font-medium">Products</span>
        </router-link>
      </li>
      <li v-if='hasAnyRole(["realm:developer","realm:admin"])'>
        <router-link to="/admin/items" class="flex items-center cursor-pointer p-1 rounded-md text-surface-700 dark:text-surface-0/80 hover:bg-surface-100 dark:hover:bg-surface-700 duration-200 transition-colors">
          <i class="pi pi-box mr-2"></i>
          <span class="font-medium">Items</span>
        </router-link>
      </li>
      <li v-if='hasAnyRole(["realm:developer","realm:admin"])'>
        <router-link to="/admin/promotions" class="flex items-center cursor-pointer p-1 rounded-md text-surface-700 dark:text-surface-0/80 hover:bg-surface-100 dark:hover:bg-surface-700 duration-200 transition-colors">
          <i class="pi pi-gift mr-2"></i>
          <span class="font-medium">Promotions</span>
        </router-link>
      </li>
      <li v-if='hasAnyRole(["realm:developer","realm:admin"])'>
        <router-link to="/admin/regions" class="flex items-center cursor-pointer p-1 rounded-md text-surface-700 dark:text-surface-0/80 hover:bg-surface-100 dark:hover:bg-surface-700 duration-200 transition-colors">
          <i class="pi pi-map mr-2"></i>
          <span class="font-medium">Regions</span>
        </router-link>
      </li>
      <li v-if='hasAnyRole(["realm:developer","realm:admin"])'>
        <router-link to="/admin/shops" class="flex items-center cursor-pointer p-1 rounded-md text-surface-700 dark:text-surface-0/80 hover:bg-surface-100 dark:hover:bg-surface-700 duration-200 transition-colors">
          <i class="pi pi-warehouse mr-2"></i>
          <span class="font-medium">Shops</span>
        </router-link>
      </li>
    </ul>
  </div>
</template>