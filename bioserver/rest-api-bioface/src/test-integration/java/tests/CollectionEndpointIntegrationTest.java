package tests;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import com.bioface.api.CollectionApiController;
@RunWith(SpringRunner.class)
@WebMvcTest(CollectionApiController.class)
@ContextConfiguration(classes = { IntegrationTestConfiguration.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CollectionEndpointIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void stage01_testGetAllCollections() throws Exception{
		mockMvc.perform(get("/collection").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(status().isOk()).andExpect(content().json("[]"));
		

	}
	
	/*@Test(expected = NestedServletException.class)
	public void addCollectionWithoutBiobank() throws Exception{
		Collection newCollection = new Collection();
		newCollection.setAcronym("COL_1");
		newCollection.setDescription("coll 1 description");
		newCollection.setName("Collection1");
		
		mockMvc.perform(post("/collection").contentType(MediaType.APPLICATION_JSON).content(new JSONObject(newCollection).toString()))
		.andExpect(status().isBadRequest());

	}*/

}
