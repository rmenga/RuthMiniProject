package br.com.ruth.exceptions;

public class TaskNotFoundException extends BusinessException {
    public TaskNotFoundException(String message) {
        super(message);
    }

    public TaskNotFoundException(Long taskId) {
        this("Task with id %d doesn't exist".formatted(taskId));
    }
}
