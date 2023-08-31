<template>
  <tr class="trow">
    <td class="text-center">{{ articleno }}</td>
    <td class="text-center text-primary">[{{ type }}]</td>
    <td class="text-center">
      <router-link
        class="text-dark link-underline"
        :to="{ name: 'qnaDetail', params: { articleno: articleno } }"
        >{{ subject }}</router-link
      >
    </td>
    <td class="text-center">{{ userid }}</td>
    <td class="text-center">{{ regtime | dateFormat }}</td>
    <td class="text-center text-secondary" v-if="state === '답변대기'">
      {{ state }}
    </td>
    <td class="text-center text-primary" v-else>
      {{ state }}
    </td>
  </tr>
</template>

<script>
import moment from "moment";

export default {
  name: "QnaListItem",
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
};
</script>

<style>
tr.trow:hover {
  color: #6f98ff;
}
</style>
