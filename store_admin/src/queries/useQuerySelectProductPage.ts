
import { useAuthStore } from "../store/authStore";
import { useAxiosAutoRequest } from '../hooks/useAxiosAutoRequest';
import { selectProductPageRequest } from '../requests/selectProductPageRequest'
import { StructurePublicProductPage } from '../structures/StructurePublicProductPage'

export type QuerySelectProductPageProps = {
  // yet nothing
}

export type QuerySelectProductPageFilter = {
  categoryId?: string,
}

export type QuerySelectProductPageSorter = {
  id?: 'asc' | 'desc',
  name?: 'asc' | 'desc',
}
const mapping: Record<string, any> = {
  categoryId: 'categoryId',
}

export function useQuerySelectProductPage(
  // @ts-ignore
  props: QuerySelectProductPageProps,
  // @ts-ignore
  sort?: QuerySelectProductPageSorter,
  // @ts-ignore
  filter?: QuerySelectProductPageFilter
) {
  const authStore = useAuthStore()

  return useAxiosAutoRequest<StructurePublicProductPage>(async () => {
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

    const query: Record<string, any> = {"size":50,"sort":"name,asc"}
    if (sortArray.length > 0) {
      query.sort = sortArray
    }

    return selectProductPageRequest({
      token,
      query,
      payload,
    })
  })
}
