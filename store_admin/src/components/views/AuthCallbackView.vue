<script setup lang="ts">
import { onMounted } from "vue"
import { useAuthStore } from "../../store/authStore"
import { useRoute, useRouter } from "vue-router"

type QueryParams = {
  code: string,
  session_state: string,
  state: string
}

const authStore = useAuthStore()
const route = useRoute()
const router = useRouter()

onMounted(async () => {
  try {
    const { code, session_state, state } = route.query as QueryParams

    await authStore.doCallback({
      code,
      sessionState: session_state,
      state
    })

    router.push(state)
  } catch (e) {
    await authStore.doLogout()
  }
})

</script>

<template>
  <div class="flex h-full">
  </div>
</template>
