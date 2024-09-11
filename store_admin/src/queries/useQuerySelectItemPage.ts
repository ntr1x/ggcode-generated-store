
import { useAuthStore } from "../store/authStore";
import { useAxiosAutoRequest } from '../hooks/useAxiosAutoRequest';
import { selectItemPageRequest } from '../requests/selectItemPageRequest'

export type QuerySelectItemPageProps = {
  // yet nothing
}

export type QuerySelectItemPageFilter = {
  shopId?: string,
}

export type QuerySelectItemPageSorter = {
  // yet nothing
}
const mapping: Record<string, any> = {
  shopId: 'shopId',
}

export function useQuerySelectItemPage(
  // @ts-ignore
  props: QuerySelectItemPageProps,
  // @ts-ignore
  sort?: QuerySelectItemPageSorter,
  // @ts-ignore
  filter?: QuerySelectItemPageFilter
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

    const query: Record<string, any> = {}
    if (sortArray.length > 0) {
      query.sort = sortArray
    }

    return selectItemPageRequest({
      token,
      query,
      payload,
    })
  })
}
