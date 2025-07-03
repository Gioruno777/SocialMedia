<template>
  <div class="min-h-screen bg-black text-gray-800">
    <NavBar />
    <main class="max-w-4xl mx-auto bg-black">
      <router-view />
    </main>
  </div>
</template>

<script setup lang="ts">
  import NavBar from '@/components/NavBar.vue'
  import { onMounted } from 'vue'
  import { useAuthStore } from '@/api/auth'

  const auth = useAuthStore()

  onMounted(() => {
    auth.checkToken()
  })
</script>
