package com.bee2code.bioimporter.configuration;

import lombok.Data;

@Data
public class Mongo {
	private String address;
	private Integer port;
	private String databaseName;
}
