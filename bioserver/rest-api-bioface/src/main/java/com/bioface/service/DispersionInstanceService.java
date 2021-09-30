package com.bioface.service;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bioface.model.BiobankForDispersion;
import com.bioface.model.DispersionInstance;
import com.bioface.model.Job;
import com.bioface.model.ext.BasicPaginationQueryRequest;
import com.bioface.model.ext.BasicPaginationQueryResponse;
import com.bioface.repository.DispersionInstanceRepository;

@Service
public class DispersionInstanceService implements IDispersionInstanceService {

	private static final Logger log = LoggerFactory.getLogger(DispersionInstanceService.class);

	@Autowired
	private DispersionInstanceRepository dispersionInstanceRepository;

	@Autowired
	private ISampleService iSampleService;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private Environment environment;

	@Autowired
	private IUserRoleService iUserRoleService;

	@Override
	public List<DispersionInstance> getAllDispersionInstances() {
		return dispersionInstanceRepository.findAll();
	}

	@Override
	public void saveDispersionInstance(DispersionInstance dispersionInstance) {
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("Authorization", "Bearer " + iSampleService.createToken());

			if(!dispersionInstance.getToken().isEmpty()) {
				String publicKeyB64 = new String(
						Files.readAllBytes(Paths.get(environment.getProperty("dispersion.publicKeyPath"))));

				BiobankForDispersion biobankForDispersion = new BiobankForDispersion();
				biobankForDispersion.setBiobankKey(publicKeyB64);
				biobankForDispersion.setBiobankName(environment.getProperty("dispersion.institutionName"));
				biobankForDispersion.setToken(dispersionInstance.getToken());

				HttpEntity<BiobankForDispersion> entity = new HttpEntity<BiobankForDispersion>(biobankForDispersion, headers);
				ResponseEntity<String> responseEntity = restTemplate.exchange(dispersionInstance.getBaseUrl() + "permissions/checkToken",
						HttpMethod.POST, entity, String.class);

				if(responseEntity.getBody().matches("true")) {
					dispersionInstanceRepository.save(dispersionInstance);
					iUserRoleService.createDispersionInstanceRoles(dispersionInstance);
				} else {
					String message = "Error while saving dispersion instance ";
					log.error(message);
					throw new RuntimeException(message);
				}
			} else {
				try {
					dispersionInstanceRepository.save(dispersionInstance);
					iUserRoleService.createDispersionInstanceRoles(dispersionInstance);
				} catch(Exception e) {
					String message = "Error while saving dispersion instance ";
					log.error(message, e);
				}

			}

		} catch (Exception e) {
			String message = "Error while saving dispersion instance ";
			log.error(message, e);
			throw new RuntimeException(message);
		}
	}

	@Override
	public void removeDispersionInstance(String id) {
		try {
			dispersionInstanceRepository.deleteById(id);
		} catch (Exception e) {
			String message = "Error while removing dispersion instance";
			throw new RuntimeException(message);
		}

	}

	public BasicPaginationQueryResponse<Job> getDispersionJobs(BasicPaginationQueryRequest request) {

		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer " + iSampleService.createToken());
		HttpEntity<BasicPaginationQueryRequest> entity = new HttpEntity<BasicPaginationQueryRequest>(request, headers);
		ResponseEntity<BasicPaginationQueryResponse<Job>> responseEntity = restTemplate.exchange(environment.getProperty("dispersionInstance.url") + "dispersionJob/",
				HttpMethod.POST, entity, new ParameterizedTypeReference<BasicPaginationQueryResponse<Job>>() {});

		return responseEntity.getBody();

	}

}
