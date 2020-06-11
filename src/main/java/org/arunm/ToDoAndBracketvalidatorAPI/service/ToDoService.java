package org.arunm.ToDoAndBracketvalidatorAPI.service;

import org.arunm.ToDoAndBracketvalidatorAPI.exception.TodoItemNotFoundException;
import org.arunm.ToDoAndBracketvalidatorAPI.model.Todo;
import org.arunm.ToDoAndBracketvalidatorAPI.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@SuppressWarnings("unused")
public class ToDoService {

    private TodoRepository todoRepository;

    public ToDoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public Todo saveTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    public Todo findTodoById(Long id) {
        Optional<Todo> todoOptional = todoRepository.findById(id);
        if (todoOptional.isPresent()) {
            return todoOptional.get();
        }
        throw new TodoItemNotFoundException(id);
    }
}

