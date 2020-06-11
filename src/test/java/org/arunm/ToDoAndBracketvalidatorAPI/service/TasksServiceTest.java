package org.arunm.ToDoAndBracketvalidatorAPI.service;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TasksServiceTest {

    private TasksService tasksService = new TasksService();

    @Test
    public void testBalancedString() {
        assertTrue(tasksService.isBalanced("{222{arunmenon}asss}"));
    }

    @Test
    public void testUnBalancedString_1() {
        assertFalse(tasksService.isBalanced("{{}"));
    }

    @Test
    public void testUnBalancedString_2() {
        assertFalse(tasksService.isBalanced("{[}]"));
    }

}
