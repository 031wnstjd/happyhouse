<template>
  <div class="container mt-14 mb-20">
    <div class="row text-center mb-4">
      <h2>공지사항</h2>
    </div>
    <div class="form-group">
      <!--Input-->
      <label for="userid">작성자:</label>
      <input
        type="text"
        id="userid"
        class="form-control mb-3"
        placeholder="작성자"
        readonly
        v-model="item.userid"
      />
    </div>
    <!--Input-->
    <label for="subject">제목:</label>
    <input
      type="text"
      id="subject"
      class="form-control mb-3"
      placeholder="제목"
      v-model="item.subject"
    />

    <!--Input-->
    <label for="content">내용:</label>
    <textarea
      id="content"
      class="form-control mb-4"
      placeholder="공지사항을 입력하세요"
      style="height: 250px"
      v-model="item.content"
    ></textarea>
    <div class="d-grid gap-2 d-md-flex justify-content-md-end">
      <button
        type="button"
        class="btn btn-outline-primary me-1"
        @click="onSubmit"
        v-if="this.type === 'register'"
      >
        등록
      </button>
      <button
        type="button"
        class="btn btn-outline-primary me-1"
        @click="onSubmit"
        v-else
      >
        수정완료
      </button>
      <button
        type="button"
        class="btn btn-outline-danger me-1"
        @click="$router.go(-1)"
      >
        취소
      </button>
    </div>
    <div class="d-grid gap-2 mx-auto col-lg-2 col-md-2">
      <button type="button" class="btn btn-primary mb-2 me-1" @click="moveList">
        목록
      </button>
    </div>
  </div>
</template>

<script>
import { writeArticle, getArticle, modifyArticle } from "@/api/board";
import { mapState } from "vuex";
import swal from "sweetalert";

const memberStore = "memberStore";

export default {
  name: "BoardInputItem",
  data() {
    return {
      item: {
        articleno: 0,
        userid: "",
        subject: "",
        content: "",
        hit: "",
      },
      isUserid: false,
    };
  },
  props: {
    type: { type: String },
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  async created() {
    if (this.type === "modify") {
      await getArticle(
        this.$route.params.articleno,
        ({ data }) => {
          this.item = data;
        },
        (error) => {
          console.log(error);
        },
      );
      this.isUserid = true;
    } else {
      // register 인 경우
      this.item.userid = this.userInfo.userid;
      this.isUserid = true;
    }
  },
  methods: {
    onSubmit() {
      if (!this.item.userid || !this.item.subject || !this.item.content) {
        swal({
          title: "모든 항목을 입력하세요",
          icon: "warning",
          button: "확인",
        });
      } else {
        this.type === "register" ? this.writeArticle() : this.modifyArticle();
      }
    },

    writeArticle() {
      writeArticle(
        {
          userid: this.item.userid,
          subject: this.item.subject,
          content: this.item.content,
          hit: this.item.hit,
        },
        ({ data }) => {
          if (data === "success") {
            swal({
              title: "공지 등록 완료!",
              icon: "success",
              button: "확인",
            });
          } else {
            swal({
              title: "공지 등록 중 문제가 발생했습니다",
              text: "다시 시도해주세요",
              icon: "error",
              button: "확인",
            });
          }

          this.moveList();
        },
        (error) => {
          console.log(error);
        },
      );
    },
    modifyArticle() {
      modifyArticle(
        {
          articleno: this.item.articleno,
          userid: this.item.userid,
          subject: this.item.subject,
          content: this.item.content,
          hit: this.item.hit,
        },
        ({ data }) => {
          if (data === "success") {
            swal({
              title: "공지 수정 완료!",
              icon: "success",
              button: "확인",
            });
          } else {
            swal({
              title: "공지 수정 중 문제가 발생했습니다",
              text: "다시 시도해주세요",
              icon: "error",
              button: "확인",
            });
          }

          // 현재 route를 /list로 변경.
          this.$router.push({ name: "boardList" }).catch(() => {});
        },
        (error) => {
          console.log(error);
        },
      );
    },
    moveList() {
      this.$router.push({ name: "boardList" }).catch(() => {});
    },
  },
};
</script>

<style></style>
