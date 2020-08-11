<style scoped>
.resetIcon {
  margin-left: 5px;
  padding-top: 4px;
}

.resetFilterLink {
  margin-left: 5px;
  background-color: red;
  margin-top: 10px;
}
</style>

<template>
<div class="wrap">
  <div class="invisible-wrap">
    <ux-nav />
    <div class="baner">
      <div class="container">
        <div class="row">

          <div class="col-sm-12 col-lg-4"></div>

          <div class="col-sm-12 col-lg-8">

            <div class="wpb_wrapper">
              <div class="box-text">

                <div class="scrollme animateme" data-when="exit" data-from="0" data-to="0.6" data-opacity="0" data-translateY="-200" data-rotatez="0">
                  <h1 class="animated animated-one fadeIn">{{$t('dashboard.mainInfo.header')}}
                     <span class="animated animated-one fadeIn">{{$t('dashboard.mainInfo.additionalInfo')}}</span></h1>
                  <span class="small animated animated-one fadeIn">{{$t('dashboard.mainInfo.description')}}</span>
                  <br /><br /><a class="btn-main page-scroll" href="#about" title="explore">{{$t('dashboard.mainInfo.button.explore')}}</a>
                </div>

              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- BANER END -->

    <!-- ABOUT SECIOTN START -->
    <section id="about">
      <img src="@/assets/img/backright.png" alt="paragraf" class="biglogo-right">

      <div class="container">
        <div class="row">
          <div class="col-md-4 scrollme animateme" data-when="exit" data-from="0" data-to="0.6" data-opacity="0" data-translateY="-100">
            <div class="bar-inside">
              <div class="bar"></div>
              <h2><strong>{{$t('dashboard.appInfo.main')}}</strong> - <span>{{$t('dashboard.appInfo.second')}}</span> </h2>
              <p>{{$t('dashboard.appInfo.third')}}</p>
            </div>
          </div>

          <div class="col-md-8 scrollme animateme" data-when="enter" data-from="0.9" data-to="0" data-opacity="0" data-translateY="-200">
            <img src="@/assets/img/intro-1-2.jpg" alt="" class="img-fluid" width="" height="">
             </div>
          </div>
        </div>
    </section>


    <section id="pie">
      <div class="pie1">

        <!-- BIOBANKS CUSTOM PIE -->
        <div class="container">
          <div class="row align-items-center">

            <div class="col-lg-5 order-2 order-sm-2 order-lg-1 ">
              <div id="counter" class="scrollme animateme" data-when="enter" data-from="0.9" data-to="0" data-opacity="0" data-translateY="-250">
                <div class="counter-value banks" data-count="16">0</div>
              </div>
            </div>

            <div class="col-lg-6 order-1 order-sm-1 order-lg-2">

              <div class="bar-right">
                <div class="bar-inside">
                  <h3><strong>{{$t('dashboard.biobanksInfo.main')}}</strong> {{$t('dashboard.biobanksInfo.second')}}</h3>
                  <br />
                  <p>{{$t('dashboard.biobanksInfo.third')}}</p>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>

      <div class="pie1" v-if="dispersionInstancesCount > 0">
        <!-- DISPERSIONS CUSTOM PIE -->
        <div class="container">
          <div class="row align-items-center">

            <div class="col-lg-6 order-1 order-sm-1 order-lg-1">

              <div class="bar">
                <div class="bar-inside">
                  <h3><strong>{{$t('dashboard.dispersionInfo.main')}}</strong> <br />{{$t('dashboard.dispersionInfo.second')}}</h3>
                  <br />
                  <p>{{$t('dashboard.dispersionInfo.third')}}</p>
                </div>
              </div>
            </div>

            <div class="col-lg-5 order-2 order-sm-2 order-lg-2 ">
              <div id="counter3" class="scrollme animateme" data-when="enter" data-from="0.9" data-to="0" data-opacity="0" data-translateY="-250">
                <div class="counter-value disp" data-count="16">0</div>
              </div>
            </div>

          </div>
        </div>
      </div>

      <!-- BIOBANK SAMPLES COUNT CUSTOM PIE -->
      <div class="container">
        <div class="row align-items-center">

          <div class="col-lg-5">
            <div id="counter2" class="scrollme animateme" data-when="enter" data-from="0.9" data-to="0" data-opacity="0" data-translateY="-250">
              <div class="counter-value mln">{{dataQuantityCount}}</div>
            </div>
          </div>

          <div class="col-lg-6">
            <div class="bar-right">
              <div class="bar-inside">
                <h3><strong>{{$t('dashboard.samplesInfo.main1')}} {{dataQuantityCount}} {{$t('dashboard.samplesInfo.main2')}}</strong> <br />{{$t('dashboard.samplesInfo.second')}}</h3>
                <br />
                <p>{{$t('dashboard.samplesInfo.third')}}</p>
              </div>
            </div>
          </div>
        </div>
      </div>

      <!-- DISPERSION SAMPLES COUNT CUSTOM PIE -->
      <div class="container" v-if="dispersionSamplesCount > 0">
        <div class="row align-items-center">

          <div class="col-lg-6">
            <div class="bar">
              <div class="bar-inside">
                <h3><strong>{{$t('dashboard.samplesInfo.main1')}} {{dispersionSamplesCount}} {{$t('dashboard.samplesInfo.main2')}}</strong> <br />{{$t('dashboard.samplesInfo.secondDisp')}}</h3>
                <br />
                <p>{{$t('dashboard.samplesInfo.third')}}</p>
              </div>
            </div>
          </div>

          <div class="col-lg-5">
            <div id="counter2" class="scrollme animateme" data-when="enter" data-from="0.9" data-to="0" data-opacity="0" data-translateY="-250">
              <div class="counter-value">{{dispersionSamplesCount}}</div>
            </div>
          </div>

        </div>
      </div>
    </section>
    <!-- ABOUT SECIOTN CUSTOM PIE END-->

    <!-- ABOUT SECIOTN DC PIE -->
    <!-- FIRST DETAILS -->
    <section class="dcpie">
      <div class="container">

        <div class="section-heading text-center">
          <h2>{{$t('dashboard.chart.header.main')}}</h2>
          <p class="text-muted">{{$t('dashboard.chart.header.second')}}</p>
          <hr>
        </div>

        <div class="row">

          <!-- FIRST DC PIE -->

          <div class="col-md-12 col-lg-6">
            <div class="dcpie-item" data-when="enter" data-from="0.9" data-to="0" data-opacity="0" data-translateY="-250">

              <h3>{{$t('dashboard.chart.title.disease')}}</h3>
              <p>{{$t('dashboard.chart.title.filtering')}}:</p>
              <hr>
              <div ref="diseaseChartLegend" style="height:150px; overflow-y: 1;" />
              <div ref="diseaseChart" />
              <div class="clearfix"></div>
              <b-link @click="resetDieseasePieChartFilters" class="reset">{{$t('dashboard.buttons.resetFilter')}}</b-link>
              <b-link class="reset resetFilterLink" v-for="(filter, index) in diseasePieChartFilters" :key="index" @click="removeDiseaseFilter(index)">
                {{filter}}
              </b-link>
            </div>
          </div>

          <!-- SECOND DC PIE -->

          <div class="col-md-12 col-lg-6 ">
            <div class="dcpie-item" data-when="enter" data-from="0.9" data-to="0" data-opacity="0" data-translateY="-250">

              <h3>{{$t('dashboard.chart.title.diseaseDetails')}}</h3>
              <p>{{$t('dashboard.chart.title.filtering')}}:</p>
              <hr>
              <div ref="diseaseDetailsChartLegend" style="height:150px; overflow-y: 1;" />
              <div ref="diseaseDetailsChart"></div>
              <div class="clearfix"></div>
              <b-link class="reset" @click="resetDiseaseDetailsPieChartFilters">{{$t('dashboard.buttons.resetFilter')}}</b-link>
              <b-link class="reset resetFilterLink" v-for="(filter, index) in diseaseDetailsPieChartFilters" :key="index" @click="removeDiseaseDetailsFilter(index)">
                {{filter}}
              </b-link>
            </div>
          </div>

        </div>


        <div class="row">

          <!-- THIRD DC PIE -->

          <div class="col-md-12 col-lg-6" style="float:none; margin: 0 auto;">
            <div>
              <h3>{{$t('dashboard.chart.title.sampleType')}}</h3>
              <p>{{$t('dashboard.chart.title.filtering')}}:</p>
              <hr>
              <div ref="materialTypeChartLegend" />
              <div ref="materialTypeChart"></div>
              <div class="clearfix"></div>
              <b-link class="reset" @click="resetmaterialTypePieChartFilters">{{$t('dashboard.buttons.resetFilter')}}</b-link>
              <b-link class="reset resetFilterLink" v-for="(filter, index) in materialTypePieChartFilters" :key="index" @click="removematerialTypeFilter(index)">
                {{filter}}
              </b-link>
            </div>
          </div>

        </div>
      </div>
    </section>
    <!-- FIRST DETAILS END -->
    <!-- SECOND DETAILS -->
    <section class="dcpie" v-if="dispersionInstancesCount > 0 && dispersionSamplesCount > 0">
      <div class="container">

        <div class="section-heading text-center">
          <h2>{{$t('dashboard.chart.dispersionHeader.main')}}</h2>
          <p class="text-muted">{{$t('dashboard.chart.dispersionHeader.second')}}</p>
          <hr>
        </div>

        <div class="row">

          <!-- FIRST DC PIE -->

          <div class="col-md-12 col-lg-6">
            <div class="dcpie-item" data-when="enter" data-from="0.9" data-to="0" data-opacity="0" data-translateY="-250">

              <h3>{{$t('dashboard.chart.title.disease')}}</h3>
              <p>{{$t('dashboard.chart.title.filtering')}}:</p>
              <hr>
              <div ref="dispersionDiseaseChartLegend" style="height:150px; overflow-y: 1;" />
              <div ref="dispersionDiseaseChart" />
              <div class="clearfix"></div>
              <b-link @click="resetDispersionDieseasePieChartFilters" class="reset">{{$t('dashboard.buttons.resetFilter')}}</b-link>
              <b-link class="reset resetFilterLink" v-for="(filter, index) in dispersionDiseasePieChartFilters" :key="index" @click="removeDispersionDiseaseFilter(index)">
                {{filter}}
              </b-link>
            </div>
          </div>

          <!-- SECOND DC PIE -->

          <div class="col-md-12 col-lg-6 ">
            <div class="dcpie-item" data-when="enter" data-from="0.9" data-to="0" data-opacity="0" data-translateY="-250">

              <h3>{{$t('dashboard.chart.title.diseaseDetails')}}</h3>
              <p>{{$t('dashboard.chart.title.filtering')}}:</p>
              <hr>
              <div ref="dispersionDiseaseDetailsChartLegend" style="height:150px; overflow-y: 1;" />
              <div ref="dispersionDiseaseDetailsChart"></div>
              <div class="clearfix"></div>
              <b-link class="reset" @click="resetDispersionDiseaseDetailsPieChartFilters">{{$t('dashboard.buttons.resetFilter')}}</b-link>
              <b-link class="reset resetFilterLink" v-for="(filter, index) in dispersionDiseaseDetailsPieChartFilters" :key="index" @click="removeDispersionDiseaseDetailsFilter(index)">
                {{filter}}
              </b-link>
            </div>
          </div>

        </div>

        <div class="row">

          <!-- THIRD DC PIE -->

          <div class="col-md-12 col-lg-6" style="float:none; margin: 0 auto;">
            <div>
              <h3>{{$t('dashboard.chart.title.sampleType')}}</h3>
              <p>{{$t('dashboard.chart.title.filtering')}}:</p>
              <hr>
              <div ref="dispersionMaterialTypeChartLegend" />
              <div ref="dispersionMaterialTypeChart"></div>
              <div class="clearfix"></div>
              <b-link class="reset" @click="resetDispersionMaterialTypePieChartFilters">{{$t('dashboard.buttons.resetFilter')}}</b-link>
              <b-link class="reset resetFilterLink" v-for="(filter, index) in dispersionMaterialTypePieChartFilters" :key="index" @click="removeDispersionMaterialTypeFilter(index)">
                {{filter}}
              </b-link>
            </div>
          </div>
        </div>
      </div>
    </section>
    <!-- SECOND DETAILS END -->
    <ux-footer />
    <!-- ABOUT SECIOTN DC PIE END-->
  </div>
</div>
</template>

<script>
import uxNav from './nav.vue'
import uxFooter from './footer.vue'
import axios from 'axios'
import '@/libs/app'

export default {
  name: 'Dashboard',
  data() {
    return {
      diseasePieChart: null,
      diseasePieChartFilters: [],
      diseaseDetailsPieChart: null,
      diseaseDetailsPieChartFilters: [],
      MaterialDetailsPieChart: null,
      MaterialDetailsPieChartFilters: [],
      materialTypePieChart: null,
      materialTypePieChartFilters: [],
      dispersionDiseasePieChart: null,
      dispersionDiseasePieChartFilters: [],
      dispersionDiseaseDetailsPieChart: null,
      dispersionDiseaseDetailsPieChartFilters: [],
      dispersionMaterialTypePieChart: null,
      dispersionMaterialTypePieChartFilters: [],
      samplesByDateChart: null,
      filters: [],
      data: [],
      dispersionData: [],
      samples: [],
      dispersionSamples: [],
      dataQuantityCount: 0,
      biobanksCount: 0,
      dispersionInstancesCount: 0,
      dispersionSamplesCount: 0
    }
  },
  methods: {
    removeDiseaseFilter(index) {
      this.removeFilter(this.diseasePieChart, index)
    },
    removeDispersionDiseaseFilter(index) {
      this.removeFilter(this.dispersionDiseasePieChart, index)
    },
    removeDiseaseDetailsFilter(index) {
      this.removeFilter(this.diseaseDetailsPieChart, index)
    },
    removeDispersionDiseaseDetailsFilter(index) {
      this.removeFilter(this.dispersionDiseaseDetailsPieChart, index)
    },
    removematerialTypeFilter(index) {
      this.removeFilter(this.materialTypePieChart, index)
    },
    removeDispersionMaterialTypeFilter(index) {
      this.removeFilter(this.dispersionMaterialTypePieChart, index)
    },
    removeSampleDetailsFilter(index) {
      this.removeFilter(this.MaterialDetailsPieChart, index)
    },
    removeFilter(chart, index) {
      var filters = chart.filters().slice(0) // copy the array of filters
      filters.splice(index, 1)
      chart.replaceFilter([filters]).redrawGroup()
    },
    resetDieseasePieChartFilters() {
      this.diseasePieChart.filterAll()
      dc.redrawAll()
    },
    resetDispersionDieseasePieChartFilters() {
      this.dispersionDiseasePieChart.filterAll()
      dc.redrawAll()
    },
    resetDiseaseDetailsPieChartFilters() {
      this.diseaseDetailsPieChart.filterAll()
      dc.redrawAll()
    },
    resetDispersionDiseaseDetailsPieChartFilters() {
      this.dispersionDiseaseDetailsPieChart.filterAll()
      dc.redrawAll()
    },
    resetmaterialTypePieChartFilters() {
      this.materialTypePieChart.filterAll()
      dc.redrawAll()
    },
    resetDispersionMaterialTypePieChartFilters() {
      this.dispersionMaterialTypePieChart.filterAll()
      dc.redrawAll()
    },
    resetMaterialDetailsPieChartFilters() {
      this.MaterialDetailsPieChart.filterAll()
      dc.redrawAll()
    },
    calculateDataQuantity() {
      this.dataQuantityCount = 0
      for (var i = 0; i < this.data.length; i++) {
        this.dataQuantityCount += this.data[i].quantity
      }
    },
    createDiseaseChart() {
      let self = this
      let dimensionDiseaseType = this.samples.dimension(item => item.diseaseType)
      let quantityByDiseaseType = dimensionDiseaseType.group().reduceSum(item => item.quantity)

      this.diseasePieChart = dc.pieChart(this.$refs.diseaseChart)
        .width(250)
        .height(250)
        .innerRadius(80)
        .label(function(d) {
          return null
        })
        .data(function(group) {
          return group.all().filter(function(kv) {
            return kv.value > 0
          })
        })
        .legend(dc.htmlLegend().container(this.$refs.diseaseChartLegend).legendText(function(d, i) {
          return d.name + ' - ' + d.data
        }))
        .dimension(dimensionDiseaseType)
        .group(quantityByDiseaseType)

      this.diseasePieChart.on('filtered', function(chart) {
        self.diseasePieChartFilters = chart.filters()
      })
    },
    createDispersionDiseaseChart() {
      let self = this
      let dimensionDiseaseType = this.dispersionSamples.dimension(item => item.diseaseType)
      let quantityByDiseaseType = dimensionDiseaseType.group().reduceSum(item => item.quantity)

      this.dispersionDiseasePieChart = dc.pieChart(this.$refs.dispersionDiseaseChart)
        .width(250)
        .height(250)
        .innerRadius(80)
        .label(function(d) {
          return null
        })
        .data(function(group) {
          return group.all().filter(function(kv) {
            return kv.value > 0
          })
        })
        .legend(dc.htmlLegend().container(this.$refs.dispersionDiseaseChartLegend).legendText(function(d, i) {
          return d.name + ' - ' + d.data
        }))
        .dimension(dimensionDiseaseType)
        .group(quantityByDiseaseType)

      this.dispersionDiseasePieChart.on('filtered', function(chart) {
        self.dispersionDiseasePieChartFilters = chart.filters()
      })
    },
    createDiseaseDetailsChart() {
      let self = this
      let dimensionDiseaseDetails = this.samples.dimension(item => item.diseaseDetails)
      let quantityByDiseaseDetails = dimensionDiseaseDetails.group().reduceSum(item => item.quantity)

      this.diseaseDetailsPieChart = dc.pieChart(this.$refs.diseaseDetailsChart)
        .width(250)
        .height(250)
        .innerRadius(80)
        .label(function(d) {
          return null
        })
        .data(function(group) {
          return group.all().filter(function(kv) {
            return kv.value > 0
          })
        })
        .legend(dc.htmlLegend().container(this.$refs.diseaseDetailsChartLegend).legendText(function(d, i) {
          return d.name + ' - ' + d.data
        }))
        .dimension(dimensionDiseaseDetails)
        .group(quantityByDiseaseDetails)

      this.diseaseDetailsPieChart.on('filtered', function(chart) {
        self.diseaseDetailsPieChartFilters = chart.filters()
      })
    },
    createDispersionDiseaseDetailsChart() {
      let self = this
      let dimensionDiseaseDetails = this.dispersionSamples.dimension(item => item.diseaseDetails)
      let quantityByDiseaseDetails = dimensionDiseaseDetails.group().reduceSum(item => item.quantity)

      this.dispersionDiseaseDetailsPieChart = dc.pieChart(this.$refs.dispersionDiseaseDetailsChart)
        .width(250)
        .height(250)
        .innerRadius(80)
        .label(function(d) {
          return null
        })
        .data(function(group) {
          return group.all().filter(function(kv) {
            return kv.value > 0
          })
        })
        .legend(dc.htmlLegend().container(this.$refs.dispersionDiseaseDetailsChartLegend).legendText(function(d, i) {
          return d.name + ' - ' + d.data
        }))
        .dimension(dimensionDiseaseDetails)
        .group(quantityByDiseaseDetails)

      this.dispersionDiseaseDetailsPieChart.on('filtered', function(chart) {
        self.dispersionDiseaseDetailsPieChartFilters = chart.filters()
      })
    },
    createMaterialChart() {
      let self = this
      let dimensionMaterialType = this.samples.dimension(item => item.materialType)
      let quantityMaterialType = dimensionMaterialType.group().reduceSum(item => item.quantity)

      this.materialTypePieChart = dc.pieChart(this.$refs.materialTypeChart)
        .width(250)
        .height(250)
        .innerRadius(80)
        .label(function(d) {
          return null
        })
        .data(function(group) {
          return group.all().filter(function(kv) {
            return kv.value > 0
          })
        })
        .legend(dc.htmlLegend().container(this.$refs.materialTypeChartLegend).legendText(function(d, i) {
          return d.name + ' - ' + d.data
        }))
        .dimension(dimensionMaterialType)
        .group(quantityMaterialType)

      this.materialTypePieChart.on('filtered', function(chart) {
        self.materialTypePieChartFilters = chart.filters()
      })
    },
    createDispersionMaterialChart() {
      let self = this
      let dimensionMaterialType = this.dispersionSamples.dimension(item => item.materialType)
      let quantityMaterialType = dimensionMaterialType.group().reduceSum(item => item.quantity)

      this.dispersionMaterialTypePieChart = dc.pieChart(this.$refs.dispersionMaterialTypeChart)
        .width(250)
        .height(250)
        .innerRadius(80)
        .label(function(d) {
          return null
        })
        .data(function(group) {
          return group.all().filter(function(kv) {
            return kv.value > 0
          })
        })
        .legend(dc.htmlLegend().container(this.$refs.dispersionMaterialTypeChartLegend).legendText(function(d, i) {
          return d.name + ' - ' + d.data
        }))
        .dimension(dimensionMaterialType)
        .group(quantityMaterialType)

      this.dispersionMaterialTypePieChart.on('filtered', function(chart) {
        self.dispersionMaterialTypePieChartFilters = chart.filters()
      })
    },
    createMaterialDetailsChart() {
      let self = this
      let dimensionMaterialDetails = this.samples.dimension(item => item.sampleDetails)
      let quantityMaterialDetails = dimensionMaterialDetails.group().reduceSum(item => item.quantity)

      this.MaterialDetailsPieChart = dc.pieChart(this.$refs.sampleDetailsChart)
        .width(250)
        .height(250)
        .innerRadius(80)
        .label(function(d) {
          return null
        })
        .data(function(group) {
          return group.all().filter(function(kv) {
            return kv.value > 0
          })
        })
        .legend(dc.htmlLegend().container(this.$refs.sampleDetailsChartLegend).legendText(function(d, i) {
          return d.name + ' - ' + d.data
        }))
        .dimension(dimensionMaterialDetails)
        .group(quantityMaterialDetails)

      this.MaterialDetailsPieChart.on('filtered', function(chart) {
        self.MaterialDetailsPieChartFilters = chart.filters()
      })
    },
    createDateChart() {
      let dimensionDate = this.samples.dimension(function(d) {
        return d3.timeDay(d.date)
      })
      var minDate = dimensionDate.bottom(1)[0].date
      var maxDate = dimensionDate.top(1)[0].date

      let quantityByDate = dimensionDate.group().reduceSum(item => item.quantity)

      this.samplesByDateChart = dc.barChart(this.$refs.samplesByDateChart)
        .dimension(dimensionDate)
        .group(quantityByDate)
        .x(d3.scaleTime().domain(minDate, maxDate))
        .xUnits(d3.timeDays)
        .elasticX(true)
        .gap(2)
        .outerPadding(5)
        .xAxisPadding(2)
        .centerBar(true)
        .width(800)
        .height(400)
        .xAxisLabel(this.$t('dashboard.chartByDate.xAxis'))
        .yAxisLabel(this.$t('dashboard.chartByDate.yAxis'))

      dc.dataCount('.dc-data-count').dimension({
        size: function() {
          return this.dataQuantityCount
        }
      }).group(this.samples.groupAll().reduceSum(item => item.quantity))
    },
    buildChart() {
      let self = this
      var dateFormatSpecifier = '%d/%m/%Y'
      var dateFormatParser = d3.timeParse(dateFormatSpecifier)
      self.data.forEach(function(d) {
        d.date = dateFormatParser(d.date)
      })

      self.samples = crossfilter.crossfilter(self.data)
      dc.config.defaultColors(d3.schemeCategory10)

      this.createDiseaseChart()
      this.createMaterialChart()
      this.createDiseaseDetailsChart()
      // this.createMaterialDetailsChart()
      // this.createDateChart()

      dc.renderAll()
    },
    buildDispersionChart() {
      let self = this
      var dateFormatSpecifier = '%d/%m/%Y'
      var dateFormatParser = d3.timeParse(dateFormatSpecifier)
      self.dispersionData.forEach(function(d) {
        d.date = dateFormatParser(d.date)
      })

      self.dispersionSamples = crossfilter.crossfilter(self.dispersionData)
      dc.config.defaultColors(d3.schemeCategory10)

      this.createDispersionDiseaseChart()
      this.createDispersionDiseaseDetailsChart()
      this.createDispersionMaterialChart()

      dc.renderAll()
    },
    initJQueryScripts() {
      $('document').ready(function($) {
        var nav = $('.navbar-dark')
        $(window).scroll(function() {
          if ($(this).scrollTop() > 50) {
            nav.addClass('squeeze')
          } else {
            nav.removeClass('squeeze')
          }
        })
      })

      var a = 0
      var biobanksNumber = this.biobanksCount
      $(window).scroll(function() {
        if ($('#counter').offset() !== undefined) {
          var oTop = $('#counter').offset().top - window.innerHeight
          if (a === 0 && $(window).scrollTop() > oTop) {
            $('.banks').each(function() {
              var $this = $(this)
              var countTo = biobanksNumber
              $({
                countNum: $this.text()
              }).animate({
                countNum: countTo
              }, {
                duration: 1000,
                easing: 'swing',
                step: function() {
                  $this.text(Math.floor(this.countNum))
                },
                complete: function() {
                  $this.text(this.countNum)
                }
              })
            })
            a = 1
          }
        }
      })

      var quantityCount = this.dataQuantityCount
      var b = 0
      $(window).scroll(function() {
        if ($('#counter').offset() !== undefined) {
          var oTop = $('#counter2').offset().top - window.innerHeight
          if (b === 0 && $(window).scrollTop() > oTop) {
            $('.mln').each(function() {
              var $this = $(this)
              var countTo = quantityCount
              $({
                countNum: $this.text()
              }).animate({
                countNum: countTo
              }, {
                duration: 1000,
                easing: 'swing',
                step: function() {
                  $this.text(Math.floor(this.countNum))
                },
                complete: function() {
                  $this.text(this.countNum)
                }
              })
            })
            b = 1
          }
        }
      })

      if (this.dispersionInstancesCount > 0) {
        var c = 0
        var dispersionNumber = this.dispersionInstancesCount
        $(window).scroll(function() {
          if ($('#counter').offset() !== undefined) {
            var oTop = $('#counter3').offset().top - window.innerHeight
            if (c === 0 && $(window).scrollTop() > oTop) {
              $('.disp').each(function() {
                var $this = $(this)
                var countTo = dispersionNumber
                $({
                  countNum: $this.text()
                }).animate({
                  countNum: countTo
                }, {
                  duration: 1000,
                  easing: 'swing',
                  step: function() {
                    $this.text(Math.floor(this.countNum))
                  },
                  complete: function() {
                    $this.text(this.countNum)
                  }
                })
              })
              c = 1
            }
          }
        })
      }
    },
    onMessage(message) {
      this.$store.state.showMessage = true
      this.$store.state.messageContent = message
    },
    onErrorMessage(message) {
      this.$store.state.messageType = 'error'
      this.onMessage(message)
    }
  },
  mounted() {
    let self = this

    axios.get('public/biobanksCount').then(function(response) {
      self.biobanksCount = response.data

    axios.get('public/chartData').then(function(response) {
      self.data = response.data.samplesData
      self.dataQuantityCount = response.data.samplesCount
      self.buildChart()

    axios.get('public/dispersionInstancesCount').then(function(response) {
      self.dispersionInstancesCount = response.data
      self.initJQueryScripts()

      if (self.dispersionInstancesCount > 0) {
        axios.get('public/dispersionSamplesCount').then(function(response) {
          self.dispersionSamplesCount = response.data
          if (self.dispersionSamplesCount > 0) {
            axios.get('public/dispersionChartData').then(function (response) {
              self.dispersionData = response.data.samplesData
              self.buildDispersionChart()
            })
          }
        })
      }
      })
      })
    }).catch(function() {
      self.onErrorMessage(self.$t('dashboard.getData.error'))
    })
  },
  components: {
    'ux-nav': uxNav,
    'ux-footer': uxFooter
  }
}
</script>
