import Vue from 'vue'
import Router from 'vue-router'
import Dashboard from '@/components/Dashboard'
import Biobanks from '@/components/biobanks'
import Collections from '@/components/collections'
import Samples from '@/components/samples'
import Projects from '@/components/projects'
import AdminPanel from '@/components/adminPanel'

Vue.use(Router)
var config = require('../../config')
console.log(config)
export default new Router({
  mode: 'history',
  base: process.env.NODE_ENV === 'production' ? config.build.assetsPublicPath : config.dev.assetsPublicPath,
  routes: [
    {
      path: '/',
      name: 'Dashboard',
      component: Dashboard
    },
    {
      path: '/biobanks',
      name: 'Biobanks',
      component: Biobanks
    },
    {
      path: '/collections',
      name: 'Collections',
      component: Collections
    },
    {
      path: '/samples',
      name: 'Samples',
      component: Samples
    },
    {
      path: '/projects',
      name: 'Projects',
      component: Projects
    },
    {
      path: '/adminPanel',
      name: 'Admin Panel',
      component: AdminPanel
    }
  ]
})
