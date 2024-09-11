
import { useAuthStore } from "../store/authStore";
import { useAxiosAutoRequest } from '../hooks/useAxiosAutoRequest';
import { getGuideRequest } from '../requests/getGuideRequest'

export type QueryGetGuideRecordProps = {
  guideId?: string
}

export type QueryGetGuideRecordFilter = {
  // yet nothing
}

export type QueryGetGuideRecordSorter = {
  // yet nothing
}

export function useQueryGetGuideRecord(
  // @ts-ignore
  props: QueryGetGuideRecordProps,
  // @ts-ignore
  sort?: QueryGetGuideRecordSorter,
  // @ts-ignore
  filter?: QueryGetGuideRecordFilter
) {
  const authStore = useAuthStore()

  return useAxiosAutoRequest<any>(async () => {
    const token = await authStore.requireToken()

    return getGuideRequest({
      token,
      guideId: props.guideId!,
    })
  })
}
