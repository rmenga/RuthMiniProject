package br.com.ruth.model;

import br.com.ruth.exceptions.BusinessException;

import java.time.LocalDateTime;

public class Task {

    private static long idCounter = 0;

    private Long id;
    private String name;
    private String description;
    private TaskStatus status;
    private LocalDateTime creationDate;
    private LocalDateTime completionDate;
    private LocalDateTime cancellationDate;

    public Task(String name, String description) {
        this.id = generateId();
        this.name = name;
        this.description = description;
        this.creationDate = LocalDateTime.now();
        this.status = TaskStatus.CREATED;
    }

    public Long getId() {
        return id;
    }

    private static long generateId() {
        return ++idCounter;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    private void setTaskStatus(TaskStatus status) {
        if (!this.getStatus().canChangeTo(status)) {
            throw new BusinessException("Status from task of id %d can't be changed from %s to %s".formatted(
                    getId(), getStatus(), status
            ));
        }

        this.status = status;
    }

    public void complete() {
        setTaskStatus(TaskStatus.COMPLETED);
        setCompletionDate(LocalDateTime.now());
    }

    public void cancel() {
        setTaskStatus(TaskStatus.CANCELED);
        setCancellationDate(LocalDateTime.now());
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    private void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getCompletionDate() {
        return completionDate;
    }

    private void setCompletionDate(LocalDateTime completionDate) {
        this.completionDate = completionDate;
    }

    public LocalDateTime getCancellationDate() {
        return cancellationDate;
    }

    private void setCancellationDate(LocalDateTime cancellationDate) {
        this.cancellationDate = cancellationDate;
    }
}
