package org.arunm.ToDoAndBracketvalidatorAPI.controller;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.arunm.ToDoAndBracketvalidatorAPI.controller.response.BalanceTestResult;
import org.arunm.ToDoAndBracketvalidatorAPI.exception.message.ToDoItemNotFoundErrorMessage;
import org.arunm.ToDoAndBracketvalidatorAPI.exception.message.ToDoValidationErrorMessage;
import org.arunm.ToDoAndBracketvalidatorAPI.service.TasksService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

@RestController
@RequestMapping("/tasks")
@Validated
public class TasksController {

    private TasksService tasksService;

    public TasksController(TasksService tasksService) {
        this.tasksService = tasksService;
    }

    @GetMapping("validateBrackets")
    @ApiResponses(value =
            {@ApiResponse(code = 404, message = "Notfound Error", response = ToDoItemNotFoundErrorMessage.class),
                    @ApiResponse(code = 400, message = "Validation Error", response = ToDoValidationErrorMessage.class)})
    public BalanceTestResult balanceTest(@RequestParam("input")
                                         @Valid
                                         @Pattern(regexp = "^[\\(\\{\\[\\)\\}\\]]+$",
                                                 message = "Should contain only following characters {,[,(,},],)") String input) {
        return new BalanceTestResult(input, tasksService.isBalanced(input));
    }
}
