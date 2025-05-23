package com.ufscar.projectmanager_backend.models;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "user")
    private List<Project> projects;

    private String token;

    public User() { }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Project> getProjects() { return projects; }

    public void setProjects(List<Project> projects) { this.projects = projects; }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}