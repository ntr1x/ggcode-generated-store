<script setup lang="ts">
import { set as setProperty } from 'lodash';
import { ref, watch, reactive } from 'vue';
import { useRoute } from 'vue-router';
import { useAuthStore } from '../../store/authStore';
import { useAxiosRequest } from '../../hooks/useAxiosRequest';
import SectionHeading from '../partials/SectionHeading.vue';
import WidgetPersonalProfile from '../widgets/WidgetPersonalProfile.vue';
import WidgetDispatchMessage from '../widgets/WidgetDispatchMessage.vue';

// @ts-ignore
const route = useRoute()
const authStore = useAuthStore()

</script>

<template>
  <div class="flex flex-col overflow-hidden">
    <SectionHeading tag="h1" title="Dashboard" />
    <WidgetPersonalProfile />
    <WidgetDispatchMessage />
  </div>
</template>
