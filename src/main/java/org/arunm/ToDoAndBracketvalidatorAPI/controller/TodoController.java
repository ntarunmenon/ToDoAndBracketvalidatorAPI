package org.arunm.ToDoAndBracketvalidatorAPI.controller;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.arunm.ToDoAndBracketvalidatorAPI.controller.request.ToDoItemAddRequest;
import org.arunm.ToDoAndBracketvalidatorAPI.exception.message.ToDoItemNotFoundErrorMessage;
import org.arunm.ToDoAndBracketvalidatorAPI.exception.message.ToDoValidationErrorMessage;
import org.arunm.ToDoAndBracketvalidatorAPI.model.Todo;
import org.arunm.ToDoAndBracketvalidatorAPI.service.ToDoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/todo")
@SuppressWarnings("unused")
public class TodoController {

    private ToDoService toDoService;

    public TodoController(ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    @GetMapping("/{id}")
    @ApiResponses(value =
            {@ApiResponse(code = 404, message = "Notfound Error", response = ToDoItemNotFoundErrorMessage.class),
                    @ApiResponse(code = 400, message = "Validation Error", response = ToDoValidationErrorMessage.class)})
    public ResponseEntity<Todo> getToDoById(@PathVariable Long id) {
        return ResponseEntity.ok(toDoService.findTodoById(id));
    }

    @PostMapping
    @ApiResponses(value =
            {@ApiResponse(code = 404, message = "Notfound Error", response = ToDoItemNotFoundErrorMessage.class),
                    @ApiResponse(code = 400, message = "Validation Error", response = ToDoValidationErrorMessage.class)})
    public ResponseEntity<Todo> createToDo(@Valid @RequestBody ToDoItemAddRequest toDoItemAddRequest) {
        return ResponseEntity.ok(toDoService.saveTodo(new Todo(toDoItemAddRequest)));
    }

    @PatchMapping("/{id}")
    @ApiResponses(value =
            {@ApiResponse(code = 404, message = "Notfound Error", response = ToDoItemNotFoundErrorMessage.class),
                    @ApiResponse(code = 400, message = "Validation Error", response = ToDoValidationErrorMessage.class)})
    public ResponseEntity<Todo> updateTodo(@PathVariable Long id, @Valid @RequestBody ToDoItemAddRequest toDoItemAddRequest) {
        Todo todo = toDoService.findTodoById(id);
        todo.setText(toDoItemAddRequest.getText());
        todo.setCompleted(toDoItemAddRequest.isCompleted());
        todo.setCreatedAt(LocalDateTime.now());
        return ResponseEntity.ok(toDoService.saveTodo(todo));

    }
}
