//从vue框架导入createApp函数
import { createApp } from 'vue'
//导入默认的css样式
// import './style.css'

//导入饿了么ui组件
import ElementPlus from 'element-plus'
//导入饿了么ui图标
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
//导入饿了么uiCss
import 'element-plus/dist/index.css'
//导入饿了么ui国际化的中文包组件
import zhCn from 'element-plus/dist/locale/zh-cn.mjs'

//导入路由对象（组件）
import router from './router/router.js'

//导入组件
//根组件
import App from './App.vue'

//单独提出来方便操作创建地基
let app = createApp(App)

//循环注册图标（官方）
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}

//利用上面的createApp函数创建一个vue应用，再挂载到#app地方
//使用一下饿了么ui再挂载，使用一下路由
app.use(ElementPlus, {locale:zhCn}).use(router).mount('#app')
