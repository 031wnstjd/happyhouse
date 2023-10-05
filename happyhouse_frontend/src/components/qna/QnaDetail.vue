<template>
  <div class="container mt-14 mb-20">
    <div class="row text-center mb-5">
      <h2>1:1 문의 내역</h2>
    </div>
    <table class="table">
      <thead></thead>
      <tbody>
        <tr>
          <td class="text-center col-md-2">
            <span class="text-primary">[{{ question.type }}]</span>
          </td>
          <td class="text-center col-md-8">
            <b>제목 : &nbsp;{{ question.subject }}</b>
          </td>
          <td class="text-center col-md-2">{{ question.regtime }}</td>
        </tr>
      </tbody>
    </table>
    <div class="row justify-content-center">
      <div class="col-md-12 mb-4">
        <div class="p-5 p-lg-7 bg-light rounded-block">
          <div v-html="message"></div>
        </div>
      </div>
    </div>
    <div class="d-grid gap-2 d-md-flex justify-content-md-end mb-2">
      <button
        type="button"
        class="btn btn-outline-primary me-1"
        @click="moveModifyQuestion"
        v-if="userInfo.userid !== 'admin'"
      >
        수정
      </button>
      <button
        type="button"
        class="btn btn-outline-danger me-1"
        @click="deleteQuestion"
        v-if="userInfo.userid !== 'admin'"
      >
        삭제
      </button>
    </div>

    <!--댓글-->
    <div
      class="rounded-4 bg-white list-group shadow-sm mb-8 mt-3"
      v-if="comment"
    >
      <div class="d-flex p-4 list-group-item">
        <div class="me-3">
          <img
            src="assets/img/avatar/운영자.png"
            alt=""
            class="avatar sm rounded-circle"
          />
        </div>
        <div class="flex-grow-1">
          <div class="d-flex mb-0 justify-content-between">
            <div class="d-flex align-items-center mb-3">
              <h6 class="mb-0 me-3" style="font-weight: bold">운영자</h6>
            </div>
            <div>
              <button
                @click="deleteReply"
                href="#!"
                class="text-muted fs-4 mb-0 bg-white"
                style="font-weight: bold; border: 0"
                v-if="userInfo.userid === 'admin'"
              >
                x
              </button>
            </div>
          </div>
          <p class="mb-0">
            {{ comment }}
          </p>
        </div>
      </div>
    </div>

    <!-- 댓글달기 -->
    <div v-if="userInfo.userid === 'admin'">
      <textarea
        id="content"
        class="form-control mb-2"
        style="height: 150px"
        v-model="answer"
      ></textarea>

      <div class="d-grid gap-2 d-md-flex justify-content-md-end mb-2">
        <button type="button" class="btn btn-primary me-1" @click="updateReply">
          댓글달기
        </button>
      </div>
    </div>
    <div
      class="d-grid gap-2 mx-auto col-md-2"
      v-if="userInfo.userid === 'admin'"
    >
      <button
        type="button"
        class="btn btn-primary mb-2 me-1"
        @click="listQuestionAdmin"
      >
        목록
      </button>
    </div>
    <div class="d-grid gap-2 mx-auto col-md-2" v-else>
      <button
        type="button"
        class="btn btn-primary mb-2 me-1"
        @click="listQuestionUser"
      >
        목록
      </button>
    </div>
  </div>
</template>

<script>
import moment from "moment";
import { getQuestion, deleteQuestion, updateReply, getReply } from "@/api/qna";
import { mapState } from "vuex";
import swal from "sweetalert";
const memberStore = "memberStore";

export default {
  name: "QnaDetail",
  data() {
    return {
      question: {},
      isComment: false,
      answer: "",
      comment: "",
    };
  },
  computed: {
    message() {
      if (this.question.content)
        return this.question.content.split("\n").join("<br>");
      return "";
    },
    ...mapState(memberStore, ["userInfo"]),
  },
  async created() {
    await getQuestion(
      this.$route.params.articleno,
      (response) => {
        this.question = response.data;
      },
      (error) => {
        console.log("삭제시 에러발생!!", error);
      },
    );
    await getReply(
      this.question.articleno,
      (response) => {
        console.log("댓글받기 성공");
        this.comment = response.data.answer;
      },
      (error) => {
        console.log(error);
      },
    );
  },
  methods: {
    listQuestionUser() {
      this.$router.push({ name: "qnaList" });
    },
    listQuestionAdmin() {
      this.$router.push({ name: "qnaAdminList" });
    },
    moveModifyQuestion() {
      this.$router.push({
        name: "qnaModify",
        params: { articleno: this.question.articleno },
      });
    },
    deleteQuestion() {
      swal({
        title: "정말 삭제하시겠습니까?",
        text: "",
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
          deleteQuestion(this.question.articleno, () => {});
          swal({
            title: "문의글 삭제 완료!",
            text: "문의글이 정상적으로 삭제되었습니다",
            icon: "success",
            button: "확인",
          }).then(this.$router.go(-1));
        }
      });
    },
    async updateReply() {
      await updateReply(
        {
          articleno: this.question.articleno,
          state: "답변완료",
          answer: this.answer,
        },
        ({ data }) => {
          if (data === "success") {
            console.log("댓글달기 성공");
            this.answer = "";
          } else {
            console.log("댓글달기 실패");
          }
        },
      );
      await getReply(
        this.question.articleno,
        (response) => {
          console.log("댓글받기 성공");
          this.comment = response.data.answer;
        },
        (error) => {
          console.log(error);
        },
      );
    },
    getReply() {},
    async deleteReply() {
      updateReply(
        {
          articleno: this.question.articleno,
          answer: null,
          state: "답변대기",
        },
        ({ data }) => {
          if (data === "success") {
            this.comment = null;
            console.log("댓글삭제 성공");
          } else {
            console.log("댓글삭제 실패");
          }
        },
      );
    },
  },
  filters: {
    dateFormat(regtime) {
      return moment(new Date(regtime)).format("YY.MM.DD hh:mm:ss");
    },
  },
};
</script>

<style></style>
