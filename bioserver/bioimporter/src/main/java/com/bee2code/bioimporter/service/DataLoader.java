package com.bee2code.bioimporter.service;

import java.util.List;

import com.bee2code.bioimporter.configuration.Configuration;
import com.bee2code.bioimporter.exception.ImporterServiceException;
import com.bee2code.bioimporter.model.json.Donor;
import com.bee2code.bioimporter.repository.IndexRepository;
import com.bee2code.bioimporter.repository.mongo.MongoRepository;
import com.bee2code.bioimporter.repository.solr.SolrRepository;
import com.mongodb.MongoClient;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DataLoader implements IDataLoader {

	private Configuration configuration;
	private IndexRepository indexRepository;

	public DataLoader(Configuration configuration) throws ImporterServiceException {
		this.configuration = configuration;
	}

	@Override
	public void load(List<Donor> donorsList) throws ImporterServiceException {
		MongoClient mongoClient = createMongoClient();
		MongoRepository mongoRepository = new MongoRepository(mongoClient, configuration);
		indexRepository = new SolrRepository(configuration, mongoRepository);
		try {
			mongoRepository.deleteAndSave(donorsList);
			indexRepository.indexObject(donorsList);
		} catch (Exception e) {
			log.error("Error index data", e);
			throw new ImporterServiceException(e.getMessage());
		} finally {
			mongoClient.close();
		}

	}

	public MongoClient createMongoClient() {
		return new MongoClient();

	}

}
