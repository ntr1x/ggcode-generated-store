<script setup lang="ts">
import Button from 'primevue/button';
import InputText from 'primevue/inputtext';
import Password from 'primevue/password';
import { ref } from 'vue';
import { RouterLink, useRouter } from 'vue-router';
import { useAuthStore } from '../../store/authStore';
import Message from 'primevue/message';

const router = useRouter()
const authStore = useAuthStore()

const isFailed = ref<boolean>(false)

const username = ref<string | undefined>()
const password = ref<string | undefined>()

const handleSubmit = async () => {
  if (username.value != null && password.value != null) {
    try {
      await authStore.doSignIn({
        username: username.value!,
        password: password.value!
      })
      router.push('/personal')
    } catch (e) {
      isFailed.value = true
    }
  }
}

const handleAuth = async (provider?: string, state?: string) => {
  await authStore.doAuth(provider, state)
}

const handleDisposeError = () => {
  isFailed.value = false
}

</script>

<template>
  <div class="grid grid-cols-1 w-full md:grid-cols-2 md:w-[40rem] overflow-hidden shadow-sm bg-white">
    <div class="p-6 md:p-9">
      <form @submit.prevent="handleSubmit">
        <h1 class="text-3xl text-center mb-4">Sign In</h1>
        <div class="grid gap-3 grid-cols-1">
          <InputText
            type="text"
            v-model="username"
            required
            placeholder="Username"
            class="w-full"
          />
          <Password
            v-model="password"
            :toggleMask="true"
            :feedback="false"
            required
            placeholder="Password"
            class="w-full"
          />
          <Message v-if="isFailed" severity="error" @close="handleDisposeError">Invalid username or password</Message>
        </div>
        <div class="grid gap-3 grid-cols-1 mt-2">
          <Button type="submit" label="Sign In" class="w-full" />
          <div class="text-center text-slate-500">or sign in with</div>
          <div class="flex gap-1 justify-center">
            <Button
              icon="pi pi-google"
              severity="secondary"
              rounded
              outlined
              aria-label="Google"
              class="w-[2rem] h-[2rem]"
              @click="() => handleAuth('google', '/personal')"
            />
            <Button
              icon="pi pi-user"
              severity="secondary"
              rounded
              outlined
              aria-label="Credentials"
              class="w-[2rem] h-[2rem]"
              @click="() => handleAuth(undefined, '/personal')"
            />
          </div>
        </div>
      </form>
    </div>
    <div class="p-6 md:p-9 flex text-white dark:text-surface-900 bg-primary-500 dark:bg-primary-400">
      <div class="m-auto grid grid-cols-1 gap-4 text-center">
        <div>
          <h3 class="text-xl font-bold">Welcome back!</h3>
        </div>
        <div>
          <div class="text-center">Do not have an account yet?</div>
          <RouterLink to="/sign-up" custom v-slot="{ navigate }">
            <Button @click="navigate" label="Sign Up" text class="text-white w-full" rounded />
          </RouterLink>
        </div>
        <div>
          <div class="text-center">Forgot your password?</div>
          <RouterLink to="/recover" custom v-slot="{ navigate }">
            <Button @click="navigate" label="Recover" text class="text-white w-full" rounded />
          </RouterLink>
        </div>
      </div>
    </div>
  </div>
</template>
