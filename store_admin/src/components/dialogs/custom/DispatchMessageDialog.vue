<script setup lang="ts">
import { reactive, inject } from 'vue';
import Dialog from 'primevue/dialog';
import Button from 'primevue/button';
import InputText from 'primevue/inputtext';
import type { ModalContext } from '../../../store/modalStore';

import SearchCustomerId from '../../controls/SearchCustomerId.vue';
import SelectDispatchType from '../../controls/SelectDispatchType.vue';

import JsonEditor from '../../editors/JsonEditor.vue';

const form = reactive<{
  customerId?: string,
  dispatchType?: number,
  sessionId?: string,
  payload?: string,
}>({
  customerId: undefined,
  dispatchType: undefined,
  sessionId: undefined,
  payload: "{}",
})

const context = inject<ModalContext>('modalContext')!

function handleSubmit() {
  console.log(form)
  context.doClose()
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
