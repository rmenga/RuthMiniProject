package br.com.ruth.service;

import br.com.ruth.dao.TaskDao;
import br.com.ruth.exceptions.TaskNotFoundException;
import br.com.ruth.model.Task;

import java.util.List;

public class TaskRegistrationService {
    private final TaskDao taskDao;

    public TaskRegistrationService(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    public List<Task> getAllTasks() {
        return taskDao.getAll();
    }

    public Task getTaskById(Long id) {
        return taskDao.getById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
    }

    public Task createTask(Task task) {
        return taskDao.save(task);
    }

    public Task updateTask(Long id, Task updatedTask) {
        Task existingTask = getTaskById(id);

        existingTask.setName(updatedTask.getName());
        existingTask.setDescription(updatedTask.getDescription());

        return taskDao.save(existingTask);
    }

    public void deleteTask(Long id) {
        getTaskById(id);
        taskDao.delete(id);
    }
}