package tests;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
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

import com.bioface.api.BiobankApiController;
import com.bioface.model.Biobank;
import com.bioface.repository.BiobankRepository;

/**
 * Names of test methods determine order
 * 
 * @author mbucko
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest(BiobankApiController.class)
@ContextConfiguration(classes = { IntegrationTestConfiguration.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BiobankEndpointIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private BiobankRepository iBiobankRepository;

	private static boolean dataLoaded = false;

	private static String biobankDbId = null;

	@Before
	public void setup() {
		// clean test database before tests
		if (!dataLoaded) {
			iBiobankRepository.deleteAll();
			dataLoaded = true;
		}
	}

	@Test
	public void stage_01_testGetAllBiobanks() throws Exception {
		this.mockMvc.perform(get("/biobank").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk()).andExpect(content().json("[]"));
	}

	@Test
	public void stage_02_testAddBiobank() throws Exception {
		Biobank biobank = new Biobank();
		biobank.setId("testBiobankId");
		biobank.setName("testBiobankName");
		biobank.setAcronym("testBiobiankAcronym");
		biobank.setJuristicPerson("John");

		this.mockMvc.perform(
				post("/biobank").contentType(MediaType.APPLICATION_JSON).content(new JSONObject(biobank).toString()))
				.andExpect(status().isOk());

	}

	@Test
	public void stage_03_testGetBiobanks() throws Exception {
		ResultActions result = this.mockMvc.perform(get("/biobank").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

		// get json array from response (there should be only 1 object)
		String resultString = result.andReturn().getResponse().getContentAsString();
		JSONArray jsonArray = new JSONArray(resultString);

		assertEquals(jsonArray.length(), 1);

		JSONObject jsonObject = jsonArray.getJSONObject(0);

		// check defined parameters
		assertEquals(jsonObject.get("id"), "testBiobankId");
		assertEquals(jsonObject.get("name"), "testBiobankName");
		assertEquals(jsonObject.get("acronym"), "testBiobiankAcronym");
		assertEquals(jsonObject.get("juristicPerson"), "John");
		// check if database generated id
		assertNotNull(jsonObject.get("dbId"));

		biobankDbId = jsonObject.getString("dbId");

	}

	@Test
	public void stage_04_addNullBiobankTest() throws Exception {
		this.mockMvc.perform(post("/biobank").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void stage_05_testUpdateBiobank() throws Exception {

		Biobank biobankToUpdate = new Biobank();
		biobankToUpdate.setBiobankId(biobankDbId);
		biobankToUpdate.setId("Biobank change Id");
		biobankToUpdate.setName("Biobank Change Name");
		biobankToUpdate.setAcronym("Biobank changeAcronym");
		biobankToUpdate.setJuristicPerson("Biobank jp change");

		this.mockMvc.perform(put("/biobank").contentType(MediaType.APPLICATION_JSON)
				.content(new JSONObject(biobankToUpdate).toString())).andExpect(status().isOk());

	}

	@Test
	public void stage_06_testGetUpdatedBiobank() throws Exception {
		ResultActions result = this.mockMvc.perform(get("/biobank/" + biobankDbId)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

		String resultString = result.andReturn().getResponse().getContentAsString();

		JSONObject biobankUpdated = new JSONObject(resultString);

		// check defined parameters
		assertEquals(biobankUpdated.get("biobankId"), "Biobank change Id");
		assertEquals(biobankUpdated.get("name"), "Biobank Change Name");
		assertEquals(biobankUpdated.get("acronym"), "Biobank changeAcronym");
		assertEquals(biobankUpdated.get("juristicPerson"), "Biobank jp change");
		assertEquals(biobankUpdated.get("dbId"), biobankDbId);
	}

}
