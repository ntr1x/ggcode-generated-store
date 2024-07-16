import { useAxiosManualRequest } from "../hooks/useAxiosManualRequest"
import { useAuthStore } from "../store/authStore";
import { eventsRemote } from '../remotes/eventsRemote'

export function useActionDispatchRemoveSelected() {
  const authStore = useAuthStore()

  function execute(data?: any) {
    const request = useAxiosManualRequest(eventsRemote, async () => {
      const token = await authStore.requireToken()
      return {
        method: 'POST',
        url: '/system/public_dispatch/removeAll',
        data: Object.assign([], data),
        params: {},
        headers: {
          Authorization: `Bearer ${token}`
        }
      }
    })
    request.execute()
    return request
  }

  return { execute }
}
