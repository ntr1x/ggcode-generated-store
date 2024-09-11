import { EventEmitter } from 'events';
import { reactive } from "vue";
import { useAuthStore } from "../store/authStore";
import { removeGuideArrayRequest } from '../requests/removeGuideArrayRequest';
import { StructurePublicGuideRecord } from '../structures/StructurePublicGuideRecord';

export type ActionGuideRemoveSelectedProps = {
  items: StructurePublicGuideRecord[]
}

export function useActionGuideRemoveSelected() {
  const authStore = useAuthStore()

  const emitter = new EventEmitter()

  const state = reactive({
    isLoading: false,
    isLoaded: false,
    isFailed: false,
  })

  const execute = async (props: ActionGuideRemoveSelectedProps) => {
    try {
      Object.assign(state, {
        isLoading: true,
        isFailed: false
      })
      
      const token = await authStore.requireToken()
      
      const { data } = await removeGuideArrayRequest({
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
