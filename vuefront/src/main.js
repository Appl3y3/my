import { createApp } from 'vue'
import App from './App.vue'
import router from './router/index'
import ElementPlus from 'element-plus';         // 引入element-ui完整包
import 'element-plus/dist/index.css';           // element-ui的css样式要单独引入

const app = createApp(App)
app.use(ElementPlus);                           //注册ElementPlus
app.use(router);                                //引用路由实例
app.mount('#app');                  // 挂载到 #app