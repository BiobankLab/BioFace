package test;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.dispersion.invoker.ApplicationRunner;
import com.dispersion.service.IVerifyingService;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.JSONObject;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes={ApplicationRunner.class})
@AutoConfigureMockMvc
public class VerifyingServiceTests {

	@Autowired
	IVerifyingService iVerifyingService;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void stage01_testGetAllSamples() throws Exception{
		
		JSONObject queryObject = new JSONObject();
		queryObject.put("query", "*:*");
		
		String user = "B1";
		
		
		ResultActions result = mockMvc.perform(post("/dispersionSample/searchSample").contentType(MediaType.APPLICATION_JSON).content((user + queryObject).toString()))
				.andExpect(status().isOk());
		
		assertNotNull(result.andReturn().getResponse().getContentAsString());
		

	}

}