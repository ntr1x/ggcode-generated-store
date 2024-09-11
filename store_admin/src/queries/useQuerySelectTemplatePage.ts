
import { useAuthStore } from "../store/authStore";
import { useAxiosAutoRequest } from '../hooks/useAxiosAutoRequest';
import { selectTemplatePageRequest } from '../requests/selectTemplatePageRequest'

export type QuerySelectTemplatePageProps = {
  // yet nothing
}

export type QuerySelectTemplatePageFilter = {
  typeId?: string,
  shapeId?: string,
}

export type QuerySelectTemplatePageSorter = {
  id?: 'asc' | 'desc',
  name?: 'asc' | 'desc',
  typeId?: 'asc' | 'desc',
  shapeId?: 'asc' | 'desc',
}
const mapping: Record<string, any> = {
  typeId: 'typeId',
  shapeId: 'shapeId',
}

export function useQuerySelectTemplatePage(
  // @ts-ignore
  props: QuerySelectTemplatePageProps,
  // @ts-ignore
  sort?: QuerySelectTemplatePageSorter,
  // @ts-ignore
  filter?: QuerySelectTemplatePageFilter
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

    const query: Record<string, any> = {"size":50,"sort":"id,desc"}
    if (sortArray.length > 0) {
      query.sort = sortArray
    }

    return selectTemplatePageRequest({
      token,
      query,
      payload,
    })
  })
}
