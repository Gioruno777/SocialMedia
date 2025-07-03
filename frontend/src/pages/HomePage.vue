<template>
  <div class="h-full flex flex-col items-center p-4 bg-gray-100">
    <PostForm v-if="auth.isLoggedIn" />
    <div v-if="isLoading">載入中...</div>
    <div v-else-if="isError">載入失敗</div>
    <div class="w-full" v-else>
      <PostCard v-for="post in data" :key="post.id" :post="post" />
    </div>
  </div>
</template>

<script setup lang="ts">
  import PostForm from '@/components/PostForm.vue'
  import PostCard from '@/components/PostCard.vue'
  import { useAuthStore } from '@/api/auth'
  import { useGetAllPost } from '@/api/post'

  const auth = useAuthStore()
  const { data, isLoading, isError } = useGetAllPost()
  console.log(data.value)
</script>
