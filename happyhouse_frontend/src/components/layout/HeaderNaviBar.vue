<template>
  <!--Header Start-->
  <header
    class="z-index-fixed header-transparent header-absolute-top sticky-reverse"
  >
    <nav class="navbar navbar-expand-lg navbar-light bg-white shadow-lg py-1">
      <div class="container position-relative">
        <router-link class="navbar-brand" to="/"
          ><img
            src="assets/img/logo/happyhouselogo.jpg"
            alt=""
            class="img-fluid"
        /></router-link>

        <!-- 로그아웃 버튼 -->
        <div
          class="d-flex align-items-center navbar-no-collapse-items order-lg-last"
          v-if="userInfo"
        >
          <button
            class="navbar-toggler order-last"
            type="button"
            data-bs-toggle="collapse"
            data-bs-target="#mainNavbarTheme"
            aria-controls="mainNavbarTheme"
            aria-expanded="false"
            aria-label="Toggle navigation"
          >
            <span class="navbar-toggler-icon">
              <i></i>
            </span>
          </button>
          <div class="nav-item me-3 me-lg-0 ms-lg-4 ms-xl-4 dropdown">
            <a
              href="#"
              class="btn btn-secondary rounded-pill py-0 ps-0 pe-3"
              data-bs-auto-close="outside"
              data-bs-toggle="dropdown"
            >
              <img
                src="assets/img/avatar/12.jpg"
                alt=""
                class="avatar sm rounded-circle me-2"
              />
              <small />{{ userInfo.userid[0] }}
            </a>
            <div
              class="dropdown-menu shadow-lg dropdown-menu-end dropdown-menu-xs p-0"
            >
              <div href="#!" class="dropdown-header border-bottom p-4">
                <div class="d-flex align-items-center">
                  <div>
                    <router-link class="navbar-brand" :to="{ name: 'mypage' }">
                      <img
                        src="assets/img/avatar/userlogo.png"
                        alt=""
                        class="avatar xl rounded-pill me-3"
                      />
                    </router-link>
                  </div>
                  <div>
                    <h5 class="mb-2 text-body" />
                    환영합니다 <b>{{ userInfo.username }}</b
                    >님
                    <span class="text-lowercase text-muted d-block mb-1">
                      {{ userInfo.email }}
                    </span>

                    <router-link
                      to="/user/mypage"
                      class="link-underline"
                      style="text-decoration: none; color: dodgerblue"
                    >
                      Mypage
                    </router-link>
                  </div>
                </div>
              </div>
              <button
                @click="onClickLogout"
                class="dropdown-item rounded-top-0 p-3"
              >
                <span class="d-block text-end">
                  <i class="bx bx-right-arrow-alt me-2"></i>
                  로그아웃
                </span>
              </button>
            </div>
          </div>
        </div>

        <!-- 로그인 버튼 -->
        <div
          class="d-flex align-items-center navbar-no-collapse-items order-lg-last"
          v-if="!userInfo"
        >
          <button
            class="navbar-toggler order-last"
            type="button"
            data-bs-toggle="collapse"
            data-bs-target="#mainNavbarTheme"
            aria-controls="mainNavbarTheme"
            aria-expanded="false"
            aria-label="Toggle navigation"
          >
            <span class="navbar-toggler-icon">
              <i></i>
            </span>
          </button>
          <div class="nav-item me-3 me-lg-0 ms-lg-4 ms-xl-4 dropdown">
            <button
              class="btn btn-outline-secondary btm-sm rounded-pill py-1"
              data-bs-auto-close="outside"
              data-bs-toggle="dropdown"
            >
              로그인
            </button>
            <div class="dropdown-menu dropdown-menu-end dropdown-menu-xs p-4">
              <div class="needs-validation" novalidate>
                <div>
                  <h3 class="mb-1">환영합니다!</h3>
                  <p class="mb-4 text-muted">로그인 정보를 입력해주세요</p>
                </div>
                <div class="input-icon-group mb-3">
                  <span class="input-icon">
                    <i class="bx bx-envelope"></i>
                  </span>
                  <input
                    type="text"
                    required
                    class="form-control"
                    autofocus=""
                    placeholder="아이디 입력"
                    v-model="user.userid"
                    @keyup.enter="confirm"
                    @keyup="resetAlert"
                  />
                </div>
                <div class="input-icon-group mb-3">
                  <span class="input-icon">
                    <i class="bx bx-key"></i>
                  </span>
                  <input
                    type="password"
                    required
                    class="form-control"
                    placeholder="비밀번호 입력"
                    v-model="user.userpwd"
                    @keyup.enter="confirm"
                    @keyup="resetAlert"
                  />
                </div>
                <div class="d-flex justify-content-between">
                  <div class="form-check">
                    <input
                      class="form-check-input"
                      type="checkbox"
                      value=""
                      id="saveId"
                      v-model="checked"
                    />
                    <label class="form-check-label" for="saveId">
                      아이디저장
                    </label>
                  </div>
                  <div>
                    <label class="text-end d-block small mb-0">
                      <router-link
                        to="/user/findid"
                        class="text-muted text-decoration-none"
                        >아이디찾기
                      </router-link>
                      <span class="text-muted me-2 ms-1">|</span>
                      <router-link
                        to="/user/findpwd"
                        class="text-muted text-decoration-none"
                        >비밀번호찾기
                      </router-link>
                    </label>
                  </div>
                </div>

                <div
                  v-if="isClickedLogin && !isLogin"
                  class="small text-danger mt-2"
                >
                  아이디 또는 비밀번호를 잘못 입력했습니다
                </div>
                <div class="d-grid mt-3">
                  <button
                    class="btn btn-primary btn-hover-arrow"
                    @click="confirm"
                  >
                    <span>로그인</span>
                  </button>
                </div>
                <p class="pt-4 mb-0 text-muted">
                  아직 회원이 아니신가요?
                  <router-link
                    to="/user/signup"
                    class="ms-2 pb-0 text-primary fw-semibold link-underline"
                    style="text-decoration: none"
                    >회원가입</router-link
                  >
                </p>
              </div>
            </div>
          </div>
        </div>

        <!--Navbar items-->
        <div class="collapse navbar-collapse" id="mainNavbarTheme">
          <ul class="navbar-nav ms-auto">
            <li class="nav-item">
              <router-link class="nav-link" to="/">홈</router-link>
            </li>
            <li class="nav-item">
              <router-link class="nav-link" :to="{ name: 'house' }"
                >아파트정보</router-link
              >
            </li>
            <li class="nav-item">
              <router-link class="nav-link" :to="{ name: 'board' }"
                >공지사항</router-link
              >
            </li>
            <li class="nav-item">
              <router-link
                class="nav-link"
                :to="{ name: 'qnaAdminList' }"
                v-if="isAdmin"
                >1:1 문의</router-link
              >
              <router-link class="nav-link" :to="{ name: 'qna' }" v-else
                >1:1 문의</router-link
              >
            </li>
          </ul>
        </div>
      </div>
    </nav>
  </header>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";
// import ms from "@/store/modules/memberStore";

const memberStore = "memberStore";

export default {
  name: "HeaderNaviBar",
  data() {
    return {
      user: {
        userid: null,
        userpwd: null,
      },
      checked: false,
      isClickedLogin: false,
    };
  },
  computed: {
    ...mapState(memberStore, [
      "isLogin",
      "userInfo",
      "isLoginError",
      "isAdmin",
    ]),
  },
  methods: {
    ...mapActions(memberStore, ["userConfirm", "getUserInfo"]),
    ...mapMutations(memberStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),
    onClickLogout() {
      // console.log("memberStore : ", ms);
      this.SET_IS_LOGIN(false);
      this.SET_USER_INFO(null);
      sessionStorage.removeItem("access-token");
      if (this.$route.path != "/")
        this.$router.push({ name: "home" }).catch(() => {});
    },
    async confirm() {
      // console.log(this.user.userid);
      await this.userConfirm(this.user);
      let token = sessionStorage.getItem("access-token");
      // 로그인 성공
      if (this.isLogin) {
        if (this.checked) {
          // 아이디저장이 체크된 상태로 로그인 성공하면 쿠키에 저장
          this.$cookies.set("save", this.user.userid);
        } else {
          // 아이디저장 체크 안 된 상태로 로그인하면 쿠키에서 삭제
          this.$cookies.remove("save");
        }
        await this.getUserInfo(token);
        this.$router.push({ name: "home" }).catch(() => {}); // 홈으로 이동
      }
      this.isClickedLogin = true;
    },
    resetAlert() {
      this.isClickedLogin = false;
    },
  },
  created() {
    const cookie = this.$cookies.get("save"); // userid
    if (cookie) {
      this.checked = true;
      this.user.userid = cookie;
    } else {
      this.checked = false;
    }
  },
};
</script>

<style></style>
