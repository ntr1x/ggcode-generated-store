import { shallowRef } from 'vue';
import { useModalStore } from '../store/modalStore';
import DialogNetworkCreate, { type DialogProps, type DialogHandlers } from '../components/dialogs/DialogNetworkCreate.vue'

export function useActionCreateNetwork() {
  const modalStore = useModalStore()

  function execute(props?: DialogProps, handlers?: DialogHandlers) {
    modalStore.openModal(() => ({
      component: shallowRef(DialogNetworkCreate),
      props,
      handlers,
    }))
  }

  return { execute }
}
