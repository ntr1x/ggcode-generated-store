<script setup lang="ts">
import SectionOrders from './SectionOrders.vue'
import SectionProfiles from './SectionProfiles.vue'
import SectionCatalogue from './SectionCatalogue.vue'
import SectionNotifications from './SectionNotifications.vue'
import SectionDictionaries from './SectionDictionaries.vue'
</script>

<template>
  <div class="flex flex-col h-full w-[25rem] shadow">
    <div class="overflow-y-auto mt-4">
      <ul class="list-none px-4 m-0">
        <li class="empty:hidden"><SectionOrders /></li>
        <li class="empty:hidden"><SectionProfiles /></li>
        <li class="empty:hidden"><SectionCatalogue /></li>
        <li class="empty:hidden"><SectionNotifications /></li>
        <li class="empty:hidden"><SectionDictionaries /></li>
      </ul>
    </div>
  </div>
</template>
