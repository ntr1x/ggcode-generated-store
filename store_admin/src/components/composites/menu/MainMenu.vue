<script setup lang="ts">
import SectionPersonal from './SectionPersonal.vue'
import SectionOrders from './SectionOrders.vue'
import SectionProfiles from './SectionProfiles.vue'
import SectionCatalogue from './SectionCatalogue.vue'
import SectionNotifications from './SectionNotifications.vue'
import SectionStructure from './SectionStructure.vue'
import SectionDictionaries from './SectionDictionaries.vue'
</script>

<template>
  <div class="flex flex-col h-full w-[25rem] shadow">
    <div class="overflow-y-auto">
      <ul class="list-none px-4 m-0 mt-4">
        <li class="empty:hidden"><SectionPersonal /></li>
        <li class="empty:hidden"><SectionOrders /></li>
        <li class="empty:hidden"><SectionProfiles /></li>
        <li class="empty:hidden"><SectionCatalogue /></li>
        <li class="empty:hidden"><SectionNotifications /></li>
        <li class="empty:hidden"><SectionStructure /></li>
        <li class="empty:hidden"><SectionDictionaries /></li>
      </ul>
    </div>
  </div>
</template>
