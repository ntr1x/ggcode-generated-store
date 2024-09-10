import { useAuthStore } from "../store/authStore";
import { useAxiosAutoRequest } from '../hooks/useAxiosAutoRequest';
import { eventsRemote } from '../remotes/eventsRemote'

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

const mapping: Record<string, any> = {
  // yet nothing
}

export function useQuerySelectTemplateTypePage(
  // @ts-ignore
  props: QuerySelectTemplateTypePageProps,
  sort?: QuerySelectTemplateTypePageSorter,
  filter?: QuerySelectTemplateTypePageFilter
) {
  const authStore = useAuthStore()

  return useAxiosAutoRequest<any>(eventsRemote, async () => {
    const token = await authStore.requireToken()

    const body: Record<string, any> = {}
    if (filter != null) {
      for (const [k, v] of Object.entries(filter)) {
        if (v != undefined) {
          body[mapping[k]] = v
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

    return {
      method: 'POST',
      url: `/system/public_template_type/select`,
      params: query,
      paramsSerializer: {
        indexes: null
      },
      headers: {
        Authorization: `Bearer ${token}`
      },
      data: body
    }
  })
}