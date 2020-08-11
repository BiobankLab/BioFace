<template>
  <b-navbar type="dark" >

      <v-snackbar v-model="snackbar" :timeout="3000" top color="success">
        <span>{{text}}</span>
        <b-btn  @click="snackbar = false">Close</b-btn>
      </v-snackbar>

      <b-navbar-nav>
        <b-nav-item v-if="logged" to="/">{{ $t("app.header") }}</b-nav-item>
        <b-nav-item v-if="logged" to="/biobanksPermissions">{{ $t("nav.BiobanksPermissions") }}</b-nav-item>
        <b-nav-item v-if="logged" to="/tokenGenerator">{{ $t("nav.token") }}</b-nav-item>
        <b-nav-item v-if="logged" to="/uploadSamples">{{ $t("nav.uploadSamples") }}</b-nav-item>
      </b-navbar-nav>

      <b-navbar-nav class="ml-auto">
        <b-nav-item-dropdown v-if="logged" text="LOGOUT" right>
          <b-dropdown-item @click="logout" to="/login" >{{ $t('nav.logout') }}</b-dropdown-item>
        </b-nav-item-dropdown>
        <b-nav-item-dropdown v-if="!logged" text="LOGIN" right>
          <b-dropdown-item to="/login" >{{ $t('nav.login') }}</b-dropdown-item>
        </b-nav-item-dropdown>
      </b-navbar-nav>

  </b-navbar>

</template>

<script>
import Vue from 'vue'
import 'vuetify/dist/vuetify.min.css'
import Vuetify from 'vuetify'


Vue.use(Vuetify)
export default {
    name: 'uxNav',
  data() {
    return {
      snackbar: false,
      text: ''
    }
  },
  created: function () {
      if(localStorage.getItem('token')){
        this.$store.state.logged = true
      }
  },
  computed: {
    logged: function() {
      return this.$store.state.logged
    }
  },
  mounted: function(){
      this.$root.$on('open-snackbar', (text) => {
        this.openSnackbar(text)
      })
  },
  methods: {
    openSnackbar(text) {
      this.text = text
      this.snackbar = true
    },
    logout(){
      localStorage.removeItem('token')
      this.$store.state.logged = false
    }
  },
}
</script>

<style scoped>
  h1,
  h2 {
    font-weight: normal;
  }

  ul {
    list-style-type: none;
    padding: 0;
  }

  li {
    display: inline-block;
    margin: 0 10px;
  }

  a {
    color: #ffffff;
  }
  .navbar{
    background: #006f9b;
  }

</style>
