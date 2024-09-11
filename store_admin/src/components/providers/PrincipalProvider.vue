<script setup lang="ts">
import { provide } from 'vue';
import { onMounted } from 'vue';
import { useAuthStore } from '../../store/authStore';
import { PrincipalResponse, useProfileStore } from '../../store/profileStore';
import { ref } from 'vue';

const authStore = useAuthStore()
const profileStore = useProfileStore()

const principal = ref<PrincipalResponse | null>(null)

provide('principal', principal)

onMounted(async () => {
  try {
    principal.value = await profileStore.fetchMe()
  } catch (e) {
    await authStore.doLogout()
  }
})

</script>

<template>
  <slot v-if="principal != null"></slot>
</template>
