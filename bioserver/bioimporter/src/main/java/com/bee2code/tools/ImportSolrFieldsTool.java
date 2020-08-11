package com.bee2code.tools;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.request.schema.SchemaRequest;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ImportSolrFieldsTool {

	private static SolrClient solrClient = null;

	public static void main(String[] args) {

		if (args.length != 2) {
			throw new RuntimeException(
					"Wrong numbers of arguments. There should be 2 in order: solr url, path to description file");
		}

		try {
			solrClient = new HttpSolrClient.Builder(args[0]).build();
			createSolrSchema(args[1]);
			System.out.println("IMPORT FINISHED");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void createSolrSchema(String descriptionFilePath)
			throws JsonParseException, JsonMappingException, IOException {

		try {
			File jsonDescription = new File(descriptionFilePath);

			ObjectMapper objectMapper = new ObjectMapper();
			List<Field> fields = objectMapper.readValue(jsonDescription, new TypeReference<List<Field>>() {
			});

			fields.forEach(f -> {
				try {
					addSchemaField(f);
				} catch (Exception e) {
					throw new RuntimeException(e.getMessage());
				}
			});
		} catch (Exception e) {
			throw e;
		}
	}

	private static void addSchemaField(Field field) throws SolrServerException, IOException {
		Map<String, Object> fieldAttributes = new LinkedHashMap<>();
		fieldAttributes.put("name", field.getFieldName());
		fieldAttributes.put("type", field.getFieldType());
		fieldAttributes.put("stored", field.isStored());
		fieldAttributes.put("indexed", field.isIndexed());
		fieldAttributes.put("multiValued", field.isMultiValued());

		SchemaRequest.AddField addFieldUpdateSchemaRequest = new SchemaRequest.AddField(fieldAttributes);
		addFieldUpdateSchemaRequest.process(solrClient);
	}
}
