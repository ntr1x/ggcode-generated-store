<script setup lang="ts">
import { reactive, inject } from 'vue';
import dedent from "dedent";
import Dialog from 'primevue/dialog';
import Button from 'primevue/button';
import InputText from 'primevue/inputtext';
import { useToast } from 'primevue/usetoast';
import type { ModalContext } from '../../../store/modalStore';

import SearchCustomerId from '../../controls/SearchCustomerId.vue';
import SelectDispatchType from '../../controls/SelectDispatchType.vue';

import JsonEditor from '../../editors/JsonEditor.vue';
import { useAuthStore } from '../../../store/authStore';
import { eventsRemote } from '../../../remotes/eventsRemote'

const authStore = useAuthStore()
const toast = useToast();

const context = inject<ModalContext>('modalContext')!

const form = reactive<{
  customerId?: string,
  dispatchType?: string,
  sessionId?: string,
  payload: string,
}>({
  customerId: undefined,
  dispatchType: undefined,
  sessionId: undefined,
  payload: dedent`{
    "title": "Test",
    "detail": "How are you doing?"
  }`,
})

async function handleSubmit() {
  try {
    const token = await authStore.requireToken()

    const data = {
      customerId: form.customerId,
      sessionId: form.sessionId,
      subscriptionId: null,
      typeId: form.dispatchType,
      statusId: 1,
      createdAt: new Date().toISOString(),
      updatedAt: null,
      payload: JSON.parse(form.payload)
    }

    await eventsRemote.post('/system/public_dispatch', data, {
      headers: {
        Authorization: `Bearer ${token}`
      }
    })
    context.doClose()
  } catch (e) {
    toast.add({
      severity: 'error',
      summary: 'Error',
      detail: e,
      life: 3000
    })
  }
}

</script>
<template>
  <Dialog :visible="true" @update:visible="context.doClose" modal header="Dispatch Message" :style="{ maxWidth: '35rem' }">
    <form @submit.prevent="handleSubmit">
      <div class="mb-2 grid grid-cols-1 gap-3">
        <div>
          <label class="block text-sm mb-1">Dispatch Type:</label>
          <SelectDispatchType v-model:dispatch-type="form.dispatchType" class="w-[20rem]" />
        </div>
        <div>
          <label class="block text-sm mb-1">Customer:</label>
          <SearchCustomerId v-model:customer-id="form.customerId" class="w-[20rem]" />
        </div>
        <div>
          <label class="block text-sm mb-1">Session Id:</label>
          <InputText v-model="form.sessionId" class="w-full" />
        </div>
        <div>
          <label class="block text-sm mb-1">Payload:</label>
          <JsonEditor v-model="form.payload"/>
        </div>
      </div>
      <div class="flex justify-end gap-2">
        <Button type="submit" label="Send"></Button>
        <Button type="button" label="Close" severity="secondary" @click="context.doClose"></Button>
      </div>
    </form>
  </Dialog>
</template>
