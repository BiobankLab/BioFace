package tests;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.json.JSONObject;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.keycloak.test.TestsHelper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.bioface.model.ext.BasicPaginationQueryRequest;

import utils.RequestUtils;

/**
 * Names of test methods determine order
 * 
 * @author mbucko
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, classes = {
		IntegrationTestConfiguration.class })
@TestPropertySource(locations = "classpath:test.properties")
public class AuthorizationTests {

	private static String BASE_URI;

	private static String KEYCLOAK_URL;

	private static Properties properties = new Properties();

	private static InputStream propertiesInputStream;

	@BeforeClass
	public static void beforeSetup() throws IOException {
		/*
		 * Load properties
		 */
		propertiesInputStream = AuthorizationTests.class.getClassLoader().getResourceAsStream("test.properties");
		properties.load(propertiesInputStream);

		/*
		 * Basic addresses
		 */
		BASE_URI = "http://localhost:" + properties.getProperty("server.port", "8080") + "/";
		KEYCLOAK_URL = properties.getProperty("keycloak.auth-server-url");

		/*
		 * Prepare keycloak realm
		 */
		TestsHelper.keycloakBaseUrl = KEYCLOAK_URL;
		TestsHelper.testRealm = properties.getProperty("keycloak.realm");
		TestsHelper.importTestRealm(properties.getProperty("admin.keycloak.username"),
				properties.getProperty("admin.keycloak.password"), "/test-bioface.json");
		TestsHelper.createDirectGrantClient();

	}

	@AfterClass
	public static void cleanUp() throws IOException {
		TestsHelper.deleteRealm(properties.getProperty("admin.keycloak.username"),
				properties.getProperty("admin.keycloak.password"), properties.getProperty("keycloak.realm"));
	}

	@Test
	public void projectApiAccessTest() throws ClientProtocolException, IOException {
		String uri = BASE_URI + "project/";
		assertAccessSuccess(RequestUtils.makeGetRequest(uri, "test", "test", true, false));
		assertBadRequest(RequestUtils.makeGetRequest(uri, "test", "test", false, false));
		assertAccessFail(RequestUtils.makeGetRequest(uri, "test", "test", true, true));
	}

	@Test
	public void sampleApiAccessTest() throws ClientProtocolException, IOException {
		String uri = BASE_URI + "sample/searchSample";
		BasicPaginationQueryRequest query = new BasicPaginationQueryRequest();
		query.setMaxRows(10);
		query.setPage(0);
		query.setQuery("*:*");

		JSONObject jsonObject = new JSONObject(query);
		assertAccessSuccess(RequestUtils.makePostRequest(uri, "test", "test", jsonObject.toString(), true, false));
		assertAccessFail(RequestUtils.makePostRequest(uri, "test", "test", jsonObject.toString(), true, true));

	}

	private void assertAccessSuccess(HttpResponse response) {
		assertEquals(200, response.getStatusLine().getStatusCode());
	}

	private void assertAccessFail(HttpResponse response) {
		assertEquals(401, response.getStatusLine().getStatusCode());

	}

	private void assertBadRequest(HttpResponse response) {
		assertEquals(400, response.getStatusLine().getStatusCode());

	}

}
