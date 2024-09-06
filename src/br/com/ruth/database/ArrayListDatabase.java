package br.com.ruth.database;

import br.com.ruth.model.Task;

import java.util.ArrayList;
import java.util.List;

public class ArrayListDatabase {
    private final List<Task> tasks = new ArrayList<>();

    public List<Task> getTasks() {
        return tasks;
    }
}
