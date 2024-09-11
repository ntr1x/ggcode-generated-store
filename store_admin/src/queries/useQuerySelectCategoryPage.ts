
import { useAuthStore } from "../store/authStore";
import { useAxiosAutoRequest } from '../hooks/useAxiosAutoRequest';
import { selectCategoryPageRequest } from '../requests/selectCategoryPageRequest'

export type QuerySelectCategoryPageProps = {
  // yet nothing
}

export type QuerySelectCategoryPageFilter = {
  // yet nothing
}

export type QuerySelectCategoryPageSorter = {
  // yet nothing
}

export function useQuerySelectCategoryPage(
  // @ts-ignore
  props: QuerySelectCategoryPageProps,
  // @ts-ignore
  sort?: QuerySelectCategoryPageSorter,
  // @ts-ignore
  filter?: QuerySelectCategoryPageFilter
) {
  const authStore = useAuthStore()

  return useAxiosAutoRequest<any>(async () => {
    const token = await authStore.requireToken()
    const payload: Record<string, any> = {}
    
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

    return selectCategoryPageRequest({
      token,
      query,
      payload,
    })
  })
}
