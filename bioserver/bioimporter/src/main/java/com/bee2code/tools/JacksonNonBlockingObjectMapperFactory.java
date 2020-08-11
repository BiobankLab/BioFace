package com.bee2code.tools;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class JacksonNonBlockingObjectMapperFactory {

	private String errorMessage = "";

	@SuppressWarnings("rawtypes")
	private List<JsonDeserializer> jsonDeserializers = new ArrayList<JsonDeserializer>();

	/**
	 * Deserializer that won't block if value parsing doesn't match with target type
	 * @param <T> Handled type
	 */
	private class NonBlockingDeserializer<T> extends JsonDeserializer<T> {
		private JsonDeserializer<T> delegate;

		public NonBlockingDeserializer(JsonDeserializer<T> _delegate) {
			this.delegate = _delegate;
		}

		@Override
		public T deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
			try {
				return delegate.deserialize(jp, ctxt);
			} catch (JsonMappingException e) {
				errorMessage += e.getMessage() + "\n";
				return null;
			}
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ObjectMapper createObjectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		SimpleModule customJacksonModule = new SimpleModule("customJacksonModule");

		//std deserializers
		for (JsonDeserializer jsonDeserializer : jsonDeserializers) {
			if (jsonDeserializer instanceof CreateEnumDeserializer) {
				CreateEnumDeserializer enumDeserializer = (CreateEnumDeserializer) jsonDeserializer;
				customJacksonModule.addDeserializer(enumDeserializer.getEnumClass(),
						new NonBlockingDeserializer(jsonDeserializer));
			} else {
				customJacksonModule.addDeserializer(jsonDeserializer.handledType(),
						new NonBlockingDeserializer(jsonDeserializer));
			}

		}

		objectMapper.registerModule(customJacksonModule);
		return objectMapper;
	}

	public void setJsonDeserializers(List<JsonDeserializer> _jsonDeserializers) {
		this.jsonDeserializers = _jsonDeserializers;
	}

	public void addJsonDeserializer(JsonDeserializer jsonDeserializer) {
		this.jsonDeserializers.add(jsonDeserializer);
	}

	public String getErrorMessage() {
		return errorMessage;
	}
}
