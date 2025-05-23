package com.ufscar.projectmanager_backend.service;

import com.ufscar.projectmanager_backend.models.Task;
import com.ufscar.projectmanager_backend.repositories.ProjectRepository;
import com.ufscar.projectmanager_backend.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> create(Task project) {
        this.taskRepository.save(project);

        return list();
    }

    public List<Task> list() {

        return this.taskRepository.findAll();
    }

    public List<Task> update() {

        return list();
    }

    public List<Task> delete(Long id) {
        this.taskRepository.deleteById(id);

        return list();
    }
}
