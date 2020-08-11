<template>
  <div>
    <section class="content-app">
      <div class="container-fluid" style="margin-top: 80px">
        <b-row class="cardElement">
          <b-col class="col-md-12">
            <b-card :header="$t('tokenGenerator.generateToken')" header-bg-variant="primary" header-text-variant="white">
              <b-form>
                <b-form-group label-for="generatedToken" :label-cols="2" :label="$t('tokenGenerator.generatedToken')">
                  <b-form-textarea id="generatedToken" style="min-height: 140px" v-model="token" type="text":placeholder="$t('tokenGenerator.generatedToken')" disabled/>
                </b-form-group>

                <b-row class="formButtons" style="padding-right:25px; justify-content: flex-end">
                  <b-btn type="submit" @click="generateToken" style="margin-left:10px;" variant="success">{{$t('buttons.generate')}}</b-btn>
                </b-row>
              </b-form>
            </b-card>
          </b-col>
        </b-row>
      </div>
    </section>
  </div>
</template>

<script>
  import TokenGeneratorService from '@/service/TokenGeneratorService.js'

  export default {
  name: "TokenGenerator",
  data() {
    return {
      token: ''
    }
  },
  methods: {
    generateToken(event) {
      event.preventDefault()
      TokenGeneratorService.generateToken().then(response => {
        this.token = response.data
      }).catch(function() {
        this.snackbarText = this.$t('permissionsNotifications.errorGettingPermisions')
        this.$root.$emit('open-snackbar', this.snackbarText)
      })
    }
  }
}
</script>

<style scoped>

</style>
