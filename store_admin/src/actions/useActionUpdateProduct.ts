import { EventEmitter } from 'events';
import { reactive } from "vue";
import { shallowRef } from 'vue';
import { useModalStore } from '../store/modalStore';
import { useAuthStore } from "../store/authStore";
import { getProductRequest } from '../requests/getProductRequest';
import DialogProductUpdate from '../components/dialogs/DialogProductUpdate.vue';

export type ActionUpdateProductProps = {
  productId: string
}

export function useActionUpdateProduct() {
  const modalStore = useModalStore()
  const authStore = useAuthStore()

  const emitter = new EventEmitter()

  const state = reactive({
    isLoading: false,
    isLoaded: false,
    isFailed: false,
  })

  const execute = async (props: ActionUpdateProductProps) => {
    try {
      Object.assign(state, {
        isLoading: true,
        isFailed: false
      })
      
      const token = await authStore.requireToken()
      
      const context = {
        product: await getProductRequest({
          productId: props.productId,
          token,
        }),
      }
      
      modalStore.openModal(() => ({
        component: shallowRef(DialogProductUpdate),
        props: {
          product: context.product,
        },
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
