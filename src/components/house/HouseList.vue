<template>
  <!-- 정보 있을 시 -->
  <div class="container-fluid" v-if="houses && houses.length != 0">
    <div class="row mt-3">
      <div class="col-12 alert alert-info">
        {{ houses.length }} 건의 검색 결과가 있습니다.
      </div>
    </div>
    <div class="row mt-3">
      <house-list-item
        v-for="(house, index) in houses"
        :key="index"
        :house="house"
      />
    </div>
  </div>
  <!-- 정보 없을 시 -->
  <div class="container-fluid" v-else>
    <div class="row mt-3">
      <div class="col-12 alert alert-danger">주택 목록이 없습니다.</div>
    </div>
  </div>
  <!--
  <b-container v-if="houses && houses.length != 0" class="bv-example-row mt-3">
    <b-row>
      <b-col
        ><b-alert show
          >{{ houses.length }} 건의 검색 결과가 있습니다.</b-alert
        ></b-col
      >
    </b-row>
    <house-list-item
      v-for="(house, index) in houses"
      :key="index"
      :house="house"
    />
  </b-container>
  <b-container v-else class="bv-example-row mt-3">
    <b-row>
      <b-col><b-alert show>주택 목록이 없습니다.</b-alert></b-col>
    </b-row>
  </b-container>
  -->
</template>

<script>
import HouseListItem from "@/components/house/HouseListItem.vue";
import { mapState, mapMutations } from "vuex";

const houseStore = "houseStore";

export default {
  name: "HouseList",
  components: {
    HouseListItem,
  },
  data() {
    return {};
  },
  created() {
    // 생성될 때, 리스트 모두 초기화
    this.CLEAR_SIDO_LIST();
    this.CLEAR_GUGUN_LIST();
    this.CLEAR_HOUSE_LIST();
  },
  computed: {
    ...mapState(houseStore, ["houses"]),
    // houses() {
    //   return this.$store.state.houses;
    // },
  },
  methods: {
    ...mapMutations(houseStore, [
      "CLEAR_SIDO_LIST",
      "CLEAR_GUGUN_LIST",
      "CLEAR_HOUSE_LIST",
    ]),
  },
};
</script>

<style></style>
