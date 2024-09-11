
import { useAuthStore } from "../store/authStore";
import { useAxiosAutoRequest } from '../hooks/useAxiosAutoRequest';
import { getGuideSubjectRequest } from '../requests/getGuideSubjectRequest'

export type QueryGetGuideSubjectRecordProps = {
  subjectId?: string
}

export type QueryGetGuideSubjectRecordFilter = {
  // yet nothing
}

export type QueryGetGuideSubjectRecordSorter = {
  // yet nothing
}

export function useQueryGetGuideSubjectRecord(
  // @ts-ignore
  props: QueryGetGuideSubjectRecordProps,
  // @ts-ignore
  sort?: QueryGetGuideSubjectRecordSorter,
  // @ts-ignore
  filter?: QueryGetGuideSubjectRecordFilter
) {
  const authStore = useAuthStore()

  return useAxiosAutoRequest<any>(async () => {
    const token = await authStore.requireToken()

    return getGuideSubjectRequest({
      token,
      subjectId: props.subjectId!,
    })
  })
}
