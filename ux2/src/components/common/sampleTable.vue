<style scoped>
.sampleColumnsDropDown {
  margin-left: 5px;
}

.btn-main {
  padding: 4px 8px;
  background-color: #24d5d8 !important;
}

.showingDetails {
  background-color: #ffc107 !important;
}

li {
  list-style-type: square !important;
  margin: 0 !important;
  padding: 0 !important;
}
</style>

<template>
<div>
  <b-table show-empty :empty-text="$t('table.emtpy')" :items="samples" :sort-by.sync="sortBy" striped responsive ref="btable" bordered no-local-sorting @sort-changed="sortingChanged" :sort-desc.sync="sortDesc" class="sampleTable" :fields="fields">
    <template slot="id" slot-scope="row">
      <b-btn @click.stop="row.toggleDetails" v-bind:class="{ showingDetails: row.detailsShowing }" class="btn-main" style="margin-right:10px; float:left;">
        <icon name="plus" />
      </b-btn> {{row.item.id}}
    </template>
    <template slot="row-details" slot-scope="row">
      <div style="text-align: left;" v-html="generateparial(row.item)" />
    </template>
  </b-table>
</div>
</template>

<script>
import moment from 'moment'
import axios from 'axios'
export default {
  name: 'sampleTable',
  data() {
    return {
      fieldsOrder: null,
      sortBy: '',
      selected: [],
      sortDesc: false,
      fields: [{
        key: 'id',
        label: this.$t('samples.table.labels.id'),
        sortable: true,
        selected: true,
        class: 'nowrap'
      }, {
        key: 'sampleId',
        label: this.$t('samples.table.labels.sampleId'),
        sortable: true,
        selected: true,
        class: 'nowrap'
      }, {
        key: 'parentSampleId',
        label: this.$t('samples.table.labels.parentSampleId'),
        sortable: true,
        selected: true,
        class: 'nowrap'
      }, {
        key: 'accession',
        label: this.$t('samples.table.labels.accession'),
        sortable: true,
        selected: true,
        class: 'nowrap'
      }, {
        key: 'timestamp',
        label: this.$t('samples.table.labels.timestamp'),
        sortable: true,
        selected: true,
        formatter: (value) => this.formatDate(value),
        class: 'nowrap'
      }, {
        key: 'collection',
        label: this.$t('samples.table.labels.collection'),
        sortable: true,
        selected: true,
        class: 'nowrap'
      }, {
        key: 'biobank',
        label: this.$t('samples.table.labels.biobank'),
        sortable: true,
        selected: true,
        class: 'nowrap'
      }, {
        key: 'owner',
        label: this.$t('samples.table.labels.owner'),
        sortable: true,
        selected: true,
        class: 'nowrap'
      }, {
        key: 'comments',
        label: this.$t('samples.table.labels.comments'),
        sortable: true,
        selected: true,
        class: 'nowrap'
      }]
    }
  },
  methods: {
    generateparial(item) {
      let result = ''
      let excludes = this.fields.map(f => f.key)
      excludes.push('diseasesIcd10Str', 'sampleMaterialTypeStr', 'diseasesTypeStr', '_version_', '_showDetails', '_text_')
      for (var fieldKey in this.fieldsOrder) {
        let key = this.fieldsOrder[fieldKey].key
        let value = item[key]
        if (value !== undefined && value !== null) {
          if (value instanceof Array) {
            result += this.arrayVariableFormat(key, value)
          } else if (key.toLowerCase().includes('timestamp') && !key.toLowerCase().includes('correctess')) {
            result += this.datePropertyFormat(key, value)
          } else {
            result += this.normalVariableFormat(key, value)
          }
        }
      }
      return result
    },
    normalVariableFormat(key, value) {
      return '<b-row class="mb-2"><span><strong>' + this.$t('samples.table.labels.' + key) + '</strong>: ' + value + '</span></b-row></br>'
    },
    arrayVariableFormat(key, value) {
      let arrayPart = ''
      arrayPart += '<ul style="margin-bottom: 0px; padding-bottom: 0px; margin-top: 10px;" class="sampleElementList">'
      for (var arrKey in value) {
        arrayPart += '<li>' + value[arrKey] + '</li>'
      }
      arrayPart += '</ul>'

      return '<b-row class="mb-2"><span><strong>' + this.$t('samples.table.labels.' + key) + '</strong>: ' + arrayPart + '</span></b-row></br>'
    },
    datePropertyFormat(key, value) {
      return '<b-row class="mb-2"><span><strong>' + this.$t('samples.table.labels.' + key) + '</strong>: ' + this.formatDate(value) + '</span></b-row></br>'
    },
    formatDate(dateString) {
      if (dateString === 'ND') {
        return dateString
      } else if (dateString !== undefined && dateString !== null && dateString.length > 0) {
        return moment(dateString).format('YYYY-MM-DD')
      }

      return ''
    },
    sortingChanged(ctx) {
      this.$emit('on-change-sort', ctx.sortBy, ctx.sortDesc)
    },
    clearSorting() {
      this.sortBy = ''
      this.sortDesc = false
    },
    onColumnChange(field) {
      /*
       used th and tdClass because property for above (class) is used
       in this case it have to be more dangerous opeartion splicing class
       string, if you want to use this properties in other ways, change this method
      */
      if (field.selected === false) {
        field.thClass = ''
        field.tdClass = ''
      } else {
        field.thClass = 'd-none'
        field.tdClass = 'd-none'
      }
    }
  },
  mounted: function() {
    this.$root.$on('clear-sorting', () => {
      this.sortBy = ''
      this.sortDesc = false
    })
  },
  created: function() {
    let self = this
    axios.get('sample/fields/order').then(function (response) {
      self.fieldsOrder = response.data
      for (let i = 0; i < self.fields.length; i++) {
        self.selected[i] = self.fields[i].key
      }
      self.fieldsOrder.sort(function(a, b) {
        if (a.order < b.order) {
          return -1
        }
        if (a.order > b.order) {
          return 1
        }
        return 0
      })
    }).catch(function (error) {
      console.log(error)
    })
  },
  props: ['samples'] // data as props from samples

}
</script>
