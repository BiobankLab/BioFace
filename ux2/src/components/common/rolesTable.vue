<style scoped>
.rolesTable {
  margin-top: 10px;
}
</style>

<template>
  <b-table show-empty :empty-text="$t('table.emtpy')" class="rolesTable" :items="roles" striped responsive bordered no-local-sorting :fields="fields"/>
</template>

<script>

export default {
    name: 'usersTable',

    data() {
        return {
            fields: [
              {
                key: 'id',
                label: this.$t('adminPanel.rolesTable.labels.id')
              },
              {
                key: 'name',
                label: this.$t('adminPanel.rolesTable.labels.name')
              },
              {
                key: 'description',
                label: this.$t('adminPanel.rolesTable.labels.description')
              }
            ]
        }
    },
    props: ['roles']
}

</script>
