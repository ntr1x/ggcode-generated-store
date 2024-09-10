
import { useAuthStore } from "../store/authStore";
import { useAxiosAutoRequest } from '../hooks/useAxiosAutoRequest';
import { selectOrderPageRequest } from '../requests/selectOrderPageRequest'

export type QuerySelectOrderPageProps = {
  // yet nothing
}

export type QuerySelectOrderPageFilter = {
  customerId?: string,
  orderType?: string,
  orderStatus?: string,
  sourceType?: string,
  regionId?: string,
}

export type QuerySelectOrderPageSorter = {
  orderTypeId?: 'asc' | 'desc',
  orderStatusId?: 'asc' | 'desc',
  id?: 'asc' | 'desc',
  customerId?: 'asc' | 'desc',
  createdAt?: 'asc' | 'desc',
  updatedAt?: 'asc' | 'desc',
}
const mapping: Record<string, any> = {
  customerId: 'customerId',
  orderType: 'orderTypeId',
  orderStatus: 'orderStatusId',
  sourceType: 'sourceTypeId',
  regionId: 'regionId',
}

export function useQuerySelectOrderPage(
  // @ts-ignore
  props: QuerySelectOrderPageProps,
  // @ts-ignore
  sort?: QuerySelectOrderPageSorter,
  // @ts-ignore
  filter?: QuerySelectOrderPageFilter
) {
  const authStore = useAuthStore()

  return useAxiosAutoRequest<any>(async () => {
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

    const query: Record<string, any> = {"size":50,"sort":"id,asc"}
    if (sortArray.length > 0) {
      query.sort = sortArray
    }

    return selectOrderPageRequest({
      token,
      query,
      payload,
    })
  })
}
