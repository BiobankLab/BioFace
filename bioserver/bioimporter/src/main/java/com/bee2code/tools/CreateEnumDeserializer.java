package com.bee2code.tools;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;

public final class CreateEnumDeserializer<T> extends JsonDeserializer<T> {

	private Class<?> enumClass;

	public CreateEnumDeserializer(Class<?> enumClass) {
		super();
		this.enumClass = enumClass;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public T deserialize(final JsonParser parser, final DeserializationContext context)
			throws IOException, JsonMappingException {

		for (Object enumValue : enumClass.getEnumConstants()) {
			Enum val = (Enum) enumValue;
			if (val.toString().equalsIgnoreCase(parser.getText())) {
				return (T) val;
			}
		}

		//prepare message error
		StringBuilder errorMessage = new StringBuilder();
		errorMessage.append("Error on deserialization ");
		errorMessage.append(enumClass.getSimpleName());
		errorMessage.append(" Possible values: [");
		for (Object o : enumClass.getEnumConstants()) {

			Enum val = (Enum) o;
			errorMessage.append(val.toString());
			errorMessage.append(", ");
		}
		errorMessage.delete(errorMessage.length() - 2, errorMessage.length());
		errorMessage.append("]");
		errorMessage.append(" Founded: ");
		errorMessage.append(parser.getText());

		//throw error
		throw JsonMappingException.from(context, errorMessage.toString());
	}

	public Class<?> getEnumClass() {
		return enumClass;
	}

}