package com.ufscar.projectmanager_backend.service;

import com.ufscar.projectmanager_backend.models.Project;
import com.ufscar.projectmanager_backend.models.Task;
import com.ufscar.projectmanager_backend.repositories.ProjectRepository;
import com.ufscar.projectmanager_backend.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> create(Task task) {
        this.taskRepository.save(task);

        return listByProjectId(task.getProject().getId());
    }

    public List<Task> list() {

        return this.taskRepository.findAll();
    }

    public List<Task> listByProjectId(Long projectId) {

        List<Task> tasks = new ArrayList<>();

        this.taskRepository.findByProjectId(projectId).forEach(task -> {
            Task newTask = new Task();
            newTask.setId(task.getId());
            newTask.setStartDate(task.getStartDate());
            newTask.setEndDate(task.getEndDate());
            newTask.setTitle(task.getTitle());
            newTask.setDescription(task.getDescription());
            newTask.setStatus(task.getStatus());

            tasks.add(newTask);
        });

        return tasks;
    }

    public List<Task> update(Task task) {

        this.taskRepository.save(task);

        return listByProjectId(task.getProject().getId());
    }

    public List<Task> delete(Long id, Long projectId) {
        this.taskRepository.deleteById(id);

        return listByProjectId(projectId);
    }

    public Task byId(Long id) {

        return this.taskRepository.findById(id).get();
    }
}
