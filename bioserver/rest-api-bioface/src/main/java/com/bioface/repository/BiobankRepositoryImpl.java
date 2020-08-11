package com.bioface.repository;

import java.util.List;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.bioface.model.Biobank;
import com.bioface.model.ext.BasicPaginationQueryRequest;
import com.bioface.model.ext.BasicPaginationQueryResponse;

public class BiobankRepositoryImpl implements CustomBiobankRepository {

	@Autowired
	private MongoTemplate mongoTemplate;

	private Criteria createBiobankKeywordCriteria(String keyword) {
		Criteria keywordCriteria = null;
		if (keyword != null && !keyword.isEmpty()) {
			keywordCriteria = new Criteria();
			keywordCriteria.orOperator(Criteria.where("id").regex(keyword, "i"),
					Criteria.where("name").regex(keyword, "i"), Criteria.where("acronym").regex(keyword, "i"),
					Criteria.where("url").regex(keyword, "i"), Criteria.where("juristicPerson").regex(keyword, "i"),
					Criteria.where("country").regex(keyword, "i"));
		}

		return keywordCriteria;
	}

	@Override
	public BasicPaginationQueryResponse<Biobank> searchBiobanks(BasicPaginationQueryRequest biobankQuery,
			List<String> biobankIdsAvailable) {
		Query query = new Query();

		Criteria keywordCriteria = createBiobankKeywordCriteria(biobankQuery.getQuery());

		Criteria orCriteria = new Criteria();
		Criteria[] regExList = new Criteria[biobankIdsAvailable.size()];

		IntStream.range(0, biobankIdsAvailable.size()).forEach(i -> {
			regExList[i] = Criteria.where("biobankId").regex("^" + biobankIdsAvailable.get(i) + "$", "i");
		});

		orCriteria.orOperator(regExList);

		if (keywordCriteria != null) {
			Criteria andCriteria = new Criteria();
			andCriteria.andOperator(keywordCriteria, orCriteria);
			query.addCriteria(andCriteria);
		} else {
			query.addCriteria(orCriteria);
		}

		query.with(biobankQuery.getPagable());

		BasicPaginationQueryResponse<Biobank> response = new BasicPaginationQueryResponse<Biobank>();
		response.setResultList(mongoTemplate.find(query, Biobank.class));
		response.setRowsNum(mongoTemplate.count(query, Biobank.class));

		return response;
	}

	@Override
	public boolean biobankWithIdExists(String biobankId) {

		Query query = new Query(Criteria.where("biobankId").regex("^" + biobankId + "$", "i"));

		return mongoTemplate.exists(query, Biobank.class);
	}

	@Override
	public BasicPaginationQueryResponse<Biobank> getAllBiobanks(BasicPaginationQueryRequest biobankQuery) {
		Query query = new Query();

		Criteria keywordCriteria = createBiobankKeywordCriteria(biobankQuery.getQuery());

		if (keywordCriteria != null) {
			query.addCriteria(keywordCriteria);
		}
		
		query.with(biobankQuery.getPagable());

		BasicPaginationQueryResponse<Biobank> response = new BasicPaginationQueryResponse<Biobank>();
		response.setResultList(mongoTemplate.find(query, Biobank.class));
		response.setRowsNum(mongoTemplate.count(query, Biobank.class));

		return response;
	}

}
