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

<template>
<div class="app wrap invisible-wrap">
  <ux-nav />
  <section class="header">
    <img src="@/assets/img/backright.png" width="" height="" alt="paragraf" class="biglogo-right">

    <div class="container-fluid">
      <div class="row">

        <div class="col-sm-12">
          <div class="bar-horisontal"></div>
          <h1><strong>{{$t('adminPanel.header')}}</strong></h1>
        </div>

      </div>
    </div>
  </section>
  <section class="content-app">
    <div class="container-fluid">
      <b-card no-body class="adminCard">
        <b-tabs card pills>
          <b-tab :title="$t('adminPanel.tabs.titles.users')" active>
            <b-row class="cardElement">
              <b-col class="col-md-6">
                <b-card :header="$t('adminPanel.tabs.headers.createUser')" header-bg-variant="info" header-text-variant="white">
                  <b-form @submit="onCreateUser" @reset="resetCreateUserForm">
                    <b-form-group horizontal label-for="newUserUsername" :label-cols="3" :label="$t('adminPanel.forms.createUser.label.username')">
                      <b-form-input id="newUserUsername" type="text" v-model="newUser.username" :placeholder="$t('adminPanel.forms.createUser.placeholder.username')" required />
                    </b-form-group>
                    <b-form-group horizontal label-for="newUserEmail" :label-cols="3" :label="$t('adminPanel.forms.createUser.label.email')">
                      <b-form-input id="newUserEmail" type="text" v-model="newUser.email" :placeholder="$t('adminPanel.forms.createUser.placeholder.email')" required />
                    </b-form-group>

                    <b-row class="formButtons" style="padding-right:25px;">
                      <b-btn class="btn-main btn-warning" type="reset" variant="none">{{$t('adminPanel.forms.createUser.button.reset')}}</b-btn>
                      <b-btn type="submit" class="btn-main" style="margin-left:10px;" variant="none">{{$t('adminPanel.forms.createUser.button.submit')}}</b-btn>
                    </b-row>
                  </b-form>
                </b-card>
              </b-col>
              <b-col class="col-md-6">
                <b-card :header="$t('adminPanel.tabs.headers.userManage')" header-bg-variant="info" header-text-variant="white">
                  <b-form-group horizontal label-for="selectUser" :label-cols="3" :label="$t('adminPanel.forms.addUserRole.label.user')">
                    <b-form-select v-model="selectedUserForManage">
                      <option :value="null">{{$t('adminPanel.forms.addUserRole.placeholder.user')}}</option>
                      <option v-for="user in users" :key="user.username" :value="user" :class="user.enabled ? 'color-green' : 'color-red'">
                        <span style="color: green;">{{user.username}}</span>
                      </option>
                    </b-form-select>
                  </b-form-group>
                  <b-btn style="margin-left:10px;" class="btn-main btn-danger" variant="none" @click="changeUserAvailability(false)">
                    {{$t('adminPanel.forms.manageUser.buttons.disable')}}
                  </b-btn>
                  <b-btn class="btn-main btn-warning" variant="none" @click="changeUserAvailability(true)">
                    {{$t('adminPanel.forms.manageUser.buttons.enable')}}
                  </b-btn>
                </b-card>
              </b-col>
            </b-row>
            <b-row>
              <b-col class="col-md-12">
                <b-card :header="$t('adminPanel.tabs.headers.userRole')" header-bg-variant="info" header-text-variant="white">
                  <b-row>
                    <b-col>
                      <b-form-group horizontal label-for="selectUser" :label-cols="3" :label="$t('adminPanel.forms.addUserRole.label.user')">
                        <b-form-select @change="onSelectUser" ref="selectUserSelect" v-model="selectedUser" id="selectUser">
                          <option :value="null">{{$t('adminPanel.forms.addUserRole.placeholder.user')}}</option>
                          <option v-for="user in users" :key="user.username" :value="user" :class="user.enabled ? 'color-green' : 'color-red'">
                            {{user.username}}
                          </option>
                        </b-form-select>
                      </b-form-group>
                    </b-col>
                  </b-row>
                  <b-row>
                    <b-col class="col-md-5">
                      <b-form-group label-for="selectPotencialRole" :label-cols="3" :label="$t('adminPanel.forms.addUserRole.label.rolesToAdd')">
                        <b-form-select v-model="selectedPotencialRole" id="selectPotencialRole" :select-size="6">
                          <option v-for="role in potencialRoles" :key="role" :value="role">
                            {{role}}
                          </option>
                        </b-form-select>
                      </b-form-group>
                    </b-col>
                    <b-col class="col-md-2" align="center">
                      <b-row class="userRoleArrow">
                        <b-col>
                          <b-btn @click="removeUserRole" variant="danger" style="margin-top: 45px;">
                            <icon name="arrow-left" />
                          </b-btn>
                        </b-col>
                      </b-row>
                      <b-row class="userRoleArrow">
                        <b-col>
                          <b-btn @click="createUserRole" style="margin-top: 15px;" variant="success">
                            <icon name="arrow-right" />
                          </b-btn>
                        </b-col>
                      </b-row>
                    </b-col>

                    <b-col class="col-md-5">
                      <b-form-group label-for="selectRole" :label-cols="3" :label="$t('adminPanel.forms.addUserRole.label.userRoles')">
                        <b-form-select v-model="selectedRole" id="selectRole" :select-size="6">
                          <option v-for="role in roles" :key="role" :value="role">
                            {{role}}
                          </option>
                        </b-form-select>
                      </b-form-group>
                    </b-col>
                  </b-row>
                </b-card>
              </b-col>
            </b-row>
          </b-tab>
          <b-tab :title="$t('adminPanel.tabs.titles.dispersion')">
            <b-row>
              <b-col>
              <b-btn class="btn btn-main create-but" style="margin-bottom: 15px;" @click="createDispersionInstanceWithToken">
                <icon name="plus" /> {{ $t("adminPanel.dispersion.buttons.createWithToken") }}
              </b-btn>
                <b-btn class="btn btn-main create-but" style="margin-bottom: 15px;" @click="createDispersionInstanceWithoutToken">
                  <icon name="plus" /> {{ $t("adminPanel.dispersion.buttons.createWithoutToken") }}
                </b-btn>
              </b-col>
            </b-row>
            <b-row>
              <b-col class="col-md-12">
              <b-card :header="$t('adminPanel.tabs.headers.dispersionManage')" header-bg-variant="info" header-text-variant="white">
                <b-table show-empty :empty-text="$t('table.emtpy')" :items="dispersionInstances" striped responsive bordered no-local-sorting :fields="dispersionFields">
                  <template slot="actions" slot-scope="row">
                      <b-link @click="onDeleteDispersionInstance(row.item)">
                          <b-btn class="btn-main"><icon scale="1.2" name="remove" /></b-btn>
                      </b-link>
                      <b-link @click="editDispersionInstance(row.item)">
                          <b-btn class="btn-main"><icon scale="1.2" name="edit" /></b-btn>
                      </b-link>
                  </template>
                </b-table>
              </b-card>
            </b-col>
            </b-row>
          </b-tab>
        </b-tabs>
      </b-card>
    </div>
  </section>
  <ux-footer />
  <b-modal ok-variant="info" header-bg-variant="info" footer-bg-variant="white" @ok="createUser" :title="$t('adminPanel.forms.createUser.confirm.title')" ref="confirmCreateUser">
    <p>{{createUserDialogMessage}}</p>
  </b-modal>
  <b-modal ok-variant="info" header-bg-variant="info" footer-bg-variant="white" @ok="deleteDispersionInstance" :title="$t('adminPanel.forms.deleteDispersionInstance.confirm.header')" ref="confirmDeleteDispersionInstance">
    <p>{{deleteDispersionInstanceMessage}}</p>
  </b-modal>
  <dispersion-instance-dialog ref="dispersionInstanceDialog" @on-success-save-dispersion-instance="onSuccessSaveDispersionInstance" @on-error-save-dispersion-instance="onErrorSaveDispersionInstance"/>
</div>
</template>

<script>
import uxNav from './nav.vue'
import searchBox from '@/components/common/searchBox'
import usersTable from '@/components/common/usersTable'
import rolesTable from '@/components/common/rolesTable'
import footer from '@/components/footer'
import axios from 'axios'
import dispersionInstaceDialog from '@/components/common/dispersionInstanceDialog'

export default {
  name: 'adminPanel',
  mounted() {
    this.getUsers()
    this.getDispersionInstances()
  },
  data() {
    return {
      showAlert: false,
      alertContent: '',
      alertVariant: '',
      users: [],
      roles: [],
      potencialRoles: [],
      newUser: {
        username: '',
        email: ''
      },
      newRole: {
        name: '',
        description: ''
      },
      selectedUser: null,
      selectedRole: null,
      selectedPotencialRole: null,
      createUserDialogMessage: '',
      createUserRoleDialogMessage: '',
      createRoleDialogMessage: '',
      selectedUserForManage: null,
      dispersionInstances: [],
      dispersionFields: [
        {
          key: 'name',
          label: this.$t('adminPanel.disperionInstanceTable.labels.name')
        },
        {
          key: 'baseUrl',
          label: this.$t('adminPanel.disperionInstanceTable.labels.baseUrl')
        },
        {
          key: 'actions',
          label: this.$t('adminPanel.disperionInstanceTable.labels.actions')
        }
      ],
      dispersionInstanceToDelete: null,
      deleteDispersionInstanceMessage: ''
    }
  },
  methods: {
    onSelectUser(event) {
      if (event !== null) {
        this.selectedUser = event.username
        this.getUserRoles()
        this.getPotencialUserRoles()
      } else {
        console.log('res')
        this.roles = []
        this.potencialRoles = []
      }
    },
    getDispersionInstances() {
      let self = this
      axios.get('dispersionInstances/').then(function(response) {
        self.dispersionInstances = response.data
        console.log(self.dispersionInstances)
      }).catch(function () {
        self.onDangerAlert(self.$t('adminPanel.disperionInstanceTable.message.get.error'))
      })
    },
    createDispersionInstanceWithToken() {
      this.$refs.dispersionInstanceDialog.onCreateDispersionInstanceWithToken()
    },
    createDispersionInstanceWithoutToken() {
      this.$refs.dispersionInstanceDialog.onCreateDispersionInstanceWithoutToken()
    },
    editDispersionInstance(instance) {
      this.$refs.dispersionInstanceDialog.onEditDispersionInstance(instance)
    },
    onSuccessSaveDispersionInstance(message) {
      this.getDispersionInstances()
      this.$refs.dispersionInstanceDialog.onHideDispersionInstanceDialog()
      this.onSuccessAlert(message)
    },
    onErrorSaveDispersionInstance(message) {
      this.getDispersionInstances()
      this.onDangerAlert(message)
    },
    onDeleteDispersionInstance(instance) {
      this.dispersionInstanceToDelete = instance
      this.deleteDispersionInstanceMessage = this.$t('adminPanel.forms.deleteDispersionInstance.confirm.title') + ': ' + instance.name
      this.$refs.confirmDeleteDispersionInstance.show()
    },
    deleteDispersionInstance() {
      let self = this
      axios.delete('admin/dispersion/' + this.dispersionInstanceToDelete.id).then(function(response) {
        self.getDispersionInstances()
        self.onSuccessAlert(self.$t('adminPanel.forms.deleteDispersionInstance.message.success'))
      }).catch(function() {
        self.onDangerAlert(self.$t('adminPanel.forms.deleteDispersionInstance.message.error'))
      })
    },
    getUsers() {
      let self = this
      axios.get('admin/users').then(function(response) {
        self.users = response.data
      }).catch(function() {
        self.onDangerAlert(self.$t('adminPanel.forms.getUsers.message.error'))
      })
    },
    getUserRoles() {
      let self = this
      axios.get('admin/userRoles/' + self.selectedUser).then(function(response) {
        self.roles = response.data
      }).catch(function() {
        self.onDangerAlert(self.$t('adminPanel.forms.getUserRole.message.error'))
      })
    },
    getPotencialUserRoles() {
      let self = this
      axios.get('admin/potencialUserRoles/' + self.selectedUser).then(function(response) {
        self.potencialRoles = response.data
      }).catch(function() {
        self.onDangerAlert(self.$t('adminPanel.forms.getUserRole.message.error'))
      })
    },
    onRoleSearch(event, value) {
      console.log('role search', value)
    },
    onCreateUser(event) {
      event.preventDefault()
      this.createUserDialogMessage = this.$t('adminPanel.forms.createUser.confirm.message.part1') + ': ' + this.newUser.username
      this.$refs.confirmCreateUser.show()
    },
    resetCreateUserForm() {
      this.newUser.username = ''
      this.newUser.email = ''
    },
    createUser() {
      let self = this
      console.log(self)
      axios.post('admin/newUser', {
        username: self.newUser.username,
        email: self.newUser.email
      }).then(function(response) {
        self.getUsers()
        self.resetCreateUserForm()
        self.onSuccessAlert(self.$t('adminPanel.forms.createUser.message.success'))
      }).catch(function() {
        self.onDangerAlert(self.$t('adminPanel.forms.createUser.message.error'))
      })
    },
    resetCreateUserRoleForm() {
      this.selectedUser = null
      this.selectedRole = null
    },
    createUserRole() {
      let self = this
      if (self.selectedUser !== null && self.selectedPotencialRole !== null) {
        axios.post('admin/userrole', {
          user: self.selectedUser,
          role: self.selectedPotencialRole
        }).then(function(response) {
          self.getUserRoles()
          self.getPotencialUserRoles()
          self.onSuccessAlert(self.$t('adminPanel.forms.addUserRole.message.success'))
        }).catch(function() {
          self.onDangerAlert(self.$t('adminPanel.forms.addUserRole.message.error'))
        })
      }
    },
    removeUserRole() {
      let self = this
      if (self.selectedUser !== null && self.selectedRole !== null) {
        axios.post('admin/deleteUserRole', {
          user: self.selectedUser,
          role: self.selectedRole
        }).then(function(response) {
          self.getUserRoles()
          self.getPotencialUserRoles()
          self.onSuccessAlert(self.$t('adminPanel.forms.deleteUserRole.message.success'))
        }).catch(function() {
          self.onDangerAlert(self.$t('adminPanel.forms.deleteUserRole.message.error'))
        })
      }
    },
    onSuccessAlert(message) {
      this.$store.state.showMessage = true
      this.$store.state.messageType = 'ok'
      this.$store.state.messageContent = message
    },
    onDangerAlert(message) {
      this.$store.state.showMessage = true
      this.$store.state.messageType = 'error'
      this.$store.state.messageContent = message
    },
    changeUserAvailability(enable) {
      if (this.selectedUserForManage !== null && enable !== this.selectedUserForManage.enabled) {
        let self = this
        axios.put('admin/disableUser', {
          username: self.selectedUserForManage.username,
          toEnable: enable
        }).then(function(response) {
          if (enable) {
            self.onSuccessAlert(self.$t('adminPanel.forms.manageUser.enable.message.success'))
          } else {
            self.onSuccessAlert(self.$t('adminPanel.forms.manageUser.disable.message.success'))
          }
          self.getUsers()
          self.selectedUserForManage = null
        }).catch(function() {
          if (enable) {
            self.onDangerAlert(self.$t('adminPanel.forms.manageUser.enable.message.error'))
          } else {
            self.onDangerAlert(self.$t('adminPanel.forms.manageUser.disable.message.error'))
          }
        })
      }
    }
  },
  components: {
    'ux-nav': uxNav,
    'search-box': searchBox,
    'users-table': usersTable,
    'roles-table': rolesTable,
    'ux-footer': footer,
    'dispersion-instance-dialog': dispersionInstaceDialog
  }
}
</script>
