package br.com.ruth.dao;

import br.com.ruth.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskDao {
    List<Task> getAll();
    Optional<Task> getById(Long id);
    Task save(Task task);
    void delete(Long id);
}
