package com.dispersion.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.PivotField;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dispersion.model.ChartHelper;
import com.dispersion.model.SampleChartData;
import com.dispersion.model.SamplesChartDataResponse;
@Service
public class PublicService implements IPublicService {

	@Autowired
	private SolrClient solrClient;

	private static final int MAX_CHART_SIZE = 10;
	
	private static final Logger log = LoggerFactory.getLogger(PublicService.class);
	/* (non-Javadoc)
	 * @see com.dispersion.service.IPublicService#countSamples()
	 */
	@Override
	public Long countSamples() {
		
		try {
			
			SolrQuery solrQuery = new SolrQuery();
			solrQuery.setQuery("*:*");
			QueryResponse response = solrClient.query(solrQuery);
			return response.getResults().getNumFound();
			
		} catch (Exception e) {
			log.error("Error while counting samples ",e);
			throw new RuntimeException("Error while counting samples");
		}
		
	}
	
	@Override
	public SamplesChartDataResponse getSamplesChartData() {

		try {
			List<SampleChartData> result = new ArrayList<>();

			SolrQuery disease1Query = new SolrQuery();
			disease1Query.setFacet(true);
			disease1Query.addFacetPivotField("diseasesTypeStr,diseasesIcd10Str,sampleMaterialTypeStr");
			disease1Query.setQuery("*:*");
			disease1Query.setRows(0);

			QueryResponse response = solrClient.query(disease1Query);
			long samplesCount = response.getResults().getNumFound();

			Map<String, ChartHelper> allDiseasesPivotList = new ConcurrentHashMap<>();

			response.getFacetPivot().forEach(fp -> {
				fp.getValue().forEach(ff -> {
					if (allDiseasesPivotList.containsKey(ff.getValue().toString())) {
						allDiseasesPivotList.get(ff.getValue().toString()).getPivot().addAll(ff.getPivot());
						allDiseasesPivotList.get(ff.getValue().toString()).addToCount(ff.getCount());
					} else {
						ChartHelper chartHelper = new ChartHelper();
						chartHelper.addToCount(ff.getCount());
						chartHelper.getPivot().addAll(ff.getPivot());
						allDiseasesPivotList.put(ff.getValue().toString(), chartHelper);
					}
				});
			});

			Map<String, ChartHelper> sortedDiseasesMap = sortChartMap(allDiseasesPivotList);

			int diseaseSize = 0;
			int diseaseDetailsSize = 0;
			int materialSize = 0;

			for (Map.Entry<String, ChartHelper> diseaseTypeMap : sortedDiseasesMap.entrySet()) {
				diseaseDetailsSize = 0;
				materialSize = 0;
				if (diseaseSize == MAX_CHART_SIZE) {
					break;
				}
				diseaseSize++;
				Map<String, ChartHelper> diseaseDetailsMap = mergePivotFields(diseaseTypeMap.getValue().getPivot());

				for (Map.Entry<String, ChartHelper> diseaseDetails : diseaseDetailsMap.entrySet()) {
					if (diseaseDetailsSize == MAX_CHART_SIZE) {
						break;
					}
					diseaseDetailsSize++;
					materialSize = 0;

					Map<String, ChartHelper> materialMap = mergePivotFields(diseaseDetails.getValue().getPivot());

					for (Map.Entry<String, ChartHelper> material : materialMap.entrySet()) {
						if (materialSize == MAX_CHART_SIZE) {
							break;
						}
						materialSize++;
						SampleChartData chartDataElement = new SampleChartData();
						chartDataElement.setDiseaseType(diseaseTypeMap.getKey());
						chartDataElement.setDiseaseDetails(diseaseDetails.getKey());
						chartDataElement.setMaterialType(material.getKey());
						chartDataElement.setQuantity(material.getValue().getCount());
						result.add(chartDataElement);
					}
				}

			}

			SamplesChartDataResponse responseChartData = new SamplesChartDataResponse();
			responseChartData.setSamplesData(result);
			responseChartData.setSamplesCount(samplesCount);

			return responseChartData;
		} catch (Exception e) {
			log.error("Error while getting dashboard chart data", e);
			throw new RuntimeException("Error while getting dashboard chart data");
		}
	}
	
	private Map<String, ChartHelper> sortChartMap(Map<String, ChartHelper> unsortedMap) {
		return unsortedMap.entrySet().stream()
				.sorted((v1, v2) -> Integer.compare(v2.getValue().getCount(), v1.getValue().getCount()))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
	}
	
	private Map<String, ChartHelper> mergePivotFields(List<PivotField> pivot) {
		Map<String, ChartHelper> mergedPivotMap = new ConcurrentHashMap<>();
		pivot.forEach(ff -> {
			if (mergedPivotMap.containsKey(ff.getValue().toString())) {
				if (ff.getPivot() != null) {
					mergedPivotMap.get(ff.getValue().toString()).getPivot().addAll(ff.getPivot());
				}
				mergedPivotMap.get(ff.getValue().toString()).addToCount(ff.getCount());
			} else {
				ChartHelper chartHelper = new ChartHelper();
				chartHelper.addToCount(ff.getCount());
				if (ff.getPivot() != null) {
					chartHelper.getPivot().addAll(ff.getPivot());
				}
				mergedPivotMap.put(ff.getValue().toString(), chartHelper);
			}
		});
		return sortChartMap(mergedPivotMap);
	}
	
}
