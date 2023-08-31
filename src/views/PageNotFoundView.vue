<template>
  <main class="main-content d-grid" id="main-content">
    <section class="position-relative overflow-hidden">
      <div class="container pt-14 pb-9 mt-5 mb-5">
        <div class="row">
          <div class="col-md-10 col-lg-8 mx-auto text-center position-relative">
            <div class="position-relative z-index-1">
              <div class="text-danger mb-4">
                <img
                  src="assets/img/graphics/illustration/404.svg"
                  class="width-18x mx-auto"
                  alt=""
                />
              </div>
              <h1 class="display-1 mb-2">404</h1>
              <h2 class="mb-5">요청하신 페이지를 찾을 수 없습니다</h2>
              <p class="w-lg-75 lead mx-auto mb-5" style="font-size: 18px">
                원하시는 결과를 찾을 수 없습니다.<br />
                올바른 URL을 입력하였는지 확인하세요.
              </p>
              <router-link
                to="/"
                class="fw-semibold"
                style="text-decoration: none"
              >
                <button class="btn btn-primary">
                  <i class="bx bx-left-arrow-alt lh-1 fw-normal me-2 mb-1"></i>
                  메인으로 돌아가기
                </button></router-link
              >
            </div>
          </div>
        </div>
      </div>
    </section>
  </main>
</template>

<script>
export default {
  name: "PageNotFoundView",
};
</script>

<style></style>
