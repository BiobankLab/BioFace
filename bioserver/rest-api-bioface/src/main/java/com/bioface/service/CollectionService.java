package com.bioface.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.ws.rs.BadRequestException;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.solr.client.solrj.SolrClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bioface.model.Collection;
import com.bioface.model.ext.BasicPaginationQueryRequest;
import com.bioface.model.ext.BasicPaginationQueryResponse;
import com.bioface.model.ext.RemoveCollectionRequest;
import com.bioface.repository.CollectionRepository;

@Service
public class CollectionService implements ICollectionService {

	private static final Logger log = LoggerFactory.getLogger(CollectionService.class);

	@Autowired
	private SolrClient solrClient;

	@Autowired
	private IUserRoleService iUserRoleService;

	@Autowired
	private CollectionRepository collectionRepository;

	@Override
	public BasicPaginationQueryResponse<Collection> searchCollections(String user,
			BasicPaginationQueryRequest queryRequest) {
		try {
			List<String> biobankRoles = iUserRoleService.getRolesWithPrefixForUser(user,
					UserRoleService.BIOBANK_ROLE_PREFIX);

			List<String> fullBiobankAccessRoles = biobankRoles.stream()
					.filter(b -> !b.endsWith(UserRoleService.BIOBANK_PROTECTED_SUFFIX)).collect(Collectors.toList());

			IntStream.range(0, fullBiobankAccessRoles.size()).forEach(i -> {
				fullBiobankAccessRoles.set(i, fullBiobankAccessRoles.get(i)
						.replaceAll(UserRoleService.BIOBANK_ROLE_PREFIX, "").toLowerCase());
			});

			return collectionRepository.getCollections(queryRequest, fullBiobankAccessRoles);

		} catch (Exception e) {
			log.error("Error during search collections: ", e);
			throw new RuntimeException("Error during search collections");
		}

	}

	@Override
	public void deleteCollection(String user, RemoveCollectionRequest request) {
		try {
			if (StringUtils.isBlank(request.getCollectionId()) || StringUtils.isBlank(request.getBiobankId())) {
				throw new BadRequestException("Collection and biobank id cannot be empty");
			}
			if (!SecurityUtils.getSubject()
					.hasRole(UserRoleService.BIOBANK_ROLE_PREFIX + request.getBiobankId().toUpperCase())) {
				log.error("Not authorized try to delete collection (user: " + user + ", collection: "
						+ request.getCollectionId() + ", biobank: " + request.getBiobankId() + ")");
				throw new AuthorizationException("Not authorized try to delete collection");
			}

			solrClient.deleteByQuery("collection: \"" + request.getCollectionId() + "\" AND biobank: \""
					+ request.getBiobankId() + "\"");
			collectionRepository.deleteByCollectionIdAndBiobankId(request.getCollectionId(), request.getBiobankId());
			solrClient.commit();
		} catch (Exception e) {
			log.error("Delete collection error", e);
			throw new RuntimeException("Error while deleting collection");
		}

	}

}
