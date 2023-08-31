<template>
  <main class="main-content" id="main-content">
    <section class="position-relative">
      <div class="container position-relative">
        <div class="overflow-hidden">
          <!--Profile info header-->
          <div class="position-relative pt-10 pb-9 pb-lg-11">
            <div class="row">
              <div class="col-lg-9 mx-auto">
                <div class="pt-4 d-flex flex-column h-100">
                  <nav class="nav justify-content-center mb-4 nav-pills">
                    <router-link to="/user/mypage" class="nav-link">
                      <i class="bx bx-user-circle me-2"></i>내정보</router-link
                    >

                    <router-link to="/user/modify" class="nav-link active"
                      ><i class="bx bx-cog me-2"></i>설정</router-link
                    >
                  </nav>

                  <div class="card shadow p-3 mb-4">
                    <h5 class="mb-4"><b>회원정보 수정</b></h5>

                    <div>
                      <div class="row align-items-center">
                        <div class="col-md-6 mb-3">
                          <label class="form-label" for="profile_name"
                            >이름</label
                          >
                          <input
                            v-bind:class="{
                              'form-control': true,
                              'is-invalid':
                                !validUsername(user.username) && usernameBlured,
                            }"
                            v-on:blur="usernameBlured = true"
                            id="profile_name"
                            type="text"
                            v-model="user.username"
                          />
                          <span class="invalid-feedback">필수 정보입니다</span>
                        </div>
                        <div class="col-md-6 mb-3">
                          <label class="form-label" for="profile_email"
                            >이메일 주소</label
                          >
                          <input
                            v-bind:class="{
                              'form-control': true,
                              'is-invalid':
                                !validEmail(user.email) && emailBlured,
                            }"
                            v-on:blur="emailBlured = true"
                            type="email"
                            id="profile_email"
                            v-model="user.email"
                          />
                          <span class="invalid-feedback"
                            >유효한 이메일 주소를 입력해주세요</span
                          >
                        </div>
                        <div class="col-md-6 mb-3">
                          <label class="form-label" for="profile_id"
                            >아이디</label
                          >
                          <input
                            type="text"
                            id="profile_id"
                            v-model="user.userid"
                            class="form-control"
                            readonly
                          />
                        </div>
                        <div class="col-md-6 mb-3">
                          <label class="form-label" for="profile_like"
                            >관심지역</label
                          >
                          <select
                            autocomplete="false"
                            id="profile_like"
                            class="form-control"
                            data-choices='{"searchEnabled":false}'
                            v-model="user.interest"
                          >
                            <Option>서울특별시</Option>
                            <Option>부산광역시</Option>
                            <Option>대구광역시</Option>
                            <Option>인천광역시</Option>
                            <Option>대구광역시</Option>
                            <Option>광주광역시</Option>
                            <Option>대전광역시 </Option>
                            <Option>울산광역시</Option>
                            <Option>세종특별자치시</Option>
                            <Option>강원도</Option>
                            <Option>충청남도</Option>
                            <Option>전라북도</Option>
                            <Option>전라남도</Option>
                            <Option>경상북도</Option>
                            <Option>경상남도</Option>
                            <Option>제주특별자치도</Option>
                          </select>
                        </div>
                      </div>
                      <div class="text-end mt-2">
                        <button
                          type="button"
                          class="btn btn-primary"
                          @click="onUpdateInfo"
                        >
                          수정하기
                        </button>
                      </div>
                    </div>
                  </div>

                  <!--Password card-->
                  <div class="card shadow p-3">
                    <h5 class="mb-4"><b>비밀번호 변경</b></h5>

                    <form>
                      <div class="row">
                        <div class="col-md-4 mb-2">
                          <label for="update_pass_current" class="form-label"
                            >현재 비밀번호</label
                          >
                          <input
                            type="password"
                            id="update_pass_current"
                            required
                            v-bind:class="{
                              'form-control': true,
                              'is-invalid': !validCurPwd && curpwdBlured,
                              'is-valid': validCurPwd,
                            }"
                            v-on:blur="curpwdBlured = true"
                            @keyup="currentPwdCheck"
                            placeholder="현재 비밀번호"
                            v-model="curPwdForm.curpwd"
                          />
                          <span class="invalid-feedback"
                            >현재 비밀번호와 일치하지 않습니다</span
                          >
                          <span class="valid-feedback"
                            >현재 비밀번호와 일치합니다</span
                          >
                        </div>
                        <div class="col-md-4 mb-3">
                          <label for="update_pass_new" class="form-label"
                            >새로운 비밀번호</label
                          >
                          <input
                            type="password"
                            v-bind:class="{
                              'form-control': true,
                              'is-invalid':
                                (!validPassword(form.userpwd) &&
                                  newpwdBlured) ||
                                (curPwdForm.curpwd === form.userpwd &&
                                  newpwdBlured),
                              'is-valid':
                                validPassword(form.userpwd) &&
                                curPwdForm.curpwd !== form.userpwd,
                            }"
                            v-on:blur="newpwdBlured = true"
                            id="update_pass_new"
                            placeholder="새로운 비밀번호"
                            v-model="form.userpwd"
                          />
                          <span
                            class="invalid-feedback"
                            v-if="curPwdForm.curpwd !== form.userpwd"
                            >비밀번호는 8~16자여야 합니다</span
                          >
                          <span
                            class="invalid-feedback"
                            v-if="curPwdForm.curpwd === form.userpwd"
                            >새로운 비밀번호를 입력하세요</span
                          >
                          <span class="valid-feedback"
                            >사용 가능한 비밀번호입니다</span
                          >
                        </div>
                        <div class="col-md-4 mb-3">
                          <label
                            for="update_pass_new_confirm"
                            class="form-label"
                            >비밀번호 확인</label
                          >
                          <input
                            type="password"
                            v-bind:class="{
                              'form-control': true,
                              'is-invalid':
                                (!chkpwd || form.userpwd !== chkpwd) &&
                                chkpwdBlured,
                              'is-valid': chkpwd && form.userpwd === chkpwd,
                            }"
                            v-on:blur="chkpwdBlured = true"
                            id="update_pass_chk_confirm"
                            placeholder="비밀번호 확인"
                            v-model="chkpwd"
                          />
                          <span class="invalid-feedback"
                            >비밀번호가 일치하지 않습니다</span
                          >
                          <span class="valid-feedback"
                            >비밀번호가 일치합니다</span
                          >
                        </div>
                      </div>
                      <small class="help-text text-muted"
                        >비밀번호는 8자리 이상 17자리 미만이어야 합니다</small
                      >
                      <div class="text-end">
                        <button
                          type="button"
                          class="btn btn-primary"
                          @click="onUpdatePwd"
                        >
                          변경하기
                        </button>
                      </div>
                    </form>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
  </main>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";
import { curPwdCheck } from "@/api/member";
import swal from "sweetalert";
const memberStore = "memberStore";

export default {
  name: "MemberModify",
  data() {
    return {
      user: {
        username: "",
        userid: "",
        email: "",
        userpwd: "",
        interest: "",
      },
      form: {
        userid: "",
        userpwd: "",
      },
      curPwdForm: {
        userid: "",
        curpwd: "",
      },

      chkpwd: "",
      validCurPwd: false,

      usernameBlured: false,
      emailBlured: false,
      validUpdate: false,
      validateUpdatePwd: false,

      curpwdBlured: false,
      newpwdBlured: false,
      chkpwdBlured: false,
    };
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  methods: {
    ...mapActions(memberStore, ["userModify", "userPwdModify"]),
    ...mapMutations(memberStore, ["SET_USER_INFO"]),
    async updateInfo() {
      // 회원정보 수정 처리
      await this.userModify(this.user);
      swal({
        title: "회원정보 수정 완료!",
        text: "회원님의 소중한 개인정보는 항상 안전하게 관리됩니다",
        icon: "success",
        button: "확인",
      });
    },

    async updatePwd() {
      this.form.userid = this.userInfo.userid;
      // 비밀번호 변경 처리
      await this.userPwdModify(this.form);
      this.SET_USER_INFO(null);
      swal({
        title: "비밀번호 변경 완료!",
        text: "변경하신 비밀번호로 다시 로그인해주세요",
        icon: "success",
        button: "확인",
      }).then(this.$router.push({ name: "home" }));
    },

    // validation
    validate() {
      this.useridBlured = true;
      this.emailBlured = true;
      this.usernameBlured = true;
      if (
        this.validEmail(this.user.email) &&
        this.validUsername(this.user.username)
      ) {
        this.validUpdate = true;
      }
    },

    currentPwdCheck() {
      this.curPwdForm.userid = this.userInfo.userid;
      // 현재 비밀번호 체크
      curPwdCheck(this.curPwdForm, (response) => {
        if (response.data === "success") {
          console.log("success");
          this.validCurPwd = true;
          return true;
        } else {
          console.log("fail");
          this.validCurPwd = false;
          return false;
        }
      });
    },

    validatePwd() {
      this.curpwdBlured = true;
      this.newpwdBlured = true;
      this.chkpwdBlured = true;
      if (
        this.validCurPwd &&
        this.validPassword(this.form.userpwd) &&
        this.chkpwd &&
        this.form.userpwd === this.chkpwd &&
        this.curPwdForm.curpwd !== this.form.userpwd
      ) {
        this.validateUpdatePwd = true;
      }
    },

    validId: function (userid) {
      if (userid.length > 4 && userid.length < 21) {
        return true;
      }
    },
    validPassword: function (userpwd) {
      if (userpwd.length > 7 && userpwd.length < 17) {
        return true;
      }
    },

    validEmail: function (email) {
      var re = /(.+)@(.+){2,}\.(.+){2,}/;
      if (re.test(email.toLowerCase())) {
        return true;
      }
    },

    validUsername: function (username) {
      if (username) {
        return true;
      }
    },

    onUpdateInfo() {
      this.validate();
      if (this.validUpdate) {
        this.updateInfo();
      }
    },

    onUpdatePwd() {
      this.validatePwd();
      if (this.validateUpdatePwd) {
        this.updatePwd();
      }
    },
  },
  created() {
    this.user.username = this.userInfo.username;
    this.user.userid = this.userInfo.userid;
    this.user.email = this.userInfo.email;
    this.user.interest = this.userInfo.interest;
    // this.user.userpwd = this.userInfo.userpwd;
  },
};
</script>

<style></style>
