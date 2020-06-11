package org.arunm.ToDoAndBracketvalidatorAPI.service;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TasksServiceTest {

    private TasksService tasksService = new TasksService();

    @Test
    public void testBalancedString() {
        assertTrue(tasksService.isBalanced("{{}}"));
    }

    @Test
    public void testUnBalancedString() {
        assertFalse(tasksService.isBalanced("{{}"));
    }
}
