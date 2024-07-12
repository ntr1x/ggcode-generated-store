<script setup lang="ts">
import { useSecurityContext } from '../../hooks/useSecurityContext';
import SectionHeading from '../partials/SectionHeading.vue';
import WidgetPersonalProfile from '../widgets/WidgetPersonalProfile.vue';
import WidgetDispatchMessage from '../widgets/WidgetDispatchMessage.vue';

// @ts-ignore
const security = useSecurityContext()


</script>

<template>
  <div class="flex flex-col flex-1 overflow-hidden">
    <SectionHeading
      tag="h1"
      title="Dashboard"
    />
    <WidgetPersonalProfile
      class="rounded-none border-0 border-b"
    />
    <WidgetDispatchMessage
      v-if='security.hasAnyRole.value(["realm:developer","realm:admin","realm:support"])'
      class="rounded-none border-0 border-b"
    />
  </div>
</template>
