<template>
  <tr class="trow">
    <!-- <td class="text-center">{{ articleno }}</td> -->
    <td class="text-center text-primary col-md-2">[{{ type }}]</td>
    <td class="text-center col-md-4">
      <router-link
        class="text-dark link-underline"
        :to="{ name: 'qnaDetail', params: { articleno: articleno } }"
        >{{ subject }}</router-link
      >
    </td>
    <td class="text-cente col-md-1">{{ userid }}</td>
    <td class="text-center col-md-2">{{ regtime | dateFormat }}</td>
    <td class="text-center text-secondary col-md-2" v-if="state === '답변대기'">
      {{ state }}
    </td>
    <td class="text-center text-primary col-md-2" v-else>
      {{ state }}
    </td>
    <button
      class="btn btn-danger btn-sm text-white bg-danger"
      @click="deleteQuestion"
    >
      삭제
    </button>
  </tr>
</template>

<script>
import moment from "moment";
import swal from "sweetalert";
import { deleteQuestion } from "@/api/qna";
export default {
  name: "QnaAdminListItem",
  props: {
    articleno: Number,
    userid: String,
    subject: String,
    regtime: String,
    state: String,
    type: String,
  },
  filters: {
    dateFormat(regtime) {
      return moment(new Date(regtime)).format("YY.MM.DD");
    },
  },
  methods: {
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
          deleteQuestion(this.articleno, () => {
            this.$router.push({ name: "qnaAdminList" }).catch(() => {});
          });
          swal({
            title: "문의글 삭제 완료!",
            text: "문의글이 정상적으로 삭제되었습니다",
            icon: "success",
            button: "확인",
          })
            .then(this.$router.go())
            .catch(() => {});
        }
      });
    },
  },
};
</script>

<style>
tr.trow:hover {
  color: #6f98ff;
}
</style>
