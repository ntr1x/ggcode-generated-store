<script setup lang="ts">
import { inject, reactive, ref, onMounted } from 'vue';
import Dialog from 'primevue/dialog';
import Button from 'primevue/button';
import InputText from 'primevue/inputtext';
import { useToast } from 'primevue/usetoast';
import type { ModalContext } from '../../store/modalStore';
import { useAuthStore } from '../../store/authStore';
import { updateProductRequest } from '../../requests/updateProductRequest';
import { StructurePublicProductRecord } from '../../structures/StructurePublicProductRecord';
import SelectCategoryId from '../controls/SelectCategoryId.vue';
import EditorJSProductDescription from '../controls/EditorJSProductDescription.vue';

export type DialogProductUpdateProps = {
  product: StructurePublicProductRecord
}

export type DialogProductUpdateHandlers = {
  success?: (data: any) => void
  failure?: (error: any) => void
}

const props = defineProps<DialogProductUpdateProps>()

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
  description?: string
  price?: string
  ean13?: string
  categoryId?: string
}>({
  name: props.product.name,
  description: props.product.description,
  price: props.product.price,
  ean13: props.product.ean13,
  categoryId: props.product.categoryId,
})

const maximized = ref(false)

async function doSubmit() {

  const token = await authStore.requireToken()
  
  const data = await updateProductRequest({
    payload: form,
    productId: props.product.id,
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
    header="Product Update"
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
          <label class="block text-sm mb-1">Description:</label>
          <EditorJSProductDescription v-model="form.description" class="w-full" />
        </div>
        <div>
          <label class="block text-sm mb-1">Price:</label>
          <InputText v-model="form.price" class="w-full" />
        </div>
        <div>
          <label class="block text-sm mb-1">Ean13:</label>
          <InputText v-model="form.ean13" class="w-full" />
        </div>
        <div>
          <label class="block text-sm mb-1">Category Id:</label>
          <SelectCategoryId v-model="form.categoryId" class="w-full" />
        </div>
      </div>
      <div class="flex justify-end gap-2">
        <Button type="submit" label="Send"></Button>
        <Button type="button" label="Close" severity="secondary" @click="handleClose"></Button>
      </div>
    </form>
  </Dialog>
</template>
