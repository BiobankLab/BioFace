package com.bioface.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.codec.binary.Base64;
import org.apache.shiro.SecurityUtils;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.request.LukeRequest;
import org.apache.solr.client.solrj.response.LukeResponse;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.bee2code.bioimporter.configuration.Configuration;
import com.bee2code.bioimporter.exception.DataDeserializationException;
import com.bee2code.bioimporter.service.ImporterService;
import com.bioface.exception.AuthorizationException;
import com.bioface.exception.BadRequestException;
import com.bioface.exception.DeserializationDataExceptionApi;
import com.bioface.model.DispersionInstance;
import com.bioface.model.EnumAccess;
import com.bioface.model.Job;
import com.bioface.model.JobStatus;
import com.bioface.model.ext.BasicPaginationQueryRequest;
import com.bioface.model.ext.SearchInDispersionQueryResponse;
import com.bioface.model.ext.UpdateSampleRequest;
import com.bioface.repository.JobRepository;
import com.bioface.utils.Utils;

@Service
public class SampleService implements ISampleService {

	@Autowired
	private SolrClient solrClient;

	@Autowired
	private JobRepository jobRepository;

	@Autowired
	private IUserRoleService iUserRoleService;

	@Autowired
	private Environment environment;

	@Autowired
	private SolrIndexer solrIndexer;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private DispersionInstanceService dispersionInstanceService;

	public static final String INSTITUTION_CI_FIELD_NAME = "biobank";
	public static final String ACCESS_FIELD = "accession";

	private static final Logger log = LoggerFactory.getLogger(SampleService.class);

	@Override
	public JSONObject getSamplesForQuery(String user, BasicPaginationQueryRequest queryRequest) {
		try {
			if (queryRequest.getQuery() == null || queryRequest.getPage() == null
					|| queryRequest.getMaxRows() == null) {
				throw new BadRequestException("Wrong query for samples");
			}

			SolrQuery solrQuery = new SolrQuery();
			String query = queryRequest.getQuery();

			String accessQuery = getAccessSolrQueryPart(user);
			if (!accessQuery.isEmpty()) {
				query += " AND (" + accessQuery + ")";
			}

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
			return Utils.parseSolrResult(solrDocumentList);

		} catch (BadRequestException bre) {
			throw bre;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}

	}

	@Override
	public void addFileToJobQueue(String user, MultipartFile file, String biobankId, Boolean upload) throws IOException {
		File newFile = null;
		try {
			if (!SecurityUtils.getSubject().isPermitted(UserRoleService.BIOBANK_PERMISSION_PREFIX + biobankId
					+ UserRoleService.BIOBANK_PERMISSION_IMPORT_SUFFIX)) {
				throw new AuthorizationException("You have no permission to import samples for biobank: " + biobankId);
			}

			if (!environment.getProperty("dispersionInstance.url").isEmpty() && upload) {
				HttpHeaders headers = new HttpHeaders();
				headers.set("Authorization", "Bearer " + createToken());

				List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
				acceptableMediaTypes.add(MediaType.MULTIPART_FORM_DATA);

				headers.setContentType(MediaType.MULTIPART_FORM_DATA);

				try {
					MultiValueMap<String, Object> requestEntity = new LinkedMultiValueMap<String, Object>();
					final String filename = file.getOriginalFilename();
					requestEntity.add("name", filename);
					requestEntity.add("filename", filename);
					
					ByteArrayResource contentsAsResource = new ByteArrayResource(file.getBytes()){
			            @Override
			            public String getFilename(){
			                return filename;
			            }
			        };
					
			        requestEntity.add("file", contentsAsResource);
			        
			        HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<MultiValueMap<String, Object>>(requestEntity, headers);
			        
					RestTemplate restTemplate = new RestTemplate();
					
					restTemplate.postForObject(
							environment.getProperty("dispersionInstance.url") + "dispersionSample/addSamples",
							entity, String.class);
					
				} catch (Exception e) {
					log.error("Error while uploading samples to dispersion instance", e);
				}
			}

			newFile = new File(environment.getProperty("file.upload.location") + UUID.randomUUID().toString());
			newFile.createNewFile();

			try (FileOutputStream outputStream = new FileOutputStream(newFile)) {
				outputStream.write(file.getBytes());
			}
			Configuration configuration = solrIndexer.getImporterConfiguration(newFile.getAbsolutePath(), biobankId,
					user);
			ImporterService.deserializeAndValidateDonorData(configuration);
			Job job = new Job();
			job.setFilePath(newFile.getAbsolutePath());
			job.setStatus(JobStatus.NEW);
			job.setUser(user);
			job.setAddTime(new Date().getTime());
			job.setOriginalFileName(file.getOriginalFilename());
			job.setBiobankId(biobankId);
			jobRepository.insert(job);
		} catch (DataDeserializationException dde) {
			log.error("Error while deserialization process", dde);
			if (newFile != null) {
				newFile.delete();
			}
			throw new DeserializationDataExceptionApi(dde.getMessage());
		} catch (AuthorizationException ae) {
			log.error("Authorization exception while adding file to job queue (user: " + user + ", biobankId:"
					+ biobankId + ")");
			throw ae;
		} catch (Exception e) {
			log.error("Unknown error while adding file to job queue", e);
			throw new RuntimeException("Error while adding file to job queue");
		}

	}

	@Override
	public String getAccessSolrQueryPart(String user) {
		if (SecurityUtils.getSubject().hasRole("ADMIN")) {
			return "";
		}
		StringBuilder accessQueryBuilder = new StringBuilder();
		accessQueryBuilder.append(ACCESS_FIELD + ":").append(EnumAccess.ACCESSIBLE.getValue());
		List<String> biobankRoles = iUserRoleService.getRolesWithPrefixForUser(user,
				UserRoleService.BIOBANK_ROLE_PREFIX);

		List<String> fullBiobankAccessRoles = biobankRoles.stream()
				.filter(b -> !b.endsWith(UserRoleService.BIOBANK_PROTECTED_SUFFIX)).collect(Collectors.toList());
		List<String> protectedBiobankAccessRoles = biobankRoles.stream()
				.filter(b -> b.endsWith(UserRoleService.BIOBANK_PROTECTED_SUFFIX)).collect(Collectors.toList());

		if (fullBiobankAccessRoles.isEmpty() && protectedBiobankAccessRoles.isEmpty()) {
			return accessQueryBuilder.toString();
		} else {
			if (!fullBiobankAccessRoles.isEmpty()) {
				accessQueryBuilder.append(" OR " + INSTITUTION_CI_FIELD_NAME + ":(");
				IntStream.range(0, fullBiobankAccessRoles.size()).forEach(i -> {
					fullBiobankAccessRoles.set(i,
							fullBiobankAccessRoles.get(i).replaceFirst(UserRoleService.BIOBANK_ROLE_PREFIX, ""));
				});
				accessQueryBuilder.append(String.join(" OR ", fullBiobankAccessRoles));
				accessQueryBuilder.append(")");
			}
			if (!protectedBiobankAccessRoles.isEmpty()) {
				accessQueryBuilder.append(" OR (" + INSTITUTION_CI_FIELD_NAME + ":(");
				IntStream.range(0, protectedBiobankAccessRoles.size()).forEach(i -> {
					protectedBiobankAccessRoles.set(i,
							protectedBiobankAccessRoles.get(i).replaceFirst(UserRoleService.BIOBANK_ROLE_PREFIX, "")
									.replaceFirst(UserRoleService.BIOBANK_PROTECTED_SUFFIX, ""));
				});
				accessQueryBuilder.append(String.join(" OR ", protectedBiobankAccessRoles));
				accessQueryBuilder.append(") + AND " + ACCESS_FIELD + ":").append(EnumAccess.LIMITED_ACCESS.getValue())
						.append(")");
			}
		}

		return accessQueryBuilder.toString();
	}

	@Override
	public void updateSampleAccess(String user, UpdateSampleRequest updateSampleRequest) {
		try {
			SolrDocument sample = solrClient.getById(updateSampleRequest.getId());

			if (sample == null) {
				throw new BadRequestException("Sample with given id doesn't exists");
			}

			if (!SecurityUtils.getSubject().hasRole(
					UserRoleService.BIOBANK_ROLE_PREFIX + sample.getFieldValue(INSTITUTION_CI_FIELD_NAME).toString())) {
				log.error("Unauthorized try changing sample access. User: " + user + ". Sample id: "
						+ updateSampleRequest.getId());
				throw new AuthorizationException("You don't have permission to change access for sample with id: "
						+ updateSampleRequest.getId());
			}

			SolrInputDocument newSample = new SolrInputDocument();
			sample.forEach((k, v) -> {
				newSample.setField(k, v);
			});
			sample.setField(ACCESS_FIELD, updateSampleRequest.getEnumAccess().getValue());
			solrClient.add(newSample);
			solrClient.commit();
		} catch (Exception e) {
			log.error("Error while updating sample access", e);
			throw new RuntimeException("Error while updating sample access");
		}

	}

	@Override
	public List<String> getBiofaceCoreFieldsList() {
		try {
			List<String> fields = new ArrayList<>();
			LukeRequest lukeRequest = new LukeRequest();
			lukeRequest.setNumTerms(0);
			LukeResponse lukeResponse = lukeRequest.process(solrClient);

			lukeResponse.getFieldInfo().forEach((k, v) -> {
				fields.add(k);
			});

			return fields;
		} catch (Exception e) {
			String errorMessage = "Error while getting bioface fields list";
			log.error(errorMessage, e);
			throw new RuntimeException(errorMessage);
		}
	}

	@Override
	public String getFieldsOrder() {
		try {
			String filePath = environment.getProperty("samples.fields.order.file");
			String order = new String(Files.readAllBytes(Paths.get(filePath)));
			return order;
		} catch (IOException e) {
			String exceptionMessage = "Error while reading sample fields order file";
			log.error(exceptionMessage, e);
			throw new RuntimeException(exceptionMessage);
		}
	}

	public PrivateKey convertPrivateKey() {
		try {

			String privateKeyB64 = new String(
					Files.readAllBytes(Paths.get(environment.getProperty("dispersion.privateKeyPath"))));
			privateKeyB64 = privateKeyB64.replace("-----BEGIN PRIVATE KEY-----", "")
					.replace("-----END PRIVATE KEY-----", "").replaceAll("\n", "");

			byte[] decoded = Base64.decodeBase64(privateKeyB64);
			PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decoded);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			PrivateKey privKey = keyFactory.generatePrivate(keySpec);

			return privKey;
		} catch (Exception e) {
			log.error("Error occured while getting private key", e);
			throw new RuntimeException("Error occured while getting private key");
		}
	}
	
	@Override
	public String createToken() {
		try {
			RSAPrivateKey privateKey = (RSAPrivateKey) convertPrivateKey();

			Algorithm algorithmRS = Algorithm.RSA256(null, privateKey);

			String institution = new String(environment.getProperty("dispersion.institutionName"));

			String token = JWT.create().withIssuer("auth0").withClaim("institution", institution).sign(algorithmRS);

			return token;
		} catch (Exception e) {
			log.error("Error occured while creating token", e);
			throw new RuntimeException("Error occured while creating token");
		}
	}

	@Override
	public List<SearchInDispersionQueryResponse> getDispersionSamplesForQuery(
			BasicPaginationQueryRequest queryRequest) {
		List<DispersionInstance> dispersionInstances = dispersionInstanceService.getAllDispersionInstances();

		List<SearchInDispersionQueryResponse> instanceResults = new ArrayList<SearchInDispersionQueryResponse>();

		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer " + createToken());

		for (DispersionInstance dispersionInstance : dispersionInstances) {
			SearchInDispersionQueryResponse newResult = new SearchInDispersionQueryResponse();
			try {
				newResult.setName(dispersionInstance.getName());
				HttpEntity<BasicPaginationQueryRequest> queryEntity = new HttpEntity<BasicPaginationQueryRequest>(
						queryRequest, headers);
				ResponseEntity<String> responseEntity = restTemplate.exchange(dispersionInstance.getBaseUrl() + "dispersionSample/searchSample",
						HttpMethod.POST, queryEntity, String.class);
				newResult.setResult(responseEntity.getBody());
				newResult.setError(false);
			} catch (Exception e) {
				log.error("Error while getting data for instance: " + dispersionInstance.getName(), e);
				newResult.setError(true);
			} finally {
				if(newResult.getResult() != null) {
					instanceResults.add(newResult);
				}
			}
		}
		return instanceResults;
	}
	
	@Override
	public Long countDispersionSamplesForQuery(
			BasicPaginationQueryRequest queryRequest) {
		List<DispersionInstance> dispersionInstances = dispersionInstanceService.getAllDispersionInstances();

		Long counter = 0L;

		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer " + createToken());

		for (DispersionInstance dispersionInstance : dispersionInstances) {
			SearchInDispersionQueryResponse newResult = new SearchInDispersionQueryResponse();
			try {
				newResult.setName(dispersionInstance.getName());
				HttpEntity<BasicPaginationQueryRequest> queryEntity = new HttpEntity<BasicPaginationQueryRequest>(
						queryRequest, headers);
				ResponseEntity<Long> responseEntity = restTemplate.exchange(dispersionInstance.getBaseUrl() + "dispersionSample/countSamples",
						HttpMethod.POST, queryEntity, Long.class);
				counter += responseEntity.getBody();
				newResult.setError(false);
			} catch (Exception e) {
				log.error("Error while getting data for instance: " + dispersionInstance.getName(), e);
				newResult.setError(true);
			}
		}
		return counter;
	}

}
