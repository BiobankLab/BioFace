<style scoped>
.infoText {
  font-size: 16px;
  line-height: 20px;
}

.card {
  padding-left: 35px;
  padding-right: 35px;
}

.card-header-button {
  background-color: #24d5d8;
  border: none;
  text-align: left;
  color: white;
  font-family: "Poppins";
}

.card-header {
  background-color: #24d5d8;
  margin-bottom: 10px !important;
}

.btn-czat {
  position: fixed;
  bottom: 25px;
  right: 25px;
  padding: 26px 25px 26px 25px;
  font-size: 21px;
  z-index: 99
}

hr {
  border: 0;
  clear: both;
  display: block;
  width: 96%;
  background-color: #000;
  height: 1px;
}

.panel-button {
  margin-left: 35px;
  margin-bottom: 15px;
}

.manage-buttons-col {
  text-align: right;
  margin-right: 35px;
  margin-bottom: 15px;
}

</style>

<template>
<div v-if="project !== null">
  <b-button style="margin-left:35px;" variant="none" class="btn-main btn-lighter btn-czat" @click="onOpenChat()" v-if="project.status !== 'NEW'">
    <icon scale="1.3" name="comment" />
  </b-button>
  <b-card no-body>
    <b-tabs pills card>
      <b-tab :title="$t('projects.manage.tabs.info')">
        <b-row>
          <b-col>
            <b-card no-body>
              <b-card-header header-tag="header" class="p-1" role="tab">
                <b-btn block href="#" v-b-toggle.projectInfo class="card-header-button bolded">{{$t('projects.manage.header.info')}}</b-btn>
              </b-card-header>
              <b-collapse id="projectInfo" visible>
                <b-card-body class="infoText">
                  <p>
                    <span class="bolded">{{$t('projects.manage.labels.name')}}:</span> {{project.name}}
                  </p>
                  <p>
                    <span class="bolded">{{$t('projects.manage.labels.description')}}:</span> {{project.description !== null && project.description !== '' ? project.description : $t('projects.manage.labels.none')}}
                  </p>
                  <p>
                    <span class="bolded">{{$t('projects.manage.labels.status')}}:</span> {{project.status}}
                  </p>
                  <p>
                    <span class="bolded">{{$t('projects.manage.labels.sharedBiobanks')}}: </span> {{formatBiobanksSharingSamples()}}
                  </p>
                </b-card-body>
              </b-collapse>
            </b-card>
          </b-col>
        </b-row>
        <b-row>
          <b-col>
            <b-card no-body>
              <b-card-header header-tag="header" class="p-1" role="tab">
                <b-btn block href="#" v-b-toggle.projectUsersInfo class="card-header-button bolded">{{$t('projects.manage.header.users')}}</b-btn>
              </b-card-header>
              <b-collapse id="projectUsersInfo" visible>
                <b-card-body class="infoText">
                  <ul v-for="user in projectUsers">
                    <li>{{user}}</li>
                  </ul>
                </b-card-body>
              </b-collapse>
            </b-card>
          </b-col>
        </b-row>
      </b-tab>
      <b-tab :title="$t('projects.manage.tabs.queries')">
        <b-row>
          <b-col>
            <b-card no-body>
              <b-card-header header-tag="header" class="p-1" role="tab">
                <b-btn block href="#" v-b-toggle.projectQueriesInfo class="card-header-button bolded">{{$t('projects.manage.header.queries')}}</b-btn>
              </b-card-header>
              <b-collapse id="projectQueriesInfo" visible class="infoText">
                <b-card-body class="infoText">
                  <b-row v-for="(value, index) in project.queries" :key="null">
                    <b-col align="left">
                      <span><span class="bolded">{{index+1}}</span>. {{value}}</span>
                      <b-btn style="padding: 6px 10px; margin-left:10px; margin-bottom:4px;" class="btn-main btn-danger" @click="onRemoveQuery(value)" v-if="canManageProject() && project.status === 'NEW'">
                        <icon scale="1" name="remove" />
                      </b-btn>
                    </b-col>
                    <hr v-if="index !== project.queries.length - 1" />
                  </b-row>
                </b-card-body>
              </b-collapse>
            </b-card>
          </b-col>
        </b-row>
      </b-tab>
      <b-tab :title="$t('projects.manage.tabs.execution')">
        <b-button class="btn-main panel-button bolded" @click="executeProject()" variant="none">
          {{$t('projects.buttons.execute')}}
        </b-button>
        <b-form-checkbox v-model="searchInDispersion" >
          <p>Search in dispersion</p>
        </b-form-checkbox>
        <b-row>
          <b-col>
            <b-card no-body>
              <b-card-header header-tag="header" class="p-1" role="tab">
                <b-btn block href="#" v-b-toggle.accordionQuery class="card-header-button bolded">{{$t('projects.manage.header.execution')}}</b-btn>
              </b-card-header>
              <b-collapse id="accordionQuery" visible>
                <b-card-body v-if="lastProjectExecution !== null" class="infoText">
                  <div>
                    <ul>
                      <span class="bolded">{{$t('projects.table.labels.lastExecutedAt')}}:</span> {{getFormattedDateExecution()}}
                    </ul>
                    <ul>
                      <span class="bolded">{{$t('projects.table.labels.rowsNum')}}:</span> {{lastProjectExecution.rowsNum}}
                    <br>
                      <span v-if="lastProjectExecution.rowsNumDisp > 0" class="bolded">{{$t('projects.table.labels.rowsNumDisp')}}: {{lastProjectExecution.rowsNumDisp}}</span>
                    </ul>
                  </div>
                  <ul v-for="result in lastProjectExecution.result">
                    <div>
                      <b-alert variant="warning" :show="result.showChangeMessage">
                        {{result.changeMessage}}
                      </b-alert>
                      <span class="bolded">{{$t('projects.manage.labels.biobank')}}:</span> {{result.biobank}}
                    </div>
                    <li v-for="(num, name) in result.resultMap">
                      {{name.toLowerCase()}} {{num}}
                    </li>
                  </ul>
                  <b-alert variant="warning" :show="project.biobankSamplesRemoved">
                    {{project.biobankSamplesRemovedMessage}}
                  </b-alert>
                </b-card-body>
                <b-card-body v-if="lastProjectExecution === null">
                  <div>
                    <ul>
                      <b>{{$t('projects.table.labels.lastExecutedAt')}}:</b> {{$t('projects.manage.labels.never')}}
                    </ul>
                  </div>
                </b-card-body>
              </b-collapse>
            </b-card>
          </b-col>
        </b-row>
        <b-tabs pills card content-class="mt3">
          <b-tab @click="onTabClick('Local')" title="Local" active>
            <sample-table :samples="samples" @on-change-sort="onChangeSort" />
            <table-paginator ref="tablePaginator" :totalRows="totalRows" @on-change-pagination="onChangePagination" />
          </b-tab>
          <b-tab @click="onTabClick(dd.name)" v-for="dd in dispersionData" v-if="dd.result.numFound !== 0" :key="dd.name" :title="dd.name">
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
      </b-tab>
      <b-tab :title="$t('projects.manage.tabs.settings')" v-if="fromBiobankFilter() && (fromBiobankList.length > 0 || canManageProject())">
        <b-row v-if="canManageProject()">
          <b-col class="manage-buttons-col">
            <b-button variant="none"  class="btn-main btn-warning" @click="editProject()" v-if="(project.status === 'NEW') && canManageProject(project)">
              <icon scale="1.2" name="edit" />
            </b-button>
            <b-button variant="none" class="btn-main btn-danger" @click="onDeleteProject()" v-if="(project.status === 'NEW') && canManageProject(project)">
              <icon scale="1.2" name="remove" />
            </b-button>
            <b-button variant="none" class="btn-main" @click="onRequestProject()" v-if="project.status === 'NEW' && canManageProject(project)">
              <icon scale="1.2" name="share-square" />
            </b-button>
            <b-button variant="none" class="btn-main btn-danger" @click="onCloseProject()" v-if="project.status === 'REQUESTED' && canManageProject(project)">
              <icon scale="1.2" name="share-square" />
            </b-button>
          </b-col>
        </b-row>
        <b-card no-body v-if="fromBiobankList.length > 0">
          <b-card-header header-tag="header" class="p-1" role="tab">
            <b-btn block href="#" v-b-toggle.accordionInfo class="card-header-button bolded">{{$t('projects.manage.header.shareSamples')}}</b-btn>
          </b-card-header>
          <b-collapse id="accordionInfo" visible>
            <b-card-body>
              <b-input-group prepend="Biobank" style="width:100%; margin-top:0px;">
                <b-form-select v-model="biobankSamplesToShare">
                  <option v-for="biobank in fromBiobankList" :key="biobank" :value="biobank">
                    {{biobank}}
                  </option>
                </b-form-select>
                <b-input-group-append>
                  <b-btn variant="outline-success" @click="shareSamples">{{$t('projects.manage.labels.samplesShare')}}</b-btn>
                </b-input-group-append>
              </b-input-group>
            </b-card-body>
          </b-collapse>
        </b-card>
        <b-card no-body v-if="canManageProject() && project.status === 'NEW'">
          <b-card-header header-tag="header" class="p-1" role="tab">
            <b-btn block href="#" v-b-toggle.accordionInfo class="card-header-button bolded">{{$t('projects.manage.header.shareProject')}}</b-btn>
          </b-card-header>
          <b-collapse id="accordionInfo" visible class="infoText">
            <b-card-body>
              <b-input-group :prepend="$t('projects.manage.labels.username')" style="width:100%; margin-top:0px;">
                <b-form-input v-model="usernameToShare" />
                <b-input-group-append>
                  <b-btn variant="outline-success" @click="shareProject">{{$t('projects.manage.labels.share')}}</b-btn>
                </b-input-group-append>
              </b-input-group>
            </b-card-body>
          </b-collapse>
        </b-card>
      </b-tab>
    </b-tabs>
  </b-card>
  <chat ref="chat" v-if="chatOpen" @on-close-chat="onCloseChat" :messageChannels="messageChannels" :project="project" />
  <b-modal @ok="deleteProject" ok-variant="info" header-bg-variant="info" footer-bg-variant="white" :title="$t('projects.manage.delete.header')" ref="confirmDeleteProject">
    <p>{{deleteProjectConfirmMessage}}</p>
  </b-modal>

  <b-modal @ok="removeQuery" ok-variant="info" header-bg-variant="info" footer-bg-variant="white" :title="$t('projects.manage.query.remove.header')" ref="confirmDeleteQuery">
    <p>{{deleteQueryConfirmMessage}}</p>
  </b-modal>

  <b-modal @ok="dispersionDataConfirmation" ok-variant="info" header-bg-variant="info" footer-bg-variant="white" :title="$t('projects.manage.request.header')" ref="confirmRequestProject">
    <p>{{requestProjectConfirmMessage}}</p>
  </b-modal>

  <b-modal @ok="requestProject('true')" @cancel="requestProject('false')" ok-variant="info" header-bg-variant="info" footer-bg-variant="white" :title="$t('projects.manage.request.header')" ref="confirmDispersionDataRequestProject">
    <p>{{$t('projects.manage.request.dispersion')}}</p>
  </b-modal>

  <b-modal @ok="closeProject" ok-variant="info" header-bg-variant="info" footer-bg-variant="white" :title="$t('projects.manage.close.header')" ref="confirmCloseProject">
    <p>{{closeProjectConfirmMessage}}</p>
  </b-modal>
</div>
</template>

<script>
import axios from 'axios'
import moment from 'moment'
import sampleTable from '@/components/common/sampleTable'
import tablePaginator from '@/components/common/tablePaginator'
import Chat from '@/components/common/Chat'
import Vue from 'vue'
export default {
  name: 'projectsTable',
  data() {
    return {
      samples: [],
      projectUsers: [],
      fromBiobankList: [],
      usernameToShare: '',
      biobankSamplesToShare: '',
      deleteProjectConfirmMessage: '',
      deleteQueryConfirmMessage: '',
      requestProjectConfirmMessage: '',
      sendProjectConfirmMessage: '',
      closeProjectConfirmMessage: '',
      projectQueryAlertVariant: '',
      projectQueryAlert: false,
      lastProjectExecution: null,
      projectQueryAlertContent: '',
      chatOpen: false,
      messageChannels: [],
      msg: '',
      totalRows: 0,
      queryRequest: {
        projectId: '',
        maxRows: this.$store.state.defaultRowsPerPage,
        page: this.$store.state.defaultPage - 1,
        sortField: '',
        sortDesc: false
      },
      searchInDispersion: false,
      dispersionData: [],
      dispersionCount: 0
    }
  },
  methods: {
    onTabClick(name) {
      this.$root.$emit('pagination', name)
    },
    canManageProject() {
      if (localStorage.username === this.project.userId) {
        return true
      }
      return false
    },
    fromBiobankFilter() {
      var el = this.fromBiobankList.find(a => a.includes('_EMPLOYEE'))
      if (el) {
        return false
      } else {
        return true
      }
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
    shareProject() {
      let self = this
      axios.post('project/share', {
        projectId: self.project.id,
        username: self.usernameToShare
      }).then(function(response) {
        self.onSuccessMessage(self.$t('projects.manage.share.success') + ': ' + self.usernameToShare)
        self.getProjectUsers()
      }).catch(function() {
        self.onErrorMessage(self.$t('projects.manage.share.error'))
      })
    },
    shareSamples() {
      let self = this
      axios.post('project/shareSamples', {
        biobank: self.biobankSamplesToShare,
        projectId: self.project.id
      }).then(function(response) {
        self.onSuccessMessage(self.$t('projects.manage.samples.share.success'))
      }).catch(function() {
        self.onErrorMessage(self.$t('projects.manage.samples.share.error'))
      })
    },
    onOpenChat() {
      let self = this
      axios.get('chat/project/' + this.project.id).then(function(response) {
        self.messageChannels = response.data
        self.messageChannels.title = self.$t('project') + ': ' + self.project.name
        self.chatOpen = true
        Vue.nextTick(function () {
          self.$refs.chat.open()
        })
      }).catch(function() {
        self.onErrorMessage(self.$t('chat.init.error'))
        self.chatOpen = false
      })
    },
    onCloseChat() {
      this.chatOpen = false
    },
    calculateProjectLastExecution(project) {
      if (project.projectHistory !== undefined && project.projectHistory !== null) {
        if (project.projectHistory.length === 1) {
          return project.projectHistory[0]
        } else {
          return project.projectHistory.reduce((m, v, i) => (v.executedAt > m.executedAt) && i ? v : m)
        }
      }
      return null
    },
    onProjectSelect() {
      Object.assign(this.$data, this.$options.data.apply(this))
    },
    editProject() {
      this.$emit('on-edit-project', this.project)
    },
    onDeleteProject() {
      this.deleteProjectConfirmMessage = this.$t('projects.manage.delete.confirmation') + ': ' + this.project.name + ' ?'
      this.$refs.confirmDeleteProject.show()
    },
    deleteProject() {
      let self = this
      axios.delete('project/' + self.project.id).then(function(response) {
        self.onSuccessMessage(self.$t('projects.manage.delete.success'))
        self.$emit('on-delete-project-end-success')
      }).catch(function() {
        self.onErrorMessage(self.$t('projects.manage.delete.error'))
      })
    },
    onRemoveQuery(queryValue) {
      this.deleteQueryConfirmMessage = this.$t('projects.manage.query.remove.message') + ': ' + queryValue + ' ?'
      this.queryToRemove = queryValue
      this.$refs.confirmDeleteQuery.show()
    },
    removeQuery() {
      let self = this
      axios.post('project/queryRemove', {
        projectId: this.project.id,
        query: this.queryToRemove
      }).then(function(response) {
        self.$emit('on-manage-project-end-success')
        self.onSuccessMessage(self.$t('projects.manage.query.remove.success'))
      }).catch(function() {
        self.onErrorMessage(self.$t('projects.manage.query.remove.error'))
      })
    },
    onRequestProject() {
      this.requestProjectConfirmMessage = this.$t('projects.manage.request.message') + ' ' + this.project.name
      this.$refs.confirmRequestProject.show()
    },
    dispersionDataConfirmation() {
      this.$refs.confirmDispersionDataRequestProject.show()
    },
    requestProject(disp) {
      let self = this
      axios.put('project/request/' + self.project.id, {disp}).then(function(response) {
        self.onSuccessMessage(self.$t('projects.manage.request.success'))
        self.$emit('on-manage-project-end-success')
      }).catch(function() {
        self.onErrorMessage(self.$t('projects.manage.request.error'))
      })
    },
    onCloseProject(project) {
      this.closeProjectConfirmMessage = this.$t('projects.manage.close.message') + ' ' + this.project.name
      this.$refs.confirmCloseProject.show()
    },
    closeProject() {
      let self = this
      axios.put('project/close/' + self.project.id).then(function(response) {
        self.onSuccessMessage(self.$t('projects.manage.close.success'))
        self.$emit('on-manage-project-end-success')
      }).catch(function() {
        self.onErrorMessage(self.$t('projects.manage.close.error'))
      })
    },
    getFormattedDateExecution() {
      if (this.lastProjectExecution !== null) {
        return moment(this.lastProjectExecution.executedAt).format('YYYY-MM-DD HH:mm:ss')
      } else {
        return this.$t('projects.table.labels.executedNever')
      }
    },
    getValueDiffWithSign(before, after) {
      let diff = after - before
      if (diff > 0) {
        return '+' + diff.toString()
      } else {
        return diff
      }
    },
    onChangePagination(tabName, currentPage, perPage) {
      if (this.queryRequest.projectId !== '') {
        this.savePagination(tabName, currentPage, perPage)
        this.queryRequest.page = currentPage - 1
        this.queryRequest.maxRows = perPage
        this.updateSamplesForProject()
        this.searchDispersionSamples()
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
      if (this.queryRequest.projectId !== '') {
        this.queryRequest.sortField = sortField
        this.queryRequest.sortDesc = sortDesc
        this.updateSamplesForProject()
      }
    },
    updateSamplesForProject() {
      let self = this
      axios.post('project/samples', this.queryRequest).then(function(response) {
        let samples = JSON.parse(response.data.samples)
        self.samples = samples.result
        self.totalRows = samples.numFound
      }).catch(function() {
        self.onErrorMessage(self.$t('projects.manage.execute.get.error'))
      })
    },
    formatBiobanksSharingSamples() {
      let info = this.$t('none')
      if (this.project.biobankShared !== undefined && this.project.biobankShared !== null && this.project.biobankShared.length > 0) {
        info = ''
        this.project.biobankShared.forEach(function(biobank) {
          info += biobank.replace(new RegExp('^BIOBANK_'), '').replace(new RegExp('_PROTECTED$'), '') + ', '
        })

        return info.substring(0, info.length - 2)
      }

      return info
    },
    executeProject() {
      let self = this
      let resultBefore = this.lastProjectExecution
      let changeFound = false
      self.queryRequest.projectId = self.project.id
      self.project.biobankSamplesRemoved = false
      axios.post('project/execute/', self.queryRequest)
        .then(function(response) {
          let samples = JSON.parse(response.data.samples)
          self.samples = samples.result
          self.totalRows = samples.numFound
          self.project.projectHistory = response.data.project.projectHistory
          self.lastProjectExecution = self.calculateProjectLastExecution(response.data.project)

          if (resultBefore !== null) {
            // biobank diff
            self.lastProjectExecution.result.forEach(function(biobankResult) {
              let newBiobankInResult = true
              resultBefore.result.forEach(function(beforeBiobankResult) {
                if (biobankResult.biobank === beforeBiobankResult.biobank) {
                  newBiobankInResult = false
                }
              })
              if (newBiobankInResult) {
                changeFound = true
                biobankResult.showChangeMessage = true
                biobankResult.changeMessage = self.$t('projects.execute.message.new')
              }
            })

            self.project.biobankSamplesRemovedMessage = self.$t('projects.execute.message.removedFromBiobank') + ': '
            resultBefore.result.forEach(function(beforeBiobankResult) {
              let removedBiobank = true
              self.lastProjectExecution.result.forEach(function(biobankResult) {
                if (biobankResult.biobank === beforeBiobankResult.biobank) {
                  removedBiobank = false
                }
              })
              if (removedBiobank) {
                changeFound = true
                self.project.biobankSamplesRemoved = true
                self.project.biobankSamplesRemovedMessage += beforeBiobankResult.biobank + ', '
              }
            })
            // compare samples count
            self.lastProjectExecution.result.forEach(function(result) {
              let biobankResultChangeMessage = ''
              resultBefore.result.forEach(function(before) {
                if (before.biobank === result.biobank) {
                  Object.keys(before.resultMap).forEach(function(beforeResultKey) {
                    Object.keys(result.resultMap).forEach(function(resultKey) {
                      if (beforeResultKey === resultKey) {
                        if (before.resultMap[beforeResultKey] !== result.resultMap[resultKey]) {
                          changeFound = true
                          biobankResultChangeMessage += beforeResultKey + ': ' + '(' + self.getValueDiffWithSign(before.resultMap[beforeResultKey], result.resultMap[resultKey]) + ') '
                        }
                      }
                    })
                  })
                }
              })

              if (biobankResultChangeMessage !== '') {
                result.changeMessage = biobankResultChangeMessage
                result.showChangeMessage = true
              }
            })
          }
          self.projectQueryAlert = true
          if (resultBefore === null) {
            self.projectQueryAlertVariant = 'success'
            self.projectQueryAlertContent = self.$t('projects.execute.message.firstExec')
          } else if (!changeFound) {
            self.projectQueryAlertVariant = 'success'
            self.projectQueryAlertContent = self.$t('projects.execute.message.equal')
          } else {
            self.projectQueryAlertVariant = 'warning'
            self.projectQueryAlertContent = self.$t('projects.execute.message.notEqual')
          }
        }).catch(function() {
          self.onErrorMessage(self.$t('projects.execute.message.error'))
        })
      if (this.searchInDispersion) {
        this.searchDispersionSamples()
      }
    },
    searchDispersionSamples() {
      let self = this
      axios.post('project/executeDisperion/', this.queryRequest).then(function(response) {
        self.dispersionData = response.data
        self.dispersionData.forEach(self.converter)
      }).catch(function() {
        self.onErrorMessage(self.$t('samples.dispersion.get.message.error'))
      })
    },
    converter(element) {
      element.result = JSON.parse(element.result)
    },
    checkIfIsFromBiobank() {
      let self = this
      axios.get('project/fromBiobank/' + this.project.id).then(function(response) {
        self.fromBiobankList = response.data
      }).catch(function() {
        self.onErrorMessage(self.$t('projects.info.error'))
      })
    },
    getProjectUsers() {
      let self = this
      axios.get('project/users/' + this.project.id).then(function(response) {
        self.projectUsers = response.data
      }).catch(function(error) {
        console.log('error', error)
      })
    }
  },
  watch: {
    project: function(newVal, oldVal) {
      this.lastProjectExecution = this.calculateProjectLastExecution(newVal)
      this.checkIfIsFromBiobank()
      this.getProjectUsers()
    }
  },
  components: {
    'sample-table': sampleTable,
    'table-paginator': tablePaginator,
    'chat': Chat
  },
  props: ['project']
}
</script>
