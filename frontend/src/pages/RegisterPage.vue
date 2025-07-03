<template>
  <div class="flex flex-col items-center justify-center h-screen bg-gray-100">
    <h2 class="text-2xl font-bold mb-4">會員註冊</h2>
    <form class="flex flex-col space-y-4" @submit="handleSubmit">
      <input
        v-model="name"
        type="text"
        placeholder="使用者名稱"
        class="px-4 py-2 border rounded w-64"
      />
      <input
        v-model="phone"
        type="text"
        placeholder="行動電話"
        class="px-4 py-2 border rounded w-64"
      />
      <input
        v-model="email"
        type="email"
        placeholder="Email"
        class="px-4 py-2 border rounded w-64"
      />
      <input
        v-model="password"
        type="password"
        placeholder="密碼"
        class="px-4 py-2 border rounded w-64"
      />
      <button type="submit" class="bg-blue-500 text-white px-4 py-2 rounded">註冊</button>
    </form>
  </div>
</template>

<script setup lang="ts">
  import { ref } from 'vue'
  import { registerApi } from '@/api/auth'
  import { useAuthStore } from '@/api/auth'
  import { useRouter } from 'vue-router'
  import { watchEffect } from 'vue'

  const name = ref('')
  const phone = ref('')
  const email = ref('')
  const password = ref('')

  const auth = useAuthStore()
  const router = useRouter()

  watchEffect(() => {
    if (auth.isLoggedIn) {
      router.push('/')
    }
  })

  const handleSubmit = async (e: Event) => {
    e.preventDefault()
    try {
      await registerApi({
        name: name.value,
        phone: phone.value,
        email: email.value,
        password: password.value,
      })
      alert('註冊成功')
      router.push('/')
    } catch (err: any) {
      console.error(err)
      const message = err?.response?.data?.message
        ? '註冊失敗: ' + err.response.data.message
        : '註冊失敗，請稍後再試'
      alert(message)
    }
  }
</script>
