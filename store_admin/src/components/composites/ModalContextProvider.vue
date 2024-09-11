<template>
  <slot v-bind="modalContext"></slot>
</template>

<script lang="ts" setup>
import { provide } from 'vue';
import { ModalItem, ModalContext, useModalStore } from '../../store/modalStore'

const props = defineProps<{
  modal: ModalItem
}>()

const modalStore = useModalStore()

const modalContext: ModalContext = {
  modal: props.modal,
  doClose: () => {
    modalStore.closeModal({ key: props.modal.key })
  }
}

provide('modalContext', modalContext)
</script>
