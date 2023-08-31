<template>
  <div class="container mt-15 mb-20">
    <div class="row text-center">
      <h2>1:1 문의 내역</h2>
    </div>
    <div class="d-grid gap-2 d-md-flex justify-content-md-end">
      <button
        type="button"
        class="btn btn-outline-primary mb-3 col-mg-1"
        @click="moveWrite()"
      >
        1:1 문의하기
      </button>
    </div>
    <table
      class="table table-hover mb-5"
      style="border-collapse: collapse; border-radius: 8px"
    >
      <thead class="bg-primary" style="background-color: #6f98ff; border: none">
        <tr style="color: white">
          <th class="text-center w-10">문의번호</th>
          <th class="text-center">문의유형</th>
          <th class="text-center">제목</th>
          <th class="text-center">작성자</th>
          <th class="text-center">작성일</th>
          <th class="text-center">답변상태</th>
        </tr>
      </thead>
      <tbody v-if="questions.length">
        <!-- 하위 component인 ListRow에 데이터 전달(props) -->
        <qna-list-item
          v-for="question in questions"
          :key="question.articleno"
          v-bind="question"
        />
      </tbody>
    </table>
    <nav
      aria-label="Page navigation example"
      class="d-flex justify-content-center"
    >
      <b-pagination
        class="mt-3"
        v-model="currentPage"
        :total-rows="rows"
        :per-page="perPage"
        aria-controls="my-table"
      ></b-pagination>
    </nav>
    <section>
      <div class="container pb-9 pb-lg-11 mt-8">
        <div
          class="px-4 rounded-3 shadow-lg py-6 px-lg-5 py-lg-7 bg-gradient-primary text-white position-relative overflow-hidden"
          data-aos="fade-up"
          data-aos-duration="400"
        >
          <svg
            class="position-absolute end-0 bottom-0 mb-4 text-white opacity-25"
            width="200"
            height="400"
            preserveAspectRatio="none"
            viewBox="0 0 150 300"
            fill="none"
            xmlns="http://www.w3.org/2000/svg"
          >
            <path
              d="M150 300C130.302 300 110.796 296.12 92.5975 288.582C74.3986 281.044 57.8628 269.995 43.934 256.066C30.0052 242.137 18.9563 225.601 11.4181 207.403C3.87986 189.204 -6.2614e-07 169.698 0 150C6.2614e-07 130.302 3.87987 110.796 11.4181 92.5975C18.9563 74.3987 30.0052 57.8628 43.934 43.934C57.8628 30.0052 74.3987 18.9563 92.5975 11.4181C110.796 3.87986 130.302 3.51961e-06 150 5.00679e-06L150 37.5C135.226 37.5 120.597 40.4099 106.948 46.0636C93.299 51.7172 80.8971 60.0039 70.4505 70.4505C60.0039 80.8971 51.7172 93.299 46.0636 106.948C40.4099 120.597 37.5 135.226 37.5 150C37.5 164.774 40.4099 179.403 46.0636 193.052C51.7172 206.701 60.0039 219.103 70.4505 229.55C80.8971 239.996 93.299 248.283 106.948 253.936C120.597 259.59 135.226 262.5 150 262.5V300Z"
              fill="currentColor"
            />
          </svg>

          <div class="row align-items-end position-relative">
            <div class="col-lg-6 col-xl-7">
              <h5 class="text-muted mb-4">
                오늘의 아파트 매물이 궁금하신가요?
              </h5>
              <h1 class="mb-5 mb-md-0 display-4">추천 매물 보러가기</h1>
            </div>
            <div class="col-lg-6 col-xl-5 text-lg-end">
              <router-link
                class="btn btn-lg btn-rise btn-outline-white rounded-pill me-2 mb-2 mb-lg-0"
                style="font-size: 50px"
                :to="{ name: 'house' }"
              >
                <span class="btn-rise-bg bg-white"></span>
                <span class="btn-rise-text">GO!</span>
              </router-link>
            </div>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
import { listUserQuestion } from "@/api/qna.js";
import QnaListItem from "@/components/qna/item/QnaListItem";
import { mapState } from "vuex";

const memberStore = "memberStore";

export default {
  name: "qnaList",
  components: {
    QnaListItem,
  },
  data() {
    return {
      questions: [],
    };
  },
  async created() {
    // userid 이용해서 문의목록 가져오기
    await listUserQuestion(
      this.userInfo.userid,
      (response) => {
        this.questions = response.data;
      },
      (error) => {
        console.log(error);
      },
    );
  },
  methods: {
    moveWrite() {
      this.$router.push({ name: "qnaRegister" }).catch(() => {});
    },
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
};
</script>

<style scope>
.tdClass {
  width: 50px;
  text-align: center;
}
.tdSubject {
  width: 300px;
  text-align: left;
}
.table {
  -webkit-border-radius: 20px;
  -moz-border-radius: 12px;
  overflow: hidden;
  border-radius: 20px;
  overflow: hidden;
}
</style>
