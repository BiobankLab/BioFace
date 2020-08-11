package com.bee2code.bioimporter.configuration;

import lombok.Data;

@Data
public class Configuration {

	private JsonFile jsonFile;
	private Mongo mongo;
	private Solr solr;

}
