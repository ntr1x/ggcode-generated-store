<script setup lang="ts">
import { useRoute } from 'vue-router';
import SectionHeading from '../partials/SectionHeading.vue';
import WidgetPersonalProfile from '../widgets/WidgetPersonalProfile.vue';
import WidgetDispatchMessage from '../widgets/WidgetDispatchMessage.vue';

// @ts-ignore
const route = useRoute()

</script>

<template>
  <div class="flex flex-col overflow-hidden">
    <SectionHeading tag="h1" title="Dashboard" />
    <WidgetPersonalProfile />
    <WidgetDispatchMessage />
  </div>
</template>
