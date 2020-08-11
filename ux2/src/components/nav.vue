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
  color: #42b983;
}

#navigation {
  height: 70px;
  border-bottom: solid 4px #64d12f;
  font-size: 18px;
  letter-spacing: 1.8px;
}

#brandLbl {
  font-size: 20px;
  letter-spacing: 1.9px;
}

.nav-link.active {
  color: #64d12f !important;
}
</style>

<template>
<nav class="navbar navbar-expand-md fixed-top navbar-dark bg-light">
  <b-navbar-toggle target="nav_collapse"></b-navbar-toggle>
  <b-navbar-brand to="/"><img src="@/assets/logo-bioface-white2.png" width="161" height="55" class="responsive"/></b-navbar-brand>

    <b-collapse is-nav id="nav_collapse">

      <b-navbar-nav>
        <b-nav-item v-if="logged && (isBiobank || isAdmin)" to="/biobanks">{{ $t("nav.Biobank") }}</b-nav-item>
        <b-nav-item v-if="logged && isBiobank" to="/collections">{{ $t("nav.Collections") }}</b-nav-item>
        <b-nav-item v-if="logged" to="/samples">{{ $t("nav.Samples") }}</b-nav-item>
        <b-nav-item v-if="logged" to="/projects">{{ $t("nav.Projects") }}</b-nav-item>
        <b-nav-item v-if="logged && isAdmin" to="/adminPanel">{{$t("nav.adminPanel")}}</b-nav-item>
      </b-navbar-nav>

      <!-- Right aligned nav items -->
      <b-navbar-nav class="ml-auto">

        <b-nav-item-dropdown right>
          <!-- Using button-content slot -->
          <template slot="button-content">
                   <em>{{ username === '' ? $t('nav.user.unlogged') : username }}</em>
               </template>
          <b-dropdown-item @click="signIn" v-if="!logged">{{ $t('nav.singIn') }}</b-dropdown-item>
          <b-dropdown-item @click="$keycloak.accountManagement()" v-if="logged">{{ $t("nav.Profile") }}</b-dropdown-item>
          <b-dropdown-item @click="signOut" v-if="logged">{{ $t("nav.signout") }}</b-dropdown-item>
        </b-nav-item-dropdown>
      </b-navbar-nav>

    </b-collapse>

</nav>
</template>

<script>
export default {
  name: 'uxNav',
  data() {
    return {
      keycloak: null
    }
  },
  computed: {
    username: function() {
      return this.$store.state.username
    },
    isAdmin: function() {
      return this.$store.state.isAdmin
    },
    isBiobank: function() {
      return this.$store.state.isBiobank
    },
    logged: function() {
      return this.$store.state.logged
    }
  },
  methods: {
    signIn() {
      this.$keycloak.login({
        redirectUri: window.location.origin + this.$router.resolve({
          name: 'Dashboard'
        }).href
      })
    },
    signOut() {
      // clear tokens from localstorage
      localStorage.removeItem('token')
      localStorage.removeItem('username')
      localStorage.removeItem('isAdmin')
      localStorage.removeItem('isBiobank')
      localStorage.logged = false
      this.$keycloak.logout({
        redirectUri: window.location.origin + this.$router.resolve({
          name: 'Dashboard'
        }).href
      })
    }
  }
}
</script>
