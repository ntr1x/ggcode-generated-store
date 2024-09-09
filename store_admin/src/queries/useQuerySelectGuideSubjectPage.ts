import { useAuthStore } from "../store/authStore";
import { useAxiosAutoRequest } from '../hooks/useAxiosAutoRequest';
import { structureRemote } from '../remotes/structureRemote'
import { StructurePublicGuideSubjectPage } from '../structures/StructurePublicGuideSubjectPage'

export type QuerySelectGuideSubjectPageProps = {
  // yet nothing
}

export type QuerySelectGuideSubjectPageFilter = {
  // yet nothing
}

export type QuerySelectGuideSubjectPageSorter = {
  id?: 'asc' | 'desc',
  name?: 'asc' | 'desc',
  position?: 'asc' | 'desc',
  createdAt?: 'asc' | 'desc',
  updatedAt?: 'asc' | 'desc',
}

const mapping: Record<string, any> = {
  // yet nothing
}

export function useQuerySelectGuideSubjectPage(
  // @ts-ignore
  props: QuerySelectGuideSubjectPageProps,
  sort?: QuerySelectGuideSubjectPageSorter,
  filter?: QuerySelectGuideSubjectPageFilter
) {
  const authStore = useAuthStore()

  return useAxiosAutoRequest<StructurePublicGuideSubjectPage>(structureRemote, async () => {
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

    const query: Record<string, any> = {"size":50,"sort":"position,asc"}
    if (sortArray.length > 0) {
      query.sort = sortArray
    }

    return {
      method: 'POST',
      url: `/system/public_guide_subject/select`,
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
