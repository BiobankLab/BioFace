package com.bee2code.bioimporter.service;

import com.bee2code.bioimporter.configuration.Configuration;
import com.bee2code.bioimporter.exception.ImporterServiceException;

public interface IImporterService {

	/**
	 * Start import
	 * 
	 * @param args
	 */
	void run(String path) throws ImporterServiceException;

	/**
	 * 
	 * @param configuration
	 * @throws ImporterServiceException
	 */
	void runWithCustomConfiguration(Configuration configuration) throws ImporterServiceException;
}
