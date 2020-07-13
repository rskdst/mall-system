// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import axios from 'axios'
import qs from 'qs'
import router from './router/index'
import store from "./store/index"
//ant
import { Button,Input,Layout,Menu,Icon,Breadcrumb,Table,Pagination,Switch,ConfigProvider,
  FormModel,Select,Radio } from 'ant-design-vue';


//ant
Vue.use(Button);
Vue.use(Breadcrumb);
Vue.use(Pagination);
Vue.use(ConfigProvider);
Vue.use(Select);
Vue.use(Radio);
Vue.use(FormModel);
Vue.use(Switch);
Vue.use(Table);
Vue.use(Input);
Vue.use(Layout);
Vue.use(Menu);
Vue.use(Menu.Item);
Vue.use(Icon);


Vue.config.productionTip = false;
Vue.prototype.$qs = qs;
Vue.prototype.$axios = axios;

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: {App},
  template: '<App/>'
})





