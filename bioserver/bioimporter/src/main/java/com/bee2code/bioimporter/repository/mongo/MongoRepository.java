package com.bee2code.bioimporter.repository.mongo;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.regex;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.bee2code.bioimporter.configuration.Configuration;
import com.bee2code.bioimporter.model.json.Donor;
import com.bee2code.bioimporter.model.json.Sample;
import com.bee2code.tools.Utils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MongoRepository {

	private final static String ICD10_FIRST_LEVEL_DICTIONARY_COLL = "icd10_level1_dictionaries";
	private final static String ICD10_SECOND_LEVEL_DICTIONARY_COLL = "icd10_level2_dictionaries";
	private final static String DONOR_COLL_NAME = "donor";
	private final static String COLLECTION_COLL_NAME = "collections";
	private Configuration configuration;
	final MongoCollection<Document> firstLevelIcd10Collection;
	final MongoCollection<Document> secondLevelIcd10Collection;
	final MongoCollection<Document> donorCollection;
	final MongoCollection<Document> collections;

	public MongoRepository(MongoClient mongoClient, Configuration configuration) {
		this.configuration = configuration;
		MongoDatabase database = mongoClient.getDatabase(configuration.getMongo().getDatabaseName());
		this.firstLevelIcd10Collection = database.getCollection(ICD10_FIRST_LEVEL_DICTIONARY_COLL);
		this.secondLevelIcd10Collection = database.getCollection(ICD10_SECOND_LEVEL_DICTIONARY_COLL);
		this.donorCollection = database.getCollection(DONOR_COLL_NAME);
		this.collections = database.getCollection(COLLECTION_COLL_NAME);
	}

	public void deleteAndSave(List<Donor> donorsList) {
		try {
			ObjectMapper mapper = Utils.getMapperWithDateFormat();
			List<Document> documents = new ArrayList<>();
			for (Donor donor : donorsList) {
				documents.add(Document.parse(mapper.writeValueAsString(donor)));
			}
			donorCollection.insertMany(documents);
			addCollections(donorsList);

		} catch (Exception e) {
			log.error("Error while saving data to mongo", e);
			throw new RuntimeException("Error while saving data to mongo");
		}
	}

	private void addCollections(List<Donor> donorsList) {
		for (Donor donor : donorsList) {
			if (donor.getSample() != null) {
				for (Sample sample : donor.getSample()) {
					if (sample.getSampleInfo() != null && sample.getSampleInfo().getCollection() != null
							&& !sample.getSampleInfo().getCollection().isEmpty()) {
						addCollectionIfNotExists(sample.getSampleInfo().getCollection());
					}
				}
			}
		}
	}

	private void addCollectionIfNotExists(String collectionId) {
		if (collections
				.find(and(eq("collectionId", collectionId), eq("biobankId", configuration.getJsonFile().getBiobank())))
				.first() == null) {
			Document newCollection = new Document("collectionId", collectionId).append("biobankId",
					configuration.getJsonFile().getBiobank());
			collections.insertOne(newCollection);
		}
	}

	/**
	 * Mapping for first level of icd10
	 */
	public String getIcd10MappingFirstLevel(String icd10) {
		return getDiseaseFromDictionary(firstLevelIcd10Collection, icd10);
	}

	/**
	 * Mapping for second level of icd10
	 */
	public String getIcd10SecondLevelMapping(String icd10) {
		return getDiseaseFromDictionary(secondLevelIcd10Collection, icd10);
	}

	public String getDiseaseFromDictionary(MongoCollection<Document> collection, String icd10) {
		String icd10Main = "";
		if (icd10.contains(".")) {
			icd10Main = icd10.split("\\.")[0];
		} else {
			icd10Main = icd10;
		}
		Document doc = collection.find(regex("values", icd10Main + "*")).first();
		if (doc != null) {
			return doc.getString("name");
		}
		return "Other";
	}

}
