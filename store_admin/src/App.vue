<script setup lang="ts">
import { RouterView } from 'vue-router';
import Toast from 'primevue/toast';
import ModalStack from './components/composites/ModalStack.vue';

</script>

<template>
  <RouterView />
  <ModalStack />
  <Toast />
</template>

<style scoped lang="scss">
</style>
