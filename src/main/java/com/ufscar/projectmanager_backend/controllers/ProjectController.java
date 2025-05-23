package com.ufscar.projectmanager_backend.controllers;

import com.ufscar.projectmanager_backend.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/projects")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @PostMapping("/teste")
    public ResponseEntity<Map<String, String>> teste(@RequestBody Map<String, String> body) {

        System.out.println("chegou no controller ufa");

        return ResponseEntity.ok(Map.of("teste2", body.get("teste")));
    }

}
