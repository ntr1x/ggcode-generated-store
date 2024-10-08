<script setup lang="ts">
import { inject, reactive } from 'vue';
import dedent from 'dedent';
import Dialog from 'primevue/dialog';
import Button from 'primevue/button';
import InputText from 'primevue/inputtext';
import { useToast } from 'primevue/usetoast';
import { useAuthStore } from '../../store/authStore';
import type { ModalContext } from '../../store/modalStore';
import SelectDispatchType from '../controls/SelectDispatchType.vue';
import SearchCustomerId from '../controls/SearchCustomerId.vue';
import JsonEditor from '../editors/JsonEditor.vue';
import { createDispatchRequest } from '../../requests/createDispatchRequest';

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

export type DialogProps = {
  // TODO: Implement
}

export type DialogHandlers = {
  success: (data: any) => void
  failure: (error: any) => void
}

defineProps<DialogProps>()

const emit = defineEmits<{
  (e: 'success', data: any): void
  (e: 'failure', error: any): void
}>()

async function doSubmit() {
  const token = await authStore.requireToken()

  const payload = {
    customerId: form.customerId,
    sessionId: form.sessionId,
    subscriptionId: null,
    typeId: form.dispatchType,
    statusId: 1,
    createdAt: new Date().toISOString(),
    updatedAt: null,
    payload: JSON.parse(form.payload)
  }

  const data = await createDispatchRequest({
    token,
    payload
  })

  return data
}

async function handleSubmit() {
  try {
    const data = await doSubmit()
    context.doClose()
    toast.add({
      severity: 'success',
      summary: 'Success',
      detail: 'Submitted',
      life: 3000
    })
    emit('success', data)
  } catch (e) {
    toast.add({
      severity: 'error',
      summary: 'Error',
      detail: e,
      life: 3000
    })
    emit('failure', e)
  }
}

async function handleClose() {
  context.doClose()
}

</script>
<template>
  <Dialog :visible="true" @update:visible="handleClose" modal maximizable header="Dispatch Message" class="w-full sm:max-w-[35rem]">
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
