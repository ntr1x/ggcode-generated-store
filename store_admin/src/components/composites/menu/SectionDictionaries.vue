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
      <span class="text-sm">Dictionaries</span>
      <i class="pi pi-chevron-down"></i>
    </div>
    <ul class="list-none p-0 m-0 overflow-hidden">
      <li>
        <router-link to="/admin/order_types" class="flex items-center cursor-pointer p-1 rounded-md text-surface-700 dark:text-surface-0/80 hover:bg-surface-100 dark:hover:bg-surface-700 duration-200 transition-colors">
          <i class="pi pi-database mr-2"></i>
          <span class="font-medium">Order Types</span>
        </router-link>
      </li>
      <li>
        <router-link to="/admin/order_statuses" class="flex items-center cursor-pointer p-1 rounded-md text-surface-700 dark:text-surface-0/80 hover:bg-surface-100 dark:hover:bg-surface-700 duration-200 transition-colors">
          <i class="pi pi-database mr-2"></i>
          <span class="font-medium">Order Statuses</span>
        </router-link>
      </li>
      <li>
        <router-link to="/admin/payment_types" class="flex items-center cursor-pointer p-1 rounded-md text-surface-700 dark:text-surface-0/80 hover:bg-surface-100 dark:hover:bg-surface-700 duration-200 transition-colors">
          <i class="pi pi-database mr-2"></i>
          <span class="font-medium">Payment Types</span>
        </router-link>
      </li>
      <li>
        <router-link to="/admin/payment_statuses" class="flex items-center cursor-pointer p-1 rounded-md text-surface-700 dark:text-surface-0/80 hover:bg-surface-100 dark:hover:bg-surface-700 duration-200 transition-colors">
          <i class="pi pi-database mr-2"></i>
          <span class="font-medium">Payment Statuses</span>
        </router-link>
      </li>
      <li>
        <router-link to="/admin/promotion_types" class="flex items-center cursor-pointer p-1 rounded-md text-surface-700 dark:text-surface-0/80 hover:bg-surface-100 dark:hover:bg-surface-700 duration-200 transition-colors">
          <i class="pi pi-database mr-2"></i>
          <span class="font-medium">Promotion Types</span>
        </router-link>
      </li>
      <li>
        <router-link to="/admin/source_types" class="flex items-center cursor-pointer p-1 rounded-md text-surface-700 dark:text-surface-0/80 hover:bg-surface-100 dark:hover:bg-surface-700 duration-200 transition-colors">
          <i class="pi pi-database mr-2"></i>
          <span class="font-medium">Source Types</span>
        </router-link>
      </li>
      <li>
        <router-link to="/admin/subscription_types" class="flex items-center cursor-pointer p-1 rounded-md text-surface-700 dark:text-surface-0/80 hover:bg-surface-100 dark:hover:bg-surface-700 duration-200 transition-colors">
          <i class="pi pi-database mr-2"></i>
          <span class="font-medium">Subscription Types</span>
        </router-link>
      </li>
      <li>
        <router-link to="/admin/dispatch_types" class="flex items-center cursor-pointer p-1 rounded-md text-surface-700 dark:text-surface-0/80 hover:bg-surface-100 dark:hover:bg-surface-700 duration-200 transition-colors">
          <i class="pi pi-database mr-2"></i>
          <span class="font-medium">Dispatch Types</span>
        </router-link>
      </li>
      <li>
        <router-link to="/admin/dispatch_statuses" class="flex items-center cursor-pointer p-1 rounded-md text-surface-700 dark:text-surface-0/80 hover:bg-surface-100 dark:hover:bg-surface-700 duration-200 transition-colors">
          <i class="pi pi-database mr-2"></i>
          <span class="font-medium">Dispatch Statuses</span>
        </router-link>
      </li>
      <li>
        <router-link to="/admin/event_sources" class="flex items-center cursor-pointer p-1 rounded-md text-surface-700 dark:text-surface-0/80 hover:bg-surface-100 dark:hover:bg-surface-700 duration-200 transition-colors">
          <i class="pi pi-database mr-2"></i>
          <span class="font-medium">Event Sources</span>
        </router-link>
      </li>
      <li>
        <router-link to="/admin/event_topics" class="flex items-center cursor-pointer p-1 rounded-md text-surface-700 dark:text-surface-0/80 hover:bg-surface-100 dark:hover:bg-surface-700 duration-200 transition-colors">
          <i class="pi pi-database mr-2"></i>
          <span class="font-medium">Event Topics</span>
        </router-link>
      </li>
      <li>
        <router-link to="/admin/event_types" class="flex items-center cursor-pointer p-1 rounded-md text-surface-700 dark:text-surface-0/80 hover:bg-surface-100 dark:hover:bg-surface-700 duration-200 transition-colors">
          <i class="pi pi-database mr-2"></i>
          <span class="font-medium">Event Types</span>
        </router-link>
      </li>
    </ul>
  </div>
</template>