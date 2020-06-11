package org.arunm.ToDoAndBracketvalidatorAPI.exception.detail;

@SuppressWarnings("unused")
public class ToDoNotFoundErrorDetail extends ErrorDetail {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
