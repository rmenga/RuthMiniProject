package br.com.ruth.dao;

import br.com.ruth.database.ArrayListDatabase;
import br.com.ruth.model.Task;

import java.util.List;
import java.util.Optional;

public class TaskArrayListDao implements TaskDao {

    private final ArrayListDatabase database;

    public TaskArrayListDao() {
        this.database = new ArrayListDatabase();
    }

    @Override
    public List<Task> getAll() {
        return database.getTasks();
    }

    @Override
    public Optional<Task> getById(Long id) {
        return database.getTasks().stream()
                .filter(t -> t.getId().equals(id))
                .findFirst();
    }

    @Override
    public Task save(Task task) {
        Optional<Task> taskOptional = getById(task.getId());

        if (taskOptional.isPresent()) {
            Task taskFromDatabase = taskOptional.get();
            taskFromDatabase.setName(task.getName());
            taskFromDatabase.setDescription(task.getDescription());

            return taskFromDatabase;
        } else {
            database.getTasks().add(task);

            return task;
        }
    }

    @Override
    public void delete(Long id) {
        database.getTasks().removeIf(task -> task.getId().equals(id));
    }
}
