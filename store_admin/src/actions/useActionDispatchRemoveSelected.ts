import { EventEmitter } from 'events';
import { reactive } from "vue";
import { useAuthStore } from "../store/authStore";
import { removeDispatchArrayRequest } from '../requests/removeDispatchArrayRequest';
import { StructurePublicDispatchRecord } from '../structures/StructurePublicDispatchRecord';

export type ActionDispatchRemoveSelectedProps = {
  items: StructurePublicDispatchRecord[]
}

export function useActionDispatchRemoveSelected() {
  const authStore = useAuthStore()

  const emitter = new EventEmitter()

  const state = reactive({
    isLoading: false,
    isLoaded: false,
    isFailed: false,
  })

  const execute = async (props: ActionDispatchRemoveSelectedProps) => {
    try {
      Object.assign(state, {
        isLoading: true,
        isFailed: false
      })
      
      const token = await authStore.requireToken()
      
      const { data } = await removeDispatchArrayRequest({
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
