<style scoped>
.usersTable {
  margin-top: 10px;
}
</style>

<template>
  <b-table show-empty :empty-text="$t('table.emtpy')" class="usersTable" :items="users" striped responsive bordered no-local-sorting :fields="fields"/>
</template>

<script>

export default {
    name: 'usersTable',

    data() {
        return {
            fields: [
              {
                key: 'id',
                label: this.$t('adminPanel.usersTable.labels.id')
              },
              {
                key: 'username',
                label: this.$t('adminPanel.usersTable.labels.username')
              },
              {
                key: 'firstName',
                label: this.$t('adminPanel.usersTable.labels.firstName')
              },
              {
                key: 'lastName',
                label: this.$t('adminPanel.usersTable.labels.lastName')
              },
              {
                key: 'email',
                label: this.$t('adminPanel.usersTable.labels.email')
              }
            ]
        }
    },
    props: ['users']
}

</script>
