<template>
  <div>
    <section class="content-app">
      <div class="container-fluid" style="margin-top: 80px">
        <b-row class="cardElement">
          <b-col class="col-md-6">
            <b-card
              :header="$t('biobanks.addBiobank')"
              header-bg-variant="primary"
              header-text-variant="white"
            >
              <b-form @submit="onAddBiobank" @reset="onResetBiobank">
                <b-form-group
                  horizontal
                  label-for="newBiobankName"
                  :label-cols="3"
                  :label="$t('biobanks.biobankName')"
                >
                  <b-form-input
                    id="newBiobankName"
                    type="text"
                    v-model="newBiobank.biobankName"
                    :placeholder="$t('biobanks.biobankName')"
                    required
                  />
                </b-form-group>
                <b-form-group
                  label-for="newBiobankKey"
                  :label-cols="3"
                  :label="$t('biobanks.publicKey')"
                >
                  <b-form-textarea
                    id="newBiobankKey"
                    style="min-height: 140px"
                    v-model="newBiobank.biobankKey"
                    type="text"
                    :placeholder="$t('biobanks.publicKey')"
                    required
                  />
                </b-form-group>

                <b-row class="formButtons" style="padding-right:25px;">
                  <b-btn
                    class="btn-main btn-warning"
                    type="reset"
                    variant="none"
                    >{{ $t("buttons.reset") }}</b-btn
                  >
                  <b-btn
                    type="submit"
                    style="margin-left:10px;"
                    variant="success"
                    >{{ $t("buttons.submit") }}</b-btn
                  >
                </b-row>
              </b-form>
            </b-card>
          </b-col>
          <b-col class="col-md-6">
            <b-card
              :header="$t('biobanks.menageBiobanksPermissions')"
              header-bg-variant="primary"
              header-text-variant="white"
            >
              <b-row>
                <b-col>
                  <b-form-group
                    horizontal
                    label-for="selectBiobank"
                    :label-cols="3"
                    :label="$t('biobanks.biobank')"
                  >
                    <b-form-select v-model="selectedBiobank" id="selectBiobank">
                      <option :value="null">{{
                        $t("biobanks.biobankPlaceholder")
                      }}</option>
                      <option
                        v-for="biobank in biobanks"
                        :key="biobank.name"
                        :value="biobank"
                      >
                        {{ biobank.name }}
                      </option>
                    </b-form-select>
                  </b-form-group>
                </b-col>
              </b-row>
              <b-row v-if="this.selectedBiobank !== null">
                <b-col>
                  <b-form-group label="Accession">
                    <b-form-radio-group v-model="selectedBiobank.accession">
                      <b-form-radio name="some-radios" value="ONLY_PUBLIC">
                        Accessible
                      </b-form-radio>
                      <b-form-radio name="some-radios" value="PUBLIC_PROTECTED">
                        Accessible + Limited access
                      </b-form-radio>
                      <b-form-radio name="some-radios" value="ALL">
                        All
                      </b-form-radio>
                    </b-form-radio-group>
                  </b-form-group>
                </b-col>
              </b-row>
              <b-row
                style="padding-left:25px;"
                v-if="this.selectedBiobank !== null"
              >
                <b-button variant="success" @click="updateBiobankAccession">
                  Save
                </b-button>
                <b-button style="margin-left: 5px" variant="danger" @click="showModal()">
                  Delete Biobank
                </b-button>
              </b-row>
            </b-card>
          </b-col>
        </b-row>
      </div>
    </section>

    <b-modal ref="deleteBiobankModal" hide-footer>
      <div class="d-block text-center">
        <h3>Are you sure you want to delete this Biobank?</h3>
      </div>
      <b-button variant="danger" @click="deleteBiobank">Yes</b-button>
      <b-button variant="success" @click="hideModal()">No</b-button>
    </b-modal>

  </div>
</template>

<script>
import BiobankPermissionService from "@/service/BiobankPermissionService.js";

export default {
  name: "biobanksPermissions",
  mounted() {
    this.getBiobanks();
  },
  data() {
    return {
      biobanks: [],
      newBiobank: {
        biobankName: "",
        biobankKey: ""
      },
      selectedBiobank: null,
      snackbarText: ""
    };
  },
  methods: {
    showModal() {
      this.$refs['deleteBiobankModal'].show()
    },
    hideModal() {
      this.$refs['deleteBiobankModal'].hide()
    },
    getBiobanks() {
      BiobankPermissionService.getBiobanks()
        .then(response => {
          this.biobanks = response.data;
        })
        .catch(function() {
          this.snackbarText = this.$t("permissionsNotifications.errorGettingBiobanks");
          this.$root.$emit("open-snackbar", this.snackbarText);
        });
    },
    onAddBiobank(event) {
      event.preventDefault();
      BiobankPermissionService.addBiobank({
        biobankName: this.newBiobank.biobankName,
        biobankKey: this.newBiobank.biobankKey
      })
        .then(response => {
          this.snackbarText = this.$t(
            "permissionsNotifications.successBiobankAdd"
          );
          this.$root.$emit("open-snackbar", this.snackbarText);
          this.getBiobanks();
          this.onResetBiobank();
        })
        .catch(function() {
          this.snackbarText = this.$t(
            "permissionsNotifications.errorBiobankAdd"
          );
          this.$root.$emit("open-snackbar", this.snackbarText);
        });
    },
    deleteBiobank(event) {
      event.preventDefault();
      BiobankPermissionService.deleteBiobank(this.selectedBiobank)
        .then(response => {
          this.snackbarText = this.$t(
            "permissionsNotifications.successBiobankDelete"
          );
          this.$root.$emit("open-snackbar", this.snackbarText);
          this.getBiobanks();
          this.onResetBiobank();
        })
        .catch(function() {
          this.snackbarText = this.$t(
            "permissionsNotifications.errorBiobankDelete"
          );
          this.$root.$emit("open-snackbar", this.snackbarText);
        });
      this.$refs['deleteBiobankModal'].hide()
    },
    updateBiobankAccession() {
      BiobankPermissionService.updateBiobankAccession(this.selectedBiobank)
        .then(response => {
          this.snackbarText = this.$t(
            "permissionsNotifications.successBiobankUpdate"
          );
          this.$root.$emit("open-snackbar", this.snackbarText);
        })
        .catch(error => {
          this.snackbarText = this.$t(
            "permissionsNotifications.errorUpdateBiobank"
          );
          this.$root.$emit("open-snackbar", this.snackbarText);
        });
    },
    onResetBiobank() {
      this.newBiobank.biobankName = "";
      this.newBiobank.biobankKey = "";
    }
  },
  components: {}
};
</script>

<style scoped>
.adminCard {
  margin-top: 20px;
}

.cardElement {
  margin-top: 10px;
}

.formButtons {
  float: right;
}

.userRoleArrow {
  width: 100%;
}
</style>
