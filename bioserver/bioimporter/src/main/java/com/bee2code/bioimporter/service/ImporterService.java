package com.bee2code.bioimporter.service;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.bee2code.bioimporter.configuration.Configuration;
import com.bee2code.bioimporter.configuration.ConfigurationLoader;
import com.bee2code.bioimporter.exception.DataDeserializationException;
import com.bee2code.bioimporter.exception.ImporterServiceException;
import com.bee2code.bioimporter.exception.InputArgumentsException;
import com.bee2code.bioimporter.model.json.Donor;
import com.bee2code.bioimporter.model.json.Sample;
import com.bee2code.bioimporter.repository.ImportDataRepository;

public class ImporterService implements IImporterService {

	private Configuration config;
	private IDataLoader iDataLoader;

	@Override
	public void run(String path) throws ImporterServiceException {
		try {
			config = ConfigurationLoader.loadAndValidConfig(path);
		} catch (FileNotFoundException e) {
			throw new ImporterServiceException("Problem to read configuration", e);
		} catch (InputArgumentsException e) {
			throw new ImporterServiceException("Problem with parameters in the configuration file", e);
		}
	}

	@Override
	public void runWithCustomConfiguration(Configuration configuration) throws ImporterServiceException {
		iDataLoader = new DataLoader(configuration);
		try {
			List<Donor> donorsList = deserializeAndValidateDonorData(configuration);
			iDataLoader.load(donorsList);
		} catch (Exception e) {
			throw new ImporterServiceException("Problem with loading data. Details: \n" + e.getMessage(), e);
		}

	}

	/**
	 * @author mbucko 
	 * Validation 
	 * 1) Remove donor if samples not exist 
	 * 2) Remove sample if sample info not exist
	 * @param donorsList
	 * @throws Exception 
	 */
	public static List<Donor> deserializeAndValidateDonorData(Configuration configuration) throws Exception {
		ImportDataRepository importDataRepository = new ImportDataRepository(configuration);
		List<Donor> donorsList = importDataRepository.loadBioData();

		if (donorsList == null || donorsList.isEmpty()) {
			throw new DataDeserializationException("No data to import");
		}

		StringBuilder dataErrorMessageBuilder = new StringBuilder();
		//for check duplicates propose 
		Map<String, String> sampleIdCollectionValidationMap = new HashMap<>();

		for (Donor donor : donorsList) {
			if (donor.getSample() == null || donor.getSample().isEmpty()) {
				dataErrorMessageBuilder.append("Samples don't exist in donor with id: " + donor.getDonorId() + "\n");
				continue;
			}

			for (Sample sample : donor.getSample()) {
				if (sample.getSampleInfo() == null) {
					dataErrorMessageBuilder.append("Sample info doesn't exist. Donor id: " + donor.getDonorId());
				} else {
					if (StringUtils.isBlank(sample.getSampleInfo().getSampleId())
							|| StringUtils.isBlank(sample.getSampleInfo().getCollection())) {
						dataErrorMessageBuilder
								.append("Exists empty sampleId or collection in at least one sample  (Donor id: "
										+ donor.getDonorId());
					}
					if (sampleIdCollectionValidationMap.containsKey(sample.getSampleInfo().getSampleId())) {
						if (sampleIdCollectionValidationMap.get(sample.getSampleInfo().getSampleId())
								.equals(sample.getSampleInfo().getCollection())) {
							dataErrorMessageBuilder.append("Duplicated sample (sample id - collection) Donor id: "
									+ donor.getDonorId() + ", ");
							dataErrorMessageBuilder.append("Sample id: " + sample.getSampleInfo().getSampleId() + ", ");
							dataErrorMessageBuilder
									.append("collection: " + sample.getSampleInfo().getCollection() + "\n");
						}
					} else {
						sampleIdCollectionValidationMap.put(sample.getSampleInfo().getSampleId(),
								sample.getSampleInfo().getCollection());
					}
					sample.getSampleInfo().setBiobank(configuration.getJsonFile().getBiobank());
				}
			}
			if (dataErrorMessageBuilder.length() > 0) {
				dataErrorMessageBuilder.append("\n");
			}
		}
		String emptyDataErrorMessage = dataErrorMessageBuilder.toString();
		if (!emptyDataErrorMessage.isEmpty()) {
			throw new DataDeserializationException(emptyDataErrorMessage);
		}

		return donorsList;

		/**
		 * @author mbucko
		 * Temporary disabled option with removing donor without samples and samples without info, 
		 * solution for now is returning exception, code left for possible future user
		 */
		/*boolean sampleExists = false;
		
		Iterator<Donor> donorIter = donorsList.iterator();
		while (donorIter.hasNext()) {
			Donor donor = donorIter.next();
			if (donor.getSample() != null && !donor.getSample().isEmpty()) {
				sampleExists = true;
				Iterator<Sample> sampleIter = donor.getSample().iterator();
				while (sampleIter.hasNext()) {
					Sample sample = sampleIter.next();
					if (sample.getSampleInfo() == null || StringUtils.isEmpty(sample.getSampleInfo().getSampleId())) {
						sampleIter.remove();
					} else {
						sample.getSampleInfo().setBiobank(configuration.getJsonFile().getBiobank());
					}
				}
			} else {
				donorIter.remove();
			}
		}
		
		if (!sampleExists) {
			throw new RuntimeException("no sample to import in uploaded file");
		}*/
	}

}
