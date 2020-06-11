package org.arunm.ToDoAndBracketvalidatorAPI.exception.message;

import org.arunm.ToDoAndBracketvalidatorAPI.exception.detail.ErrorDetail;

import java.util.ArrayList;
import java.util.List;

public class ToDoErrorMessage {

    private String name;

    private List<ErrorDetail> details = new ArrayList<>();

    public ToDoErrorMessage(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<ErrorDetail> getDetails() {
        return details;
    }

    public void addErrorDetail(ErrorDetail detail) {
        this.details.add(detail);
    }
}
