<style scoped>
.createBiobankSubmitButton {
  margin-left: 15px;
}

</style>

<template>
<div>
  <b-form @submit="submitDispersionInstanceForm">
    <b-modal ref="dispersionInstanceDialog" :footer-bg-variant="footerBgVariant" no-close-on-backdrop :header-bg-variant="headerBgVariant" :header-text-variant="headerTextVariant" :title="dialogTitle">

      <b-form-group horizontal label-for="dispersionInstanceName" :label-cols="4" label-size="sm" :label="$t('adminPanel.forms.createDispersionInstance.labels.name')">
        <b-form-input id="dispersionInstanceName" type="text" :disabled="isEdit === true" v-model="dispersionInstance.name" :placeholder="$t('adminPanel.forms.createDispersionInstance.placeholders.name')" required />
      </b-form-group>

      <b-form-group v-if="showTokenInput" horizontal label-for="dispersionInstanceToken" :label-cols="4" label-size="sm" :label="$t('adminPanel.forms.createDispersionInstance.labels.token')">
        <b-form-textarea id="dispersionInstanceToken" rows="7" type="text" @input=getHtmlFromToken(dispersionInstance.token) v-model="dispersionInstance.token" :placeholder="$t('adminPanel.forms.createDispersionInstance.labels.token')" required />
      </b-form-group>

      <b-form-group horizontal label-for="dispersionInstanceBaseUrl" :label-cols="4" label-size="sm" :label="$t('adminPanel.forms.createDispersionInstance.labels.baseUrl')">
        <b-form-input id="dispersionInstanceBaseUrl" type="text" v-model="dispersionInstance.baseUrl" :placeholder="$t('adminPanel.forms.createDispersionInstance.placeholders.baseUrl')" :disabled="showTokenInput" required />
      </b-form-group>

      <div slot="modal-footer" class="w-100">
        <b-btn class="float-right createBiobankSubmitButton" variant="info" type="submit">
          {{$t('biobanks.dialog.buttons.submit')}}
        </b-btn>

        <b-btn class="float-right" variant="secondary" @click="onHideDispersionInstanceDialog">
          {{$t('biobanks.dialog.buttons.cancel')}}
        </b-btn>
      </div>
    </b-modal>
  </b-form>
</div>
</template>

<script>
import axios from 'axios'
export default {
  name: 'dispersionInstanceDialog',
  data() {
    return {
      dispersionInstance: {
        id: null,
        name: '',
        token: '',
        baseUrl: ''
      },
      headerTextVariant: 'light',
      headerBgVariant: 'info',
      footerBgVariant: 'white',
      dialogTitle: '',
      showTokenInput: false,
      isEdit: false
    }
  },
  methods: {
    resetDialog() {
      Object.assign(this.$data, this.$options.data())
    },
    submitDispersionInstanceForm(event) {
      event.preventDefault()
      let self = this
      if (this.dispersionInstance.baseUrl !== '' && this.dispersionInstance.baseUrl != null) {
        axios.post('admin/dispersion', this.dispersionInstance).then(function(response) {
          self.$emit('on-success-save-dispersion-instance', self.$t('adminPanel.forms.createDispersionInstance.message.success'))
        }).catch(function() {
          self.$emit('on-error-save-dispersion-instance', self.$t('adminPanel.forms.createDispersionInstance.message.error'))
        })
      } else {
        alert(self.$t('adminPanel.forms.createDispersionInstance.message.wrongTokenError'))
      }
    },
    onHideDispersionInstanceDialog() {
      this.resetDialog()
      this.$refs.dispersionInstanceDialog.hide()
    },
    getHtmlFromToken(token) {
      try {
        this.dispersionInstance.baseUrl = JSON.parse(atob(token.split('.')[1])).url
      } catch (e) {
        return null
      }
    },
    onCreateDispersionInstanceWithToken() {
      this.showTokenInput = true
      this.dialogTitle = this.$t('adminPanel.forms.createDispersionInstance.headers.create')
      this.$refs.dispersionInstanceDialog.show()
    },
    onCreateDispersionInstanceWithoutToken() {
      this.resetDialog()
      this.dialogTitle = this.$t('adminPanel.forms.createDispersionInstance.headers.create')
      this.$refs.dispersionInstanceDialog.show()
    },
    onEditDispersionInstance(dispersionInstance) {
      this.resetDialog()
      this.dialogTitle = this.$t('adminPanel.forms.createDispersionInstance.headers.edit')
      Object.assign(this.dispersionInstance, dispersionInstance)
      this.isEdit = true
      this.$refs.dispersionInstanceDialog.show()
    }
  }
}
</script>
