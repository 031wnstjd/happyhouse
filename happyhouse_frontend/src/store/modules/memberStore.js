import jwt_decode from "jwt-decode";
import { modify, login, withdrawal, modifyPwd } from "@/api/member.js";
import { findById } from "../../api/member";

const memberStore = {
  namespaced: true,
  state: {
    isAdmin: false,
    isLogin: false,
    isLoginError: false,
    userInfo: null,
  },
  getters: {
    checkUserInfo: function (state) {
      return state.userInfo;
    },
    checkId: function (state) {
      return state.isDuplicated;
    },
  },
  mutations: {
    SET_IS_LOGIN: (state, isLogin) => {
      state.isLogin = isLogin;
    },
    SET_IS_LOGIN_ERROR: (state, isLoginError) => {
      state.isLoginError = isLoginError;
    },
    SET_USER_INFO: (state, userInfo) => {
      state.isLogin = true;
      state.userInfo = userInfo;
    },
    SET_IS_ADMIN: (state, isAdmin) => {
      state.isAdmin = isAdmin;
    },
  },

  actions: {
    async userConfirm({ commit }, user) {
      await login(
        user,
        (response) => {
          if (response.data.message === "success") {
            let token = response.data["access-token"];
            commit("SET_IS_LOGIN", true);
            commit("SET_IS_LOGIN_ERROR", false);
            sessionStorage.setItem("access-token", token);
          } else {
            commit("SET_IS_LOGIN", false);
            commit("SET_IS_LOGIN_ERROR", true);
          }
        },
        (error) => {
          console.log(error);
        },
      );
    },
    getUserInfo({ commit }, token) {
      let decode_token = jwt_decode(token);
      findById(
        decode_token.userid,
        (response) => {
          if (response.data.message === "success") {
            commit("SET_USER_INFO", response.data.userInfo); // 암호화된 비밀번호가 들어감 (=> 비밀번호 체크를 백엔드에 요청을 보내서 해야함)
            if (response.data.userInfo.userid === "admin") {
              commit("SET_IS_ADMIN", true);
            } else {
              commit("SET_IS_ADMIN", false);
            }
          } else {
            console.log("유저 정보 없음!!");
          }
        },
        (error) => {
          console.log(error);
        },
      );
    },

    async userDelete({ commit }, userid) {
      await withdrawal(
        userid,
        (response) => {
          if (response.data === "success") {
            commit("SET_IS_LOGIN", false);
            commit("SET_USER_INFO", null);
            sessionStorage.removeItem("access-token");
            console.log("회원탈퇴 성공");
          } else {
            console.log("회원탈퇴 실패");
          }
        },
        (error) => {
          console.log(error);
        },
      );
    },
    async userModify({ commit }, user) {
      await modify(
        user,
        (response) => {
          if (response.data === "success") {
            commit("SET_USER_INFO", user);
            console.log("회원수정 성공");
          } else {
            console.log("회원수정 실패");
          }
        },
        (error) => {
          console.log(error);
        },
      );
    },

    async userPwdModify({ commit }, form) {
      await modifyPwd(
        form,
        (response) => {
          if (response.data === "success") {
            commit("SET_IS_LOGIN", false);
            sessionStorage.removeItem("access-token");
            console.log("비밀번호 변경 성공");
          } else {
            console.log("비밀번호 변경 실패");
          }
        },
        (error) => {
          console.log(error);
        },
      );
    },
  },
};

export default memberStore;
