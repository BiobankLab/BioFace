import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export const store = new Vuex.Store({
  state: {
    logged: false,
    showMessage: false,
    messageContent: '',
    messageType: ''
  },
  mutations: {
    changeShowMessage(state, payload) {
      state.showMessage = payload.value
    },
    changeMessageContent (state, payload) {
      state.messageContent = payload.value
    },
    changeMessageType (state, payload) {
      state.messageType = payload.value
    },
    changeLogged (state, payload) {
      state.logged = payload.value
    }
  }
})
