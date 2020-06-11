package org.arunm.ToDoAndBracketvalidatorAPI.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BalanceTestResult {
    private String input;
    @JsonProperty("isBalanced")
    private boolean balanced;

    public BalanceTestResult(String input, boolean balanced) {
        this.input = input;
        this.balanced = balanced;
    }

    public BalanceTestResult() {

    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public boolean isBalanced() {
        return balanced;
    }

    public void setBalanced(boolean balanced) {
        this.balanced = balanced;
    }
}
