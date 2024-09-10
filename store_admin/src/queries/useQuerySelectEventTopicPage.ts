
import { useAuthStore } from "../store/authStore";
import { useAxiosAutoRequest } from '../hooks/useAxiosAutoRequest';
import { selectEventTopicPageRequest } from '../requests/selectEventTopicPageRequest'

export type QuerySelectEventTopicPageProps = {
  // yet nothing
}

export type QuerySelectEventTopicPageFilter = {
  // yet nothing
}

export type QuerySelectEventTopicPageSorter = {
  name?: 'asc' | 'desc',
  description?: 'asc' | 'desc',
}

export function useQuerySelectEventTopicPage(
  // @ts-ignore
  props: QuerySelectEventTopicPageProps,
  // @ts-ignore
  sort?: QuerySelectEventTopicPageSorter,
  // @ts-ignore
  filter?: QuerySelectEventTopicPageFilter
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

    return selectEventTopicPageRequest({
      token,
      query,
      payload,
    })
  })
}
