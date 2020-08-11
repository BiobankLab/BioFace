package com.bioface.service;

import java.util.List;
import java.util.stream.IntStream;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.permission.WildcardPermission;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bioface.exception.BadRequestException;
import com.bioface.model.Biobank;
import com.bioface.model.ext.BasicPaginationQueryRequest;
import com.bioface.model.ext.BasicPaginationQueryResponse;
import com.bioface.repository.BiobankRepository;

@Service
public class BiobankService implements IBiobankService {

	@Autowired
	private BiobankRepository iBiobankRepository;

	@Autowired
	private IUserRoleService iUserRoleService;

	private static final Logger log = LoggerFactory.getLogger(BiobankService.class);

	private static final int MAX_BIOBANK_ID_LENGTH = 70;
	private static final int MIN_BIOBANK_ID_LENGTH = 3;

	@Override
	public List<Biobank> getAllBiobanks(String user) {
		return iBiobankRepository.findAll();
	}

	@Override
	public void addBiobank(String user, Biobank biobank) {
		try {
			if (biobank.getBiobankId() == null || biobank.getBiobankId().isEmpty()) {
				throw new BadRequestException("Biobank id cannot be empty");
			}

			if (biobank.getBiobankId().length() > MAX_BIOBANK_ID_LENGTH || biobank.getBiobankId().contains(" ")
					|| biobank.getBiobankId().contains("_")
					|| biobank.getBiobankId().length() < MIN_BIOBANK_ID_LENGTH) {
				throw new BadRequestException("Biobank id cannot be longer than " + MAX_BIOBANK_ID_LENGTH
						+ " signs and contains spaces or underscores");
			}
			biobank.setBiobankId(biobank.getBiobankId().toLowerCase());
			if (iBiobankRepository.biobankWithIdExists(biobank.getBiobankId())) {
				throw new BadRequestException("Biobank with id: " + biobank.getBiobankId() + " already exists");
			}
			iBiobankRepository.save(biobank);
			iUserRoleService.createBiobankRoles(biobank);
		} catch (BadRequestException e) {
			throw e;
		} catch (Exception e) {
			log.error("Error during create new Biobank", e);
			throw e;
		}
	}

	@Override
	public void updateBiobank(String user, Biobank biobank) {
		try {
			if (biobank.getId() != null && iBiobankRepository.existsById(biobank.getId())) {

				Biobank orgBiobank = iBiobankRepository.findById(biobank.getId()).get();
				biobank.setBiobankId(orgBiobank.getBiobankId());

				if (SecurityUtils.getSubject().hasRole("ADMIN") || SecurityUtils.getSubject()
						.isPermitted(new WildcardPermission(UserRoleService.BIOBANK_PERMISSION_PREFIX
								+ biobank.getBiobankId() + UserRoleService.BIOBANK_PERMISSION_EDIT_SUFFIX))) {
					iBiobankRepository.save(biobank);
				} else {
					throw new AuthorizationException("You don't have permission to edit this biobank");
				}

			} else {
				throw new BadRequestException(biobank.getId());
			}
		} catch (BadRequestException bre) {
			throw bre;
		} catch (Exception e) {
			log.error("Error during update biobank: ", e);
			throw e;
		}
	}

	@Override
	public BasicPaginationQueryResponse<Biobank> searchBiobanks(String user, BasicPaginationQueryRequest biobankQuery) {
		try {
			if (SecurityUtils.getSubject().hasRole("ADMIN")) {
				return iBiobankRepository.getAllBiobanks(biobankQuery);
			} else {
				List<String> userBiobankRoles = iUserRoleService.getRolesWithPrefixForUser(user,
						UserRoleService.BIOBANK_ROLE_PREFIX);

				if (userBiobankRoles.isEmpty()) {
					throw new RuntimeException("No permission for any biobank");
				}

				IntStream.range(0, userBiobankRoles.size()).forEach(i -> {
					userBiobankRoles.set(i,
							userBiobankRoles.get(i).replaceFirst(UserRoleService.BIOBANK_ROLE_PREFIX, ""));
				});
				BasicPaginationQueryResponse<Biobank> biobankResponse = iBiobankRepository.searchBiobanks(biobankQuery,
						userBiobankRoles);
				for (Biobank biobank : biobankResponse.getResultList()) {
					if (SecurityUtils.getSubject().isPermitted(UserRoleService.BIOBANK_PERMISSION_PREFIX
							+ biobank.getBiobankId() + UserRoleService.BIOBANK_PERMISSION_EDIT_SUFFIX)) {
						biobank.setCanEdit(true);
					} else {
						biobank.setCanEdit(false);
					}
					if (SecurityUtils.getSubject().isPermitted(UserRoleService.BIOBANK_PERMISSION_PREFIX
							+ biobank.getBiobankId() + UserRoleService.BIOBANK_PERMISSION_IMPORT_SUFFIX)) {
						biobank.setCanImport(true);
					} else {
						biobank.setCanImport(false);
					}
				}
				return biobankResponse;
			}
		} catch (Exception e) {
			log.error("Error during search Biobanks: ", e);
			throw new RuntimeException("Error occured while searching biobank");
		}
	}

}
