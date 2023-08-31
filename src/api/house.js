import { apiInstance, houseInstance } from "./index.js";

const api = apiInstance();
const house = houseInstance();

function sidoList(success, fail) {
  api.get(`/map/sido`).then(success).catch(fail);
}

function gugunList(params, success, fail) {
  api.get(`/map/gugun`, { params: params }).then(success).catch(fail);
}

// open api 이용
function apiHouseList(params, success, fail) {
  house.get(``, { params: params }).then(success).catch(fail);
}

// db 이용
function houseList(gugunCode, success, fail) {
  api.get(`/map/apt/${gugunCode}`).then(success).catch(fail);
}

function searchHouse(keyword, success, fail) {
  api.get(`/map/${keyword}`).then(success).catch(fail);
}

export { sidoList, gugunList, houseList, apiHouseList, searchHouse };
