package tests;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.request.AbstractUpdateRequest;
import org.apache.solr.client.solrj.request.ContentStreamUpdateRequest;
import org.apache.solr.client.solrj.request.CoreAdminRequest;
import org.apache.solr.client.solrj.request.schema.SchemaRequest;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.params.CommonParams;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import junit.framework.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, classes = {
		IntegrationTestConfiguration.class })
@TestPropertySource(locations = "classpath:test.properties")
public class NestedDocumentsIntegrationTest {

	public static HttpSolrClient httpSolrClient = new HttpSolrClient.Builder("http://localhost:8983/solr").build();

	private static Properties properties = new Properties();

	private static InputStream propertiesInputStream;

	private static String baseSolrUrl;

	@BeforeClass
	public static void readJSONAndInsertSolr() throws Exception {

		try {

			propertiesInputStream = AuthorizationTests.class.getClassLoader().getResourceAsStream("test.properties");
			properties.load(propertiesInputStream);

			baseSolrUrl = properties.getProperty("test.solr.url", "http://localhost:8983/solr");

			File test_json_file = new File(
					AuthorizationTests.class.getClassLoader().getResource("donor.json").getPath());

			CoreAdminRequest.createCore("test_core", properties.getProperty("solr.path"), httpSolrClient);

			httpSolrClient.setBaseURL(baseSolrUrl + "/test_core");

			Map<String, Object> fieldAttributes = new LinkedHashMap<>();
			fieldAttributes.put("name", "*id");
			fieldAttributes.put("type", "plongs");
			fieldAttributes.put("multiValued", false);

			SchemaRequest.AddDynamicField addFieldUpdateSchemaRequest = new SchemaRequest.AddDynamicField(
					fieldAttributes);

			addFieldUpdateSchemaRequest.process(httpSolrClient);

			ContentStreamUpdateRequest updateRequest = new ContentStreamUpdateRequest("/update/json/docs");
			updateRequest.addFile(test_json_file, "application/json");

			updateRequest.setParam("split", "/diseases");

			updateRequest.setAction(AbstractUpdateRequest.ACTION.COMMIT, true, true);

			httpSolrClient.request(updateRequest);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void executing_query_test() throws Exception {

		try {
			SolrQuery query = new SolrQuery();
			query.add(CommonParams.Q, "*:*");
			query.set(CommonParams.FL, "id, gender, age");
			query.set("group", "true");
			query.set("group.limit", 10);
			query.set("group.field", "id");
			query.set("omitHeader", true);

			final QueryResponse response = httpSolrClient.query(query);

			Map<String, Collection<Object>> valuesMap = response.getGroupResponse().getValues().get(0).getValues()
					.get(0).getResult().get(0).getFieldValuesMap();

			Assert.assertEquals("[Transgender]", valuesMap.get("gender").toString());
			Assert.assertEquals("[-13315]", valuesMap.get("id").toString());
			Assert.assertEquals("[62712545]", valuesMap.get("age").toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@AfterClass
	public static void delete() throws Exception {

		try {
			SchemaRequest.DeleteDynamicField removeFieldUpdateSchemaRequest = new SchemaRequest.DeleteDynamicField(
					"*id");

			removeFieldUpdateSchemaRequest.process(httpSolrClient);

			httpSolrClient.setBaseURL(baseSolrUrl);
			CoreAdminRequest.unloadCore("test_core", httpSolrClient);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
