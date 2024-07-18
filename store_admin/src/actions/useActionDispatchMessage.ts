import { shallowRef } from 'vue';
import { useModalStore } from '../store/modalStore';
import DialogDispatchMessage, { type DialogProps, type DialogHandlers } from '../components/dialogs/DialogDispatchMessage.vue'

export function useActionDispatchMessage() {
  const modalStore = useModalStore()

  function execute(props?: DialogProps, handlers?: DialogHandlers) {
    modalStore.openModal(() => ({
      component: shallowRef(DialogDispatchMessage),
      props,
      handlers,
    }))
  }

  return { execute }
}
