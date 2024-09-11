
import { useAuthStore } from "../store/authStore";
import { useAxiosAutoRequest } from '../hooks/useAxiosAutoRequest';
import { selectTemplateTypePageRequest } from '../requests/selectTemplateTypePageRequest'

export type QuerySelectTemplateTypePageProps = {
  // yet nothing
}

export type QuerySelectTemplateTypePageFilter = {
  // yet nothing
}

export type QuerySelectTemplateTypePageSorter = {
  id?: 'asc' | 'desc',
  name?: 'asc' | 'desc',
  description?: 'asc' | 'desc',
}

export function useQuerySelectTemplateTypePage(
  // @ts-ignore
  props: QuerySelectTemplateTypePageProps,
  // @ts-ignore
  sort?: QuerySelectTemplateTypePageSorter,
  // @ts-ignore
  filter?: QuerySelectTemplateTypePageFilter
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

    const query: Record<string, any> = {}
    if (sortArray.length > 0) {
      query.sort = sortArray
    }

    return selectTemplateTypePageRequest({
      token,
      query,
      payload,
    })
  })
}
