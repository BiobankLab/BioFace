<style scoped>
</style>

<template>
  <b-pagination size="md" :total-rows="totalRows" v-model="currentPage" :per-page="perPage" class="sampleTableOptions" align="center" />
</template>

<script>
export default {
  name: 'tablePaginator',
  data() {
    return {
      currentPage: 1,
      perPage: 5
    }
  },
  watch: {
    perPage: function(newVal, oldVal) {
      this.$emit('on-change-pagination', this.currentPage, this.perPage)
    },
    currentPage: function(newVal, oldVal) {
      this.$emit('on-change-pagination', this.currentPage, this.perPage)
    }
  },
  props: ['totalRows']
}
</script>
