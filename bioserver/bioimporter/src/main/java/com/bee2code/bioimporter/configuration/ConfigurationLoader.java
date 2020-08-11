package com.bee2code.bioimporter.configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import com.bee2code.bioimporter.exception.InputArgumentsException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConfigurationLoader {

	public static Configuration loadAndValidConfig(String path) throws InputArgumentsException, FileNotFoundException {

		File file = new File(path);
		Configuration config = readConfiguration(new FileInputStream(file));

		/*
		 * if (config.getRiakPort() == null) { throw new
		 * InputArgumentsException("No Riak port specified"); } if
		 * (config.getRiakNodes().length == 0) { throw new
		 * InputArgumentsException("No Riak nodes specified"); }
		 * 
		 * for (String csvFilePath : config.getCsvFilesPath()) { if (!new
		 * File(csvFilePath).exists()) { throw new
		 * InputArgumentsException("Specified file path is wrong"); } } if
		 * (config.getIndexName() == null || config.getIndexName().isEmpty()) { throw
		 * new InputArgumentsException("No index name specified"); }
		 */
		return config;
	}

	public static void validateCsv(Configuration config) throws InputArgumentsException {

	}

	public static Configuration readConfiguration(InputStream file) throws InputArgumentsException {
		ObjectMapper objectMapper = new ObjectMapper();
		Configuration config;
		try {
			config = objectMapper.readValue(file, Configuration.class);
			return config;
		} catch (IOException e) {
			throw new InputArgumentsException("Error while reading configuration file", e);
		}
	}

}
