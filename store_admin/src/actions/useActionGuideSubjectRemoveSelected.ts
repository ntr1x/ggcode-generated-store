import { EventEmitter } from 'events';
import { reactive } from "vue";
import { useAuthStore } from "../store/authStore";
import { removeGuideSubjectArrayRequest } from '../requests/removeGuideSubjectArrayRequest';
import { StructurePublicGuideSubjectRecord } from '../structures/StructurePublicGuideSubjectRecord';

export type ActionGuideSubjectRemoveSelectedProps = {
  items: StructurePublicGuideSubjectRecord[]
}

export function useActionGuideSubjectRemoveSelected() {
  const authStore = useAuthStore()

  const emitter = new EventEmitter()

  const state = reactive({
    isLoading: false,
    isLoaded: false,
    isFailed: false,
  })

  const execute = async (props: ActionGuideSubjectRemoveSelectedProps) => {
    try {
      Object.assign(state, {
        isLoading: true,
        isFailed: false
      })
      
      const token = await authStore.requireToken()
      
      const { data } = await removeGuideSubjectArrayRequest({
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
