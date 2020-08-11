<template>
<div>
  <b-input-group style="margin-top: 0px;">
    <b-input-group-text slot="append">
      <b-link @click="onEnter"><icon name="search" color="white"></icon></b-link>
    </b-input-group-text>
    <b-form-input name="q" autocomplete="off" v-model="autocompleteText" ref="input" @keyup.enter.native="onEnter" @keyup.ctrl.32.native="emitAutocompleteDictionaryNeed" :placeholder="$t('searchBox.samples.placeholder')">
    </b-form-input>
  </b-input-group>
</div>
</template>

<script>
export default {
  name: 'AutocompleteSearchInput',
  data() {
    return {
      autocompleteText: '',
      lastCharacter: ''
    }
  },
  methods: {
    onChooseDictionaryValue(value, indexFrom, indexTo) {
      this.autocompleteText = this.autocompleteText.substring(0, indexFrom) + value + this.autocompleteText.substring(indexTo, this.autocompleteText.length)
      this.focusInput()
    },
    focusInput() {
      this.$refs.input.focus()
    },
    onEnter(event) {
      this.$emit('on-enter', this.autocompleteText)
    },
    emitAutocompleteDictionaryNeed(event) {
      console.log('emit')
      var endIndex = event.target.selectionStart
      var possibleFieldName = event.target.value

      var wordStartIndex = 0
      var wordEndIndex = 0

      // find space before
      for (var i = endIndex - 1; i >= 0; i--) {
        if (possibleFieldName.charAt(i) === ' ' || possibleFieldName.charAt(i) === '(') {
          // add 1 - first letter of the word needed
          wordStartIndex = i + 1
          break
        }
      }

      // find space after
      for (var j = endIndex; j < possibleFieldName.length; j++) {
        if (possibleFieldName.charAt(j) === ' ' || possibleFieldName.charAt(j) === ')') {
          wordEndIndex = j
          break
        }
      }

      // if there is no space after
      if (wordEndIndex === 0) {
        wordEndIndex = possibleFieldName.length
      }

      possibleFieldName = possibleFieldName.substring(wordStartIndex, wordEndIndex)

      this.$emit('dictionary-request', possibleFieldName, wordStartIndex, wordEndIndex)
    }
  }
}
</script>

<style lang="scss">
#autocomplete {
    width: 25%;
}
</style>
