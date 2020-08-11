package com.dispersion.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.dispersion.model.BiobankAccessEnum;
import com.dispersion.model.BiobankForDispersion;
import com.dispersion.model.BiobankListElement;
import com.dispersion.model.NewBiobankModel;

@Service
public class BiobankPermissionsService implements IBiobankPermissionsService {

	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private Environment environment;


	private static final Logger log = LoggerFactory.getLogger(BiobankPermissionsService.class);

	@Override
	public void addBiobank(NewBiobankModel biobank) {

		try (Connection connection = dataSource.getConnection()) {

			String insertBiobankSql = "INSERT INTO BIOBANK(NAME,KEY,ACCESS) VALUES ('" + biobank.getBiobankName()
					+ "','" + biobank.getBiobankKey() + "','" + BiobankAccessEnum.ONLY_PUBLIC.getName() + "')";

			PreparedStatement addBiobankStatement = connection.prepareStatement(insertBiobankSql);

			addBiobankStatement.execute();

			log.info("Biobank " + biobank.getBiobankName() + " was added by admin");

		} catch (Exception e) {
			log.error("Error while adding biobank " + biobank, e);
			throw new RuntimeException("Error while adding biobank");
		}

	}
	
	@Override
	public void deleteBiobank(BiobankListElement biobank) {
		
		try(Connection connection = dataSource.getConnection()) {
			
			String deleteBiobankSql = "DELETE FROM BIOBANK WHERE NAME = '" + biobank.getName() + "'";
			
			PreparedStatement deleteBiobankStatement = connection.prepareStatement(deleteBiobankSql);
			
			deleteBiobankStatement.execute();
			
		} catch (Exception e) {
			log.error("Error while deleting biobank " + biobank, e);
			throw new RuntimeException("Error while deleting biobank");
		}
		
	}

	@Override
	public List<BiobankListElement> getBiobanksList() {

		try (Connection connection = dataSource.getConnection()) {
			String getBiobanksSql = "SELECT NAME, ACCESS FROM BIOBANK";

			List<BiobankListElement> biobanks = new ArrayList<>();
			ResultSet result = connection.createStatement().executeQuery(getBiobanksSql);

			while (result.next()) {
				BiobankListElement biobankListElement = new BiobankListElement();
				biobankListElement.setName(result.getString("NAME"));
				biobankListElement.setAccession(BiobankAccessEnum.valueOf(result.getString("ACCESS")));
				biobanks.add(biobankListElement);
			}

			biobanks = biobanks.stream()
					.filter(a -> !a.getName().equals(environment.getProperty("institution.name")))
					.collect(Collectors.toList());
			
			return biobanks;

		} catch (Exception e) {
			log.error("Error while geting biobanks");
			throw new RuntimeException("Error while geting biobanks");
		}

	}

	@Override
	public void updateBiobankAccession(BiobankListElement biobank) {
		try (Connection connection = dataSource.getConnection()) {
			String updateBiobankSql = "UPDATE BIOBANK SET ACCESS = '" + biobank.getAccession() + "' WHERE NAME = '"
					+ biobank.getName() + "'";

			PreparedStatement updateBiobankStmt = connection.prepareStatement(updateBiobankSql);
			
			updateBiobankStmt.executeUpdate();
		} catch (Exception e) {
			String errorMsg = "Error while updating biobank accession";
			log.error(errorMsg, e);
			throw new RuntimeException(errorMsg);
		}
	}

	@Override
	public boolean checkTokenToConnect(BiobankForDispersion biobank) {
		try (Connection connection = dataSource.getConnection()) {
			String sql = "SELECT TOKEN FROM TOKENS WHERE TOKEN = ? AND USED = 0";

			PreparedStatement getPermissionTokenStatement = connection.prepareStatement(sql);
			getPermissionTokenStatement.setString(1, biobank.getToken());

			ResultSet result = getPermissionTokenStatement.executeQuery();

			if (result.next() != false) {

				StringBuilder addBiobankSql = new StringBuilder();
				addBiobankSql.append("INSERT INTO BIOBANK(NAME, KEY, ACCESS)  VALUES (");
				addBiobankSql.append("'" + biobank.getBiobankName() + "'");
				addBiobankSql.append(", ");
				addBiobankSql.append("'" + biobank.getBiobankKey() + "','");
				addBiobankSql.append(BiobankAccessEnum.ONLY_PUBLIC.getName() + "')");

				PreparedStatement addBiobankStatement = connection.prepareStatement(addBiobankSql.toString());

				String setUsedSql = "UPDATE TOKENS SET USED = 1 WHERE TOKEN = ?";

				PreparedStatement setUsedStatement = connection.prepareStatement(setUsedSql);
				setUsedStatement.setString(1, biobank.getToken());

				connection.setAutoCommit(false);

				addBiobankStatement.execute();
				setUsedStatement.execute();

				connection.commit();

				connection.setAutoCommit(true);

				log.info("Biobank " + biobank.getBiobankName() + " was added by token");

				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			log.error("Error while geting token");
			throw new RuntimeException("Error while geting token");
		}

	}

}
