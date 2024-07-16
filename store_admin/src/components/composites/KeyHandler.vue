<script setup lang="ts">
import { useMagicKeys, whenever } from '@vueuse/core';
import { useModalStore } from '../../store/modalStore';
import { shallowRef } from 'vue';
import PlatformDialogPrincipal from '../dialogs/PlatformDialogPrincipal.vue';
import { inject } from 'vue';
import type { PrincipalResponse } from '../../store/profileStore';
import PlatformDialogPage from '../dialogs/PlatformDialogPage.vue';
import { useRouter } from 'vue-router';
import { useAuthStore } from '../../store/authStore';
import { useUiStore } from '../../store/uiStore';

const router = useRouter()
const modalStore = useModalStore()
const authStore = useAuthStore()
const uiStore = useUiStore()

const keys = useMagicKeys()
const altP = keys['Alt+P']
const altS = keys['Alt+S']
const altH = keys['Alt+H']
const altL = keys['Alt+L']
const altT = keys['Alt+T']

const principal = inject<PrincipalResponse>('principal')!

whenever(altP, () => {
  modalStore.openModal(() => ({
    component: shallowRef(PlatformDialogPrincipal),
    props: {
      principal
    }
  }))
})

whenever(altS, () => {
  modalStore.openModal(() => ({
    component: shallowRef(PlatformDialogPage),
    props: {
      principal
    }
  }))
})

whenever(altH, () => {
  router.push('/admin')
})

whenever(altL, () => {
  authStore.doLogout()
})

whenever(altT, () => {
  uiStore.toggleMenuSidebar()
})
</script>

<template>
</template>