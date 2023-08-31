<template>
  <div class="container-fluid mt-15">
    <!-- 검색창 & 지역 선택 select -->
    <div class="row mt-3 border">
      <div class="col-12 bg-white text-dark">
        <house-search-bar />
      </div>
    </div>
    <div class="row">
      <!-- 검색 결과 -->
      <div class="col-4 bg-white text-dark border">
        <div class="row mt-3">
          <div class="col-12 addScroll">
            <house-list />
          </div>
        </div>
      </div>
      <!-- map -->
      <div class="col-8 bg-white text-dark border">
        <div class="row mt-3">
          <div class="col-12 addScroll">
            <house-detail />
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<!--
    <b-container class="bv-example-row mt-3 text-center">
      <h3 class="underline-orange">
        <b-icon icon="house-fill"></b-icon> House Service
      </h3>
      <b-row>
        <b-col>
          <house-search-bar></house-search-bar>
        </b-col>
      </b-row>
      <b-row>
        <b-col cols="6" align="left">
          <house-list />
        </b-col>
        <b-col cols="6">
          <house-detail />
        </b-col>
      </b-row>
    </b-container>
    -->
<script>
import HouseSearchBar from "@/components/house/HouseSearchBar.vue";
import HouseList from "@/components/house/HouseList.vue";
import HouseDetail from "@/components/house/HouseDetail.vue";

export default {
  name: "HouseView",
  components: {
    HouseSearchBar,
    HouseList,
    HouseDetail,
  },
};
</script>
<style scoped>
.underline-orange {
  display: inline-block;
  background: linear-gradient(
    180deg,
    rgba(255, 255, 255, 0) 70%,
    rgba(231, 149, 27, 0.3) 30%
  );
}

.addScroll {
  overflow-y: auto;
  height: 650px;
}
</style>
