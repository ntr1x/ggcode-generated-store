<script setup lang="ts">
import Panel from 'primevue/panel'
import { inject } from 'vue';
import { type PrincipalResponse } from '../../store/profileStore';

const principal = inject<PrincipalResponse>('principal')!
</script>

<template>
  <Panel
    header="Personal Profile"
    pt:header:class="flex items-center justify-between text-surface-700 dark:text-surface-0/80 bg-surface-50 dark:bg-surface-900 border-b border-surface-200 dark:border-surface-700 rounded-tr-lg p-5"
    pt:content:class="p-5 border-b border-surface-200 dark:border-surface-700 bg-surface-0 dark:bg-surface-900 text-surface-700 dark:text-surface-0/80"
  >
    <div class="flex items-center gap-3 mb-3">
      <label class="font-semibold w-[6rem]">Username</label>
      <div v-text="principal.username"></div>
    </div>
    <div class="flex items-center gap-3 mb-3">
      <label class="font-semibold w-[6rem]">Email</label>
      <div v-text="principal.email"></div>
    </div>
    <div class="flex items-center gap-3 mb-3">
      <label class="font-semibold w-[6rem]">Full Name</label>
      <div v-if="principal.fullName" v-text="principal.fullName"></div>
      <div v-else><i>&lt;not defined&gt;</i></div>
    </div>
    <div class="flex items-center gap-3 mb-3">
      <label class="font-semibold w-[6rem]">Issuer</label>
      <div v-text="principal.issuer"></div>
    </div>
    <div class="flex items-center gap-3 mb-3">
      <label class="font-semibold w-[6rem]">ID</label>
      <div v-text="principal.subject"></div>
    </div>
    <div class="flex items-center gap-3 mb-3">
      <label class="font-semibold w-[6rem]">Authorities</label>
      <div>
        <div v-for="authority in principal.authorities" v-text="authority"></div>
      </div>
    </div>
  </Panel>
</template>
