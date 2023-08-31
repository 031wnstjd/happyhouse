<template>
  <div id="app">
    <!-- Preloader Spinner -->
    <div class="spinner-loader bg-tint-primary">
      <div class="spinner-border text-primary" role="status"></div>
      <span class="small d-block ms-2">Loading...</span>
    </div>
    <!-- main content -->
    <navi-bar />
    <router-view />
    <footer-bar />
  </div>
</template>

<script>
import NaviBar from "@/components/layout/HeaderNaviBar.vue";
import FooterBar from "@/components/layout/FooterBar.vue";
export default {
  name: "App",
  components: {
    NaviBar,
    FooterBar,
  },
  mounted() {
    const script = document.createElement("script");
    script.src = "../assets/js/theme.bundle.js"; // main javascript 파일 header에 추가
    document.head.appendChild(script);
  },
};
</script>
<style>
/* @import url(https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css); */
@import url(../public/assets/fonts/boxicons/css/boxicons.min.css);
@import url(../public/assets/vendor/cssmodule/css/aos.css);
@import url(../public/assets/fonts/iconsmind/iconsmind.css);
@import url(../public/assets/vendor/cssmodule/css/choices.min.css);
@import url(../public/assets/vendor/cssmodule/css/swiper-bundle.min.css);
@import url(../public/assets/css/theme-teal.min.css);
@import url(../public/assets/vendor/cssmodule/css/prism-tomorrow.css);
@import url(../public/assets/css/theme.min.css);
</style>
