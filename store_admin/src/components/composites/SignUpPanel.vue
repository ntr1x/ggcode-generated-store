<script setup lang="ts">
import Button from 'primevue/button';
import InputText from 'primevue/inputtext';
import Password from 'primevue/password';
import { ref } from 'vue';
import { RouterLink } from 'vue-router';

const name = ref<string | undefined>()
const email = ref<string | undefined>()
const password = ref<string | undefined>()
const confirmation = ref<string | undefined>()
</script>

<template>
  <div class="grid grid-cols-2 w-[40rem] overflow-hidden shadow-sm bg-white">
    <div class="p-9 flex text-white dark:text-surface-900 bg-blue-500 dark:bg-blue-400">
      <div class="m-auto grid grid-cols-1 gap-4 text-center">
        <div>
          <h3 class="text-xl font-bold">Join us!</h3>
        </div>
        <div>
          <div class="text-center">Already have an account?</div>
          <RouterLink to="/sign-in" custom v-slot="{ navigate }">
            <Button @click="navigate" label="Sign In" text class="w-full text-white" rounded severity="info" />
          </RouterLink>
        </div>
      </div>
    </div>
    <div class="p-9">
      <h1 class="text-3xl text-center mb-4">Sign Up</h1>
      <div class="grid gap-3 grid-cols-1">
        <InputText
          type="text"
          v-model="name"
          placeholder="Name"
          class="w-full"
        />
        <InputText
          type="text"
          v-model="email"
          placeholder="Email"
          class="w-full"
        />
        <Password
          v-model="password"
          :toggleMask="true"
          :feedback="false"
          placeholder="Password"
          class="w-full"
        />
        <Password
          v-model="confirmation"
          :toggleMask="true"
          :feedback="false"
          placeholder="Confirmation"
          class="w-full"
        />
      </div>
      <div class="grid gap-3 grid-cols-1 mt-2">
        <Button label="Sign Up" class="w-full" severity="info" />
        <div class="text-center text-slate-500">or sign in with</div>
        <div class="flex gap-1 justify-center">
          <Button icon="pi pi-google" severity="secondary" rounded outlined aria-label="Google" class="w-[2rem] h-[2rem]" />
          <RouterLink to="/admin" custom v-slot="{ navigate }">
            <Button @click="navigate" icon="pi pi-user" severity="secondary" rounded outlined aria-label="Credentials" class="w-[2rem] h-[2rem]" />
          </RouterLink>
        </div>
      </div>
    </div>
  </div>
</template>
