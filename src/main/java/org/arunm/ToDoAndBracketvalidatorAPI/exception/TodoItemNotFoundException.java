package org.arunm.ToDoAndBracketvalidatorAPI.exception;

public class TodoItemNotFoundException extends RuntimeException {
    private Long id;

    public TodoItemNotFoundException(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
