import Vue from 'vue'
import Router from 'vue-router'
import DashBoard from '@/components/DashBoard'
import BiobanksPermissions from "../components/BiobanksPermissions";
import Vuetify from "vuetify";
import TokenGenerator from "../components/TokenGenerator";
import UploadSamples from "../components/UploadSamples";
import LoginPage from "../components/LoginPage";

Vue.use(Router)
Vue.use(Vuetify)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'DashBoard',
      component: DashBoard
    },
    {
      path: '/biobanksPermissions',
      name: 'BiobanksPermissions',
      component: BiobanksPermissions
    },
    {
      path: '/tokenGenerator',
      name: 'TokenGenerator',
      component: TokenGenerator
    },
    {
      path: '/uploadSamples',
      name: 'UploadSamples',
      component: UploadSamples
    },
    {
      path: '/login',
      name: 'LoginPage',
      component: LoginPage
    }
  ]
})
