<style scoped>
.createBiobankButton {
  margin-left: 15px;
  margin-top: 15px;
}

.betweenCardsSpace {
  margin-top: 15px;
}

.cardElementsSpace {
  margin-top: 15px;
}

.elements-margin {
  margin-left: 40px;
  margin-right: 40px;
}

h3 {
  font-weight: 600;
}
</style>

<template>
<div class="app wrap invisible-wrap">
  <ux-nav></ux-nav>
  <section class="header">
    <img src="@/assets/img/backright.png" width="" height="" alt="paragraf" class="biglogo-right">
    <div class="container-fluid">
      <div class="row">
        <div class="col-sm-12">
          <div class="bar-horisontal"></div>
          <h1><strong>{{$t('biobanks.header')}}</strong></h1>
        </div>
      </div>
    </div>
  </section>
  <div class="container-fluid">
    <div class="row">
      <div class="add-to-project">
        <div class="float-left bolded">
          <h3>{{$t('biobanks.cards.header.biobanks')}}</h3>
        </div>
      </div>
    </div>
  </div>

  <b-row class="elements-margin">
    <b-col cols="8" lg="4" xl="2" v-if="isAdmin">
      <b-btn class="btn btn-main create-but" @click="createBiobank">
        <icon name="plus" /> {{ $t("biobanks.buttons.create") }}</b-btn>
    </b-col>
    <b-col xl="6" lg="7" cols="10">
      <search-box @on-enter="onEnter" />
    </b-col>
  </b-row>
  <b-row class="betweenCardsSpace elements-margin">
    <b-col class="col-md-12">
      <biobank-dialog @biobank-submited="onSubmitedBiobank" ref="createBiobankRef" align="left"></biobank-dialog>
      <biobank-table @on-edit-biobank="onEditBiobank" :biobanks="biobanks" @on-change-sort="onChangeSort" ref="biobankTable" />
      <table-paginator ref="tablePaginator" :totalRows="totalRows" @on-change-pagination="onChangePagination" />
    </b-col>
  </b-row>

  <div v-if="!isAdmin">
    <div class="container-fluid">
      <div class="row">
        <div class="add-to-project">
          <div class="float-left bolded">
            <h3>{{$t('biobanks.cards.header.samples')}}</h3>
          </div>
        </div>
      </div>
    </div>

    <b-row class="elements-margin">
      <b-col class="col-md-12">
        <upload-samples @on-success-upload-samples="onSuccessUploadSamples" :biobanks="biobanks" />
        <b-tabs pills card content-class="mt3">
          <b-tab title="Local" @click="onTabClick('Local')">
        <samples-jobs-table :jobs="jobs" class="cardElementsSpace" @on-change-jobs-sort="onChangeJobsSort" />
        <table-paginator ref="jobsTablePaginator" :totalRows="jobsTotalRows" @on-change-pagination="onChangeJobsPagination" />
          </b-tab>
          <b-tab title="Dispersion" @click="onTabClick('Dispersion')">
            <samples-jobs-table :jobs="dispersionJobs" class="cardElementsSpace" @on-change-jobs-sort="onChangeDispersionJobsSort" />
            <table-paginator ref="jobsTablePaginator" :totalRows="dispersionJobsTotalRows" @on-change-pagination="onChangeDispersionJobsPagination" />
          </b-tab>
        </b-tabs>
      </b-col>
    </b-row>
  </div>

  <ux-footer />
</div>
</template>

<script>
import uxNav from './nav.vue'
import searchBox from '@/components/common/searchBox'
import biobankTable from '@/components/common/biobankTable'
import biobankDialog from '@/components/common/biobankDialog'
import tablePaginator from '@/components/common/tablePaginator'
import uploadSamples from '@/components/common/uploadSamples'
import samplesJobsTable from '@/components/common/samplesJobsTable'
import footer from '@/components/footer'
import axios from 'axios'

export default {
  name: 'biobanks',
  data() {
    return {
      searched: false,
      biobanks: [],
      totalRows: 0,
      jobs: [],
      jobsTotalRows: 0,
      jobSearched: false,
      dispersionJobs: [],
      dispersionJobsTotalRows: 0,
      selectedBiobank: null,
      queryRequest: {
        query: '',
        maxRows: this.$store.state.defaultRowsPerPage,
        page: this.$store.state.defaultPage,
        sortField: '',
        sortDesc: false
      },
      jobQueryRequest: {
        query: '',
        maxRows: this.$store.state.defaultRowsPerPage,
        page: this.$store.state.defaultPage,
        sortField: '',
        sortDesc: false
      },
      dispersionJobQueryRequest: {
        query: '',
        maxRows: this.$store.state.defaultRowsPerPage,
        page: this.$store.state.defaultPage,
        sortField: '',
        sortDesc: false
      }
    }
  },
  components: {
    'ux-nav': uxNav,
    'search-box': searchBox,
    'biobank-table': biobankTable,
    'biobank-dialog': biobankDialog,
    'table-paginator': tablePaginator,
    'upload-samples': uploadSamples,
    'samples-jobs-table': samplesJobsTable,
    'ux-footer': footer
  },
  computed: {
    isAdmin: function() {
      if (localStorage.isAdmin && localStorage.isAdmin === 'true') {
        return true
      }
      return false
    }
  },
  methods: {
    onTabClick(name) {
      this.$root.$emit('pagination', name)
    },
    onEnter(event, value) {
      this.queryRequest.query = value
      this.search()
    },
    search() {
      this.searched = true
      this.queryRequest.page = 0
      this.$refs.tablePaginator.onReset()
      this.updateBiobanks()
    },
    updateBiobanks() {
      let self = this
      if (this.searched) {
        axios.post('biobank/search', this.queryRequest).then(function(response) {
          self.biobanks = response.data.resultList
          self.totalRows = response.data.rowsNum
        }).catch(function(error) {
          console.log('error', error.response)
        })
      }
    },
    onChangeSort(sortField, sortDesc) {
      this.queryRequest.sortField = sortField
      this.queryRequest.sortDesc = sortDesc
      this.updateBiobanks()
    },
    onChangePagination(currentPage, perPage) {
      this.queryRequest.page = currentPage - 1
      this.queryRequest.maxRows = perPage
      this.updateBiobanks()
    },
    createBiobank() {
      this.$refs.createBiobankRef.onCreateBiobank()
    },
    onEditBiobank(biobank) {
      this.$refs.createBiobankRef.onEditBiobank(biobank)
    },
    onSubmitedBiobank() {
      this.updateBiobanks()
    },
    searchJobs() {
      this.jobSearched = true
      this.jobQueryRequest.page = 0
      this.dispersionJobQueryRequest.page = 0
      this.$refs.jobsTablePaginator.onReset()
      this.updateJobs()
      this.updateDispersionJobs()
    },
    updateJobs() {
      if (this.jobSearched) {
        let self = this
        axios.post('job/', this.jobQueryRequest).then(function(response) {
          self.jobs = response.data.resultList
          self.jobs.forEach(job => {
            if (job.status === 'ERROR') {
              job._rowVariant = 'danger'
            } else if (job.status === 'FINISHED') {
              job._rowVariant = 'success'
            }
          })
          self.jobsTotalRows = response.data.rowsNum
        })
      }
    },
    updateDispersionJobs() {
      if (this.jobSearched) {
        let self = this
        axios.post('job/dispersion', this.dispersionJobQueryRequest).then(function(response) {
          self.dispersionJobs = response.data.resultList
          self.dispersionJobs.forEach(job => {
            if (job.status === 'ERROR') {
              job._rowVariant = 'danger'
            } else if (job.status === 'FINISHED') {
              job._rowVariant = 'success'
            }
          })
          self.dispersionJobsTotalRows = response.data.rowsNum
        })
      }
    },
    onSuccessUploadSamples() {
      if (this.jobSearched) {
        this.updateJobs()
        this.updateDispersionJobs()
      }
    },
    onChangeJobsSort(sortField, sortDesc) {
      this.jobQueryRequest.sortField = sortField
      this.jobQueryRequest.sortDesc = sortDesc
      this.updateJobs()
    },
    onChangeJobsPagination(tabName, currentPage, perPage) {
      this.jobQueryRequest.page = currentPage
      this.jobQueryRequest.maxRows = perPage
      this.updateJobs()
    },
    onChangeDispersionJobsSort(sortField, sortDesc) {
      this.dispersionJobQueryRequest.sortField = sortField
      this.dispersionJobQueryRequest.sortDesc = sortDesc
      this.updateDispersionJobs()
    },
    onChangeDispersionJobsPagination(tabName, currentPage, perPage) {
      this.dispersionJobQueryRequest.page = currentPage
      this.dispersionJobQueryRequest.maxRows = perPage
      this.updateDispersionJobs()
    }
  },
  mounted() {
    this.search()
    if (!this.isAdmin) {
      this.searchJobs()
    }
  }
}
</script>
