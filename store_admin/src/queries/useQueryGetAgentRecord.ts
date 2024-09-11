
import { useAuthStore } from "../store/authStore";
import { useAxiosAutoRequest } from '../hooks/useAxiosAutoRequest';
import { getAgentRequest } from '../requests/getAgentRequest'

export type QueryGetAgentRecordProps = {
  agentId?: string
}

export type QueryGetAgentRecordFilter = {
  // yet nothing
}

export type QueryGetAgentRecordSorter = {
  // yet nothing
}

export function useQueryGetAgentRecord(
  // @ts-ignore
  props: QueryGetAgentRecordProps,
  // @ts-ignore
  sort?: QueryGetAgentRecordSorter,
  // @ts-ignore
  filter?: QueryGetAgentRecordFilter
) {
  const authStore = useAuthStore()

  return useAxiosAutoRequest<any>(async () => {
    const token = await authStore.requireToken()

    return getAgentRequest({
      token,
      agentId: props.agentId!,
    })
  })
}
