import { useAuthStore } from "../store/authStore";
import { useAxiosAutoRequest } from '../hooks/useAxiosAutoRequest';
import { eventsRemote } from '../remotes/eventsRemote'

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
  sort?: QuerySelectTemplatePageSorter,
  filter?: QuerySelectTemplatePageFilter
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

    const query: Record<string, any> = {"size":50,"sort":"id,desc"}
    if (sortArray.length > 0) {
      query.sort = sortArray
    }

    return {
      method: 'POST',
      url: `/system/public_template/select`,
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
