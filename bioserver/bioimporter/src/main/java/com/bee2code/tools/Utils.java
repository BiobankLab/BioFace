package com.bee2code.tools;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.bee2code.bioimporter.model.json.ABOEnum;
import com.bee2code.bioimporter.model.json.DataAvailability;
import com.bee2code.bioimporter.model.json.Donor.GenderEnum;
import com.bee2code.bioimporter.model.json.EthnicGroup.EgNameEnum;
import com.bee2code.bioimporter.model.json.HairColor.FisherSallerEnum;
import com.bee2code.bioimporter.model.json.KellEnum;
import com.bee2code.bioimporter.model.json.LewisEnum;
import com.bee2code.bioimporter.model.json.Questionnaire;
import com.bee2code.bioimporter.model.json.RhEnum;
import com.bee2code.bioimporter.model.json.Sample.MaterialTypeEnum;
import com.bee2code.bioimporter.model.json.Sample.StorageTemperatureEnum;
import com.bee2code.bioimporter.model.json.SampleInfo.AccesionEnum;
import com.bee2code.bioimporter.model.json.SampleMaterialNucleicAcid.KindEnum;
import com.bee2code.bioimporter.model.json.SampleMaterialWholeBlood.MethodEnum;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.deser.std.NumberDeserializers;
import com.fasterxml.jackson.databind.deser.std.StringDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.google.common.collect.Streams;

public class Utils {
	public static final List<Class<?>> enumClasses = Arrays.asList(MaterialTypeEnum.class, StorageTemperatureEnum.class,
			MethodEnum.class, KindEnum.class, AccesionEnum.class, ABOEnum.class, KellEnum.class, LewisEnum.class,
			RhEnum.class, Questionnaire.class, GenderEnum.class, DataAvailability.class, EgNameEnum.class,
			FisherSallerEnum.class, StorageTemperatureEnum.class, KindEnum.class);

	public static JacksonNonBlockingObjectMapperFactory prepareDonorObjectMapperFactory() {
		JacksonNonBlockingObjectMapperFactory factory = new JacksonNonBlockingObjectMapperFactory();
		List<JsonDeserializer> stdList = Arrays.asList(new NumberDeserializers.IntegerDeserializer(Integer.class, null),
				new NumberDeserializers.ShortDeserializer(Short.class, null),
				new NumberDeserializers.DoubleDeserializer(Double.class, null),
				new NumberDeserializers.LongDeserializer(Long.class, null),
				new NumberDeserializers.BigDecimalDeserializer(), new StringDeserializer()

		);

		//handle enums
		List<JsonDeserializer> enumDeserializers = new ArrayList<>();
		for (Class<?> enumClass : enumClasses) {
			enumDeserializers.add(new CreateEnumDeserializer<>(enumClass));
		}

		factory.setJsonDeserializers(
				Streams.concat(stdList.stream(), enumDeserializers.stream()).collect(Collectors.toList()));

		return factory;

	}

	public static List<String> parseIntegerListToString(List<Integer> intList) {
		if (intList != null) {
			return intList.stream().map(i -> i.toString()).collect(Collectors.toList());
		}

		return null;
	}

	public static ObjectMapper getMapperWithDateFormat() {
		ObjectMapper mapper = new ObjectMapper();
		SimpleModule offsetDateTimeModule = new SimpleModule();
		offsetDateTimeModule.addSerializer(OffsetDateTime.class, new JsonSerializer<OffsetDateTime>() {
			@Override
			public void serialize(OffsetDateTime offsetDateTime, JsonGenerator jsonGenerator,
					SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
				offsetDateTime = offsetDateTime.truncatedTo(ChronoUnit.SECONDS);
				jsonGenerator.writeString(DateTimeFormatter.ISO_DATE_TIME.format(offsetDateTime));
			}
		});
		mapper.registerModule(offsetDateTimeModule);

		return mapper;
	}

}
