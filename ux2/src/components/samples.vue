<style scoped>

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
            <h1><strong>{{$t('samples.header')}}</strong></h1>
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
              <h3>{{$t('samples.additionalInfo')}}</h3>
            </div>

            <b-btn style="margin-top: 2px; margin-left:15px;"
                   class="btn-main float-right"
                   @click="addQueryToProject"
                   variant="secondary">{{$t('samples.project.buttons.addToProject')}}</b-btn>
            <b-form-select class="float-right" v-model="selectedProject">
              <option :value="null">Select project</option>
              <option v-for="project in projects" :key="project.id" :value="project">
                {{project.name}}
              </option>
            </b-form-select>

          </div>
        </div>
      </div>

      <div class="container-fluid">
        <b-row>
          <b-col class="col-md-10">
            <search-input style="margin-bottom: 15px;" ref="searchBox" @on-enter="onEnter" :dictionary="dictionary" />
          </b-col>
          <b-col class="col-md-1">
            <b-form-checkbox switch size="lg" v-model="searchInDispersion" >
              <p style="text-align: center">Search in dispersion</p>
            </b-form-checkbox>
          </b-col>
        </b-row>
        <b-tabs pills card content-class="mt3">
          <b-tab @click="onTabClick('Local')" title="Local" active>
            <sample-table ref="sampleTable" :samples="samples" @on-change-sort="onChangeSort"></sample-table>
            <table-paginator ref="tablePaginator" :totalRows="totalRows" @on-change-pagination="onChangePagination" />
          </b-tab>
          <b-tab @click="onTabClick(dd.name)" v-for="dd in dispersionData"  v-if="dd.result && dd.result.numFound > 0 && showDispersionTab" :key="dd.name" :title="dd.name">
            <div v-if="!dd.error">
              <sample-table :samples="dd.result.result" @on-change-sort="onChangeSort"></sample-table>
              <table-paginator :totalRows="dd.result.numFound" @on-change-pagination="onChangePagination"/>
            </div>
            <div v-else>
              <b-alert
                :show="true"
                variant="danger"
              >
              {{$t('samples.dispersion.get.messageError')}}
            </b-alert>
            </div>
          </b-tab>
        </b-tabs>

      </div>

    </section>
    <ux-footer />
  </div>

</template>

<script>
  import uxNav from './nav.vue'
  import searchBox from '@/components/common/searchBox'
  import sampleTable from '@/components/common/sampleTable'
  import queryParser from '@/libs/parser/query'
  import uploadSamples from '@/components/common/uploadSamples'
  import tablePaginator from '@/components/common/tablePaginator'
  import footer from '@/components/footer'
  import axios from 'axios'
  import AutocompleteSearch from '@/components/common/autocomplete/AutocompleteSearch'
  export default {
    name: 'samples',
    data() {
      return {
        parseErrorMessage: '',
        projects: [],
        samples: [],
        dispersionData: [],
        projectMessage: false,
        projectMessageVariant: '',
        projectMessageContent: '',
        selectedProject: null,
        parseError: false,
        isProjectQuery: false,
        totalRows: 0,
        queryRequest: {
          query: '',
          projectId: '',
          maxRows: this.$store.state.defaultRowsPerPage,
          page: this.$store.state.defaultPage - 1,
          sortField: '',
          sortDesc: false
        },
        dictionary: [],
        searchInDispersion: false,
        showDispersionTab: false,
        checkIfExist: false
      }
    },
    components: {
      'ux-nav': uxNav,
      'search-box': searchBox,
      'sample-table': sampleTable,
      'upload-samples': uploadSamples,
      'table-paginator': tablePaginator,
      'ux-footer': footer,
      'search-input': AutocompleteSearch
    },
    methods: {
      onTabClick(name) {
        this.$root.$emit('pagination', name)
      },
      onEnter(value) {
        this.isProjectQuery = false
        if (value) {
          try {
            queryParser.parse(value)
            this.queryRequest.query = value
            this.parseError = false
          } catch (error) {
            this.parseError = true
            this.onErrorMessage(this.$t('samples.get.parse.error') + '. ' + error.message)
          }
        } else {
          this.queryRequest.query = '*:*'
        }
        this.searchSamples()
        this.selectedProject = null
      },
      searchSamples() {
        if (this.queryStr !== '') {
          this.$root.$emit('clear-sorting')
          this.$root.$emit('reset-tabs')
          this.queryRequest.sortField = ''
          this.queryRequest.page = 0
          this.searched = true
          this.updateSamples()

          if (this.searchInDispersion) {
            this.searchDispersionSamples()
            this.showDispersionTab = true
          } else {
            this.showDispersionTab = false
          }
        }
      },
      searchDispersionSamples() {
        let self = this
        axios.post('sample/searchSample/dispersion', this.queryRequest).then(function(response) {
          self.dispersionData = response.data
          console.log('sample/searchSample/dispersion', response)
          for (let i = 0, len = self.dispersionData.length; i < len; i++) {
            self.dispersionData[i].result = JSON.parse(self.dispersionData[i].result)
          }
        }).catch(function() {
          self.onErrorMessage(self.$t('samples.dispersion.get.message.error'))
        })
      },
      updateSamples() {
        let self = this
        if (this.searched) {
          axios({
            method: 'post',
            url: 'sample/searchSample',
            data: self.queryRequest
          })
            .then(function(response) {
              self.samples = response.data.result
              self.totalRows = response.data.numFound
            })
            .catch(function(error) {
              self.onErrorMessage(error.response.data, 'danger')
            })
        }
      },
      clearSamples() {
        this.samples = []
      },
      onChangePagination(tabName, currentPage, perPage) {
        this.savePagination(tabName, currentPage, perPage)
        this.queryRequest.page = currentPage - 1
        this.queryRequest.maxRows = perPage
        if (this.queryRequest.query !== '') {
          this.updateSamples()
          if (this.searchInDispersion === true) {
            this.searchDispersionSamples()
          }
        } else if (this.queryRequest.projectId !== '') {
          this.executeProject()
        }
      },
      savePagination(tabName, currentPage, perPage) {
        for (let i = 0; i < this.$store.state.tabsNames.length; i++) {
          if (this.$store.state.tabsNames[i].tabName === tabName) {
            this.$store.state.tabsNames[i].currentPage = currentPage
            this.$store.state.tabsNames[i].perPage = perPage
            this.checkIfExist = true
          }
        }
        if (this.checkIfExist === false) {
          this.$store.state.tabsNames.push({
            tabName,
            currentPage,
            perPage
          })
        }
        this.checkIfExist = false
      },
      onChangeSort(sortField, sortDesc) {
        this.queryRequest.sortField = sortField
        this.queryRequest.sortDesc = sortDesc
        if (this.queryRequest.query !== '') {
          this.updateSamples()
          this.searchDispersionSamples()
        } else if (this.queryRequest.projectId !== '') {
          this.executeProject()
        }
      },
      getUserProjects() {
        let self = this
        axios
          .get('project/')
          .then(function(response) {
            self.projects = response.data
          })
          .catch(function() {
            self.onErrorMessage(self.$t('samples.project.get.message.error'))
          })
      },
      onMessage(message) {
        this.$store.state.showMessage = true
        this.$store.state.messageContent = message
      },
      onSuccessMessage(message) {
        this.$store.state.messageType = 'ok'
        this.onMessage(message)
      },
      onErrorMessage(message) {
        this.$store.state.messageType = 'error'
        this.onMessage(message)
      },
      addQueryToProject() {
        let self = this

        if (self.selectedProject === null) {
          self.onErrorMessage(self.$t('samples.project.query.add.message.fail.emptyProject'))
        }

        if (self.queryRequest.query !== '' && self.queryRequest.query !== '*:*') {
          axios
            .post('project/queryAdd', {
              projectId: self.selectedProject.id,
              query: self.queryRequest.query,
              dispersion: self.showDispersionTab
            })
            .then(function(response) {
              self.onSuccessMessage(self.$t('samples.project.query.add.message.success'))
              self.selectedProject = null
            })
            .catch(function() {
              self.onErrorMessage(self.$t('samples.project.query.add.message.error'))
            })
        } else {
          self.onErrorMessage(self.$t('samples.project.query.add.message.fail.emptyQuery'))
        }
      },
      getFieldsList() {
        let self = this
        let excludes = ['_version_', '_text_', 'diseasesIcd10Str', 'sampleMaterialTypeStr', 'diseasesTypeStr']
        axios
          .get('sample/fields')
          .then(function(response) {
            self.dictionary = response.data
            excludes.forEach(ex => {
              self.dictionary.splice(self.dictionary.indexOf(ex), 1)
            })
          })
          .catch(function() {
            self.onErrorMessage(self.$t('samples.fieldslist.error'))
          })
      }
    },
    created() {
      this.getUserProjects()
      this.getFieldsList()
    },
    mounted() {
      this.onEnter()
    }
  }

</script>
