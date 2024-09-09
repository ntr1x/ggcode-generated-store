import { EventEmitter } from 'events';
import { reactive } from "vue";
import { useAuthStore } from "../store/authStore";
import { removeProductArrayRequest } from '../requests/removeProductArrayRequest';
import { StructurePublicProductRecord } from '../structures/StructurePublicProductRecord';

export type ActionProductRemoveSelectedProps = {
  items: StructurePublicProductRecord[]
}

export function useActionProductRemoveSelected() {
  const authStore = useAuthStore()

  const emitter = new EventEmitter()

  const state = reactive({
    isLoading: false,
    isLoaded: false,
    isFailed: false,
  })

  const execute = async (props: ActionProductRemoveSelectedProps) => {
    try {
      Object.assign(state, {
        isLoading: true,
        isFailed: false
      })
      
      const token = await authStore.requireToken()
      
      const { data } = await removeProductArrayRequest({
        payload: props.items,
        token,
      })

      emitter.emit('success', data)

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
