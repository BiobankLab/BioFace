<style scoped>

</style>

<template>

<b-table show-empty :empty-text="$t('table.emtpy')" :items="biobanks" :fields="fields" :sort-by.sync="sortBy" :sort-desc.sync="sortDesc" no-local-sorting @sort-changed="sortingChanged" striped responsive bordered>
    <template slot="url" slot-scope="data">
        <a target="_blank" :href="`//${data.value}`">
      {{data.value}}
    </a>
    </template>
    <template slot="actions" slot-scope="row">
        <b-link @click="onEditBiobank(row.item)" v-if="row.item.canEdit || isAdmin">
            <b-btn class="btn-main"><icon scale="1.2" name="edit" /></b-btn>
        </b-link>
    </template>
</b-table>

</template>

<script>

export default {
    name: 'biobankTable',
    data() {
        return {
            sortBy: '',
            sortDesc: false,
            searched: false,
            fields: [{
                key: 'id',
                label: this.$t('biobanks.table.labels.dbId'),
                sortable: true,
                class: 'nowrap'
            }, {
                key: 'biobankId',
                label: this.$t('biobanks.table.labels.id'),
                sortable: true,
                class: 'nowrap'
            }, {
                key: 'acronym',
                label: this.$t('biobanks.table.labels.acronym'),
                sortable: true,
                class: 'nowrap'
            }, {
                key: 'name',
                label: this.$t('biobanks.table.labels.name'),
                sortable: true,
                class: 'nowrap'
            }, {
                key: 'url',
                label: this.$t('biobanks.table.labels.url'),
                sortable: true,
                class: 'nowrap'
            }, {
                key: 'juristicPerson',
                label: this.$t('biobanks.table.labels.juristicPerson'),
                sortable: true,
                class: 'nowrap'
            }, {
                key: 'country',
                label: this.$t('biobanks.table.labels.country'),
                sortable: true,
                class: 'nowrap'
            }, {
                key: 'actions',
                label: this.$t('biobanks.table.labels.actions'),
                class: 'nowrap'
            }]
        }
    },
    computed: {
      isAdmin: function() {
        return this.$store.state.isAdmin
      }
    },
    methods: {
        onEditBiobank(record, index) {
                this.$emit('on-edit-biobank', record)
            },
            sortingChanged(ctx) {
                this.$emit('on-change-sort', ctx.sortBy, ctx.sortDesc)
            }
    },
    props: ['biobanks']
}

</script>
