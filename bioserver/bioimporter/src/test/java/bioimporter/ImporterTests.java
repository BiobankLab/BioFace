package bioimporter;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.bee2code.bioimporter.configuration.Configuration;
import com.bee2code.bioimporter.configuration.JsonFile;
import com.bee2code.bioimporter.configuration.Mongo;
import com.bee2code.bioimporter.configuration.Solr;
import com.bee2code.bioimporter.exception.ImporterServiceException;
import com.bee2code.bioimporter.model.json.ABOEnum;
import com.bee2code.bioimporter.model.json.DataAvailability;
import com.bee2code.bioimporter.model.json.Donor;
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
import com.bee2code.bioimporter.service.ImporterService;
import com.bee2code.tools.JacksonNonBlockingObjectMapperFactory;
import com.bee2code.tools.Utils;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ImporterTests {

	private ImporterService importerService = new ImporterService();

	private static final List<Class<?>> enumClasses = Arrays.asList(MaterialTypeEnum.class,
			StorageTemperatureEnum.class, MethodEnum.class, KindEnum.class, AccesionEnum.class, ABOEnum.class,
			KellEnum.class, LewisEnum.class, RhEnum.class, Questionnaire.class, GenderEnum.class,
			DataAvailability.class, EgNameEnum.class, FisherSallerEnum.class, StorageTemperatureEnum.class,
			KindEnum.class);

	//@Test
	public void testImport() throws ImporterServiceException {
		Configuration configuration = new Configuration();
		configuration.setJsonFile(new JsonFile(getClass().getClassLoader().getResource("donorsList.json").getPath()));
		configuration.getJsonFile().setBiobank("bb1");
		configuration.getJsonFile().setUser("admin");

		Solr solr = new Solr();
		solr.setUrl("http://localhost:8983/solr/bioface");

		configuration.setSolr(solr);

		Mongo mongoConf = new Mongo();
		mongoConf.setUrl("http://localhost:27017");
		configuration.setMongo(mongoConf);

		importerService.runWithCustomConfiguration(configuration);
	}

	@Test
	public void mappingDonorTest() {

		JacksonNonBlockingObjectMapperFactory factory = Utils.prepareDonorObjectMapperFactory();
		ObjectMapper objectMapper = factory.createObjectMapper();

		File jsonDonorListFile = new File(getClass().getClassLoader().getResource("donorsList.json").getFile());
		List<Donor> donorsList;
		try {
			donorsList = objectMapper.readValue(jsonDonorListFile, new TypeReference<List<Donor>>() {
			});
			System.out.println(factory.getErrorMessage());
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
