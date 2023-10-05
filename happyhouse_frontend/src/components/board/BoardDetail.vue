<template>
  <div class="container mt-15 mb-20">
    <div class="row text-center mb-5">
      <h2>공지사항</h2>
    </div>
    <table class="table">
      <thead></thead>
      <tbody>
        <tr>
          <td class="text-center col-md-1">
            <span>No. {{ item.articleno }}</span>
          </td>
          <td class="text-center col-md-2">조회수 : {{ item.hit }}</td>
          <td class="text-center col-md-6">
            <b>제목 : &nbsp;{{ item.subject }}</b>
          </td>
          <td class="text-center col-md-3">{{ item.regtime }}</td>
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
        @click="moveModifyArticle"
        v-if="userInfo && userInfo.userid === 'admin'"
      >
        수정
      </button>
      <button
        type="button"
        class="btn btn-outline-danger me-1"
        @click="deleteArticle"
        v-if="userInfo && userInfo.userid === 'admin'"
      >
        삭제
      </button>
    </div>

    <div class="d-grid gap-2 mx-auto col-md-2">
      <button
        type="button"
        class="btn btn-primary mb-2 me-1"
        @click="listArticle"
      >
        목록
      </button>
    </div>
  </div>
</template>

<script>
import moment from "moment";
import { getArticle, deleteArticle, countHit } from "@/api/board";
import { mapState } from "vuex";
import swal from "sweetalert";
const memberStore = "memberStore";

export default {
  name: "boardDetail",
  data() {
    return {
      item: {},
    };
  },
  computed: {
    message() {
      if (this.item.content) return this.item.content.split("\n").join("<br>");
      return "";
    },
    ...mapState(memberStore, ["userInfo"]),
  },
  async created() {
    await countHit(this.$route.params.articleno, () => {});
    await getArticle(this.$route.params.articleno, (response) => {
      this.item = response.data;
    });
  },
  methods: {
    listArticle() {
      this.$router.push({ name: "boardList" });
    },
    moveModifyArticle() {
      this.$router.push({
        name: "boardModify",
        params: { articleno: this.item.articleno },
      });
    },
    deleteArticle() {
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
          deleteArticle(this.item.articleno, () => {});
          swal({
            title: "공지글 삭제 완료!",
            text: "공지글이 정상적으로 삭제되었습니다",
            icon: "success",
            button: "확인",
          }).then(this.$router.go(-1));
        }
      });
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
