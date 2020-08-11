package com.bee2code.bioimporter.configuration;

import lombok.Data;

@Data
public class JsonFile {

	private String path;
	private String user;
	private String biobank;

	public JsonFile() {
		super();
	}

	public JsonFile(String path) {
		super();
		this.path = path;
	}

}
