package com.example.demo.service;

import com.example.demo.model.Task;
import com.example.demo.repository.TaskRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepo;

    public List<Task> getAllTasks() {
        OffsetDateTime now = OffsetDateTime.now(ZoneOffset.UTC);
        List<Task> tasks = taskRepo.findAll();
        for (Task task : tasks) {
            if (!"Completed".equalsIgnoreCase(task.getStatus())) {
                task.setStatus(task.getDeadline().isBefore(now) ? "Outdated" : "Pending");
            }
        }
        return taskRepo.saveAll(tasks); // persist status changes
    }

    public Task createTask(@Valid Task task) {
        OffsetDateTime now = OffsetDateTime.now(ZoneOffset.UTC);
        if (task.getDeadline().isBefore(now)) {
            throw new IllegalArgumentException("Deadline cannot be in the past.");
        }
        task.setStatus("Pending");
        return taskRepo.save(task);
    }

    public Task updateTask(Long id, @Valid Task updatedTask) {
        OffsetDateTime now = OffsetDateTime.now(ZoneOffset.UTC);

        return taskRepo.findById(id).map(task -> {
            if (updatedTask.getDeadline().isBefore(now)) {
                throw new IllegalArgumentException("Deadline cannot be in the past.");
            }
            task.setTitle(updatedTask.getTitle());
            task.setCategory(updatedTask.getCategory());
            task.setDeadline(updatedTask.getDeadline());
            if (!"Completed".equalsIgnoreCase(task.getStatus())) {
                task.setStatus(now.isAfter(task.getDeadline()) ? "Outdated" : "Pending");
            }
            return taskRepo.save(task);
        }).orElseThrow(() -> new RuntimeException("Task not found with id " + id));
    }

    public Task markAsCompleted(Long id) {
        return taskRepo.findById(id).map(task -> {
            task.setStatus("Completed");
            return taskRepo.save(task);
        }).orElseThrow(() -> new RuntimeException("Task not found with id " + id));
    }

    public void deleteTask(Long id) {
        taskRepo.deleteById(id);
    }
}
