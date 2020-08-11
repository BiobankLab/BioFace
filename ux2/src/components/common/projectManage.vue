<style scoped>
.projectManageButtonSubmit {
  float: right;
}

.projectManageButtonCancel {
  float: right;
  margin-right: 15px;
}

.projectManageForm {}

.projectManageTitle {
  margin-bottom: 15px;
  text-decoration: underline;
}
</style>

<template>
<div class="projectManageForm">
  <b-form @submit="submitProjectForm">
    <b-modal ref="projectManageDialog" no-close-on-backdrop header-bg-variant="info" footer-bg-variant="white" :title="dialogTitle">
      <b-alert @dismissed="errorAlertShow=false" :show="errorAlertShow" variant="danger" dismissible>
        {{errorAlertContent}}
      </b-alert>
      <b-form-group horizontal label-for="projectName" :label-cols="4" :label="$t('projects.manage.labels.name')">
        <b-form-input :state="nameState" horizontal id="projectName" type="text" v-model="project.name" :placeholder="$t('projects.manage.placeholders.name')" required :disabled="!create" aria-describedby="inputLiveFeedback"/>
        <b-form-invalid-feedback id="inputLiveFeedback">
          {{this.$t('projects.manage.message.create.validation')}}
        </b-form-invalid-feedback>
      </b-form-group>

      <b-form-group horizontal label-for="projectDescription" :label-cols="4" :label="$t('projects.manage.labels.description')">
        <b-form-textarea id="projectDescription" v-model="project.description" :placeholder="$t('projects.manage.placeholders.description')" :rows="3" :max-rows="6" />
      </b-form-group>

      <b-form-group horizontal label-for="projectSamplesType" :label-cols="4" :label="$t('projects.manage.labels.samplesType')">
        <b-form-select id="projectSamplesType" multiple v-model="project.sampleType" :options="sampleTypes" required />
      </b-form-group>
      <div slot="modal-footer" class="w-100">
        <b-button class="projectManageButtonSubmit" type="submit" variant="info">{{$t('projects.manage.buttons.submit')}}</b-button>
        <b-button class="projectManageButtonCancel" variant="secondary" @click="onCancel">{{$t('projects.manage.buttons.cancel')}}</b-button>
      </div>
    </b-modal>
  </b-form>
</div>
</template>

<script>
import axios from 'axios'
export default {
  name: 'projectManage',
  data() {
    return {
      dialogTitle: '',
      project: {
        id: null,
        name: '',
        description: '',
        sampleType: []
      },
      sampleTypes: [
        'Physical sample',
        'Data files',
        'Analysing data'
      ],
      create: true,
      errorAlertContent: '',
      errorAlertShow: false
    }
  },
  computed: {
    nameState() {
      return this.project.name.length <= 70 && this.project.name.length >= 3 && !this.project.name.includes('_')
    }
  },
  methods: {
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
    onAddProject() {
      Object.assign(this.$data, this.$options.data())
      this.create = true
      this.dialogTitle = this.$t('projects.manage.title.create')
      this.$refs.projectManageDialog.show()
    },
    onEditProject(project) {
      this.create = false
      this.project = JSON.parse(JSON.stringify(project))
      this.dialogTitle = this.$t('projects.manage.title.edit')
      this.$refs.projectManageDialog.show()
    },
    submitProjectForm(event) {
      event.preventDefault()
      if (!this.nameState) {
        return
      }
      let self = this
      if (this.create) {
        axios.post('project/', this.project).then(function(response) {
          self.$emit('on-manage-project-end-success')
          self.$refs.projectManageDialog.hide()
          self.onSuccessMessage(self.$t('projects.manage.message.create.success'))
        }).catch(function(error) {
          self.errorAlertShow = true
          if (error.response.data.status === 400) {
            self.errorAlertContent = self.$t('projects.manage.message.create.badRequest')
          } else {
            self.errorAlertContent = self.$t('projects.manage.message.create.error')
          }
        })
      } else {
        axios.put('project/', this.project).then(function(response) {
          self.$emit('on-manage-project-end-success')
          self.$refs.projectManageDialog.hide()
          self.onSuccessMessage(self.$t('projects.manage.message.edit.success'))
        }).catch(function() {
          self.errorAlertContent = self.$t('projects.manage.message.edit.error')
        })
      }
    },
    onCancel() {
      this.$refs.projectManageDialog.hide()
    }
  }
}
</script>
