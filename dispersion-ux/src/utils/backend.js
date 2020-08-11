import axios from "axios";
import router from '.././router/index';
import {store} from ".././store";

const backend = axios.create({
  baseURL: process.env.BACKEND_URL
});

backend.interceptors.request.use(
  function(config) {
    if (localStorage.token) {
      config.headers.Authorization = "Bearer " + localStorage.token;
    } else if(config.url !== 'login/') {
      localStorage.removeItem('token')
      store.commit('changeLogged', {value: false});
      router.push('/login')
      store.commit('changeShowMessage', {value: true});
      store.commit('changeMessageContent', {value: 'You have to log in first'});
      store.commit('changeMessageType', {value: 'error'});
    }

    return config;
  },
  function(error) {
    console.log("error", error);
  }
);

backend.interceptors.response.use(
  function(config){
    return config;
},
  function(error) {
    localStorage.removeItem('token')
    store.commit('changeLogged', {value: false});
    router.push('/login')
    store.commit('changeShowMessage', {value: true});
    store.commit('changeMessageContent', {value: 'You have to log in first'});
    store.commit('changeMessageType', {value: 'error'});
  }
);

export default backend;
