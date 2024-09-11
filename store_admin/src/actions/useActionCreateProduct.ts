import { EventEmitter } from 'events';
import { reactive } from "vue";
import { shallowRef } from 'vue';
import { useModalStore } from '../store/modalStore';
import DialogProductCreate from '../components/dialogs/DialogProductCreate.vue';

export function useActionCreateProduct() {
  const modalStore = useModalStore()

  const emitter = new EventEmitter()

  const state = reactive({
    isLoading: false,
    isLoaded: false,
    isFailed: false,
  })

  const execute = async () => {
    try {
      Object.assign(state, {
        isLoading: true,
        isFailed: false
      })
      
      modalStore.openModal(() => ({
        component: shallowRef(DialogProductCreate),
        handlers: {
          success: (data: any) => emitter.emit('success', data),
          failure: (error: any) => emitter.emit('failure', error)
        },
      }))

      Object.assign(state, {
        isLoading: false,
        isFailed: false,
        isLoaded: true,
      })
    } catch (e) {
      Object.assign(state, {
        isLoading: false,
        isFailed: true
      })
      emitter.emit('failure', e)
      throw e
    }
  }

  return { execute, state, emitter }
}
