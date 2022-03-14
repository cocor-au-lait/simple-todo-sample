package cocor.au.lait.todolist.task;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.subsectionWithPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
class TaskApiTests {

	@Autowired
	private MockMvc mvc;

	@Test
	void listTasks() throws Exception {
		this.mvc.perform(get("/tasks"))
				.andExpect(status().isOk())
				.andDo(document("tasks-list",
						responseFields(
								fieldWithPath("_embedded.tasks[].id").description("Task ID"),
								fieldWithPath("_embedded.tasks[].summary").description("Summary of task"),
								subsectionWithPath("_embedded.tasks[]._links").ignored(),
								subsectionWithPath("_links").ignored(),
								subsectionWithPath("page").description("Page information"))));
	}

	@Test
	void createTasks() throws Exception {
		var content = "{\"summary\": \"My task\"}";
		this.mvc.perform(post("/tasks").content(content))
				.andExpect(status().isCreated())
				.andDo(document("tasks-create",
						requestFields(
								fieldWithPath("summary").description("Summary of task"))));
	}

	@Test
	void updateTasks() throws Exception {
		var content = "{\"summary\": \"Task B (Updated)\"}";
		this.mvc.perform(patch("/tasks/{id}", "00a40c84-ac14-469a-968b-3c1bce70150e").content(content))
				.andExpect(status().isOk())
				.andDo(document("tasks-update",
						requestFields(
								fieldWithPath("summary").description("Summary of task"))));
	}

	@Test
	void deleteTasks() throws Exception {
		this.mvc.perform(delete("/tasks/{id}", "38b3f62d-0206-4c9d-a3a7-fd6bdf015699"))
				.andExpect(status().isNoContent())
				.andDo(document("tasks-delete"));
	}

}
