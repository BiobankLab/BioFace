package bioimporter;

import com.bee2code.bioimporter.configuration.Configuration;
import com.bee2code.bioimporter.configuration.JsonFile;
import com.bee2code.bioimporter.configuration.Mongo;
import com.bee2code.bioimporter.configuration.Solr;
import com.bee2code.bioimporter.service.ImporterService;

public class SolrRepositoryTest {

	private static ImporterService importerService;
	private static Configuration configuration;
	private static final String JSON_OBJECT = "{\"access\":\"Open\",\"sex\":\"F\",\"blodGroup\":\"AB\",\"diseases\":[],\"institution\":\"Biobank Lodz\",\"elsi\":\"Recontact needed\",\"eyeColor\":\"1\",\"psychologicalQuestionnaire\":\"NO\",\"materials\":[],\"dob\":\"ND\",\"biobankId\":\"12\",\"addictionQuestionnaire\":\"YES\",\"dobCorectness\":\"ND\",\"_id\":null,\"id\":\"BB7272667\",\"hairColor\":\"A\",\"sesQuestionnaire\":\"YES\",\"anthropologicalQuestionnaire\":\"YES\",\"projectName\":\"XX\",\"region\":\"Lodzkie\\/Poland\\/Europe\",\"collectionId\":\"34\"}";

	//@BeforeClass
	public static void prepare() {
		configuration = new Configuration();
		Solr solr = new Solr();
		solr.setUrl("http://localhost:8983/solr/bstest");
		configuration.setSolr(solr);
		Mongo mongo = new Mongo();
		mongo.setAddress("localhost");
		mongo.setPort(27017);
		configuration.setMongo(mongo);

		JsonFile jsonFile = new JsonFile();
		jsonFile.setPath("/home/mateusz/Desktop/PRACA/donor.json");
		configuration.setJsonFile(jsonFile);

		importerService = new ImporterService();
	}

	//@Test
	public void indexTest() throws Exception {
		importerService.runWithCustomConfiguration(configuration);
	}
}
