<style scoped>
.jobsTable {
  margin-top: 20px;
}
</style>

<template>
<div>
  <b-table show-empty :empty-text="$t('table.emtpy')" class="jobsTable" :items="jobs" striped responsive bordered no-local-sorting :fields="fields" @sort-changed="sortingChanged">
    <template slot="status" slot-scope="row">
    <b-link @click="onOpenErrorDetailsDialog(row)" v-if="row.item.status === 'ERROR'" style="color: black">{{row.item.status}} <icon name="comment" color="black" scale="1"/></b-link>
    <div v-else>{{row.item.status}}</div>
  </template>
  </b-table>

  <b-modal hide-footer header-bg-variant="danger" :title="$t('jobsTable.error')" ref="errorDetailsModal">
    <b-form-textarea id="textarea1"
                     :disabled="true"
                     v-model="errorDetails"
                     :rows="10"
                     :max-rows="10">
    </b-form-textarea>
  </b-modal>
</div>
</template>

<script>
import moment from 'moment'
export default {
  name: 'samplesJobsTable',
  data() {
    return {
      errorDetails: '',
      fields: [{
        key: 'originalFileName',
        label: this.$t('jobsTable.originalFileName'),
        sortable: true,
      }, {
        key: 'addTime',
        label: this.$t('jobsTable.addTime'),
        formatter: 'formatTime',
        sortable: true
      }, {
        key: 'status',
        label: this.$t('jobsTable.status'),
        sortable: true
      }, {
        key: 'indexerStartTime',
        label: this.$t('jobsTable.indexerStartTime'),
        formatter: 'formatTime',
        sortable: true
      }, {
        key: 'indexerFinishTime',
        label: this.$t('jobsTable.indexerFinishTime'),
        formatter: 'formatTime',
        sortable: true
      }]
    }
  },
  methods: {
    onOpenErrorDetailsDialog(row) {
      this.errorDetails = row.item.exceptionMessage
      this.$refs.errorDetailsModal.show()
    },
    formatTime(time) {
      if (time === null) {
        return ''
      }

      return moment(time).format('YYYY-MM-DD HH:mm:ss')
    },
    sortingChanged(ctx) {
      this.$emit('on-change-jobs-sort', ctx.sortBy, ctx.sortDesc)
    }
  },
  props: ['jobs']
}
</script>
