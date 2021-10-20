import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'

import axios from 'axios';
Vue.prototype.$axios = axios;


import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
Vue.config.productionTip = false;
Vue.use(ElementUI);

import echarts from 'echarts'
// 在vue.prototype属性中添加echarts属性为$echarts，在代码中通过this.$echarts就可以使用了。
Vue.prototype.$echarts = echarts



new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')

var url="";
/*
http://localhost:8080
*/

export default url;