package com.bee2code.bioimporter.repository;

import java.util.List;

import com.bee2code.bioimporter.model.json.Donor;

public interface IImportDataRepository {

	List<Donor> loadBioData() throws Exception;
}
