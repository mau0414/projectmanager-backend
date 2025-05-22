package com.ufscar.projectmanager_backend.service;

import com.ufscar.projectmanager_backend.models.Project;
import com.ufscar.projectmanager_backend.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public List<Project> create(Project project) {
        this.projectRepository.save(project);

        return list();
    }

    public List<Project> list() {

        return this.projectRepository.findAll();
    }

    public List<Project> update() {

        return list();
    }

    public List<Project> delete(Long id) {
        this.projectRepository.deleteById(id);

        return list();
    }
}
