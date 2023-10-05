import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import VueCookies from "vue-cookies";
import loader from "vue-ui-preloader";

// preloader
Vue.use(loader);
//쿠키를 사용한다.
Vue.use(VueCookies);
//쿠키의 만료일은 7일이다. (글로벌 세팅)
Vue.$cookies.config("7d");

import { BootstrapVue, IconsPlugin } from "bootstrap-vue";

// Import Bootstrap and BootstrapVue CSS files (order is important)
// import "bootstrap/dist/css/bootstrap.css";
import "bootstrap-vue/dist/bootstrap-vue.css";

// Make BootstrapVue available throughout your project
Vue.use(BootstrapVue);
// Optionally install the BootstrapVue icon components plugin
Vue.use(IconsPlugin);

Vue.config.productionTip = false;

new Vue({
  VueCookies,
  loader,
  router,
  store,
  render: (h) => h(App),
}).$mount("#app");
