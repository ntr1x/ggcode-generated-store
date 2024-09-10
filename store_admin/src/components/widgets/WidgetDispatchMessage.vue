<script setup lang="ts">
import { shallowRef, inject } from 'vue'
import Button from 'primevue/button'
import Panel from 'primevue/panel'
import { useModalStore } from '../../store/modalStore'
import DialogDispatchMessage from '../dialogs/DialogDispatchMessage.vue'
import { type PrincipalResponse } from '../../store/profileStore'
import { useAuthStore } from '../../store/authStore'
import { Ref } from 'vue'
import { createDispatchRequest } from '../../requests/createDispatchRequest'

const modalStore = useModalStore()
const authStore = useAuthStore()

const principal = inject<Ref<PrincipalResponse | null>>('principal')!

function handleOpenModal() {
  modalStore.openModal(() => ({
    component: shallowRef(DialogDispatchMessage),
  }))
}

async function handleTest() {
  const token = await authStore.requireToken()

  const payload = {
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

  const data = await createDispatchRequest({
    token,
    payload
  })

  return data
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
