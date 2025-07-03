<template>
  <nav class="bg-white p-4 shadow">
    <div class="max-w-4xl mx-auto flex justify-between">
      <router-link to="/">首頁</router-link>

      <div class="space-x-4">
        <template v-if="auth.isLoading">
          <span>載入中...</span>
        </template>

        <template v-else-if="auth.isLoggedIn">
          <button @click="logout">登出</button>
        </template>

        <template v-else>
          <router-link to="/login">登入</router-link>
          <router-link to="/register">註冊</router-link>
        </template>
      </div>
    </div>
  </nav>
</template>

<script setup lang="ts">
  import { useAuthStore } from '@/api/auth'
  import router from '@/router'

  const auth = useAuthStore()

  const logout = async () => {
    auth.logout()
    router.push('/')
  }
</script>
