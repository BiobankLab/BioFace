package utils;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.keycloak.test.TestsHelper;

public class RequestUtils {

	private static final String WRONG_TOKEN = "wrong_token";

	public static HttpResponse makeGetRequest(String uri, String username, String password, boolean addToken,
			boolean wrongToken) throws ClientProtocolException, IOException {

		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(uri);

		if (addToken) {
			addTokenToRequest(request, username, password, wrongToken);
		}

		return client.execute(request);
	}

	public static HttpResponse makePostRequest(String uri, String username, String password, String body,
			boolean addToken, boolean wrongToken) throws ClientProtocolException, IOException {
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost request = new HttpPost(uri);

		request.setHeader("Accept", "application/json");
		request.setHeader("Content-type", "application/json");

		if (addToken) {
			addTokenToRequest(request, username, password, wrongToken);
		}

		HttpEntity entity = new ByteArrayEntity(body.getBytes("UTF-8"));

		request.setEntity(entity);

		return client.execute(request);

	}

	private static void addTokenToRequest(HttpRequestBase request, String username, String password,
			boolean wrongToken) {
		String accessToken = "";
		if (wrongToken) {
			accessToken = WRONG_TOKEN;
		} else {
			accessToken = TestsHelper.getToken(username, password, TestsHelper.testRealm);
			assertNotNull(accessToken);
		}
		request.addHeader("Authorization", "Bearer " + accessToken);
	}

}
