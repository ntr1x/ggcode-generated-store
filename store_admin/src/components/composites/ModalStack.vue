<script setup lang="ts">
import { storeToRefs } from 'pinia';
import { ModalItem, useModalStore } from '../../store/modalStore';
import ModalContextProvider from './ModalContextProvider.vue';

const modalStore = useModalStore()
const { modals } = storeToRefs(modalStore)
</script>

<template>
  <Teleport to="body">
    <template v-for="modal in modals" :key="modal.key">
      <ModalContextProvider :modal="modal as ModalItem">
        <component :is="modal.component" v-on="modal.handlers || {}" v-bind="modal.props || {}" />
      </ModalContextProvider>
    </template>
  </Teleport>
</template>
