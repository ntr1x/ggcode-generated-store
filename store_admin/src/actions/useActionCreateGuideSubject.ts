import { shallowRef } from 'vue';
import { useModalStore } from '../store/modalStore';
import DialogGuideSubjectCreate, { type DialogProps, type DialogHandlers } from '../components/dialogs/DialogGuideSubjectCreate.vue'

export function useActionCreateGuideSubject() {
  const modalStore = useModalStore()

  function execute(props?: DialogProps, handlers?: DialogHandlers) {
    modalStore.openModal(() => ({
      component: shallowRef(DialogGuideSubjectCreate),
      props,
      handlers,
    }))
  }

  return { execute }
}
