<template>
  <div class="w-full flex flex-col px-4 py-3 text-2xl bg-red-100 border-2 border-white">
    <div class="flex justify-end" v-if="post.userId === currentUserId">
      <button class="text-red-500 ml-auto" @click="handleDelete" :disabled="isPending">
        {{ isPending ? '刪除中...' : 'X' }}
      </button>
    </div>
    <div class="flex justify-between items-center w-full text-gray-700 mb-1">
      <p class="font-bold">{{ post.userName }}</p>
      <p class="text-red-700 mt-3 text-xs">{{ formatDate(post.createdAt) }}</p>
    </div>

    <form
      class="w-full mb-6 flex items-cemters"
      v-if="post.userId === currentUserId"
      @submit.prevent="handleUpdate"
    >
      <input class="w-9/10" v-model="content" :placeholder="post.content" />
      <button type="submit" class="w-1/10" :disabled="isUpdatePending">
        {{ isUpdatePending ? '編輯中...' : '編輯' }}
      </button>
    </form>

    <div v-if="post.userId !== currentUserId" class="mb-6">{{ post.content }}</div>
    <div class="py-2 border-t border-b border-black">留言💬</div>
    <div class="flex gap-2">
      <div v-if="isLoading">載入中...</div>
      <div v-else-if="isError">載入失敗</div>
      <div v-else-if="data.length > 0" class="w-full">
        <CommentCard v-for="comment in data" :key="comment.id" :comment="comment" />
      </div>
      <div v-else class="text-sm text-gray-500 mt-2">尚無留言</div>
    </div>
    <CommentForm v-if="auth.isLoggedIn" :postId="post.postId" />
  </div>
</template>

<script setup lang="ts">
  import { useGetComment } from '@/api/comment'
  import { formatDate } from '@/utils/time'
  import CommentCard from './CommentCard.vue'
  import CommentForm from './CommentForm.vue'
  import { useAuthStore } from '@/api/auth'
  import { getCurrentUserId } from '@/utils/jwtUtil'
  import { ref } from 'vue'
  import { useDeletePost, useUpdatePost } from '@/api/post'

  const currentUserId = ref<number | null>(getCurrentUserId())

  const props = defineProps<{
    post: {
      postId: number
      userId: number
      userName: string
      content: string
      createdAt: string
    }
  }>()

  const auth = useAuthStore()

  const { data, isLoading, isError } = useGetComment(props.post.postId)
  const { deletePost, isPending } = useDeletePost()

  const handleDelete = () => {
    if (confirm('確定要刪除這篇貼文嗎？')) {
      deletePost(props.post.postId)
    }
  }

  const content = ref(props.post.content)

  const { updatePost, isPending: isUpdatePending } = useUpdatePost()

  const handleUpdate = () => {
    updatePost({
      postId: props.post.postId,
      formData: {
        content: content.value,
      },
    })
  }
</script>
