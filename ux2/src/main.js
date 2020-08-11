// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import BootstrapVue from 'bootstrap-vue'
import App from './App'
import router from './router'
import './assets/customize.scss'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import {store} from './store'
import Icon from 'vue-awesome/components/Icon'
import 'vue-awesome/icons'
import {i18n} from './locales/locales'
import './tokenConf'
import '@/libs/google_fonts.css'
import '@/libs/animate.css'
import '@/libs/jquery.easing.min.js'
import '@/libs/jquery.scrollme.min.js'
import '@/libs/jquery-picklist.css'
import '@/libs/jquery.ui.widget.js'
import '@/libs/jquery-picklist.js'
require('../node_modules/dc/dc.css')
Vue.component('icon', Icon)
Vue.use(BootstrapVue)
Vue.config.productionTip = false
window.$ = require('jquery')
window.JQuery = require('jquery')

/* eslint-disable no-new */
new Vue({
    el: '#app',
    i18n,
    router,
    store,
    template: '<App/>',
    components: {
        App
    }
})
