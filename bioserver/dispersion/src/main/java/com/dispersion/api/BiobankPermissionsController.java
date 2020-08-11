package com.dispersion.api;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dispersion.model.BiobankForDispersion;
import com.dispersion.model.BiobankListElement;
import com.dispersion.model.NewBiobankModel;
import com.dispersion.service.IBiobankPermissionsService;

@Controller
@RequestMapping(value = "/permissions")
public class BiobankPermissionsController {
	
	@Autowired
	private IBiobankPermissionsService iBiobankPermissionsService;

	@RequestMapping(method = RequestMethod.GET, value = "/biobanks")
	public ResponseEntity<List<BiobankListElement>> getBiobanksList() {
		return new ResponseEntity<>(iBiobankPermissionsService.getBiobanksList(), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/biobank/accession")
	public ResponseEntity<Void> changeBiobankAccession(@RequestBody BiobankListElement biobank) {
		iBiobankPermissionsService.updateBiobankAccession(biobank);
		return ResponseEntity.ok().build();
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/checkToken")
	public ResponseEntity<Boolean> checkIfTokenIsActive(Principal principal, @RequestBody BiobankForDispersion biobank) {
		return new ResponseEntity<>(iBiobankPermissionsService.checkTokenToConnect(biobank), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/addBiobank")
	public ResponseEntity<Void> addBiobank(@RequestBody NewBiobankModel biobank) {
		iBiobankPermissionsService.addBiobank(biobank);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/deleteBiobank")
	public ResponseEntity<Void> deleteBiobank(@RequestBody BiobankListElement biobank) {
		iBiobankPermissionsService.deleteBiobank(biobank);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
