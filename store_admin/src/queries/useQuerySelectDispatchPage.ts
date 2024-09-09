import { useAuthStore } from "../store/authStore";
import { useAxiosAutoRequest } from '../hooks/useAxiosAutoRequest';
import { eventsRemote } from '../remotes/eventsRemote'
import { StructurePublicDispatchPage } from '../structures/StructurePublicDispatchPage'

export type QuerySelectDispatchPageProps = {
  // yet nothing
}

export type QuerySelectDispatchPageFilter = {
  customerId?: string,
  typeId?: string,
  statusId?: string,
}

export type QuerySelectDispatchPageSorter = {
  id?: 'asc' | 'desc',
  typeId?: 'asc' | 'desc',
  statusId?: 'asc' | 'desc',
  createdAt?: 'asc' | 'desc',
  updatedAt?: 'asc' | 'desc',
}

const mapping: Record<string, any> = {
  customerId: 'customerId',
  typeId: 'typeId',
  statusId: 'statusId',
}

export function useQuerySelectDispatchPage(
  // @ts-ignore
  props: QuerySelectDispatchPageProps,
  sort?: QuerySelectDispatchPageSorter,
  filter?: QuerySelectDispatchPageFilter
) {
  const authStore = useAuthStore()

  return useAxiosAutoRequest<StructurePublicDispatchPage>(eventsRemote, async () => {
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

    const query: Record<string, any> = {"size":50,"sort":"createdAt,desc"}
    if (sortArray.length > 0) {
      query.sort = sortArray
    }

    return {
      method: 'POST',
      url: `/system/public_dispatch/select`,
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
