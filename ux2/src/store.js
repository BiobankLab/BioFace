import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export const store = new Vuex.Store({
  state: {
    defaultPage: 1,
    defaultRowsPerPage: 5,
    defaultRowsPerPageTemplate: [5, 10, 15, 20, 50],
    keycloakClientId: process.env.KEYCLOAK_CLIENT,
    keycloaakRealm: process.env.KEYCLOAK_REALM,
    keycloakAuthUrl: process.env.KEYCLOAK_AUTH_URL,
    username: '',
    isBiobank: false,
    isAdmin: false,
    logged: false,
    showMessage: false,
    messageContent: '',
    messageType: '',
    tabsNames: [
      {
        tabName: '',
        perPage: '',
        currentPage: ''
      }
    ]
  },
  mutations: {
    changeUsername (state, payload) {
      state.username = payload.value
    },
    changeIsBiobank (state, payload) {
      state.isBiobank = payload.value
    },
    changeIsAdmin (state, payload) {
      state.isAdmin = payload.value
    },
    changeLogged (state, payload) {
      state.logged = payload.value
    },
    changeShowMessage (state, payload) {
      state.showMessage = payload.value
    },
    changeMessageContent (state, payload) {
      state.messageContent = payload.value
    }
  }
})
