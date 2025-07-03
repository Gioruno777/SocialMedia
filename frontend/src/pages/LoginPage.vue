<template>
  <div class="flex flex-col items-center justify-center h-screen bg-gray-100">
    <h2 class="text-2xl font-bold mb-4">登入</h2>
    <form class="flex flex-col space-y-4" @submit.prevent="handleSubmit">
      <input
        v-model="phone"
        type="phone"
        placeholder="行動電話"
        class="px-4 py-2 border rounded w-64"
      />
      <input
        v-model="password"
        type="password"
        placeholder="密碼"
        class="px-4 py-2 border rounded w-64"
      />
      <button type="submit" class="bg-blue-500 text-white px-4 py-2 rounded" :disabled="isPending">
        {{ isPending ? '登入中...' : '登入' }}
      </button>
    </form>
  </div>
</template>

<script setup lang="ts">
  import { ref } from 'vue'
  import { useLogin } from '@/api/auth'
  import { useAuthStore } from '@/api/auth'
  import { useRouter } from 'vue-router'
  import { watchEffect } from 'vue'

  const phone = ref('')
  const password = ref('')

  const { login, isPending } = useLogin()
  const auth = useAuthStore()
  const router = useRouter()

  watchEffect(() => {
    if (auth.isLoggedIn) {
      router.push('/')
    }
  })

  const handleSubmit = () => {
    if (!phone.value || !password.value) {
      alert('請輸入帳號或密碼')
      return
    }

    login({
      phone: phone.value,
      password: password.value,
    })
  }
</script>
