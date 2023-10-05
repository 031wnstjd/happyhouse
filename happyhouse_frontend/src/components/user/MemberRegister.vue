<template>
  <!--Hero signup-->
  <section class="position-relative bg-light">
    <!--Hero divider vector-->
    <svg
      class="position-absolute start-0 bottom-0 h-lg-30"
      preserveAspectRatio="none"
      width="100%"
      height="35%"
      viewBox="0 0 1296 220"
      fill="none"
      xmlns="http://www.w3.org/2000/svg"
    >
      <path
        fill-rule="evenodd"
        clip-rule="evenodd"
        d="M1296 0H1241.56C1188.17 0 1080.35 0 971.477 21.9407C863.651 43.8814 755.826 87.7628 648
        110.296C540.174 132.237 432.349 132.237 323.477 128.086C215.651 124.528 107.826 117.412
        54.4362 113.854L3.71933e-05 110.296V220H54.4362C107.826 220 215.651 220 323.477 220C432.349
        220 540.174 220 648 220C755.826 220 863.651 220 971.477 220C1080.35 220 1188.17 220 1241.56
        220H1296V0Z"
        fill="white"
      />
    </svg>
    <div class="container col-xl-10 col-xxl-10 pt-12 pt-lg-14 pb-9">
      <div class="row align-items-center pb-lg-11">
        <div class="col-lg-8 text-center text-lg-start">
          <!--Hero heading-->
          <h1 class="display-5 mb-6">여러분의 행복한 집을 찾으세요!</h1>
          <!--Hero subheading-->
          <figure class="border-start border-4 border-primary ps-4">
            <blockquote class="blockquote fs-1 lh-sm mb-4 mb-lg-5">
              <p class="w-lg-90 lead fst-italic">
                "인간은 자신이 필요로 하는 것을 찾아 세계를 여행하고 집에 돌아와
                그것을 발견한다." - 조지무어
              </p>
            </blockquote>
          </figure>
        </div>
        <div class="col-11 mx-auto col-lg-4">
          <div class="position-relative pe-4 pt-4">
            <!--Signup form-->
            <div
              class="px-4 py-5 rounded-4 bg-white shadow-lg z-index-1 position-relative needs-validation"
            >
              <!--Input floating-->
              <div class="form-floating mb-3">
                <input
                  type="text"
                  v-bind:class="{
                    'form-control': true,
                    'is-invalid':
                      (!validId(form.userid) && useridBlured) || isDuplicatedId,
                    'is-valid': validId(form.userid),
                  }"
                  v-on:blur="useridBlured = true"
                  required
                  id="floatingId"
                  placeholder="ID"
                  v-model="form.userid"
                  @keyup="idDuplicate"
                />
                <label for="floatingId">회원 아이디</label>
                <span class="invalid-feedback" v-if="!isDuplicatedId"
                  >아이디는 5~20자여야 합니다</span
                >
                <span class="invalid-feedback" v-if="isDuplicatedId"
                  >이미 사용중인 아이디입니다</span
                >
                <span class="valid-feedback" v-if="!isDuplicatedId"
                  >사용 가능한 아이디입니다</span
                >
              </div>
              <!--Password floating-->
              <div class="form-floating mb-3">
                <input
                  type="password"
                  required
                  v-bind:class="{
                    'form-control': true,
                    'is-invalid':
                      !validPassword(form.userpwd) && passwordBlured,
                    'is-valid': validPassword(form.userpwd),
                  }"
                  v-on:blur="passwordBlured = true"
                  id="floatingPassword"
                  placeholder="Password"
                  v-model="form.userpwd"
                />
                <label for="floatingPassword">회원 비밀번호</label>
                <span class="invalid-feedback"
                  >비밀번호는 8~16자여야 합니다</span
                >
                <span class="valid-feedback">사용 가능한 비밀번호입니다</span>
              </div>
              <!--Password floating-->
              <div class="form-floating mb-3">
                <input
                  type="text"
                  required
                  v-bind:class="{
                    'form-control': true,
                    'is-invalid':
                      !validUsername(form.username) && usernameBlured,
                    'is-valid': validUsername(form.username),
                  }"
                  v-on:blur="usernameBlured = true"
                  id="floatingName"
                  placeholder="UserName"
                  v-model="form.username"
                />
                <label for="floatingName">회원 이름</label>
                <span class="invalid-feedback">필수 정보입니다</span>
              </div>
              <!--Password floating-->
              <div class="form-floating mb-3">
                <input
                  type="email"
                  required
                  v-bind:class="{
                    'form-control': true,
                    'is-invalid': !validEmail(form.email) && emailBlured,
                    'is-valid': validEmail(form.email),
                  }"
                  v-on:blur="emailBlured = true"
                  id="floatingEmail"
                  placeholder="Email"
                  v-model="form.email"
                />
                <label for="floatingEmail">회원 이메일 주소</label>
                <span class="invalid-feedback"
                  >유효한 이메일 주소를 입력해주세요</span
                >
                <span class="valid-feedback"
                  >사용 가능한 이메일 주소입니다</span
                >
              </div>
              <div class="mb-3">
                <select
                  autocomplete="false"
                  id="profile_like"
                  class="form-control"
                  style="height: 55px"
                  data-choices='{"searchEnabled":false}'
                  v-model="form.interest"
                >
                  <option disabled value="">관심지역 선택</option>
                  <option selected>서울특별시</option>
                  <option>부산광역시</option>
                  <option>대구광역시</option>
                  <option>인천광역시</option>
                  <option>대구광역시</option>
                  <option>광주광역시</option>
                  <option>대전광역시</option>
                  <option>울산광역시</option>
                  <option>세종특별자치시</option>
                  <option>강원도</option>
                  <option>충청남도</option>
                  <option>전라북도</option>
                  <option>전라남도</option>
                  <option>경상북도</option>
                  <option>경상남도</option>
                  <option>제주특별자치도</option>
                </select>
              </div>
              <!--label-->
              <div class="mb-3 form-check">
                <input
                  class="form-check-input me-1"
                  id="terms"
                  type="checkbox"
                  value=""
                />
                <label class="form-check-label small" for="terms"
                  >뉴스레터를 구독하시겠습니까?</label
                >
              </div>
              <!--Submit button-->
              <button
                type="submit"
                class="w-100 btn btn-lg btn-primary"
                @click="this.submit"
              >
                가입하기
              </button>
              <!--Divider line-->
              <!-- <hr class="mt-4 mb-3" />
              Info text-->
              <!-- <small class="text-muted"
                >By clicking Sign up, you agree to the terms & conditions of
                use.</small
              > -->
            </div>
          </div>
        </div>
      </div>
    </div>
    <!--/.content-->
  </section>
  <!--/Hero end-->
</template>

<script>
import { signUp, idCheck } from "@/api/member";
import swal from "sweetalert";

export default {
  name: "MemberRegister",
  data() {
    return {
      form: {
        username: "",
        userid: "",
        userpwd: "",
        email: "",
        interest: "",
      },
      useridBlured: false,
      passwordBlured: false,
      usernameBlured: false,
      emailBlured: false,
      valid: false,
      isDuplicatedId: false,
    };
  },

  methods: {
    async onSubmit() {
      this.form.username.trim();
      this.form.userid.trim();
      this.form.userpwd.trim();
      this.form.email.trim();
      await signUp(
        this.form,
        (response) => {
          if (response.data === "success") {
            console.log("회원가입 성공");
            swal({
              title: "회원가입 성공!",
              text: "로그인 후 마음에 드는 아파트 매물을 찾아보세요!",
              icon: "success",
              button: "확인",
            });
          } else {
            console.log("회원가입 실패");
            swal(
              "회원가입 중 문제가 발생하였습니다",
              "다시 시도해주세요",
              "warning",
            );
          }
        },
        (error) => {
          console.log(error);
        },
      );

      this.$router.push({ name: "home" }); // 회원가입 후 홈페이지로 이동
    },

    // validation
    validate() {
      this.useridBlured = true;
      this.passwordBlured = true;
      this.emailBlured = true;
      this.usernameBlured = true;
      if (
        this.validId(this.form.userid) &&
        this.validEmail(this.form.email) &&
        this.validPassword(this.form.userpwd) &&
        this.validUsername(this.form.username) &&
        this.form.interest !== "" &&
        !this.isDuplicatedId
      ) {
        this.valid = true;
      }
      if (
        this.validId(this.form.userid) &&
        this.validEmail(this.form.email) &&
        this.validPassword(this.form.userpwd) &&
        this.validUsername(this.form.username) &&
        !this.isDuplicatedId &&
        this.form.interest === ""
      ) {
        swal({
          title: "관심지역을 선택해주세요",
          text: "회원님 맞춤 서비스를 제공해드립니다",
          icon: "warning",
          button: "확인",
        });
      }
    },

    // 아이디 중복 체크
    async idDuplicate() {
      await idCheck(
        this.form.userid,
        (response) => {
          if (response.data === "success") {
            console.log("success");
            this.isDuplicatedId = true;
          } else {
            console.log("fail");
            this.isDuplicatedId = false;
          }
        },
        (error) => {
          console.log(error);
        },
      );
    },

    validId(userid) {
      if (userid.length > 4 && userid.length < 21) {
        return true;
      }
    },
    validPassword(userpwd) {
      if (userpwd.length > 7 && userpwd.length < 17) {
        return true;
      }
    },

    validEmail(email) {
      var re = /(.+)@(.+){2,}\.(.+){2,}/;
      if (re.test(email.toLowerCase())) {
        return true;
      }
    },

    validUsername(username) {
      if (username) {
        return true;
      }
    },

    submit() {
      this.validate();
      if (this.valid) {
        this.onSubmit();
      }
    },
  },
};
</script>

<style></style>
