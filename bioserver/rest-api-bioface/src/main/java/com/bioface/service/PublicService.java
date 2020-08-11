package com.bioface.service;

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
import org.springframework.web.client.RestTemplate;

import com.bioface.model.DispersionInstance;
import com.bioface.model.SampleChartData;
import com.bioface.model.SamplesChartDataResponse;
import com.bioface.model.ext.ChartHelper;
import com.bioface.repository.BiobankRepository;
import com.bioface.repository.DispersionInstanceRepository;

@Service
public class PublicService implements IPublicService {

	@Autowired
	private SolrClient solrClient;
	
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private BiobankRepository biobankRepository;
	
	@Autowired
	private DispersionInstanceRepository dispersionInstanceRepository;
	
	@Autowired
	private IDispersionInstanceService iDispersionInstanceService;

	private static final int MAX_CHART_SIZE = 10;

	private static final Logger log = LoggerFactory.getLogger(PublicService.class);

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
	
	@Override
	public SamplesChartDataResponse getDispersionSamplesChartData() {
		
		List<DispersionInstance> dispersionInstances = dispersionInstanceRepository.findAll();
		
		SamplesChartDataResponse responseChartData = new SamplesChartDataResponse();
		
		List<SampleChartData> samplesData = new ArrayList<>();
		
		for(DispersionInstance dispersionInstance : dispersionInstances) {
			try {
				samplesData.addAll((restTemplate.getForObject(dispersionInstance.getBaseUrl() + "public/chartData", SamplesChartDataResponse.class)).getSamplesData());
			} catch (Exception e) {
				log.error("Error while getting dispersion samples data " + e);
			}
			
		}
		
		responseChartData.setSamplesData(samplesData);
		
		return responseChartData;
	}

	@Override
	public Long getBiobanksCount() {
		return biobankRepository.count();
	}
	
	@Override
	public Long getDispersionInstancesCount() {
		return dispersionInstanceRepository.count();
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
	
	@Override
	public Long getDispersionSamplesCount() {
		
		List<DispersionInstance> instances = iDispersionInstanceService.getAllDispersionInstances();
		
		Long dispersionSamplesCount = (long) 0;
		
		for(DispersionInstance instance : instances) {
			try {
				dispersionSamplesCount += restTemplate.getForObject(instance.getBaseUrl() + "public/countSamples", Long.class);
			} catch (Exception e) {
				log.error("Error while getting dispersion samples count" + e);
			}
		}
		
		return dispersionSamplesCount;
		
	}

}
