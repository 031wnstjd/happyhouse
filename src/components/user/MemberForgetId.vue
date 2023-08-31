<template>
  <main>
    <!--page-hero-->
    <section
      class="bg-white position-relative d-flex justify-content-center h-100"
      v-if="!idList"
    >
      <div
        class="bg-dark d-none d-md-flex position-fixed end-0 top-0 w-md-50 w-lg-60 h-100"
      >
        <!--Divider shape-->
        <svg
          class="position-absolute start-0 top-0 h-100 text-white z-index-1"
          width="80"
          height="1096"
          preserveAspectRatio="none"
          viewBox="0 0 58 1096"
          fill="none"
          xmlns="http://www.w3.org/2000/svg"
        >
          <path
            opacity="0.24"
            fill-rule="evenodd"
            clip-rule="evenodd"
            d="M58 1096L53.2727 1034.97C48.3636 973.947 38.7273 852.719 39.8182 730.667C41.0909 608.614 53.2727 487.386 52 365.333C50.7273 243.281 36.3636 122.053 29.0909 61.0263L21.8182 2.02656e-06H-1.72853e-06L-1.72853e-06 61.0263C-1.72853e-06 122.053 -1.72853e-06 243.281 -1.72853e-06 365.333C-1.72853e-06 487.386 -1.72853e-06 608.614 -1.72853e-06 730.667C-1.72853e-06 852.719 -1.72853e-06 973.947 -1.72853e-06 1034.97L-1.72853e-06 1096H58Z"
            fill="white"
          />
          <path
            fill-rule="evenodd"
            clip-rule="evenodd"
            d="M37 1096L33.9843 1034.97C30.8527 973.947 24.7053 852.719 25.4013 730.667C26.2132 608.614 33.9843 487.386 33.1724 365.333C32.3605 243.281 23.1975 122.053 18.558 61.0263L13.9185 2.02656e-06H1.11014e-06L1.11014e-06 61.0263C1.11014e-06 122.053 1.11014e-06 243.281 1.11014e-06 365.333C1.11014e-06 487.386 1.11014e-06 608.614 1.11014e-06 730.667C1.11014e-06 852.719 1.11014e-06 973.947 1.11014e-06 1034.97L1.11014e-06 1096H37Z"
            fill="white"
          />
        </svg>

        <!--Image-->
        <img
          src="assets/img/backgrounds/bgg2.png"
          alt=""
          class="bg-image"
          style="opacity: 0.8"
        />
      </div>
      <div class="container z-index-2 position-relative">
        <div class="row align-items-center vh-100">
          <div
            class="col-lg-4 pb-4 pb-lg-15 pt-lg-3 me-auto col-md-6 z-index-2"
          >
            <div>
              <h3 class="mb-2">아이디를 잊어버리셨나요?</h3>
              <p class="mb-4 text-muted">
                인증을 위해 가입하신 이메일 주소를 입력해주세요
              </p>
              <div>
                <div class="needs-validation">
                  <form class="mb-3">
                    <input
                      type="email"
                      v-bind:class="{
                        'form-control': true,
                      }"
                      id="email"
                      autofocus
                      placeholder="가입하신 이메일 주소를 입력해주세요"
                      @keyup="resetEmailBlured"
                      @keyup.enter="findId"
                      v-model="email"
                    />
                    <span
                      class="small text-danger"
                      v-if="!validEmail(email) && emailBlured"
                      >유효한 이메일 주소를 입력해주세요</span
                    >
                    <div
                      class="small text-danger mt-1"
                      v-if="validEmail(email) && emailBlured && !valid"
                    >
                      가입되지 않은 회원의 이메일 주소입니다
                    </div>
                  </form>
                  <div class="d-grid">
                    <button class="btn btn-dark" @click="findId">
                      이메일 인증
                    </button>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>

    <section v-else>
      <section class="position-relative">
        <div class="container pt-10 pb-9 pb-lg-11">
          <div class="row pt-lg-7 justify-content-center text-center">
            <div class="col-xl-8">
              <div
                class="width-10x height-10x rounded-circle position-relative bg-success text-white flex-center mb-4"
              >
                <i class="bx bx-check lh-1 display-4 fw-normal"></i>
              </div>
              <h1 class="display-6 mb-3">가입하신 아이디 정보입니다</h1>
              <h5 class="mb-5">항상 저희 서비스를 이용해주셔서 감사합니다</h5>
              <div class="row justify-content-center">
                <div class="col-md-12 mb-5">
                  <div class="p-5 p-lg-7 bg-light rounded-block">
                    <h5 v-for="(id, idx) in idList" :key="idx">
                      {{ id }}
                    </h5>
                  </div>
                </div>
              </div>
              <router-link to="/" class="btn btn-outline-primary">
                홈으로</router-link
              >
            </div>
          </div>
        </div>
      </section>
      <section>
        <div class="container pb-9 pb-lg-11 mt-8">
          <div
            class="px-4 rounded-3 shadow-lg py-6 px-lg-5 py-lg-7 bg-gradient-primary text-white position-relative overflow-hidden"
            data-aos="fade-up"
            data-aos-duration="400"
          >
            <svg
              class="position-absolute end-0 bottom-0 mb-4 text-white opacity-25"
              width="200"
              height="400"
              preserveAspectRatio="none"
              viewBox="0 0 150 300"
              fill="none"
              xmlns="http://www.w3.org/2000/svg"
            >
              <path
                d="M150 300C130.302 300 110.796 296.12 92.5975 288.582C74.3986 281.044 57.8628 269.995 43.934 256.066C30.0052 242.137 18.9563 225.601 11.4181 207.403C3.87986 189.204 -6.2614e-07 169.698 0 150C6.2614e-07 130.302 3.87987 110.796 11.4181 92.5975C18.9563 74.3987 30.0052 57.8628 43.934 43.934C57.8628 30.0052 74.3987 18.9563 92.5975 11.4181C110.796 3.87986 130.302 3.51961e-06 150 5.00679e-06L150 37.5C135.226 37.5 120.597 40.4099 106.948 46.0636C93.299 51.7172 80.8971 60.0039 70.4505 70.4505C60.0039 80.8971 51.7172 93.299 46.0636 106.948C40.4099 120.597 37.5 135.226 37.5 150C37.5 164.774 40.4099 179.403 46.0636 193.052C51.7172 206.701 60.0039 219.103 70.4505 229.55C80.8971 239.996 93.299 248.283 106.948 253.936C120.597 259.59 135.226 262.5 150 262.5V300Z"
                fill="currentColor"
              />
            </svg>

            <div class="row align-items-end position-relative">
              <div class="col-lg-6 col-xl-7">
                <h5 class="text-muted mb-4">
                  오늘의 아파트 매물이 궁금하신가요?
                </h5>
                <h1 class="mb-5 mb-md-0 display-4">추천 매물 보러가기</h1>
              </div>
              <div class="col-lg-6 col-xl-5 text-lg-end">
                <router-link
                  class="btn btn-lg btn-rise btn-outline-white rounded-pill me-2 mb-2 mb-lg-0"
                  style="font-size: 50px"
                  :to="{ name: 'house' }"
                >
                  <span class="btn-rise-bg bg-white"></span>
                  <span class="btn-rise-text">GO!</span>
                </router-link>
              </div>
            </div>
          </div>
        </div>
      </section>
    </section>
  </main>
</template>

<script>
import { findIdByEmail, emailCheck } from "@/api/member";
import swal from "sweetalert";
export default {
  name: "MemberForgetId",
  data() {
    return {
      idList: "",
      email: "",
      valid: true,
      emailBlured: false,
      onclick: true,
    };
  },
  methods: {
    async findId() {
      await this.validateEmail(); // 이메일 유효성 체크 (이메일 형식 & 가입된 이메일인지 여부)
      if (this.valid && this.validEmail(this.email)) {
        // valid가 true면 아이디찾기 실행
        findIdByEmail(this.email, (response) => {
          console.log(1);
          if (response.status === 200) {
            this.idList = response.data;
            swal({
              title: "아이디 찾기 완료!",
              text: "가입하신 아이디 정보를 확인하세요",
              icon: "success",
              button: "확인",
            });
            console.log(this.idList);
          } else {
            console.log("아이디찾기 중 문제 발생");
          }
        });
        this.onclick = true;
      }
    },
    async validateEmail() {
      this.emailBlured = true;
      await emailCheck(
        this.email,
        (response) => {
          if (response.data === "success") {
            console.log("success");
            this.valid = true;
          } else {
            console.log("fail");
            this.valid = false;
          }
        },
        (error) => {
          console.log(error);
        },
      );
    },
    validEmail(email) {
      var re = /(.+)@(.+){2,}\.(.+){2,}/;
      if (re.test(email.toLowerCase())) {
        return true;
      }
    },
    resetEmailBlured() {
      this.emailBlured = false;
    },
  },
};
</script>

<style></style>
