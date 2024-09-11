import { type Ref, type ComputedRef, inject, computed } from "vue"
import { type PrincipalResponse } from '../store/profileStore';

export type SecurityContext = {
  principal: Ref<PrincipalResponse | null>
  hasAnyRole: ComputedRef<(roles: string[]) => boolean>
}

export function useSecurityContext(): SecurityContext {
  const principal = inject<Ref<PrincipalResponse | null>>('principal')!

  const hasAnyRole = computed(() => (roles: string[]) => {
    if (roles.length == 0) {
      return true
    }
    if (principal.value == null) {
      return false
    }
    const authorities = principal?.value?.authorities || []
    return authorities.filter((value: string) => roles.includes(value)).length > 0
  })

  return {
    principal,
    hasAnyRole,
  }
}
