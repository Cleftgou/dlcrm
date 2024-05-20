import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [vue()],

  server: {
    port: 8081, //端口号
    host:'0.0.0.0', //ip地址
    open: false, //是否自动打开浏览器
  }
})
