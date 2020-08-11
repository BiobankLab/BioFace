/* eslint-disable vue/require-v-for-key */
<template>
<b-form-select v-model="selected" v-show="dictionary !== undefined && dictionary.length > 0" ref="dictselect" @change="onEnter" @keyup.enter.native="onEnter"  @keyup.esc.native="onExit" :select-size="selectSize">
  <option @click="onEnter" v-for="option in dictionary" :value="option" :key="option">
    <div v-html="$options.filters.highlight(option, 'abc')" />
  </option>
</b-form-select>
</template>

<script>
import Vue from 'vue'
Vue.filter('highlight', function(words, query) {
  return words.replace(query, '<div style="color:red">' + query + '</div>')
})
export default {
  name: 'AutocompleteSearchCombo',
  props: {
    dictionary: Array
  },
  watch: {
    dictionary(newDictionary) {
      this.$nextTick(() => {
        if (newDictionary.length > 0) {
          if (newDictionary.length === 1) {
            this.selectSize = 2
          } else {
            this.selectSize = newDictionary.length < 25 ? newDictionary.length : 25
          }
          this.$refs.dictselect.$el.focus()
        }
      })
    }
  },
  methods: {
    onEnter(event) {
      console.log(event)
      this.$emit('dictionary-val-selected', event.target.value)
    },
    onExit() {
      this.$emit('on-select-exit')
    }
  },
  data() {
    return {
      selected: '',
      selectSize: 2
    }
  }
}
</script>

<style>
.highlight {
  color: red;
}
</style>
