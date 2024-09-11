<script setup lang="ts">
import { inject, reactive, ref, onMounted } from 'vue';
import Dialog from 'primevue/dialog';
import Button from 'primevue/button';
import InputText from 'primevue/inputtext';
import ToggleButton from 'primevue/togglebutton';
import { useToast } from 'primevue/usetoast';
import type { ModalContext } from '../../store/modalStore';
import { useAuthStore } from '../../store/authStore';
import { createNetworkRequest } from '../../requests/createNetworkRequest';
import SelectNetworkType from '../controls/SelectNetworkType.vue';
import SelectShopId from '../controls/SelectShopId.vue';

export type DialogNetworkCreateProps = {
  // TODO: Implement
}

export type DialogNetworkCreateHandlers = {
  success?: (data: any) => void
  failure?: (error: any) => void
}

const props = defineProps<DialogNetworkCreateProps>()

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
  typeId?: string
  shopId?: string
  name?: string
  password?: string
  hidden?: boolean
}>({
})

const maximized = ref(false)

async function doSubmit() {

  const token = await authStore.requireToken()
  
  const data = await createNetworkRequest({
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
    header="Network Create"
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
          <label class="block text-sm mb-1">Type Id:</label>
          <SelectNetworkType v-model="form.typeId" class="w-full" />
        </div>
        <div>
          <label class="block text-sm mb-1">Shop Id:</label>
          <SelectShopId v-model="form.shopId" class="w-full" />
        </div>
        <div>
          <label class="block text-sm mb-1">Name:</label>
          <InputText v-model="form.name" class="w-full" />
        </div>
        <div>
          <label class="block text-sm mb-1">Password:</label>
          <InputText v-model="form.password" class="w-full" />
        </div>
        <div>
          <label class="block text-sm mb-1">Hidden:</label>
          <ToggleButton v-model="form.hidden" class="min-w-[8rem]" />
        </div>
      </div>
      <div class="flex justify-end gap-2">
        <Button type="submit" label="Send"></Button>
        <Button type="button" label="Close" severity="secondary" @click="handleClose"></Button>
      </div>
    </form>
  </Dialog>
</template>
