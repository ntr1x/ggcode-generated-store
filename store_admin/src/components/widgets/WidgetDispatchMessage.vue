<script setup lang="ts">
import { shallowRef, inject } from 'vue'
import Button from 'primevue/button'
import Panel from 'primevue/panel'
import { useModalStore } from '../../store/modalStore'
import DispatchMessageDialog from '../dialogs/custom/DispatchMessageDialog.vue'
import { eventsRemote } from '../../remotes/eventsRemote'
import { type PrincipalResponse } from '../../store/profileStore'
import { useAuthStore } from '../../store/authStore'
import { Ref } from 'vue'

const modalStore = useModalStore()
const authStore = useAuthStore()

const principal = inject<Ref<PrincipalResponse | null>>('principal')!

function handleOpenModal() {
  modalStore.openModal(() => ({
    component: shallowRef(DispatchMessageDialog),
  }))
}

async function handleTest() {
  const token = await authStore.requireToken()

  const data = {
    customerId: principal.value?.subject,
    sessionId: null,
    subscriptionId: null,
    typeId: 1,
    statusId: 1,
    createdAt: new Date().toISOString(),
    updatedAt: null,
    payload: {
      title: "Test",
      detail: "How are you doing?",
    }
  }

  await eventsRemote.post('/system/public_dispatch', data, {
    headers: {
      Authorization: `Bearer ${token}`
    }
  })
}

</script>

<template>
  <Panel
    header="Dispatch Message"
    pt:header:class="flex items-center justify-between text-surface-700 dark:text-surface-0/80 bg-surface-50 dark:bg-surface-900 border-b border-surface-200 dark:border-surface-700 rounded-tr-lg p-5"
    pt:content:class="p-5 border-b border-surface-200 dark:border-surface-700 bg-surface-0 dark:bg-surface-900 text-surface-700 dark:text-surface-0/80"
  >
    <div class="mb-2">
      <p class="m-0">
        Dispatch notification to the registered user.
      </p>
    </div>
    <div class="flex gap-2">
      <Button size="small" label="Send" @click="handleOpenModal" />
      <Button size="small" label="Test" @click="handleTest"/>
    </div>
  </Panel>
</template>
