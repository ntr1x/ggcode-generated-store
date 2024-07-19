import { shallowRef } from 'vue';
import { useModalStore } from '../store/modalStore';
import DialogGuideCreate, { type DialogProps, type DialogHandlers } from '../components/dialogs/DialogGuideCreate.vue'

export function useActionCreateGuide() {
  const modalStore = useModalStore()

  function execute(props?: DialogProps, handlers?: DialogHandlers) {
    modalStore.openModal(() => ({
      component: shallowRef(DialogGuideCreate),
      props,
      handlers,
    }))
  }

  return { execute }
}
