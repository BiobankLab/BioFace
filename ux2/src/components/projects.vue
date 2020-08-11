<style scoped>
.projectChooseHeader {
  background-color: #24d5d8;
}

.activeProject {
  background-color: #6BAED6;
}

.between-panel-space {
  margin-left: 50px;
  margin-right: 50px;
  margin-top: 10px;
}
</style>

<template>
<div class="app">
  <div class="wrap">
    <div class="invisible-wrap">
      <ux-nav />
      <section class="header">
        <img src="@/assets/img/backright.png" width="" height="" alt="paragraf" class="biglogo-right">

        <div class="container-fluid">
          <div class="row">

            <div class="col-sm-12">
              <div class="bar-horisontal"></div>
              <h1><strong>{{$t('projects.header')}}</strong></h1>
            </div>
          </div>
        </div>
      </section>
      <section class="content-app">
        <b-row style="margin-left:50px;">
          <b-col cols="8" lg="4" xl="2">
            <b-btn class="btn btn-main bolded" @click="onAddProject" variant="none">
              Create project
              <icon style="margin-left:5px;" scale="0.9" name="plus" />
            </b-btn>
          </b-col>
          <b-col xl="6" lg="7" cols="10">
            <search-box v-if="false" @on-enter="onEnter" />
          </b-col>
        </b-row>
        <b-row class="between-panel-space">
          <b-col cols="12" class="card-body" lg="4" xl="4" style="color:white;">
            <b-list-group v-if="projectsOwn.length > 0">
              <b-list-group-item class="projectChooseHeader bolded"><b>{{$t('projects.manage.labels.list.owner').toUpperCase()}}</b></b-list-group-item>
              <b-list-group-item v-bind:class="{ activeProject: project.active }" v-for="project in projectsOwn" :key="project.id" @click="onProjectSelect(project)" button>{{project.name}}</b-list-group-item>
            </b-list-group>

            <b-list-group v-if="projectsMember.length > 0">
              <b-list-group-item class="projectChooseHeader bolded"><b>{{$t('projects.manage.labels.list.member').toUpperCase()}}</b></b-list-group-item>
              <b-list-group-item v-bind:class="{ activeProject: project.active }" v-for="project in projectsMember" :key="project.id" @click="onProjectSelect(project)" button>{{project.name}}</b-list-group-item>
            </b-list-group>

            <b-list-group v-if="projectsBiobank.length > 0">
              <b-list-group-item class="projectChooseHeader bolded"><b>{{$t('projects.manage.labels.list.biobank').toUpperCase()}}</b></b-list-group-item>
              <b-list-group-item v-bind:class="{ activeProject: project.active }" v-for="project in projectsBiobank" :key="project.id" @click="onProjectSelect(project)" button>{{project.name}}</b-list-group-item>
            </b-list-group>
          </b-col>

          <b-col cols="12" lg="8" xl="8">
            <project-info @on-manage-project-end-success="onEndManageProjectSuccess" @on-delete-project-end-success="onSuccessDelete" ref="projectInfo" :project="selectedProject" @on-edit-project="onEditProject" />
          </b-col>

        </b-row>
      </section>
      <project-manage @on-manage-project-end-success="onEndManageProjectSuccess" ref="projectManage" class="projectManage" />
      <ux-footer />
    </div>
  </div>
</div>
</template>

<script>
import uxNav from './nav.vue'
import searchBox from '@/components/common/searchBox'
import projectManage from '@/components/common/projectManage'
import tablePaginator from '@/components/common/tablePaginator'
import projectInfo from '@/components/common/projectInfo'
import axios from 'axios'
import footer from '@/components/footer'

export default {
  name: 'projects',
  data() {
    return {
      projectsOwn: [],
      projectsMember: [],
      projectsBiobank: [],
      selectedProject: null,
      showManage: false,
      searched: false,
      queryRequest: {
        query: '',
        totalRows: 0,
        maxRows: this.$store.state.defaultRowsPerPage,
        page: this.$store.state.defaultPage - 1,
        sortField: '',
        sortDesc: false
      },
      selectedId: null
    }
  },
  methods: {
    onEnter(event, value) {
      this.queryRequest.query = value
      this.searchProjects()
    },
    onSuccessDelete() {
      console.log('eer')
      this.searchProjects()
      this.selectedProject = null
    },
    onProjectSelect(project) {
      if (this.selectedId !== project.id) {
        this.$refs.projectInfo.onProjectSelect()
        this.selectedProject = project
        this.deactiveAllProjects()
        this.selectedId = project.id
        project.active = true
      }
    },
    deactiveAllProjects() {
      this.projectsOwn.forEach(function(project) {
        project.active = false
      })
      this.projectsMember.forEach(function(project) {
        project.active = false
      })
      this.projectsBiobank.forEach(function(project) {
        project.active = false
      })
    },
    clearAllProjectsList() {
      this.projectsOwn = []
      this.projectsMember = []
      this.projectsBiobank = []
    },
    searchProjects() {
      let self = this
      axios.post('project/search', this.queryRequest)
        .then(function(response) {
          self.clearAllProjectsList()
          let projects = response.data.resultList
          projects.forEach(function(p) {
            if (p.projectAccessType === 'OWNER') {
              self.projectsOwn.push(p)
            } else if (p.projectAccessType === 'MEMBER') {
              self.projectsMember.push(p)
            } else {
              self.projectsBiobank.push(p)
            }
          })
          self.updateSelectedProject()
        }).catch(function() {
          self.$store.state.showMessage = true
          self.$store.state.messageType = 'error'
          self.$store.state.messageContent = self.$t('projects.search.message.error')
        })
    },
    updateSelectedProject() {
      let self = this
      if (this.selectedProject !== null) {
        this.projectsOwn.forEach(function(project) {
          if (project.id === self.selectedProject.id) {
            self.selectedProject = project
            self.selectedProject.active = true
          }
        })
        this.projectsMember.forEach(function(project) {
          if (project.id === self.selectedProject.id) {
            self.selectedProject = project
            self.selectedProject.active = true
          }
        })
        this.projectsBiobank.forEach(function(project) {
          if (project.id === self.selectedProject.id) {
            self.selectedProject = project
            self.selectedProject.active = true
          }
        })
      }
    },
    onAddProject() {
      this.$refs.projectManage.onAddProject()
    },
    onEditProject(project) {
      this.$refs.projectManage.onEditProject(project)
    },
    onEndManageProjectSuccess() {
      this.searchProjects()
    }
  },
  components: {
    'ux-nav': uxNav,
    'search-box': searchBox,
    'project-manage': projectManage,
    'table-paginator': tablePaginator,
    'project-info': projectInfo,
    'ux-footer': footer
  },
  created() {
    this.searchProjects()
  }
}
</script>
