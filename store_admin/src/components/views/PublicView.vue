<script setup lang="ts">
import { RouterLink } from 'vue-router'
import Button from "primevue/button";
</script>

<template>
  <div class="flex h-screen">
    <div class="m-auto">
      <RouterLink to="/admin" custom v-slot="{ navigate }">
        <Button size="large" label="Sign In" icon="pi pi-sign-in" @click="navigate" />
      </RouterLink>
    </div>
  </div>
</template>