<script setup lang="ts">
import SectionHeading from '../partials/SectionHeading.vue';
import WidgetPersonalProfile from '../widgets/WidgetPersonalProfile.vue';
import WidgetDispatchMessage from '../widgets/WidgetDispatchMessage.vue';


</script>

<template>
  <div class="flex flex-col overflow-hidden">
    <SectionHeading tag="h1" title="Dashboard" />
    <WidgetPersonalProfile />
    <WidgetDispatchMessage />
  </div>
</template>
