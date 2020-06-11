package org.arunm.ToDoAndBracketvalidatorAPI.controller;

import org.arunm.ToDoAndBracketvalidatorAPI.ToDoAndBracketvalidatorApiApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = ToDoAndBracketvalidatorApiApplication.class)
@AutoConfigureMockMvc
public class TasksControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldOnlyAllowBrackets() throws Exception {
        this.mockMvc.perform(get("/tasks/validateBrackets")
                .param("input", "aaaa"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldBeBalanced() throws Exception {
        this.mockMvc.perform(get("/tasks/validateBrackets")
                .param("input", "{}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isBalanced", equalTo(true)));
    }
}
