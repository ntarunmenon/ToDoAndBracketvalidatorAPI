package org.arunm.ToDoAndBracketvalidatorAPI.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.arunm.ToDoAndBracketvalidatorAPI.ToDoAndBracketvalidatorApiApplication;
import org.arunm.ToDoAndBracketvalidatorAPI.controller.request.ToDoItemAddRequest;
import org.arunm.ToDoAndBracketvalidatorAPI.exception.TodoItemNotFoundException;
import org.arunm.ToDoAndBracketvalidatorAPI.model.Todo;
import org.arunm.ToDoAndBracketvalidatorAPI.service.ToDoService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.Charset;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = ToDoAndBracketvalidatorApiApplication.class)
@AutoConfigureMockMvc
public class TodoControllerTest {

    @MockBean
    ToDoService toDoServiceMock;

    private static final MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    private ObjectMapper jsonMapper = new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);


    @Autowired
    private MockMvc mockMvc;

    @Test
    public void itemNotFound() throws Exception {
        when(toDoServiceMock.findTodoById(1L)).thenThrow(new TodoItemNotFoundException(1L));

        this.mockMvc.perform(get("/todo/1"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.details[0].message", equalTo("Item with 1 not found")));
    }

    @Test
    public void patchitemNotFound() throws Exception {
        ToDoItemAddRequest toDoItemAddRequest = new ToDoItemAddRequest();
        toDoItemAddRequest.setCompleted(true);
        toDoItemAddRequest.setText("1234");

        when(toDoServiceMock.findTodoById(1L)).thenThrow(new TodoItemNotFoundException(1L));

        this.mockMvc.perform(patch("/todo/1")
                .contentType(contentType)
                .content(jsonMapper.writeValueAsString(toDoItemAddRequest)))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.details[0].message", equalTo("Item with 1 not found")));
    }

    @Test
    public void testCreateItem() throws Exception {
        Todo returnToDo = new Todo();
        returnToDo.setId(100L);
        when(toDoServiceMock.saveTodo(ArgumentMatchers.any(Todo.class))).thenReturn(returnToDo);

        ToDoItemAddRequest toDoItemAddRequest = new ToDoItemAddRequest();
        toDoItemAddRequest.setCompleted(true);
        toDoItemAddRequest.setText("1234");

        this.mockMvc.perform(post("/todo")
                .contentType(contentType)
                .content(jsonMapper.writeValueAsString(toDoItemAddRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(100)));
    }
}
