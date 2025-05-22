package com.ufscar.projectmanager_backend.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.time.LocalDate;

@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "project")
    private List<Task> tasks;

    private String description;

    private LocalDate    startDate;

    private LocalDate endDate;

    @NotNull()
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    public Project() { }

    public Project(String name, LocalDate startDate, LocalDate endDate) {
        this.setTitle(name);
        this.setStartDate(startDate);
        this.setEndDate(endDate);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }
}