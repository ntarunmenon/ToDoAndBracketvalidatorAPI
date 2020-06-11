package org.arunm.ToDoAndBracketvalidatorAPI.repository;

import org.arunm.ToDoAndBracketvalidatorAPI.model.Todo;
import org.springframework.data.repository.CrudRepository;

public interface TodoRepository extends CrudRepository<Todo, Long> {
}
