package com.bee2code.bioimporter.repository.solr;

import java.util.List;

import com.bee2code.bioimporter.configuration.Configuration;
import com.bee2code.bioimporter.model.json.Donor;
import com.bee2code.bioimporter.repository.IndexRepository;
import com.bee2code.bioimporter.repository.mongo.MongoRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * Repository to create a index in solr.
 *
 * @author mdylag
 *
 */
@Slf4j
public class SolrRepository implements IndexRepository {

	private Configuration config;
	private MongoRepository mongoRepository;

	public SolrRepository(Configuration configuration, MongoRepository mongoRepository) {
		config = configuration;
		this.mongoRepository = mongoRepository;
	}

	@Override
	public void indexObject(List<Donor> donorsList) throws Exception {
		SolrIndexer solrIndexer = new SolrIndexer(config, donorsList, mongoRepository);
		solrIndexer.index();

	}

}
