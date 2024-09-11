<script setup lang="ts">
import { inject, reactive, ref, onMounted } from 'vue';
import Dialog from 'primevue/dialog';
import Button from 'primevue/button';
import InputText from 'primevue/inputtext';
import InputNumber from 'primevue/inputnumber';
import { useToast } from 'primevue/usetoast';
import type { ModalContext } from '../../store/modalStore';
import { useAuthStore } from '../../store/authStore';
import { createGuideSubjectRequest } from '../../requests/createGuideSubjectRequest';

export type DialogGuideSubjectCreateProps = {
  // TODO: Implement
}

export type DialogGuideSubjectCreateHandlers = {
  success?: (data: any) => void
  failure?: (error: any) => void
}

const props = defineProps<DialogGuideSubjectCreateProps>()

const emit = defineEmits<{
  (e: 'success', data: any): void
  (e: 'failure', error: any): void
}>()

onMounted(() => {
  console.trace(props)
})

const authStore = useAuthStore()

const toast = useToast();

const context = inject<ModalContext>('modalContext')!

const form = reactive<{
  name?: string
  position?: number
}>({
})

const maximized = ref(false)

async function doSubmit() {

  const token = await authStore.requireToken()
  
  const data = await createGuideSubjectRequest({
    payload: form,
    token,
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
  <Dialog
    :visible="true"
    @update:visible="handleClose"
    modal
    maximizable
    header="Guide Subject Create"
    class="w-full"
    :class="{
      'sm:max-w-[35rem]': !maximized
    }"
    @maximize="maximized = true"
    @unmaximize="maximized = false"
  >
    <form @submit.prevent="handleSubmit">
      <div class="mb-2 grid grid-cols-1 gap-3">
        <div>
          <label class="block text-sm mb-1">Name:</label>
          <InputText v-model="form.name" class="w-full" />
        </div>
        <div>
          <label class="block text-sm mb-1">Position:</label>
          <InputNumber v-model="form.position" class="w-full" />
        </div>
      </div>
      <div class="flex justify-end gap-2">
        <Button type="submit" label="Send"></Button>
        <Button type="button" label="Close" severity="secondary" @click="handleClose"></Button>
      </div>
    </form>
  </Dialog>
</template>
