<template>
<div>
  <AutocompleteSearchInput ref="autoCompleteInput" @on-enter="onEnter" v-on:dictionary-request="onDictionaryRequest" />
  <AutocompleteSearchCombo ref="combo" class="combo" v-on:dictionary-val-selected="onDictionaryValueChanged" v-bind:dictionary="currentDictionary" v-on:on-select-exit="onSelectExit" />
</div>
</template>

<script>
import AutocompleteSearchInput from './AutocompleteSearchInput.vue'
import AutocompleteSearchCombo from './AutocompleteSearchCombo.vue'

export default {
  name: 'AutocompleteSearch',
  components: {
    AutocompleteSearchCombo,
    AutocompleteSearchInput
  },
  props: {
    dictionary: Array
  },
  data() {
    return {
      currentDictionary: [],
      startIndex: null,
      endIndex: null
    }
  },
  methods: {
    onEnter(autocompleteText) {
      console.log('search on enter')
      this.$emit('on-enter', autocompleteText)
    },
    onSelectExit() {
      this.currentDictionary = []
      this.$refs.autoCompleteInput.focusInput()
    },
    onDictionaryValueChanged(dictValue) {
      this.$refs.autoCompleteInput.onChooseDictionaryValue(dictValue, this.startIndex, this.endIndex)
      this.currentDictionary = []
    },
    onDictionaryRequest(searchWord, startIndex, endIndex) {
      this.startIndex = startIndex
      this.endIndex = endIndex
      this.currentDictionary = []
      this.dictionary.forEach(val => {
        // case insensitive
        if (val.toLowerCase().indexOf(searchWord.toLowerCase()) !== -1) {
          this.currentDictionary.push(val)
        }
      })
    }
  }
}
</script>

<style>

</style>
