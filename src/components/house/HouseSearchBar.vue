<template>
  <div class="row mt-3 pb-3">
    <!-- 검색 input 창 -->
    <div class="col-3 bg-white text-dark">
      <input
        type="text"
        class="form-control"
        v-model.trim="keyword"
        placeholder="아파트명 입력"
        @keypress.enter="sendKeyword"
      />
    </div>
    <!-- 검색 버튼 -->
    <div class="col-1 bg-white text-dark">
      <button class="btn btn-primary" @click="sendKeyword">검색</button>
    </div>
    <!-- 시,도 구,군 선택 -->
    <div class="d-flex col-7 bg-white text-dark">
      <div class="p-2">
        <b-form-select
          v-model="sidoCode"
          :options="sidos"
          @change="gugunList"
          class="form-select"
        ></b-form-select>
      </div>
      <div class="p-2">
        <b-form-select
          v-model="gugunCode"
          :options="guguns"
          @change="searchApt"
          class="form-select"
        ></b-form-select>
      </div>
    </div>
    <!-- reset 버튼 -->
    <div class="col-1 bg-white text-dark text-end">
      <button class="btn btn-danger mt-2" @click="resetInfo">reset</button>
    </div>
  </div>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";

/*
  namespaced: true를 사용했기 때문에 선언해줍니다.
  index.js 에서 modules 객체의 '키' 이름입니다.

  modules: {
    키: 값
    memberStore: memberStore,
    houseStore: houseStore
  }  
*/
const houseStore = "houseStore";

export default {
  name: "HouseSearchBar",
  data() {
    return {
      sidoCode: null,
      gugunCode: null,
      keyword: "",
    };
  },
  computed: {
    ...mapState(houseStore, ["sidos", "guguns", "houses"]),
    // sidos() {
    //   return this.$store.state.sidos;
    // },
  },
  created() {
    // this.$store.dispatch("getSido");
    // this.sidoList();
    this.CLEAR_SIDO_LIST();
    this.getSido();
  },
  methods: {
    ...mapActions(houseStore, [
      "getSido",
      "getGugun",
      "getHouseList",
      "searchKeyword",
    ]),
    ...mapMutations(houseStore, [
      "CLEAR_SIDO_LIST",
      "CLEAR_GUGUN_LIST",
      "CLEAR_HOUSE_LIST",
    ]),
    // sidoList() {
    //   this.getSido();
    // },
    gugunList() {
      console.log(this.sidoCode);
      // select option 고를 시 리스트 초기화
      this.CLEAR_GUGUN_LIST();
      this.CLEAR_HOUSE_LIST();
      this.gugunCode = null;
      if (this.sidoCode) this.getGugun(this.sidoCode);
    },
    searchApt() {
      if (this.gugunCode) this.getHouseList(this.gugunCode);
    },
    sendKeyword() {
      // 동 선택 정보가 있었다면 초기화
      this.CLEAR_GUGUN_LIST();
      this.sidoCode = null;
      this.gugunCode = null;
      this.getSido();
      // vuex의 house 정보 초기화
      this.CLEAR_HOUSE_LIST();
      // 아파트명으로 검색한 결과로 houseList 만들기
      if (this.keyword) this.searchKeyword(this.keyword);
    },
    resetInfo() {
      // 모든 정보 초기화
      this.keyword = "";
      this.CLEAR_GUGUN_LIST();
      this.CLEAR_HOUSE_LIST();
      this.sidoCode = null;
      this.gugunCode = null;
      this.getSido();
    },
  },
};
</script>

<style></style>
