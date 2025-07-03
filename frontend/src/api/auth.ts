import router from '@/router'
import { authClient, getAuthHeaders } from '@/utils/authClient'
import { useMutation } from '@tanstack/vue-query'
import axios from 'axios'
import { defineStore } from 'pinia'
import { ref } from 'vue'
const API_BASE_URL = import.meta.env.VITE_BACKEND_URL

export interface RegisterPayload {
  name: string
  phone: string
  email: string
  password: string
}

export interface LoginPayload {
  phone: string
  password: string
}

export const registerApi = async (data: RegisterPayload) => {
  return axios.post(`${API_BASE_URL}/api/auth/register`, data)
}

export const useLogin = () => {
  const auth = useAuthStore()

  const request = async (formData: LoginPayload) => {
    const response = await fetch(`${API_BASE_URL}/api/auth/login`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(formData),
    })

    const data = await response.json()
    if (!response.ok) {
      throw new Error('登入失敗')
    }

    if (data.token) {
      authClient.setToken(data.token)
    }

    return data
  }
  const { mutate: login, isPending } = useMutation({
    mutationFn: request,
    onSuccess: async () => {
      await auth.checkToken()
      router.push('/')
    },
    onError: () => {
      alert('登入失敗')
    },
  })

  return { login, isPending }
}

export const useAuthStore = defineStore('auth', () => {
  const isLoggedIn = ref(false)
  const isLoading = ref(true)

  const checkToken = async () => {
    isLoading.value = true
    try {
      const res = await fetch(`${API_BASE_URL}/api/auth/validatetoken`, {
        method: 'GET',
        headers: {
          ...getAuthHeaders(),
        },
      })
      isLoggedIn.value = res.ok
    } catch {
      isLoggedIn.value = false
    } finally {
      isLoading.value = false
    }
  }

  const logout = async () => {
    authClient.removeToken()
    isLoggedIn.value = false
    isLoading.value = false
  }

  return { isLoggedIn, isLoading, checkToken, logout }
})
