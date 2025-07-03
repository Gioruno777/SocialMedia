import { getAuthHeaders } from '@/utils/authClient'
import { useMutation, useQuery, useQueryClient } from '@tanstack/vue-query'
const API_BASE_URL = import.meta.env.VITE_BACKEND_URL

export interface PostPayload {
  content: string
}

export const useCreatePost = () => {
  const queryClient = useQueryClient()
  const request = async (formData: PostPayload) => {
    const response = await fetch(`${API_BASE_URL}/api/post`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        ...getAuthHeaders(),
      },
      body: JSON.stringify(formData),
    })

    const data = await response.json()
    if (!response.ok) {
      throw new Error('發文失敗')
    }
    return data
  }

  const { mutate: createPost, isPending } = useMutation({
    mutationFn: request,
    onSuccess: async () => {
      alert('發文成功')
      await queryClient.invalidateQueries({ queryKey: ['GetAllPost'] })
    },
    onError: () => {
      alert('發文失敗')
    },
  })
  return { createPost, isPending }
}

export const useGetAllPost = () => {
  const request = async () => {
    const response = await fetch(`${API_BASE_URL}/api/post`, {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
        ...getAuthHeaders(),
      },
    })

    if (!response.ok) {
      throw new Error('貼文讀取失敗')
    }

    return response.json()
  }
  const { data, isLoading, isError } = useQuery({
    queryKey: ['GetAllPost'],
    queryFn: request,
  })
  return { data, isLoading, isError }
}

export const useDeletePost = () => {
  const queryClient = useQueryClient()

  const request = async (postId: number) => {
    const response = await fetch(`${API_BASE_URL}/api/post/${postId}`, {
      method: 'DELETE',
      headers: {
        'Content-Type': 'application/json',
        ...getAuthHeaders(),
      },
    })

    const data = await response.json()
    if (!response.ok) {
      throw new Error('刪除失敗')
    }
    return data
  }

  const { mutate: deletePost, isPending } = useMutation({
    mutationFn: request,
    onSuccess: async () => {
      alert('刪除成功')
      await queryClient.invalidateQueries({ queryKey: ['GetAllPost'] })
    },
    onError: () => {
      alert('刪除失敗')
    },
  })
  return { deletePost, isPending }
}

export const useUpdatePost = () => {
  const queryClient = useQueryClient()

  const request = async ({ postId, formData }: { postId: number; formData: PostPayload }) => {
    const response = await fetch(`${API_BASE_URL}/api/post/${postId}`, {
      method: 'PATCH',
      headers: {
        'Content-Type': 'application/json',
        ...getAuthHeaders(),
      },
      body: JSON.stringify(formData),
    })

    const data = await response.json()
    if (!response.ok) {
      throw new Error('編輯失敗')
    }
    return data
  }

  const { mutate: updatePost, isPending } = useMutation({
    mutationFn: request,
    onSuccess: async () => {
      alert('編輯成功')
      await queryClient.invalidateQueries({ queryKey: ['GetAllPost'] })
    },
    onError: () => {
      alert('編輯失敗')
    },
  })
  return { updatePost, isPending }
}
