// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import {store} from './store'

import BootstrapVue from 'bootstrap-vue'
import './assets/customize.scss'
import 'bootstrap-vue/dist/bootstrap-vue.css'

Vue.use(BootstrapVue)

import Icon from 'vue-awesome/components/Icon'
import 'vue-awesome/icons'

Vue.component('icon', Icon)

import {i18n} from './locales/locales'

Vue.config.productionTip = false



/* eslint-disable no-new */
new Vue({
  el: '#app',
  i18n,
  store,
  router,
  components: { App },
  template: '<App/>'
})
