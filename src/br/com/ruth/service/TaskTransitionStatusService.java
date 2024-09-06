package br.com.ruth.service;

import br.com.ruth.dao.TaskDao;
import br.com.ruth.model.Task;

public class TaskTransitionStatusService {

    private final TaskRegistrationService taskRegistrationService;
    private final TaskDao taskDao;

    public TaskTransitionStatusService(TaskRegistrationService taskRegistrationService, TaskDao taskDao) {
        this.taskRegistrationService = taskRegistrationService;
        this.taskDao = taskDao;
    }

    public void complete(Long id) {
        Task task = taskRegistrationService.getTaskById(id);
        task.complete();

        taskDao.save(task);
    }

    public void cancel(Long id) {
        Task task = taskRegistrationService.getTaskById(id);
        task.cancel();

        taskDao.save(task);
    }
}
