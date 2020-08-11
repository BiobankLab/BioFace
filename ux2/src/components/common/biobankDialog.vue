<style scoped>
.createBiobankSubmitButton {
  margin-left: 15px;
}
</style>

<template>
<div>
  <b-form @submit="submitBiobankForm">
    <b-modal ref="createBiobankDialog" :footer-bg-variant="footerBgVariant" no-close-on-backdrop :header-bg-variant="headerBgVariant" :header-text-variant="headerTextVariant" :title="dialogTitle">
      <b-alert :variant="biobankAlertVariant" dismissible :show="biobankAlert" @dismissed="biobankAlert=false">
        {{biobankAlertContent}}
      </b-alert>

      <b-form-group horizontal label-for="biobankId" :label-cols="4" label-size="sm" :label="$t('biobanks.dialog.labels.id')">
        <b-form-input :state="idState" :disabled="edit" id="biobankId" type="text" v-model="biobank.biobankId" :placeholder="$t('biobanks.dialog.placeholders.id')" required aria-describedby="inputLiveFeedback" />
        <b-form-invalid-feedback id="inputLiveFeedback">
          {{this.$t('biobanks.dialog.messages.create.validation')}}
        </b-form-invalid-feedback>
      </b-form-group>

      <b-form-group horizontal label-for="biobankName" :label-cols="4" label-size="sm" :label="$t('biobanks.dialog.labels.name')">
        <b-form-input id="biobankName" type="text" v-model="biobank.name" :placeholder="$t('biobanks.dialog.placeholders.name')" required />
      </b-form-group>

      <b-form-group horizontal label-for="biobankAcronym" :label-cols="4" label-size="sm" :label="$t('biobanks.dialog.labels.acronym')">
        <b-form-input id="biobankAcronym" type="text" v-model="biobank.acronym" :placeholder="$t('biobanks.dialog.placeholders.acronym')" required />
      </b-form-group>

      <b-form-group horizontal label-for="biobankUrl" :label-cols="4" label-size="sm" :label="$t('biobanks.dialog.labels.url')">
        <b-form-input id="biobankUrl" type="text" v-model="biobank.url" :placeholder="$t('biobanks.dialog.placeholders.url')" required />
      </b-form-group>

      <b-form-group horizontal label-for="biobankJuristicPerson" :label-cols="4" label-size="sm" :label="$t('biobanks.dialog.labels.juristicPerson')">
        <b-form-input id="biobankJuristicPerson" type="text" v-model="biobank.juristicPerson" :placeholder="$t('biobanks.dialog.placeholders.juristicPerson')" required />
      </b-form-group>

      <b-form-group horizontal label-for="biobankCountry" :label-cols="4" label-size="sm" :label="$t('biobanks.dialog.labels.country')">
        <b-form-input id="biobankCountry" type="text" v-model="biobank.country" :placeholder="$t('biobanks.dialog.placeholders.country')" required />
      </b-form-group>

      <div slot="modal-footer" class="w-100">
        <b-btn class="float-right createBiobankSubmitButton" variant="info" type="submit">
          {{$t('biobanks.dialog.buttons.submit')}}
        </b-btn>

        <b-btn class="float-right" variant="secondary" @click="onHideCreateBiobankDialog">
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
  name: 'biobankDialog',
  data() {
    return {
      create: false, // create mode
      edit: false, // edit mode
      dialogTitle: '',
      biobank: {
        id: null,
        biobankId: '',
        name: '',
        acronym: '',
        url: '',
        juristicPerson: '',
        country: ''
      },
      biobankAlert: false,
      biobankAlertVariant: '',
      biobankAlertContent: '',
      processing: false,
      headerTextVariant: 'light',
      headerBgVariant: 'info',
      footerBgVariant: 'white'
    }
  },
  computed: {
    idState() {
      return this.biobank.biobankId.length >= 3 &&
        this.biobank.biobankId.length <= 70 &&
        !this.biobank.biobankId.includes('_') &&
        !this.biobank.biobankId.includes(' ')
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
    resetDialog() {
      Object.assign(this.$data, this.$options.data())
    },
    onCreateBiobank() {
      this.resetDialog()
      this.dialogTitle = this.$t('biobanks.dialog.headers.create')
      this.create = true
      this.$refs.createBiobankDialog.show()
    },
    onEditBiobank(biobank) {
      this.resetDialog()
      this.dialogTitle = this.$t('biobanks.dialog.headers.edit')
      this.edit = true
      this.biobank = JSON.parse(JSON.stringify(biobank))
      this.$refs.createBiobankDialog.show()
    },
    onHideCreateBiobankDialog() {
      this.$refs.createBiobankDialog.hide()
    },
    onBiobankAlert(message, variant) {
      this.biobankAlert = true
      this.biobankAlertVariant = variant
      this.biobankAlertContent = message
    },
    submitBiobankForm(event) {
      let self = this
      event.preventDefault()
      if (this.processing === true) {
        return
      }
      this.processing = true
      if (this.create) {
        axios.post('biobank/', this.biobank).then(function(response) {
          self.$refs.createBiobankDialog.hide()
          self.onSuccessMessage(self.$t('biobanks.dialog.messages.create.success'))
          self.$emit('biobank-submited')
        }).catch(function(error) {
          console.log(error)
          if (error.response.data.status === 400) {
            self.onBiobankAlert(self.$t('biobanks.dialog.messages.create.idExists'), 'danger')
          } else {
            self.onBiobankAlert(self.$t('biobanks.dialog.messages.create.error'), 'danger')
          }
        })
      } else if (this.edit) {
        axios.put('biobank/', this.biobank).then(function(response) {
          self.$refs.createBiobankDialog.hide()
          self.onSuccessMessage(self.$t('biobanks.dialog.messages.edit.success'))
          self.$emit('biobank-submited')
        }).catch(function() {
          self.onBiobankAlert(self.$t('biobanks.dialog.messages.edit.error'), 'danger')
        })
      }
      this.processing = false
    }
  }
}
</script>
