<script setup lang="ts">
import Button from 'primevue/button';

</script>

<template>
  <div class="flex p-3 bg-primary-500">
    <div class="mx-auto w-[1200px] flex">
      <div class="flex flex-1"></div>
      <div class="flex flex-none gap-3">
        <RouterLink to="/sign-up" custom v-slot="{ navigate }">
          <Button label="Register" text class="text-white" @click="navigate" />
        </RouterLink>
        <RouterLink to="/sign-in" custom v-slot="{ navigate }">
          <Button label="Sign In" text class="text-white" @click="navigate" />
        </RouterLink>
      </div>
    </div>
  </div>
</template>