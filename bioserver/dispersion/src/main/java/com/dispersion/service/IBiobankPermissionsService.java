package com.dispersion.service;

import java.util.List;

import com.dispersion.model.BiobankForDispersion;
import com.dispersion.model.BiobankListElement;
import com.dispersion.model.NewBiobankModel;

public interface IBiobankPermissionsService {

	List<BiobankListElement> getBiobanksList();

	void addBiobank(NewBiobankModel biobank);

	boolean checkTokenToConnect(BiobankForDispersion biobank);
	
	void updateBiobankAccession(BiobankListElement biobank);

	void deleteBiobank(BiobankListElement biobank);

}