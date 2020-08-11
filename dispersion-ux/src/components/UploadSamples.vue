<style scoped>

.elements-space {
  margin-left: 15px;
}

.formButtons {
  float: right;
  margin-top: 10px;
}

.btn-main {
  padding: 0px 16px !important;
}
</style>

<template>
<div>
  <section style="margin-top: 100px" class="content-app">
  <b-row>
    <b-col class="col-md-4">
    </b-col>
    <b-col class="col-md-4">
      <b-card :header="$t('samples.uploadHeader')" header-bg-variant="primary" header-text-variant="white">
        <b-form-file enctype="multipart/form-data" v-model="file" ref="samplesUpload" :placeholder="$t('samples.upload')" style="text-align:left;"></b-form-file>
        <b-row class="formButtons" style="padding-right:25px;">
          <b-btn class="btn-main btn-warning" @click="reset">{{$t("buttons.reset")}}</b-btn>
          <b-btn variant="success" style="margin-left:10px;" @click="upload">{{$t("buttons.upload")}}</b-btn>
        </b-row>
      </b-card>
    </b-col>
  </b-row>
  </section>
  <b-tabs pills card content-class="mt3">
  <b-tab title="Processes">
  <samples-jobs-table :jobs="jobs" @on-change-jobs-sort="onChangeJobsSort"></samples-jobs-table>
  <table-paginator :totalRows="jobsTotalRows" @on-change-pagination="onChangeJobsPagination"/>
  </b-tab>
  </b-tabs>
</div>
</template>

<script>
import UploadSamplesService from  '@/service/UploadSamplesService.js'
import samplesJobsTable from './SamplesJobsTable'
import tablePaginator from './TablePaginator'
export default {
  name: "uploadSamples",
  data() {
    return {
      file: null,
      snackbarText: '',
      jobs: [],
      jobsTotalRows: 1,
      jobSearched: false,
      jobQueryRequest: {
        query: '',
        maxRows: 5,
        page: 0,
        sortField: '',
        sortDesc: false
      }
    }
  },
  components: {
    'samples-jobs-table': samplesJobsTable,
    'table-paginator': tablePaginator
  },
  methods: {
    upload() {
      let self = this
      var formData = new FormData()
      formData.append('file', this.file)

      UploadSamplesService.uploadSamples(formData).then(response => {
        this.snackbarText = self.$t("samples.successUpload")
        self.$root.$emit('open-snackbar', self.snackbarText)
        this.updateJobs()
      }).catch(function() {
        self.snackbarText = self.$t("samples.errorUpload")
        self.$root.$emit('open-snackbar', self.snackbarText)
      })
    },
    searchJobs() {
      this.jobSearched = true
      this.jobQueryRequest.page = 0
      this.updateJobs()
    },
    updateJobs() {
      if (this.jobSearched) {
        let self = this
        UploadSamplesService.job(this.jobQueryRequest).then(response => {
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
    onChangeJobsPagination(currentPage, perPage) {
      this.jobQueryRequest.page = currentPage
      this.jobQueryRequest.maxRows = perPage
      this.updateJobs()
    },
    onChangeJobsSort(sortField, sortDesc) {
      this.jobQueryRequest.sortField = sortField
      this.jobQueryRequest.sortDesc = sortDesc
      this.updateJobs()
    },
    reset() {
      this.file = null
    }
  },
  mounted() {
      this.searchJobs()
  }
}
</script>
