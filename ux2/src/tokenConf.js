import axios from 'axios'
import Vue from 'vue'
import Keycloak from 'keycloak-js'
import {store} from './store'
import router from './router'

if (localStorage.username) {
  store.commit('changeUsername', {value: localStorage.username})
}
if (localStorage.isAdmin && localStorage.isAdmin === 'true') {
  store.commit('changeIsAdmin', {value: true})
}
if (localStorage.isBiobank && localStorage.isBiobank === 'true') {
  store.commit('changeIsBiobank', {value: true})
}
if (localStorage.logged && localStorage.logged === 'true') {
  store.commit('changeLogged', {value: true})
}

var keycloak = Vue.prototype.$keycloak = Keycloak()

axios.interceptors.request.use(function(config) {
  if (process.env.NODE_ENV === 'development') {
    config.baseURL = process.env.SERVER_URL
  }

  if (localStorage.token && !config.url.startsWith('public')) {
    config.headers.Authorization = 'Bearer ' + localStorage.token
  } else if (!config.url.startsWith('public')) {
    keycloak.login({
      redirectUri: window.location.origin + router.resolve({
        name: 'Dashboard'
      }).href
    })
  }
  return config
}, function(error) {
  console.log('error', error)
})

axios.get('public/keycloakUrl').then(function(response) {
  keycloak = Vue.prototype.$keycloak = Keycloak({
    url: response.data,
    realm: store.state.keycloaakRealm,
    clientId: store.state.keycloakClientId
  })
  keycloak.init()

  keycloak.onAuthSuccess = function() {
      localStorage.username = keycloak.idTokenParsed.preferred_username
      localStorage.token = keycloak.token
      store.commit('changeUsername', {value: localStorage.username})
      store.commit('changeLogged', {value: true})
      localStorage.logged = true
      axios.get('userroles/').then(function(response) {
          localStorage.isAdmin = response.data.admin
          store.commit('changeIsAdmin', {value: response.data.admin})
          localStorage.isBiobank = response.data.biobank
          store.commit('changeIsBiobank', {value: response.data.biobank})
      }).catch(function(error) {
          console.log('error', error)
      })
  }
})

axios.interceptors.response.use(function(response) {
  return response
}, function(error) {
  if (error.response.status === 401) {
    localStorage.removeItem('token')
    localStorage.removeItem('username')
    localStorage.logged = false
    keycloak.login({
      redirectUri: window.location.origin + router.resolve({
        name: 'Dashboard'
      }).href
    })
  } else {
    return Promise.reject(error)
  }
})
