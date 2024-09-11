
import { useAuthStore } from "../store/authStore";
import { useAxiosAutoRequest } from '../hooks/useAxiosAutoRequest';
import { selectTemplateShapePageRequest } from '../requests/selectTemplateShapePageRequest'

export type QuerySelectTemplateShapePageProps = {
  // yet nothing
}

export type QuerySelectTemplateShapePageFilter = {
  // yet nothing
}

export type QuerySelectTemplateShapePageSorter = {
  id?: 'asc' | 'desc',
  name?: 'asc' | 'desc',
  description?: 'asc' | 'desc',
}

export function useQuerySelectTemplateShapePage(
  // @ts-ignore
  props: QuerySelectTemplateShapePageProps,
  // @ts-ignore
  sort?: QuerySelectTemplateShapePageSorter,
  // @ts-ignore
  filter?: QuerySelectTemplateShapePageFilter
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

    return selectTemplateShapePageRequest({
      token,
      query,
      payload,
    })
  })
}
