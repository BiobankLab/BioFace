package com.bee2code.bioimporter.service;

import java.util.List;

import com.bee2code.bioimporter.exception.ImporterServiceException;
import com.bee2code.bioimporter.model.json.Donor;

public interface IDataLoader {
	public void load(List<Donor> donorsList) throws ImporterServiceException;
}
