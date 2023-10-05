<template>
  <div class="container mt-14 mb-20">
    <div class="row text-center mb-4">
      <h2>1:1 문의 내역 [Admin]</h2>
    </div>
    <table class="table">
      <thead></thead>
      <tbody>
        <tr>
          <td>
            <span class="text-primary text-center">[{{ question.type }}]</span>
          </td>
          <td class="text-center col-md-9">
            <b>제목 : &nbsp;{{ question.subject }}</b>
          </td>
          <td class="text-center">{{ question.regtime }}</td>
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

    <div class="rounded-4 bg-white list-group shadow-sm mb-8 mt-3">
      <div class="d-flex p-4 list-group-item">
        <div class="me-3">
          <img
            src="assets/img/avatar/2.jpg"
            alt=""
            class="avatar sm rounded-circle"
          />
        </div>
        <div class="flex-grow-1">
          <div class="d-flex mb-0 justify-content-between">
            <div class="d-flex align-items-center">
              <h6 class="mb-0 me-3">운영자</h6>
              <small class="text-muted">04 July</small>
            </div>
            <div class="mb-3">
              <a
                href="#!"
                class="text-muted fs-4 mb-0"
                style="font-weight: bold"
                >x</a
              >
            </div>
          </div>
          <p class="mb-0">
            {{ comment }}
          </p>
        </div>
      </div>
    </div>

    <textarea
      id="content"
      class="form-control mb-2"
      style="height: 150px"
      v-model="comment"
    ></textarea>

    <div class="d-grid gap-2 d-md-flex justify-content-md-end mb-10">
      <button type="button" class="btn btn-primary me-1" @click="reply">
        댓글달기
      </button>
    </div>

    <div class="d-grid gap-2 mx-auto col-md-2">
      <button
        type="button"
        class="btn btn-primary mb-2 me-1 mt-3"
        @click="listQuestion"
      >
        목록
      </button>
    </div>
  </div>
</template>

<script>
import moment from "moment";
import { getQuestion, deleteQuestion } from "@/api/qna";
import swal from "sweetalert";

export default {
  name: "QnaAdminDetail",
  data() {
    return {
      question: {},
      comment: "",
    };
  },
  computed: {
    message() {
      if (this.question.content)
        return this.question.content.split("\n").join("<br>");
      return "";
    },
  },
  created() {
    getQuestion(
      this.$route.params.articleno,
      (response) => {
        this.question = response.data;
      },
      (error) => {
        console.log("삭제시 에러발생!!", error);
      },
    );
  },
  methods: {
    listQuestion() {
      this.$router.push({ name: "qnaAdminList" }).catch(() => {});
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
          deleteQuestion(this.question.articleno, () => {
            this.$router.push({ name: "qnaAdminList" }).catch(() => {});
          });
          swal({
            title: "문의글 삭제 완료!",
            text: "문의글이 정상적으로 삭제되었습니다",
            icon: "success",
            button: "확인",
          })
            .then(this.$router.push({ name: "qnaAdminList" }))
            .catch(() => {});
        }
      });
    },
    reply() {},
  },
  filters: {
    dateFormat(regtime) {
      return moment(new Date(regtime)).format("YY.MM.DD hh:mm:ss");
    },
  },
};
</script>

<style></style>
