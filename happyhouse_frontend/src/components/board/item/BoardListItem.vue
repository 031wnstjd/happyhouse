<template>
  <tr class="trow">
    <td class="text-center">{{ articleno }}</td>
    <td class="text-center">
      <router-link
        class="text-dark link-underline"
        :to="{ name: 'boardDetail', params: { articleno: articleno } }"
        >{{ subject }}</router-link
      >
    </td>
    <td class="text-center">{{ userid }}</td>
    <td class="text-center">{{ regtime | dateFormat }}</td>
    <td class="text-center">{{ hit }}</td>
  </tr>
</template>

<script>
import moment from "moment";

export default {
  name: "BoardListItem",
  props: {
    articleno: Number,
    subject: String,
    userid: String,
    regtime: String,
    hit: Number,
  },
  filters: {
    dateFormat(regtime) {
      return moment(new Date(regtime)).format("YY.MM.DD");
    },
  },
};
</script>

<style></style>
