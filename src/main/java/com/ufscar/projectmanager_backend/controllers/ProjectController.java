package com.ufscar.projectmanager_backend.controllers;

import com.ufscar.projectmanager_backend.models.Project;
import com.ufscar.projectmanager_backend.models.User;
import com.ufscar.projectmanager_backend.service.ProjectService;
import com.ufscar.projectmanager_backend.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.hibernate.mapping.Any;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/projects")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    @GetMapping("")
    public ResponseEntity<Map<String, List<Project>>> list(HttpServletRequest request) {

        Long userId = (Long) request.getAttribute("userId");
        List<Project> currentProjects = projectService.listByUserId(userId);

        return ResponseEntity.ok(Map.of("projects", currentProjects));
    }

    @PostMapping("")
    public ResponseEntity<Map<String, List<Project>>> create(@RequestBody Map<String, String> body, HttpServletRequest request) {

        Long userId = (Long) request.getAttribute("userId");
        User user = userService.findById(userId);
        String startDateString = body.get("startDate");
        LocalDate startDate = null;
        String endDateString = body.get("endDate");
        LocalDate endDate = null;
        if (startDateString != null && !startDateString.isEmpty()) startDate = LocalDate.parse(startDateString);
        if (endDateString != null && !endDateString.isEmpty()) endDate = LocalDate.parse(endDateString);
        Project project = new Project(body.get("title"), startDate, endDate, body.get("description"), user);
        List<Project> currentProjects = this.projectService.create(project);

        return ResponseEntity.ok(Map.of("projects", currentProjects));
    }

    @PostMapping("/teste")
    public ResponseEntity<Map<String, String>> teste(@RequestBody Map<String, String> body) {

        return ResponseEntity.ok(Map.of("teste2", body.get("teste")));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, List<Project>>> delete(@PathVariable Long id, HttpServletRequest request) {

        Long userId = (Long) request.getAttribute("userId");
        List<Project> currentProjects = this.projectService.delete(id, userId);

        return ResponseEntity.ok(Map.of("projects", currentProjects));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, List<Project>>> edit(@PathVariable Long id, @RequestBody Map<String, String> body, HttpServletRequest request) {

        Long userId = (Long) request.getAttribute("userId");
        String startDateString = body.get("startDate");
        LocalDate startDate = null;
        String endDateString = body.get("endDate");
        LocalDate endDate = null;
        if (startDateString != null && !startDateString.isEmpty()) startDate = LocalDate.parse(startDateString);
        if (endDateString != null && !endDateString.isEmpty()) endDate = LocalDate.parse(endDateString);
        Project project = projectService.byId(id);
        project.setTitle(body.get("title"));
        project.setDescription(body.get("description"));
        project.setStartDate(startDate);
        project.setEndDate(endDate);
        List<Project> currentProjects = this.projectService.update(project, userId);

        return ResponseEntity.ok(Map.of("projects", currentProjects));
    }

}
