package com.ufscar.projectmanager_backend.controllers;

import com.ufscar.projectmanager_backend.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/projects")
public class TaskController {
    @Autowired
    private TaskService taskService;

}
