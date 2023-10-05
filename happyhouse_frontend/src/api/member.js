import { apiInstance } from "./index.js";

const api = apiInstance();

async function signUp(form, success, fail) {
  await api
    .post(`/users/signup`, JSON.stringify(form))
    .then(success)
    .catch(fail);
}

async function login(user, success, fail) {
  await api
    .post(`/users/signin`, JSON.stringify(user))
    .then(success)
    .catch(fail);
}

async function findById(userid, success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await api.get(`/users/${userid}`).then(success).catch(fail);
}

async function withdrawal(userid, success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await api.delete(`/users/${userid}`).then(success).catch(fail);
}

async function modify(user, success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await api
    .patch(`/users/info`, JSON.stringify(user))
    .then(success)
    .catch(fail);
}

async function modifyPwd(form, success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await api
    .patch(`/users/password`, JSON.stringify(form))
    .then(success)
    .catch(fail);
}

async function idCheck(userid, success, fail) {
  await api.get(`/users/id-check/${userid}`).then(success).catch(fail);
}

async function findIdByEmail(email, success, fail) {
  await api.get(`/users/find-id/${email}`).then(success).catch(fail);
}

async function emailCheck(email, success, fail) {
  await api.get(`/users/email-check/${email}`).then(success).catch(fail);
}

async function findPwdCheck(form, success, fail) {
  await api
    .post(`/users/find-pwd-check`, JSON.stringify(form))
    .then(success)
    .catch(fail);
}

async function findPwd(form, success, fail) {
  await api
    .post(`/users/find-pwd`, JSON.stringify(form))
    .then(success)
    .catch(fail);
}

async function curPwdCheck(form, success, fail) {
  await api
    .post(`/users/current-pwd-check`, JSON.stringify(form))
    .then(success)
    .catch(fail);
}

// function logout(success, fail)

export {
  signUp,
  login,
  findById,
  withdrawal,
  modify,
  modifyPwd,
  idCheck,
  emailCheck,
  findIdByEmail,
  findPwdCheck,
  findPwd,
  curPwdCheck,
};
