import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import ElementPlus from "element-plus";
import "element-plus/lib/theme-chalk/index.css";
import store from './store'
import axios from 'axios';

axios.defaults.baseURL = 'http://localhost:8080/api/v1/'

createApp(App).use(store).use(ElementPlus).use(router).mount('#app')
