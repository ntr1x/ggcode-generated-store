<script setup lang="ts">
import { RouterView } from 'vue-router';
import MainToolbar from '../partials/MainToolbar.vue';
import PrincipalProvider from '../providers/PrincipalProvider.vue';
import KeyHandler from '../composites/KeyHandler.vue';
import MainReference from '../partials/MainReference.vue';
import SocketConnection from '../providers/SocketConnection.vue';
import SocketSubscription from '../providers/SocketSubscription.vue';

</script>

<template>
  <PrincipalProvider>
    <SocketConnection name="events" uri="ws://api.local.example.com/api/assembly_web/ws" />
    <SocketSubscription
      socket-name="events"
      :selectors='[{"headers":{"channel":{"type":"EQUAL","value":"public_dispatch"}}}]'
    />
    <div class="flex h-full">
      <KeyHandler />
      <div class="grow flex flex-row relative overflow-hidden">
        <MainToolbar class="flex-none" />
        <MainReference class="flex-none" />
        <div class="flex-1 overflow-auto flex flex-col">
          <RouterView />
        </div>
      </div>
    </div>
  </PrincipalProvider>
</template>
