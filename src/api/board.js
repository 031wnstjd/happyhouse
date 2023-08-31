import { apiInstance } from "./index.js";

const api = apiInstance();

async function listArticle(success, fail) {
  await api.get(`/board/list`).then(success).catch(fail);
}

async function writeArticle(article, success, fail) {
  await api.post(`/board`, JSON.stringify(article)).then(success).catch(fail);
}

async function getArticle(articleno, success, fail) {
  await api.get(`/board/${articleno}`).then(success).catch(fail);
}

async function modifyArticle(article, success, fail) {
  await api
    .put(`/board/${article.articleno}`, JSON.stringify(article))
    .then(success)
    .catch(fail);
}

async function deleteArticle(articleno, success, fail) {
  await api.delete(`/board/${articleno}`).then(success).catch(fail);
}

async function countHit(articleno, success, fail) {
  await api.patch(`/board/${articleno}`).then(success).catch(fail);
}

export {
  listArticle,
  writeArticle,
  getArticle,
  modifyArticle,
  deleteArticle,
  countHit,
};
