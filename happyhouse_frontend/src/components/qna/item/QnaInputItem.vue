<template>
  <div class="container mt-14 mb-20">
    <div class="row text-center mb-4">
      <h2>1:1 문의하기</h2>
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
        v-model="question.userid"
      />
    </div>
    <!--Input-->
    <label for="type">문의유형:</label>
    <select
      autocomplete="false"
      id="type"
      class="form-control mb-3"
      data-choices='{"searchEnabled":false}'
      v-model="question.type"
    >
      <option disabled value="">문의유형을 선택하세요</option>
      <option>일반문의</option>
      <option>허위매물신고</option>
      <option>이벤트문의</option>
    </select>
    <!--Input-->
    <label for="subject">제목:</label>
    <input
      type="text"
      id="subject"
      class="form-control mb-3"
      placeholder="제목"
      v-model="question.subject"
    />

    <!--Input-->
    <label for="content">내용:</label>
    <textarea
      id="content"
      class="form-control mb-4"
      placeholder="문의하실 내용을 입력해주세요"
      style="height: 250px"
      v-model="question.content"
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
import { writeQuestion, getQuestion, modifyQuestion } from "@/api/qna";
import { mapState } from "vuex";
import swal from "sweetalert";

const memberStore = "memberStore";

export default {
  name: "QnaInputItem",
  data() {
    return {
      question: {
        articleno: 0,
        userid: "",
        subject: "",
        content: "",
        type: "",
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
  created() {
    if (this.type === "modify") {
      getQuestion(
        this.$route.params.articleno,
        ({ data }) => {
          this.question = data;
        },
        (error) => {
          console.log(error);
        },
      );
      this.isUserid = true;
    } else {
      // register 인 경우
      this.question.userid = this.userInfo.userid;
      this.isUserid = true;
    }
  },
  methods: {
    onSubmit() {
      if (
        !this.question.userid ||
        !this.question.subject ||
        !this.question.content ||
        !this.question.type
      ) {
        swal({
          title: "모든 항목을 입력하세요",
          icon: "warning",
          button: "확인",
        });
      } else
        this.type === "register"
          ? this.registQuestion()
          : this.modifyQuestion();
    },

    registQuestion() {
      writeQuestion(
        {
          userid: this.question.userid,
          subject: this.question.subject,
          content: this.question.content,
          type: this.question.type,
        },
        ({ data }) => {
          if (data === "success") {
            swal({
              title: "문의 등록 완료!",
              text: "최대한 신속하게 답변드리겠습니다",
              icon: "success",
              button: "확인",
            });
          } else {
            swal({
              title: "문의 등록 중 문제가 발생했습니다",
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
    modifyQuestion() {
      modifyQuestion(
        {
          articleno: this.question.articleno,
          userid: this.question.userid,
          subject: this.question.subject,
          content: this.question.content,
          type: this.question.type,
        },
        ({ data }) => {
          if (data === "success") {
            swal({
              title: "문의 수정 완료!",
              text: "최대한 신속하게 답변드리겠습니다",
              icon: "success",
              button: "확인",
            });
          } else {
            swal({
              title: "문의 수정 중 문제가 발생했습니다",
              text: "다시 시도해주세요",
              icon: "error",
              button: "확인",
            });
          }

          // 현재 route를 /list로 변경.
          this.$router.go(-1).catch(() => {});
        },
        (error) => {
          console.log(error);
        },
      );
    },
    moveList() {
      this.$router.push({ name: "qnaList" }).catch(() => {});
    },
    // moveBeforePage(){
    //   this.$router.push("/qna/detail/")
    // }
  },
};
</script>

<style></style>
