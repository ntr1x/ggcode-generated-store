<script setup lang="ts">
import { storeToRefs } from "pinia";
import MainMenu from "../composites/menu/MainMenu.vue"
import { useUiStore } from "../../store/uiStore";

const uiStore = useUiStore()
const { isMenuSidebarPinned } = storeToRefs(uiStore)

</script>

<template>
  <div class="overflow-hidden border border-1" :class="{
    'w-0': !isMenuSidebarPinned,
  }">
    <div class="h-full">
      <MainMenu />
    </div>
  </div>
</template>
