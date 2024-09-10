
import { useAuthStore } from "../store/authStore";
import { useAxiosAutoRequest } from '../hooks/useAxiosAutoRequest';
import { selectNetworkPageRequest } from '../requests/selectNetworkPageRequest'
import { StructurePublicNetworkPage } from '../structures/StructurePublicNetworkPage'

export type QuerySelectNetworkPageProps = {
  // yet nothing
}

export type QuerySelectNetworkPageFilter = {
  typeId?: string,
  shopId?: string,
  hidden?: string,
}

export type QuerySelectNetworkPageSorter = {
  id?: 'asc' | 'desc',
  name?: 'asc' | 'desc',
  typeId?: 'asc' | 'desc',
  shopId?: 'asc' | 'desc',
  hidden?: 'asc' | 'desc',
}
const mapping: Record<string, any> = {
  typeId: 'typeId',
  shopId: 'shopId',
  hidden: 'hidden',
}

export function useQuerySelectNetworkPage(
  // @ts-ignore
  props: QuerySelectNetworkPageProps,
  // @ts-ignore
  sort?: QuerySelectNetworkPageSorter,
  // @ts-ignore
  filter?: QuerySelectNetworkPageFilter
) {
  const authStore = useAuthStore()

  return useAxiosAutoRequest<StructurePublicNetworkPage>(async () => {
    const token = await authStore.requireToken()
    const payload: Record<string, any> = {}
    if (filter != null) {
      for (const [k, v] of Object.entries(filter)) {
        if (v != undefined) {
          payload[mapping[k]] = v
        }
      }
    }
    
    const sortArray: string[] = []
    if (sort != null) {
      for (const [k, v] of Object.entries(sort)) {
        if (v != null) {
          sortArray.push(`${k},${v}`)
        }
      }
    }

    const query: Record<string, any> = {"size":50,"sort":"id,desc"}
    if (sortArray.length > 0) {
      query.sort = sortArray
    }

    return selectNetworkPageRequest({
      token,
      query,
      payload,
    })
  })
}
