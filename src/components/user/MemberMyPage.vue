<template>
  <main class="main-content" id="main-content">
    <section class="position-relative">
      <div class="container position-relative py-5">
        <!--/.row-->
      </div>
    </section>
    <section class="position-relative">
      <div class="container position-relative">
        <div class="">
          <nav class="nav justify-content-center mb-5 mt-8 nav-pills">
            <router-link to="/user/mypage" class="nav-link active">
              <i class="bx bx-user-circle me-2"></i>내정보</router-link
            >

            <router-link to="/user/modify" class="nav-link"
              ><i class="bx bx-cog me-2"></i>설정</router-link
            >
          </nav>

          <!--Profile info header-->
          <div class="position-relative pt-lg-12 pb-7 pb-lg-11">
            <div class="row justify-content-center">
              <div class="col-md-7 col-lg-4 mb-5 mt-3 mb-lg-0">
                <div class="mt-lg-n14 position-relative z-index-1">
                  <div class="card shadow p-3">
                    <div>
                      <h4 class="mb-2">{{ userInfo.username }}</h4>
                      <small class="d-block mb-4"
                        >회원님의 소중한 개인정보는 항상 안전하게
                        관리됩니다</small
                      >
                      <ul class="list-group list-group-flush mb-0">
                        <li
                          class="d-flex align-items-stretch list-group-item px-0 justify-content-center"
                        >
                          <span class="small"
                            >이름 : {{ userInfo.username }}</span
                          >
                        </li>
                        <li
                          class="d-flex align-items-stretch list-group-item px-0 justify-content-center"
                        >
                          <span class="small"
                            >이메일 : {{ userInfo.email }}</span
                          >
                        </li>
                        <li
                          class="d-flex align-items-stretch list-group-item px-0 justify-content-center"
                        >
                          <span class="small"
                            >아이디 : {{ userInfo.userid }}</span
                          >
                        </li>
                        <li
                          class="d-flex align-items-stretch list-group-item px-0 justify-content-center"
                        >
                          <span class="small"
                            >관심지역 : {{ userInfo.interest }}</span
                          >
                        </li>
                      </ul>
                      <ul class="list-group border-top list-group-flush mb-3">
                        <li class="list-group-item px-0">
                          <div class="d-grid pt-4">
                            <button
                              class="btn btn-primary border shadow-sm mb-2 py-1"
                              type="button"
                              @click="movePage"
                            >
                              회원정보수정
                            </button>
                            <button
                              class="btn btn-danger shadow-sm py-1"
                              type="button"
                              @click="deleteAccount"
                            >
                              회원탈퇴
                            </button>
                          </div>
                        </li>
                      </ul>
                    </div>
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
import { mapState, mapActions } from "vuex";
import swal from "sweetalert";

const memberStore = "memberStore";

export default {
  name: "MemberMyPage",
  components: {},
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  methods: {
    ...mapActions(memberStore, ["userDelete"]),
    movePage() {
      this.$router.push({ name: "modify" });
    },
    async deleteAccount() {
      swal({
        title: "정말 탈퇴 하시겠습니까?",
        text: "탈퇴하시면 회원님의 정보는 영구히 삭제됩니다",
        icon: "error",
        buttons: {
          cancel: {
            text: "취소",
            visible: true,
          },
          confirm: {
            text: "확인",
          },
        },
        dangerMode: true,
      }).then((willDelete) => {
        if (willDelete) {
          this.userDelete(this.userInfo.userid);
          swal({
            title: "회원탈퇴 성공!",
            text: "지금까지 저희 서비스를 이용해주셔서 감사합니다",
            icon: "success",
            button: "확인",
          }).then(this.$router.push({ name: "home" }));
        }
      });
    },
  },
};
</script>

<style></style>
