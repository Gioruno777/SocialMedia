import { createApp } from 'vue'
import './style.css'
import router from './router'
import App from './App.vue'
import { VueQueryPlugin } from '@tanstack/vue-query'
import { createPinia } from 'pinia'

const app = createApp(App)
app.use(router)
app.use(VueQueryPlugin)
app.use(createPinia())
app.mount('#app')
