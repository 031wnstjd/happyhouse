<template>
  <div class="map_wrap">
    <div
      id="map"
      style="width: 100%; height: 650px; position: relative; overflow: hidden"
    ></div>
    <ul id="category">
      <li id="BK9" data-order="0" @click="onClickCategory('BK9', 'bank')">
        <span class="category_bg bank"></span>
        은행
      </li>
      <li id="MT1" data-order="1" @click="onClickCategory('MT1', 'mart')">
        <span class="category_bg mart"></span>
        마트
      </li>
      <li id="PM9" data-order="2" @click="onClickCategory('PM9', 'pharmacy')">
        <span class="category_bg pharmacy"></span>
        약국
      </li>
      <li id="OL7" data-order="3" @click="onClickCategory('OL7', 'gasStation')">
        <span class="category_bg oil"></span>
        주유소
      </li>
      <li id="CE7" data-order="4" @click="onClickCategory('CE7', 'cafe')">
        <span class="category_bg cafe"></span>
        카페
      </li>
      <li
        id="CS2"
        data-order="5"
        @click="onClickCategory('CS2', 'convenienceStore')"
      >
        <span class="category_bg store"></span>
        편의점
      </li>
    </ul>
  </div>
</template>

<script>
import { mapState } from "vuex";

const houseStore = "houseStore";

export default {
  name: "HouseDetail",
  data() {
    return {
      map: null,
      mapTypeControl: null,
      zoomControl: null,
      placeOverlay: null,
      contentNode: null,
      markers: [],
      aptMarkers: [],
      currCategory: "",
      infowindow: null,
      ps: null,
      overlay: null,
      categories: [
        "bank",
        "mart",
        "pharmacy",
        "gasStation",
        "cafe",
        "convenienceStore",
      ],
      categoryStatus: {
        bank: "off",
        mart: "off",
        pharmacy: "off",
        gasStation: "off",
        cafe: "off",
        convenienceStore: "off",
      },
    };
  },
  computed: {
    ...mapState(houseStore, ["houses", "house"]),
    myhouse: function () {
      return this.house;
    },
    myhouses: function () {
      return this.houses;
    },
  },
  watch: {
    myhouse: function () {
      console.log("단일 변경");
      this.initKakaoMap();
    },
    myhouses: function () {
      console.log("리스트 변경");
      this.initKakaoMap();
    },
  },
  created() {},
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initKakaoMap();
    } else {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initKakaoMap);
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=7248b8bbab6da34cbb5654285546b9e6&libraries=services";
      document.head.appendChild(script);
    }
  },
  methods: {
    initKakaoMap() {
      /* KAKAO MAP API 부분 */
      const mapContainer = document.getElementById("map");
      const mapOptions = {
        center: new kakao.maps.LatLng(37.5403169, 126.99526),
        level: 7,
      };
      this.placeOverlay = new kakao.maps.CustomOverlay({ zIndex: 1 });
      this.map = new kakao.maps.Map(mapContainer, mapOptions);
      this.mapTypeControl = new kakao.maps.MapTypeControl();
      this.zoomControl = new kakao.maps.ZoomControl();
      this.ps = new kakao.maps.services.Places(this.map);
      this.contentNode = document.createElement("div");
      kakao.maps.event.addListener(this.map, "idle", this.searchPlaces);
      this.map.addControl(
        this.mapTypeControl,
        kakao.maps.ControlPosition.TOPRIGHT,
      );
      this.map.addControl(this.zoomControl, kakao.maps.ControlPosition.RIGHT);
      this.contentNode.className = "placeinfo_wrap";
      this.addEventHandle(
        this.contentNode,
        "mousedown",
        kakao.maps.event.preventMap,
      );
      this.addEventHandle(
        this.contentNode,
        "touchstart",
        kakao.maps.event.preventMap,
      );
      this.placeOverlay.setContent(this.contentNode);
      // 1. 여기서 아파트 리스트의 위치들을 받아온다
      this.displayMarker();
      // this.displayMarker(this.getAptListPositions);
    },
    addEventHandle(target, type, callback) {
      if (target.addEventListener) {
        target.addEventListener(type, callback);
      } else {
        target.attachEvent("on" + type, callback);
      }
    },
    searchPlaces() {
      if (!this.currCategory) {
        return;
      }
      this.placeOverlay.setMap(null);
      this.removeMarker();
      this.ps.categorySearch(this.currCategory, this.placesSearchCB, {
        useMapBounds: true,
      });
    },
    placesSearchCB(data, status) {
      if (status === kakao.maps.services.Status.OK) {
        this.displayPlaces(data);
      }
    },
    displayPlaces(places) {
      const kakaoMap = this;
      const order = document
        .getElementById(this.currCategory)
        .getAttribute("data-order");
      for (let i = 0; i < places.length; i++) {
        const marker = this.addMarker(
          new kakao.maps.LatLng(places[i].y, places[i].x),
          order,
        );
        (function (marker, place) {
          kakao.maps.event.addListener(marker, "click", function () {
            kakaoMap.displayPlaceInfo(place);
          });
        })(marker, places[i]);
      }
    },
    addMarker(position, order) {
      const imageSrc =
          "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/places_category.png",
        imageSize = new kakao.maps.Size(27, 28),
        imgOptions = {
          spriteSize: new kakao.maps.Size(72, 208),
          spriteOrigin: new kakao.maps.Point(46, order * 36),
          offset: new kakao.maps.Point(11, 28),
        },
        markerImage = new kakao.maps.MarkerImage(
          imageSrc,
          imageSize,
          imgOptions,
        ),
        marker = new kakao.maps.Marker({
          position: position,
          image: markerImage,
        });
      marker.setMap(this.map);
      this.markers.push(marker);
      return marker;
    },
    removeMarker() {
      for (let i = 0; i < this.markers.length; i++) {
        this.markers[i].setMap(null);
      }
      this.markers = [];
    },
    displayPlaceInfo(place) {
      let content =
        '<div class="placeinfo">' +
        '   <a class="title" href="' +
        place.place_url +
        '" target="_blank" title="' +
        place.place_name +
        '">' +
        place.place_name +
        "</a>";
      if (place.road_address_name) {
        content +=
          '    <span title="' +
          place.road_address_name +
          '">' +
          place.road_address_name +
          "</span>" +
          '  <span class="jibun" title="' +
          place.address_name +
          '">(지번 : ' +
          place.address_name +
          ")</span>";
      } else {
        content +=
          '    <span title="' +
          place.address_name +
          '">' +
          place.address_name +
          "</span>";
      }
      content +=
        '    <span class="tel">' +
        place.phone +
        "</span>" +
        "</div>" +
        '<div class="after"></div>';
      this.contentNode.innerHTML = content;
      this.placeOverlay.setPosition(new kakao.maps.LatLng(place.y, place.x));
      this.placeOverlay.setMap(this.map);
    },
    onClickCategory(selectedId, key) {
      const id = selectedId;
      if (this.categoryStatus[key] === "off") {
        this.offAllCategoryStatus();
        this.categoryStatus[key] = "on";
      } else {
        this.categoryStatus[key] = "off";
      }
      this.placeOverlay.setMap(null);
      if (this.categoryStatus[key] === "on") {
        this.currCategory = id;
        this.searchPlaces();
      } else {
        this.currCategory = "";
        this.removeMarker();
      }
    },
    offAllCategoryStatus() {
      this.categories.forEach((category) => {
        this.categoryStatus[category] = "off";
      });
    },
    // 마커 표시하기
    displayMarker() {
      // aptMarkers 에 기존 정보가 있다면, 모두 지우기
      if (this.aptMarkers.length > 0) {
        this.aptMarkers.forEach((marker) => marker.setMap(null));
        this.aptMarkers = [];
      }
      // positions 변수에 map 정보 담기
      let temp = [];

      // 검색의 경우
      if (this.houses[0].aptName) {
        console.log("검색");
        temp = this.houses.map((apt) => [apt.lat, apt.lng]);
      }
      console.log(temp);
      const positions = temp.map(
        (position) => new kakao.maps.LatLng(...position),
      );

      if (positions.length > 0) {
        this.aptMarkers = positions.map(
          (position) =>
            new kakao.maps.Marker({
              map: this.map,
              position,
            }),
        );
        this.aptMarkers.forEach((marker, index) => {
          let convertedAptPrice = 0;
          if (this.houses[index].recentPrice) {
            convertedAptPrice = this.convertAptPrice(
              this.houses[index].recentPrice,
            );
          } else {
            convertedAptPrice = 0;
          }
          const content = `
						<div class="wrap">
							<div class="info">
								<div class="title">
									${this.houses[index].aptName}
								</div>
								<div class="body">
									<div class="desc">
										<div class="price">
										${convertedAptPrice}
										</div>
										<div class="address">
											<span class="address__info">
												${this.houses[index].sidoName} ${this.houses[index].gugunName} ${this.houses[index].dongName}
											</span>
											<span class="address__jibun">
												(지번) ${this.houses[index].jibun}
											</span>
										</div>
									</div>
								</div>
							</div>
						</div>
					`;
          const overlay = new kakao.maps.CustomOverlay({
            content: content,
            map: this.map,
            position: marker.getPosition(),
          });
          kakao.maps.event.addListener(marker, "click", function () {
            overlay.setMap(this.map);
          });
        });
        const bounds = positions.reduce(
          (bounds, latlng) => bounds.extend(latlng),
          new kakao.maps.LatLngBounds(),
        );
        this.map.setBounds(bounds);
      }
    },
    convertAptPrice(price) {
      const _price = price.trim();
      if (_price.length <= 5) {
        const thousand = price.replace(",", "");
        return thousand;
      }
      const hundredMillion = _price.substring(0, _price.length - 5);
      const thousand = parseInt(
        _price.substring(_price.length - 5).replace(",", ""),
      );
      const convertedPrice =
        thousand === 0
          ? `${hundredMillion}억`
          : `${hundredMillion}억 ${thousand}`;
      return convertedPrice;
    },
  },
};
</script>

<style>
/* KAKAO MAP API 부분 */
#map {
  overflow: hidden;
  position: absolute;
  width: 100%;
  height: 100%;
  box-sizing: border-box;
}
.map_wrap {
  overflow: hidden;
  position: absolute;
  width: 65%;
  height: 100%;
  box-sizing: border-box;
}
.radius_border {
  border: 1px solid #919191;
  border-radius: 5px;
}

#category {
  overflow: hidden;
  position: absolute;
  top: 10px;
  left: 10px;
  padding: 0;
  z-index: 2;
  border-radius: 5px;
  border: 1px solid gray;
  box-shadow: rgb(0 0 0 / 15%) 0px 2px 2px 0px;
  background: #fff;
}

#category li {
  float: left;
  width: 50px;
  padding: 5px 0;
  border-right: 1px solid rgb(226, 226, 226);
  font-size: 13px;
  font-weight: 300;
  color: #000;
  list-style: none;
  text-align: center;
  transition: all 150ms ease-out 0s;
  cursor: pointer;
}
#category li.on {
  background: #eee;
}
#category li:hover {
  background-color: yellowgreen;
  color: #fff;
}
#category li:last-child {
  margin-right: 0;
  border-right: 0;
}
#category li span {
  display: block;
  margin: 0 auto 5px;
  width: 27px;
  height: 28px;
}
#category li .category_bg {
  background: url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/places_category.png)
    no-repeat;
}
#category li .bank {
  background-position: -8px 0;
}
#category li .mart {
  background-position: -8px -36px;
}
#category li .pharmacy {
  background-position: -8px -72px;
}
#category li .oil {
  background-position: -8px -108px;
}
#category li .cafe {
  background-position: -8px -144px;
}
#category li .store {
  background-position: -8px -180px;
}
#category li .hospital {
  background-position: -8px -72px;
}
#category li.on .category_bg {
  background-position-x: -46px;
}

.placeinfo_wrap {
  position: absolute;
  bottom: 28px;
  left: -150px;
  width: 300px;
}
.placeinfo {
  position: relative;
  width: 100%;
  border-radius: 5px;
  border: 1px solid #ccc;
  border-bottom: 2px solid #ddd;
  background-color: #fff;
}
.placeinfo:nth-of-type(n) {
  border: 0;
  box-shadow: 0px 1px 2px #888;
}
.placeinfo_wrap .after {
  position: relative;
  left: 50%;
  width: 22px;
  height: 12px;
  margin-left: -12px;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png");
  content: "";
}
.placeinfo a,
.placeinfo a:hover,
.placeinfo a:active {
  color: #fff;
  text-decoration: none;
}
.placeinfo a,
.placeinfo span {
  display: block;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}
.placeinfo span {
  font-size: 13px;
  padding: 10px 20px 0 20px;
  cursor: default;
}
.placeinfo .title {
  margin: -1px -1px 0 -1px;
  padding: 10px 15px;
  border-radius: 5px 5px 0 0;
  background: lightblue
    url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/arrow_white.png)
    no-repeat right 14px center;
  font-weight: bold;
  font-size: 14px;
  color: #fff;
}
.placeinfo .tel {
  padding-bottom: 10px;
  color: lightblue;
}
.placeinfo .jibun {
  margin-top: 0;
  padding-top: 0;
  padding-bottom: 0;
  font-size: 11px;
  color: #999;
}

.wrap {
  overflow: hidden;
  position: absolute;
  left: 0;
  z-index: 50;
  bottom: 40px;
  width: 200px;
  margin-left: -100px;
  font-size: 12px;
  line-height: 1.5;
  text-align: left;
}
.wrap * {
  padding: 0;
  margin: 0;
}
.wrap .info {
  overflow: hidden;
  width: 100%;
  border-radius: 5px;
  border-bottom: 2px solid #ccc;
  border-right: 1px solid #ccc;
  background: #fff;
}
.wrap .info:nth-child(1) {
  border: 0;
  box-shadow: 0px 1px 2px #888;
}
.info .title {
  padding: 10px 15px;
  border-bottom: 1px solid #ddd;
  background-color: yellowgreen;
  font-size: 14px;
  font-weight: 500;
  color: #fff;
}
.info .close {
  position: absolute;
  top: 10px;
  right: 50px;
  width: 17px;
  height: 17px;
  color: #888;
}
.info .close:hover {
  cursor: pointer;
}
.info .body {
  display: flex;
  overflow: hidden;
  position: relative;
  padding: 10px 20px;
}
.info .desc {
  display: flex;
  justify-content: space-between;
  flex-direction: column;
  position: relative;
}
.desc .price {
  font-size: 20px;
  font-weight: 500;
  color: yellowgreen;
}
.desc .address {
  display: flex;
  flex-direction: column;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}
.address__info {
  color: lightgray;
}
.address__jibun {
  color: lightgary;
}
.address__info {
  color: lightgray;
}

.address__jibun {
  color: lightgary;
}

.info .img {
  overflow: hidden;
  width: 75px;
  height: 75px;
  margin-right: 15px;
  border: none;
  border-radius: 3px;

  /**
  img {
    width: 75px;
    height: 75px;
    border-radius: 3px;
  }
  */
}

img {
  width: 75px;
  height: 75px;
  border-radius: 3px;
}

.info:after {
  position: absolute;
  left: 50%;
  bottom: 0;
  width: 22px;
  height: 12px;
  margin-left: -12px;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png");
  content: "";
}
</style>
