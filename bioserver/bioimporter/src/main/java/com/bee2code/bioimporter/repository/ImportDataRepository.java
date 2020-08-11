package com.bee2code.bioimporter.repository;

import java.io.File;
import java.util.List;

import com.bee2code.bioimporter.configuration.Configuration;
import com.bee2code.bioimporter.exception.DataDeserializationException;
import com.bee2code.bioimporter.model.json.Donor;
import com.bee2code.tools.JacksonNonBlockingObjectMapperFactory;
import com.bee2code.tools.Utils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ImportDataRepository implements IImportDataRepository {

	private Configuration configuration;

	public ImportDataRepository(Configuration configuration) {
		super();
		this.configuration = configuration;
	}

	@Override
	public List<Donor> loadBioData() throws Exception {
		try {
			if (configuration.getJsonFile().getBiobank() == null
					|| configuration.getJsonFile().getBiobank().isEmpty()) {
				throw new RuntimeException("Missing biobank");
			}

			JacksonNonBlockingObjectMapperFactory factory = Utils.prepareDonorObjectMapperFactory();
			ObjectMapper objectMapper = factory.createObjectMapper();
			File jsonFile = new File(configuration.getJsonFile().getPath());
			List<Donor> donorsList = objectMapper.readValue(jsonFile, new TypeReference<List<Donor>>() {
			});

			if (factory.getErrorMessage() != null && !factory.getErrorMessage().isEmpty()) {
				throw new DataDeserializationException(factory.getErrorMessage());
			}

			return donorsList;
		} catch (DataDeserializationException dde) {
			log.error("Deserialization data error", dde);
			throw dde;
		} catch (Exception e) {
			log.error("Unknown error while parsing data", e);
			throw new RuntimeException(e.getMessage());
		}
	}

}
