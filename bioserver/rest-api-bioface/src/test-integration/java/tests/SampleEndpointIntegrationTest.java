package tests;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.JSONObject;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.bioface.api.SampleApiController;
@RunWith(SpringRunner.class)
@WebMvcTest(SampleApiController.class)
@ContextConfiguration(classes = { IntegrationTestConfiguration.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SampleEndpointIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void stage01_testGetAllSamples() throws Exception{
		
		JSONObject queryObject = new JSONObject();
		queryObject.put("query", "*:*");
		
		
		ResultActions result = mockMvc.perform(post("/sample/searchSample").contentType(MediaType.APPLICATION_JSON).content(queryObject.toString()))
				.andExpect(status().isOk());
		
		assertNotNull(result.andReturn().getResponse().getContentAsString());
		

	}
	
}
