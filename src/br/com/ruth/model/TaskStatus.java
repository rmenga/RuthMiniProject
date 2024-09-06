package br.com.ruth.model;

import java.util.List;

public enum TaskStatus {

    CREATED,
    COMPLETED(CREATED),
    CANCELED(CREATED);

    private final List<TaskStatus> allowedTransitions;

    TaskStatus(TaskStatus... allowedTransitions) {
        this.allowedTransitions = List.of(allowedTransitions);
    }

    public boolean canChangeTo(TaskStatus newStatus) {
        return newStatus.allowedTransitions.contains(this);
    }
}