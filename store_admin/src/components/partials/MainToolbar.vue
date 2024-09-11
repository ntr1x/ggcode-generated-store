<script setup lang="ts">
import Button from "primevue/button";
import { useUiStore } from "../../store/uiStore";
import { useAuthStore } from "../../store/authStore";
import { storeToRefs } from "pinia";
import { inject } from "vue";
import { PrincipalResponse } from "../../store/profileStore";
import { useModalStore } from "../../store/modalStore";
import { shallowRef } from "vue";
import PlatformDialogPrincipal from "../dialogs/PlatformDialogPrincipal.vue";
import PlatformDialogPage from "../dialogs/PlatformDialogPage.vue";

const uiStore = useUiStore()
const authStore = useAuthStore()
const modalStore = useModalStore()
const { isMenuSidebarPinned } = storeToRefs(uiStore)
const principal = inject<PrincipalResponse>('principal')!

function openPageDialog() {
  modalStore.openModal(() => ({
    component: shallowRef(PlatformDialogPage),
    props: {
      principal
    }
  }))
}

function openPrincipalDialog() {
  modalStore.openModal(() => ({
    component: shallowRef(PlatformDialogPrincipal),
    props: {
      principal
    }
  }))
}

</script>

<template>
  <div class="bg-surface-900 shadow-md bg-gradient-to-r from-primary-500/70 to-primary-600/80">
    <div class="flex flex-col gap-2 p-3 h-full">
      <div class="flex-none flex flex-col gap-2">
        <Button icon="pi pi-bars" @click="uiStore.toggleMenuSidebar()" :class="{
          '!bg-white !text-primary-400 !hover:bg-white': isMenuSidebarPinned
        }" />
      </div>
      <div class="flex-1 flex flex-col gap-2">
        <RouterLink to="/personal" custom v-slot="{ navigate }">
          <Button text rounded class="text-white" icon="pi pi-home" size="large" @click="navigate" />
        </RouterLink>
        <Button text rounded class="text-white" icon="pi pi-search" size="large" @click="openPageDialog" />
      </div>
      <div class="flex-none flex flex-col gap-2">
        <Button text rounded class="text-white" icon="pi pi-user" size="large" @click="openPrincipalDialog" />
        <Button icon="pi pi-sign-out" aria-label="Sign Out" @click="authStore.doLogout()" />
      </div>
    </div>
  </div>
</template>
