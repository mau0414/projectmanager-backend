package com.ufscar.projectmanager_backend.service;

import com.ufscar.projectmanager_backend.models.Project;
import com.ufscar.projectmanager_backend.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public List<Project> create(Project project) {
        this.projectRepository.save(project);

        return listByUserId(project.getUser().getId());
    }

    public List<Project> list() {

        return this.projectRepository.findAll();
    }

    public List<Project> listByUserId(Long userId) {

        List<Project> projects = new ArrayList<>();

        this.projectRepository.findByUserId(userId).forEach(project -> {
            Project newProject = new Project();
            newProject.setId(project.getId());
            newProject.setStartDate(project.getStartDate());
            newProject.setEndDate(project.getEndDate());
            newProject.setTitle(project.getTitle());
            newProject.setDescription(project.getDescription());

            projects.add(newProject);
        });

        return projects;
    }
    public List<Project> update() {

        return list();
    }

    public List<Project> delete(Long id) {
        this.projectRepository.deleteById(id);

        return list();
    }
}
