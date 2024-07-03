<script lang="ts" setup>
import { inject } from 'vue';
import { PrincipalResponse } from '../../../store/profileStore';
import { computed } from 'vue';
import { Ref } from 'vue';

const principal = inject<Ref<PrincipalResponse | null>>('principal')

const hasAnyRole = computed(() => (roles: string[]) => {
  return (principal?.value?.authorities || []).filter(value => roles.includes(value)).length > 0
})
</script>

<template>
  <div class="mb-3" v-if='hasAnyRole(["realm:developer","realm:admin","realm:support"])'>
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
      <span class="text-sm">Notifications</span>
      <i class="pi pi-chevron-down"></i>
    </div>
    <ul class="list-none p-0 m-0 overflow-hidden">
      <li v-if='hasAnyRole(["realm:developer","realm:admin","realm:support"])'>
        <router-link to="/admin/dispatches" class="flex items-center cursor-pointer p-1 rounded-md text-surface-700 dark:text-surface-0/80 hover:bg-surface-100 dark:hover:bg-surface-700 duration-200 transition-colors">
          <i class="pi pi-bell mr-2"></i>
          <span class="font-medium">Dispatches</span>
        </router-link>
      </li>
      <li v-if='hasAnyRole(["realm:developer","realm:admin","realm:support"])'>
        <router-link to="/admin/subscriptions" class="flex items-center cursor-pointer p-1 rounded-md text-surface-700 dark:text-surface-0/80 hover:bg-surface-100 dark:hover:bg-surface-700 duration-200 transition-colors">
          <i class="pi pi-directions mr-2"></i>
          <span class="font-medium">Subscriptions</span>
        </router-link>
      </li>
      <li v-if='hasAnyRole(["realm:developer","realm:admin","realm:support"])'>
        <router-link to="/admin/events" class="flex items-center cursor-pointer p-1 rounded-md text-surface-700 dark:text-surface-0/80 hover:bg-surface-100 dark:hover:bg-surface-700 duration-200 transition-colors">
          <i class="pi pi-bolt mr-2"></i>
          <span class="font-medium">Events</span>
        </router-link>
      </li>
    </ul>
  </div>
</template>