import { apiInstance } from "./index.js";

const api = apiInstance();

async function listQusetion(success, fail) {
  await api.get(`/qna`).then(success).catch(fail);
}

async function listUserQuestion(userid, success, fail) {
  await api.get(`/qna/list/${userid}`).then(success).catch(fail);
}

async function writeQuestion(question, success, fail) {
  await api.post(`/qna`, JSON.stringify(question)).then(success).catch(fail);
}

async function getQuestion(articleno, success, fail) {
  await api.get(`/qna/${articleno}`).then(success).catch(fail);
}

async function modifyQuestion(question, success, fail) {
  await api
    .patch(`/qna/${question.articleno}`, JSON.stringify(question))
    .then(success)
    .catch(fail);
}

async function deleteQuestion(articleno, success, fail) {
  await api.delete(`/qna/${articleno}`).then(success).catch(fail);
}

async function updateReply(form, success, fail) {
  await api
    .patch(`/qna/reply/${form.articleno}`, JSON.stringify(form))
    .then(success)
    .catch(fail);
}

async function getReply(articleno, success, fail) {
  await api.get(`/qna/reply/${articleno}`).then(success).catch(fail);
}

export {
  listQusetion,
  listUserQuestion,
  writeQuestion,
  getQuestion,
  modifyQuestion,
  deleteQuestion,
  updateReply,
  getReply,
};
