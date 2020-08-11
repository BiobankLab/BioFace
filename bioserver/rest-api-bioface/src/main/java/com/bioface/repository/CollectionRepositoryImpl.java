package com.bioface.repository;

import java.util.List;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.bioface.model.Collection;
import com.bioface.model.ext.BasicPaginationQueryRequest;
import com.bioface.model.ext.BasicPaginationQueryResponse;

public class CollectionRepositoryImpl implements CustomCollectionRepository {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public BasicPaginationQueryResponse<Collection> getCollections(BasicPaginationQueryRequest query,
			List<String> accesibleBiobanks) {
		Query mongoQuery = new Query();

		Criteria collectionBiobankCriteria = Criteria.where("biobankId").in(accesibleBiobanks);
		if (Strings.isEmpty(query.getQuery())) {
			mongoQuery.addCriteria(collectionBiobankCriteria);
		} else {
			Criteria searchCriteria = new Criteria();
			searchCriteria.andOperator(collectionBiobankCriteria,
					Criteria.where("collectionId").regex(query.getQuery(), "i"));
			mongoQuery.addCriteria(searchCriteria);
		}

		mongoQuery.with(query.getPagable());

		BasicPaginationQueryResponse<Collection> response = new BasicPaginationQueryResponse<Collection>();
		response.setResultList(mongoTemplate.find(mongoQuery, Collection.class));
		response.setRowsNum(mongoTemplate.count(mongoQuery, Collection.class));
		return response;
	}
}
