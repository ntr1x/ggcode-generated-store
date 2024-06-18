<script setup lang="ts">
import Dialog from 'primevue/dialog';
import type { ModalContext } from '../../store/modalStore';
import { inject } from 'vue';
import Button from 'primevue/button';
import { PrincipalResponse } from '../../store/profileStore';

const context = inject<ModalContext>('modalContext')!

defineProps<{ principal: PrincipalResponse }>()

</script>
<template>
  <Dialog :visible="true" @update:visible="context.doClose" modal header="Your Account" :style="{ maxWidth: '35rem' }">
    <!-- <span class="text-surface-600 dark:text-surface-0/70 block mb-5">Update your information.</span> -->
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
    <div class="flex justify-end gap-2">
      <Button type="button" label="Close" severity="secondary" @click="context.doClose"></Button>
    </div>
  </Dialog>
</template>
