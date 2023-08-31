import Vue from "vue";
import VueRouter from "vue-router";
import HomeView from "../views/HomeView.vue";

import store from "@/store/index.js";
import swal from "sweetalert";

// const originalPush = VueRouter.prototype.push;
// VueRouter.prototype.push = function push(location) {
//   return originalPush.call(this, location).catch(() => {
//     return window.location.reload();
//   });
// };

Vue.use(VueRouter);

// https://router.vuejs.org/kr/guide/advanced/navigation-guards.html
const onlyAuthUser = async (to, from, next) => {
  // console.log(store);
  const checkUserInfo = store.getters["memberStore/checkUserInfo"];
  const getUserInfo = store._actions["memberStore/getUserInfo"];
  let token = sessionStorage.getItem("access-token");
  if (checkUserInfo == null && token) {
    await getUserInfo(token);
  }
  if (checkUserInfo === null) {
    swal(
      "로그인이 필요한 페이지입니다",
      "오른쪽 상단의 로그인 버튼을 클릭해주세요",
      "warning",
    );
    next({ name: "home" });
    // router.push({ name: "signIn" });
  } else {
    // console.log("로그인 했다.");
    next();
  }
};

const routes = [
  {
    path: "*",
    redirect: "/404",
  },
  {
    path: "/404",
    name: "error",
    component: () => import("@/views/PageNotFoundView.vue"),
  },
  {
    path: "/",
    name: "home",
    component: HomeView,
  },
  {
    path: "/user",
    name: "user",
    component: () => import("@/views/MemberView.vue"),
    children: [
      {
        path: "singin",
        name: "signIn",
        component: () => import("@/components/user/MemberLogin.vue"),
      },
      {
        path: "signup",
        name: "signUp",
        component: () => import("@/components/user/MemberRegister.vue"),
      },
      {
        path: "mypage",
        name: "mypage",
        beforeEnter: onlyAuthUser,
        component: () => import("@/components/user/MemberMyPage.vue"),
      },
      {
        path: "modify",
        name: "modify",
        beforeEnter: onlyAuthUser,
        component: () => import("@/components/user/MemberModify.vue"),
      },
      {
        path: "findpwd",
        name: "memberForgetPwd",
        component: () => import("@/components/user/MemberForgetPwd.vue"),
      },
      {
        path: "findid",
        name: "memberForgetId",
        component: () => import("@/components/user/MemberForgetId.vue"),
      },
    ],
  },
  {
    path: "/board",
    name: "board",
    component: () => import("@/views/BoardView.vue"),
    redirect: "/board/list",
    children: [
      {
        path: "list",
        name: "boardList",
        component: () => import("@/components/board/BoardList.vue"),
      },
      {
        path: "write",
        name: "boardRegister",
        beforeEnter: onlyAuthUser,
        component: () => import("@/components/board/BoardRegister.vue"),
      },
      {
        path: "detail/:articleno",
        name: "boardDetail",
        component: () => import("@/components/board/BoardDetail.vue"),
      },
      {
        path: "modify/:articleno",
        name: "boardModify",
        beforeEnter: onlyAuthUser,
        component: () => import("@/components/board/BoardModify.vue"),
      },
    ],
  },
  {
    path: "/qna",
    name: "qna",
    beforeEnter: onlyAuthUser,
    component: () => import("@/views/QnaView.vue"),
    redirect: "/qna/list",
    children: [
      {
        path: "admin/:articleno",
        name: "qnaAdminDetail",
        beforeEnter: onlyAuthUser,
        component: () => import("@/components/qna/QnaAdminDetail.vue"),
      },
      {
        path: "admin",
        name: "qnaAdminList",
        beforeEnter: onlyAuthUser,
        component: () => import("@/components/qna/QnaAdminList.vue"),
      },
      {
        path: "list",
        name: "qnaList",
        beforeEnter: onlyAuthUser,
        component: () => import("@/components/qna/QnaList.vue"),
      },
      {
        path: "write",
        name: "qnaRegister",
        beforeEnter: onlyAuthUser,
        component: () => import("@/components/qna/QnaRegister.vue"),
      },
      {
        path: "detail/:articleno",
        name: "qnaDetail",
        beforeEnter: onlyAuthUser,
        component: () => import("@/components/qna/QnaDetail.vue"),
      },
      {
        path: "modify/:articleno",
        name: "qnaModify",
        beforeEnter: onlyAuthUser,
        component: () => import("@/components/qna/QnaModify.vue"),
      },
    ],
  },
  {
    path: "/house",
    name: "house",
    component: () => import("@/views/HouseView.vue"),
  },
];

const router = new VueRouter({
  scrollBehavior() {
    return { x: 0, y: 0 };
  },
  // mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
