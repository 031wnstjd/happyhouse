<template>
  <div
    class="row m-2"
    @click="selectHouse"
    @mouseover="colorChange(true)"
    @mouseout="colorChange(false)"
    :class="{ 'mouse-over-bgcolor': isColor }"
  >
    <div class="col-2 text-center align-self-center">
      <b-img
        thumbnail
        src="https://picsum.photos/250/250/?image=58"
        alt="Image 1"
      ></b-img>
    </div>
    <div class="col-10 align-self-center">
      <span class="fs-5 text-dark" v-if="house.recentPrice">
        매매가 {{ house.recentPrice | recentPrice }}
      </span>
      <span class="fs-5 text-dark" v-else>
        매매가 {{ house.거래금액 | recentPrice }}
      </span>
      <br />
      <span class="text-secondary"
        >{{ house.aptName }}{{ house.아파트 }} <br />({{ house.buildYear
        }}{{ house.건축년도 }}년 건축)
      </span>
      <!--
      <span class="badge bg-secondary text-wrap text-start"
        >{{ house.aptName }}{{ house.아파트 }}
      </span>
      -->
    </div>
  </div>
  <!--
  <b-row
    class="m-2"
    @click="selectHouse"
    @mouseover="colorChange(true)"
    @mouseout="colorChange(false)"
    :class="{ 'mouse-over-bgcolor': isColor }"
  >
    <b-col cols="2" class="text-center align-self-center">
      <b-img
        thumbnail
        src="https://picsum.photos/250/250/?image=58"
        alt="Image 1"
      ></b-img>
    </b-col>
    <b-col cols="10" class="align-self-center">
      [{{ house.일련번호 }} {{ house.dongCode }}] {{ house.아파트 }}
      {{ house.aptName }}
    </b-col>
  </b-row>
  -->
</template>

<script>
import { mapActions } from "vuex";

const houseStore = "houseStore";

export default {
  name: "HouseListItem",
  data() {
    return {
      isColor: false,
    };
  },
  props: {
    house: Object,
  },
  methods: {
    ...mapActions(houseStore, ["detailHouse"]),
    selectHouse() {
      // console.log("listRow : ", this.house);
      // this.$store.dispatch("getHouse", this.house);
      this.detailHouse(this.house);
    },
    colorChange(flag) {
      this.isColor = flag;
    },
  },
  filters: {
    recentPrice: function (value) {
      value = parseInt(value.replace(/,/g, ""));
      let first = "";
      let second = "";
      if (value >= 10000) {
        // 억 이상
        first = parseInt(value / 10000);
        second = value % 10000;
        if (second) {
          value = first + "억 " + second + "만원";
        } else {
          value = first + "억 ";
        }
      } else {
        // 천만원 단위(존재여부 모름)
        value = value + "만원";
      }

      return value;
    },
  },
};
</script>

<style scoped>
.apt {
  width: 50px;
}
.mouse-over-bgcolor {
  background-color: lightblue;
}
</style>
