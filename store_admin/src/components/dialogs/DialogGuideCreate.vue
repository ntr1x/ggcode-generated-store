<script setup lang="ts">
import { inject, reactive } from 'vue';
import Dialog from 'primevue/dialog';
import Button from 'primevue/button';
import InputText from 'primevue/inputtext';
import InputNumber from 'primevue/inputnumber';
import { useToast } from 'primevue/usetoast';
import type { ModalContext } from '../../store/modalStore';
import { useAuthStore } from '../../store/authStore';
import { structureRemote } from '../../remotes/structureRemote'
import SelectGuideSubject from '../controls/SelectGuideSubject.vue';
import PlatformUploadS3 from '../controls/platform/PlatformUploadS3.vue';

const authStore = useAuthStore()

const toast = useToast();

const context = inject<ModalContext>('modalContext')!

const form = reactive<{
  name?: string
  link?: string
  attachment?: string
  position?: number
  subjectId?: string
}>({
})

export type DialogProps = {
  // TODO: Implement
}

export type DialogHandlers = {
  success?: (data: any) => void
  failure?: (error: any) => void
}

defineProps<DialogProps>()

const emit = defineEmits<{
  (e: 'success', data: any): void
  (e: 'failure', error: any): void
}>()

async function doSubmit() {

  const token = await authStore.requireToken()

  const payload = {
    ...form
  }

  const { data } = await structureRemote.request({
    method: 'POST',
    url: '/system/public_guide',
    data: Object.assign({}, payload),
    params: {},
    headers: {
      Authorization: `Bearer ${token}`
    }
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
  <Dialog :visible="true" @update:visible="handleClose" modal maximizable header="Guide Create" class="w-full sm:max-w-[35rem]">
    <form @submit.prevent="handleSubmit">
      <div class="mb-2 grid grid-cols-1 gap-3">
        <div>
          <label class="block text-sm mb-1">Name:</label>
          <InputText v-model="form.name" class="w-full" />
        </div>
        <div>
          <label class="block text-sm mb-1">Link:</label>
          <InputText v-model="form.link" class="w-full" />
        </div>
        <div>
          <label class="block text-sm mb-1">Attachment:</label>
          <PlatformUploadS3 bucket="test-image" v-model="form.attachment" class="w-full"/>
        </div>
        <div>
          <label class="block text-sm mb-1">Position:</label>
          <InputNumber v-model="form.position" class="w-full" />
        </div>
        <div>
          <label class="block text-sm mb-1">Subject Id:</label>
          <SelectGuideSubject v-model="form.subjectId" class="w-full" />
        </div>
      </div>
      <div class="flex justify-end gap-2">
        <Button type="submit" label="Send"></Button>
        <Button type="button" label="Close" severity="secondary" @click="handleClose"></Button>
      </div>
    </form>
  </Dialog>
</template>
