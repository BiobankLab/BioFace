<style scoped>
h3 {
  font-weight: 600;
}

.elements-margin {
  margin-bottom: 15px;
}
</style>

<template>
<div>

  <div class="wrap app">
    <div class="invisible-wrap">
      <ux-nav></ux-nav>
      <section class="header">
        <img src="@/assets/img/backright.png" width="" height="" alt="paragraf" class="biglogo-right">

        <div class="container-fluid">
          <div class="row">

            <div class="col-sm-12">
              <div class="bar-horisontal"></div>
              <h1><strong>{{$t('collections.header')}}</strong></h1>
            </div>

          </div>
        </div>
      </section>
      <section class="content-app">

        <!-- Add to project SECTION START -->
        <div class="container-fluid" id="sampletable">
          <div class="row">
            <div class="add-to-project">

              <div class="float-left">
                <h3>{{$t('collections.additionalInfo')}}</h3>
              </div>

              <div class="float-right">
              </div>

            </div>
          </div>
        </div>
        <!-- Add to project SECTION END -->

        <div class="container-fluid">
          <b-row class="elements-margin">
            <b-col xl="6" lg="7" cols="10">
              <search-box @on-enter="onEnter" />
            </b-col>
          </b-row>
          <div class="row">
            <!-- RIGHT cestion START -->
            <div class="col-md-12">
              <collection-table @on-delete-collection="onDeleteCollection" :collections="collections" @on-change-sort="onChangeSort"></collection-table>
              <table-paginator ref="jobsTablePaginator" :totalRows="totalRows" @on-change-pagination="onChangePagination" />
            </div>
          </div>
        </div>
      </section>
      <ux-footer />
    </div>
  </div>
</div>
</template>

<script>
import uxNav from './nav.vue'
import searchBox from '@/components/common/searchBox'
import collectionTable from '@/components/common/collectionTable'
import tablePaginator from '@/components/common/tablePaginator'
import uxFooter from './footer.vue'
import axios from 'axios'

export default {
  name: 'collections',
  data() {
    return {
      collections: [],
      totalRows: 0,
      queryRequest: {
        query: '',
        maxRows: this.$store.state.defaultRowsPerPage,
        page: this.$store.state.defaultPage - 1,
        sortField: '',
        sortDesc: false
      }
    }
  },
  components: {
    'ux-nav': uxNav,
    'search-box': searchBox,
    'collection-table': collectionTable,
    'table-paginator': tablePaginator,
    'ux-footer': uxFooter
  },

  created() {
    this.search()
  },
  methods: {
    onEnter(event, value) {
      this.queryRequest.query = value
      this.search()
    },
    onDeleteCollection() {
      this.search()
    },
    search() {
      let self = this
      axios.post('collection/search', this.queryRequest).then(function(response) {
        console.log(response)
        self.collections = response.data.resultList
        self.totalRows = response.data.rowsNum
      }).catch(function(error) {
        console.log('error', error.response)
      })
    },
    onChangePagination(currentPage, perPage) {
      this.queryRequest.page = currentPage - 1
      this.queryRequest.maxRows = perPage
      this.search()
    },
    onChangeSort(sortField, sortDesc) {
      this.queryRequest.sortField = sortField
      this.queryRequest.sortDesc = sortDesc
      this.search()
    }
  }
}
</script>
