import axios from "axios";

let url = "http://localhost:9000";

export const userService = {
  addCategory,
  getAllCategory,
  addItem,
  getItembyCategory
};

function addCategory(reqObj) {
  return axios
    .post(`${url}/category`, reqObj)
    .then(response => {
      return Promise.resolve(response);
    })
    .catch(err => {
      return Promise.reject(err);
    });
}

function addItem(reqObj) {
  return axios
    .post(`${url}/item`, reqObj)
    .then(response => {
      return Promise.resolve(response);
    })
    .catch(err => {
      return Promise.reject(err);
    });
}

function getItembyCategory(id) {
  return axios
    .get(`${url}/category/${id}/item`)
    .then(response => {
      return Promise.resolve(response);
    })
    .catch(err => {
      return Promise.reject(err);
    });
}

function getAllCategory() {
  return axios
    .get(`${url}/category`)
    .then(response => {
      return Promise.resolve(response);
    })
    .catch(err => {
      return Promise.reject(err);
    });
}
