package com.ufscar.projectmanager_backend.controllers;

import com.ufscar.projectmanager_backend.models.Project;
import com.ufscar.projectmanager_backend.models.Task;
import com.ufscar.projectmanager_backend.models.TaskStatus;
import com.ufscar.projectmanager_backend.models.User;
import com.ufscar.projectmanager_backend.service.ProjectService;
import com.ufscar.projectmanager_backend.service.TaskService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @Autowired
    private ProjectService projectService;

    @GetMapping("")
    public ResponseEntity<Map<String, List<Task>>> list(@RequestParam Long projectId) {

        System.out.println("chegou no get de tasks");

        List<Task> currentTasks = taskService.listByProjectId(projectId);

        System.out.println("retorno de tasks" + currentTasks);
        return ResponseEntity.ok(Map.of("tasks", currentTasks));
    }

    @PostMapping("")
    public ResponseEntity<Map<String, List<Task>>> create(@RequestBody Map<String, String> body) {

        Long projectId = Long.valueOf((String) body.get("projectId"));
        Project project = projectService.findById(projectId);

        String startDateString = body.get("startDate");
        LocalDate startDate = null;
        String endDateString = body.get("endDate");
        LocalDate endDate = null;
        if (startDateString != null && !startDateString.isEmpty()) startDate = LocalDate.parse(startDateString);
        if (endDateString != null && !endDateString.isEmpty()) endDate = LocalDate.parse(endDateString);
        Task task = new Task(body.get("title"), startDate, endDate, body.get("description"), project);
        List<Task> currentTasks = this.taskService.create(task);

        return ResponseEntity.ok(Map.of("tasks", currentTasks));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, List<Task>>> moveTask(@PathVariable Long id, @RequestBody Map<String, String> body) {

        System.out.println("chegou no put certo de mover task e o task id = " + id);

        Task task = taskService.byId(id);
        String taskNewStatusStr = body.get("newStatus");

        if (taskNewStatusStr.equals("INPROGRESS")) {
            task.setStatus(TaskStatus.INPROGRESS);
        } else if (taskNewStatusStr.equals("DONE")) {
            task.setStatus(TaskStatus.DONE);
        }

        List<Task> currentTasks = this.taskService.update(task);

        return ResponseEntity.ok(Map.of("tasks", currentTasks));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, List<Task>>> delete(@PathVariable Long id, HttpServletRequest request) {

        Long projectId = taskService.byId(id).getProject().getId();
        List<Task> currentTasks = this.taskService.delete(id, projectId);

        return ResponseEntity.ok(Map.of("tasks", currentTasks));
    }


}
