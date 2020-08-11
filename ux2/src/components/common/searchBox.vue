<style scoped>

</style>

<template>
<div>
  <b-input-group style="margin-top: 0px;">
    <b-input-group-text slot="append">
      <b-link @click="onEnter"><icon name="search" color="white"></icon></b-link>
    </b-input-group-text>
    <b-form-input v-model="queryValue" type="text" :placeholder="$t('searchBox.placeholder')" @keyup.enter.native="onEnter">
    </b-form-input>
  </b-input-group>
</div>
</template>

<script>
export default {
  name: 'searchBox',

  data() {
    return {
      queryValue: '',
      response: ''
    }
  },

  methods: {
    onEnter(event) {
      this.$emit('on-enter', event, this.queryValue)
    },
    clearQueryValue() {
      this.queryValue = ''
    }
  }
}
</script>
