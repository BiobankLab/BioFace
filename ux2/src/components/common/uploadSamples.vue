<style scoped>

.elements-space {
  margin-left: 15px;
}

.btn-main {
  padding: 0px 16px !important;
}
</style>

<template>
<div>
  <b-row>
    <b-form-select v-model="selectedBiobank" style="margin-right: 25px; margin-top: 7px; vertical-align: middle;">
      <option :value="null">{{$t('biobanks.placeholders.choose')}}</option>
      <option v-for="biobank in biobanks" :key="biobank.id" :value="biobank" :disabled="!biobank.canImport">
        {{biobank.biobankId}}
      </option>
    </b-form-select>
    <b-form-file enctype="multipart/form-data" v-model="csvSamples" ref="samplesUpload" :placeholder="$t('samples.upload.placeholder')" style="text-align:left;"></b-form-file>
    <b-btn class="btn-main elements-space bolded" variant="none" @click="showDispersionUploadModal">{{$t('upload')}}</b-btn>
    <b-btn class="btn-main btn-warning elements-space bolded" @click="reset">{{$t('reset')}}</b-btn>
  </b-row>

  <b-modal hide-footer header-bg-variant="danger":title="$t('biobanks.jobs.dialog.error.title')" ref="errorDetailsModal">
    <b-form-textarea id="textarea1"
                     v-model="errorUploadDetails"
                     :rows="10"
                     :max-rows="10"
                     :disabled="true">
    </b-form-textarea>
  </b-modal>

  <b-modal hide-footer :title="$t('biobanks.jobs.dialog.error.dispersionHeader')" ref="dispersionUpload">
    <p>Do you want to upload samples to dispersion module?</p>
    <b-button @click="upload" :pressed.sync="uploadToDispersion">Yes</b-button>
    <b-button @click="upload">No</b-button>
  </b-modal>

</div>
</template>

<script>
import axios from 'axios'
export default {
  name: 'createCollection',
  data() {
    return {
      errorUploadDetails: '',
      csvSamples: null,
      processing: false,
      selectedBiobank: null,
      succesUploadSample: false,
      uploadToDispersion: false
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
    showDispersionUploadModal() {
      this.$refs.dispersionUpload.show()
    },
    upload() {
      let self = this

      this.$refs.dispersionUpload.hide()

      if (self.csvSamples === null || self.processing) {
        self.onErrorMessage(self.$t('samples.upload.message.emptyFile'))
        return
      }

      if (self.selectedBiobank === null) {
        self.onErrorMessage(self.$t('samples.upload.message.emptyBiobank'))
        return
      }

      this.processing = true
      var formData = new FormData()
      formData.append('file', this.csvSamples)
      formData.append('upload', this.uploadToDispersion)

      axios.post('sample/addSamples/' + self.selectedBiobank.biobankId, formData).then(function(response) {
        self.reset()
        self.succesUploadSample = true
        self.$emit('on-success-upload-samples')
        self.onSuccessMessage(self.$t('samples.upload.message.success'))
      }).catch(function(error) {
        console.log(error)
        if (error.response.data.status === 400) {
          self.errorUploadDetails = error.response.data.message
          self.$refs.errorDetailsModal.show()
        } else {
          self.onErrorMessage(self.$t('samples.upload.message.error'))
        }
      })
      this.uploadToDispersion = false
      this.processing = false
    },
    reset() {
      this.$refs.samplesUpload.reset()
    }
  },
  props: ['biobanks']
}
</script>
