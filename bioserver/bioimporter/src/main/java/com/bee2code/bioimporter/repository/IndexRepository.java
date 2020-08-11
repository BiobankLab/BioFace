package com.bee2code.bioimporter.repository;

import java.util.List;

import com.bee2code.bioimporter.model.json.Donor;

public interface IndexRepository {
	public void indexObject(List<Donor> donorsList) throws Exception;
}
