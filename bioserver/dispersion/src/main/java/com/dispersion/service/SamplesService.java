package com.dispersion.service;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Date;
import java.util.UUID;

import javax.sql.DataSource;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.bee2code.bioimporter.configuration.Configuration;
import com.bee2code.bioimporter.exception.DataDeserializationException;
import com.bee2code.bioimporter.model.json.SampleInfo.AccesionEnum;
import com.bee2code.bioimporter.service.ImporterService;
import com.dispersion.model.BasicPaginationQueryRequest;
import com.dispersion.model.BiobankAccessEnum;
import com.dispersion.model.Job;
import com.dispersion.model.JobStatus;
import com.dispersion.repository.JobRepository;
import com.dispersion.utils.Utils;

@Service
public class SamplesService implements ISamplesService {

	@Autowired
	private SolrClient solrClient;

	@Autowired
	private JobRepository jobRepository;

	@Autowired
	private SolrIndexer solrIndexer;

	@Autowired
	private Environment environment;

	@Autowired
	private DataSource dataSource;

	public static final String ACCESS_FIELD = "accession";

	private static final Logger log = LoggerFactory.getLogger(SamplesService.class);
	
	@Override
	public long countSamplesForQuery(String biobank, BasicPaginationQueryRequest queryRequest) {
		try {
			if (queryRequest.getQuery() == null || queryRequest.getPage() == null
					|| queryRequest.getMaxRows() == null) {
				throw new RuntimeException("Wrong query for samples");
			}

			SolrQuery solrQuery = new SolrQuery();
			String query = queryRequest.getQuery();

			String authorizedSolrQueryPart = getAuthorizedSolrQueryPart(biobank);

			query += authorizedSolrQueryPart;

			solrQuery.setQuery(query);
			solrQuery.setRows(0);
			
			return solrClient.query(solrQuery).getResults().getNumFound();
		} catch (SignatureVerificationException e) {
			log.error("Token's Signature is invalid", e);
			throw new RuntimeException("Token's Signature is invalid");
		} catch (RuntimeException bre) {
			throw bre;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public JSONObject getSamplesForQuery(String biobank, BasicPaginationQueryRequest queryRequest) {
		try {

			if (queryRequest.getQuery() == null || queryRequest.getPage() == null
					|| queryRequest.getMaxRows() == null) {
				throw new RuntimeException("Wrong query for samples");
			}

			SolrQuery solrQuery = new SolrQuery();
			String query = queryRequest.getQuery();

			String authorizedSolrQueryPart = getAuthorizedSolrQueryPart(biobank);

			query += authorizedSolrQueryPart;

			solrQuery.setQuery(query);
			solrQuery.setStart(queryRequest.getPage() * queryRequest.getMaxRows());
			solrQuery.setRows(queryRequest.getMaxRows());
			
			if (queryRequest.getSortField() != null && !queryRequest.getSortField().isEmpty()) {
				solrQuery.addSort(queryRequest.getSortField(), queryRequest.isSortDesc() ? ORDER.desc : ORDER.asc);
			}
			
			SolrDocumentList solrDocumentList = null;
			QueryResponse response = null;

			response = solrClient.query(solrQuery);
			solrDocumentList = response.getResults();

			log.info("Biobank " + biobank + " requested for samples, request query: " + queryRequest.getQuery());

			if(solrDocumentList.getNumFound() == 0) {
				return null;
			} else {
				return Utils.parseSolrResult(solrDocumentList);
			}

		} catch (SignatureVerificationException e) {
			log.error("Token's Signature is invalid", e);
			throw new RuntimeException("Token's Signature is invalid");
		} catch (RuntimeException bre) {
			throw bre;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}

	}

	private String getAuthorizedSolrQueryPart(String biobank) {
		try (Connection connection = dataSource.getConnection()) {

			String accessSql = "SELECT ACCESS FROM BIOBANK WHERE NAME = '" + biobank + "'";
			BiobankAccessEnum biobankAccess = null;

			ResultSet result = connection.createStatement().executeQuery(accessSql);

			if (result.next()) {
				biobankAccess = BiobankAccessEnum.valueOf(result.getString("ACCESS"));
			}

			if (biobankAccess == null) {
				throw new RuntimeException("ACCESS not found for Biobank with name: " + biobank);
			}

			StringBuilder accessQueryBuilder = new StringBuilder();
			accessQueryBuilder.append(" AND (accession:");

			switch (biobankAccess) {
			case ALL: {
				return "";
			}
			case ONLY_PUBLIC: {
				accessQueryBuilder.append(AccesionEnum.ACCESSIBLE.toString());
				accessQueryBuilder.append(")");
				return accessQueryBuilder.toString();
			}
			case PUBLIC_PROTECTED: {
				accessQueryBuilder.append(AccesionEnum.ACCESSIBLE.toString());
				accessQueryBuilder.append(" OR ");
				accessQueryBuilder.append(ACCESS_FIELD + ":" + AccesionEnum.LIMITED_ACCESS.toString() + " )");
				return accessQueryBuilder.toString();
			}
			default: {
				throw new RuntimeException("Not known access level: " + biobankAccess);
			}
			}
		} catch (Exception e) {
			String errorMsg = "Error while getting accession query part";
			log.error(errorMsg, e);
			throw new RuntimeException(errorMsg);
		}

	}

	@Override
	public void importSamples(MultipartFile file) {
		File newFile = null;
		String biobank = environment.getProperty("institution.name");
		try {
			newFile = new File(environment.getProperty("file.upload.location") + UUID.randomUUID().toString());
			newFile.createNewFile();

			try (FileOutputStream outputStream = new FileOutputStream(newFile)) {
				outputStream.write(file.getBytes());
			}
			Configuration configuration = solrIndexer.getImporterConfiguration(newFile.getAbsolutePath(), biobank);
			ImporterService.deserializeAndValidateDonorData(configuration);
			Job job = new Job();
			job.setFilePath(newFile.getAbsolutePath());
			job.setStatus(JobStatus.NEW);
			job.setUser(environment.getProperty("institution.name"));
			job.setAddTime(new Date().getTime());
			job.setOriginalFileName(file.getOriginalFilename());
			job.setBiobankId(biobank);
			jobRepository.save(job);

			log.info("Biobank " + biobank + " added samples to que");

		} catch (DataDeserializationException dde) {
			log.error("Error while deserialization process", dde);
			if (newFile != null) {
				newFile.delete();
			}
			throw new RuntimeException(dde.getMessage());
		} catch (Exception e) {
			log.error("Error while importing samples", e);
			throw new RuntimeException("Error while getting samples");
		}
	}

}
