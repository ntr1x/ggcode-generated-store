import { shallowRef } from 'vue';
import { useModalStore } from '../store/modalStore';
import DialogDispatchMessage, { type DialogProps } from '../components/dialogs/DialogDispatchMessage.vue'

export function useActionDispatchMessage() {
  const modalStore = useModalStore()

  function execute(props?: DialogProps) {
    modalStore.openModal(() => ({
      component: shallowRef(DialogDispatchMessage),
      props,
    }))
  }

  return { execute }
}
