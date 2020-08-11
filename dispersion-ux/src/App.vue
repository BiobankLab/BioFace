<template>
  <div id="app">
    <ux-nav></ux-nav>
    <router-view/>
    <user-message/>
  </div>
</template>

<script>
import UxNav from "./components/Nav";
import message from "./components/Message"
export default {
  name: 'App',
  components: {
    'ux-nav': UxNav,
    'user-message': message
  }
}
</script>

<style>

</style>
