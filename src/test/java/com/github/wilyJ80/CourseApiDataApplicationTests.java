package com.github.wilyJ80;

import static org.hamcrest.Matchers.hasSize;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class CourseApiDataApplicationTests {

	@Autowired
	MockMvc mockMvc;

	@Test
	void contextLoads() {
	}

	@Test
	void shouldTestTopicUsage() throws Exception {

		this.mockMvc.perform(get("/topics")).andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").isArray())
				.andExpect(jsonPath("$", hasSize(0)));

		String javascriptJSON = "{ \"id\": \"javascript\", \"name\": \"JavaScript\", \"description\": \"Best Language Ever\"}";

		this.mockMvc.perform(post("/topics").contentType(MediaType.APPLICATION_JSON).content(javascriptJSON))
				.andExpect(status().isCreated()).andExpect(jsonPath("$.id").value("javascript"))
				.andExpect(jsonPath("$.name").value("JavaScript"))
				.andExpect(jsonPath("$.description").value("Best Language Ever"));

		String javaJSON = "{ \"id\": \"java\", \"name\": \"Java\", \"description\": \"Second Best Language Ever\"}";

		this.mockMvc.perform(post("/topics").contentType(MediaType.APPLICATION_JSON).content(javaJSON))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.id").value("java"))
				.andExpect(jsonPath("$.name").value("Java"))
				.andExpect(jsonPath("$.description").value("Second Best Language Ever"));

		this.mockMvc.perform(get("/topics")).andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").isArray())
				.andExpect(jsonPath("$", hasSize(2)));

		this.mockMvc.perform(delete("/topics/java")).andDo(print())
				.andExpect(status().isOk());

		this.mockMvc.perform(get("/topics")).andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").isArray())
				.andExpect(jsonPath("$", hasSize(1)));

		String typescriptJSON = "{ \"id\": \"typescript\", \"name\": \"TypeScript\", \"description\": \"Fake typing for the nerds\"}";

		this.mockMvc.perform(put("/topics/javascript").contentType(MediaType.APPLICATION_JSON).content(typescriptJSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$.id").value("typescript"))
				.andExpect(jsonPath("$.name").value("TypeScript"))
				.andExpect(jsonPath("$.description").value("Fake typing for the nerds"));
	}
}
