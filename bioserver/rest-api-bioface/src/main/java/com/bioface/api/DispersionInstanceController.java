package com.bioface.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bioface.model.DispersionInstance;
import com.bioface.service.IDispersionInstanceService;

@Controller
@RequestMapping(value = "/dispersionInstances")
public class DispersionInstanceController {

	@Autowired
	private IDispersionInstanceService iDispersionInstanceService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<List<DispersionInstance>> getAllDispersionInstances() {
		return ResponseEntity.ok().body(iDispersionInstanceService.getAllDispersionInstances());
	}
}
