<style scoped>



</style>

<template>
<div>
  <b-table show-empty :empty-text="$t('table.emtpy')" :items="collections" :fields="fields" :sort-by.sync="sortBy" striped responsive ref="btable" bordered no-local-sorting @sort-changed="sortingChanged" :sort-desc.sync="sortDesc">
    <template slot="actions" slot-scope="row">
        <b-link @click="onDeleteCollection(row.item)">
            <b-btn class="btn-main"><icon scale="1.2" name="remove" /></b-btn>
        </b-link>
    </template>
  </b-table>


  <b-modal @ok="deleteCollection" ok-variant="danger" header-bg-variant="danger" footer-bg-variant="white" :title="$t('collections.manage.delete.header')" ref="deleteCollection">
    <p>{{deleteCollectionConfirmMessage}}</p>
  </b-modal>
</div>
</template>

<script>
import axios from 'axios'
export default {
  name: 'collectionTable',
  data() {
    return {
      sortBy: '',
      deleteCollectionConfirmMessage: '',
      sortDesc: false,
      collectionToDelete: {
        collectionId: null,
        biobankId: null
      },
      fields: [{
          key: 'collectionId',
          label: this.$t('collections.table.labels.collectionId'),
          sortable: true,
          class: 'nowrap'
        },
        {
          key: 'biobankId',
          label: this.$t('collections.table.labels.biobankId'),
          sortable: true,
          class: 'nowrap'
        },
        {
          key: 'actions',
          label: this.$t('collections.table.labels.actions'),
          class: 'nowrap'
        }
      ]
    }
  },
  methods: {
    sortingChanged(ctx) {
      this.$emit('on-change-sort', ctx.sortBy, ctx.sortDesc)
    },
    onDeleteCollection(collection) {
      this.collectionToDelete.collectionId = collection.collectionId
      this.collectionToDelete.biobankId = collection.biobankId
      this.deleteCollectionConfirmMessage = this.$t('collections.manage.delete.confirm') + ': ' + this.collectionToDelete.collectionId + ' ?'
      this.$refs.deleteCollection.show()
    },
    deleteCollection(collection) {
      let self = this
      axios.post('collection/delete/', this.collectionToDelete).then(function(response) {
        self.$emit('on-delete-collection')
        self.$store.state.showMessage = true
        self.$store.state.messageType = 'ok'
        self.$store.state.messageContent = self.$t('collections.manage.delete.message.success')
      }).catch(function() {
        self.$store.state.showMessage = true
        self.$store.state.messageType = 'error'
        self.$store.state.messageContent = self.$t('collections.manage.delete.message.error')
      })
    }
  },
  props: ['collections']
}
</script>
