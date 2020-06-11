package org.arunm.ToDoAndBracketvalidatorAPI.exception;

import org.arunm.ToDoAndBracketvalidatorAPI.exception.detail.ToDoNotFoundErrorDetail;
import org.arunm.ToDoAndBracketvalidatorAPI.exception.detail.ToDoValidationErrorDetail;
import org.arunm.ToDoAndBracketvalidatorAPI.exception.message.ToDoErrorMessage;
import org.arunm.ToDoAndBracketvalidatorAPI.exception.message.ToDoItemNotFoundErrorMessage;
import org.arunm.ToDoAndBracketvalidatorAPI.exception.message.ToDoValidationErrorMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.text.MessageFormat;

@ControllerAdvice
@RestController
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(TodoItemNotFoundException.class)
    public final ResponseEntity<ToDoErrorMessage> handleNotFoundException(TodoItemNotFoundException ex, WebRequest request) {
        ToDoItemNotFoundErrorMessage toDoError = new ToDoItemNotFoundErrorMessage();
        ToDoNotFoundErrorDetail notFoundErrorDetail = new ToDoNotFoundErrorDetail();
        notFoundErrorDetail.setMessage(MessageFormat.format("Item with {0} not found", ex.getId()));
        toDoError.addErrorDetail(notFoundErrorDetail);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(toDoError);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public final ResponseEntity<ToDoErrorMessage> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request) {
        ToDoValidationErrorMessage toDoError = new ToDoValidationErrorMessage();
        ex.getConstraintViolations().forEach(constraintViolation -> toDoError.addErrorDetail(createErrorDetail(constraintViolation)));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(toDoError);
    }

    private ToDoValidationErrorDetail createErrorDetail(ConstraintViolation<?> constraintViolation) {
        ToDoValidationErrorDetail toDoValidationErrorDetail = new ToDoValidationErrorDetail();
        toDoValidationErrorDetail.setLocation("params");
        toDoValidationErrorDetail.setMsg(constraintViolation.getMessage());
        toDoValidationErrorDetail.setValue(constraintViolation.getInvalidValue().toString());
        return toDoValidationErrorDetail;
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ToDoValidationErrorMessage toDoError = new ToDoValidationErrorMessage();

        ToDoValidationErrorDetail toDoValidationErrorDetail = new ToDoValidationErrorDetail();
        toDoValidationErrorDetail.setLocation("params");
        toDoValidationErrorDetail.setMsg(ex.getBindingResult().getFieldError().getDefaultMessage());
        toDoValidationErrorDetail.setParam(ex.getBindingResult().getFieldError().getField());
        toDoValidationErrorDetail.setValue(ex.getBindingResult().getFieldError().getRejectedValue().toString());
        toDoError.addErrorDetail(toDoValidationErrorDetail);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(toDoError);
    }
}
