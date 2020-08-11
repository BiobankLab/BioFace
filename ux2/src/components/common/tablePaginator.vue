<style scoped>



</style>

<template>
<b-input-group>
  <b-pagination size="md" :total-rows="totalRows" v-model="currentPage" :per-page="perPage" class="sampleTableOptions" align="center" />
  <b-form-group label-for="rowsPerPageSelect" horizontal class="selectRows" breakpoint label="Rows per page: ">
    <b-form-select id="rowsPerPageSelect" size="md" v-model="perPage" :options="rowsPerPageTemplate" />
  </b-form-group>
  <span style="margin-top:8px; margin-left:10px;" v-if="totalRows > 0">showing <strong>{{(currentPage-1)*perPage+1}}</strong> to <strong>{{currentPage*perPage < totalRows ? currentPage*perPage : totalRows}}</strong> of <strong>{{totalRows}}</strong> entries</span>
</b-input-group>
</template>

<script>
export default {
  name: 'tablePaginator',

  data() {
    return {
      rowsPerPageTemplate: this.$store.state.defaultRowsPerPageTemplate,
      currentPage: this.$store.state.defaultPage,
      perPage: this.$store.state.defaultRowsPerPage,
      tabName: ''
    }
  },
  methods: {
    onReset() {
      this.currentPage = 1
    },
    setPagination(element, index) {
      if (this.$store.state.tabsNames[index].tabName === element) {
        this.currentPage = this.$store.state.tabsNames[index].currentPage
        this.perPage = this.$store.state.tabsNames[index].perPage
      }
    }
  },
  mounted: function() {
    this.$root.$on('reset-tabs', () => {
      this.currentPage = 1
      this.perPage = 5
    })
    this.$root.$on('pagination', (name) => {
      this.tabName = name
      this.currentPage = this.$store.state.defaultPage
      this.perPage = this.$store.state.defaultRowsPerPage
      this.$store.state.tabsNames.forEach((e, index) => this.setPagination(name, index))
    })
  },
  watch: {
    perPage: function(newVal, oldVal) {
      this.$emit('on-change-pagination', this.tabName, this.currentPage, this.perPage)
    },
    currentPage: function(newVal, oldVal) {
      this.$emit('on-change-pagination', this.tabName, this.currentPage, this.perPage)
    }
  },
  props: ['totalRows']
}
</script>
