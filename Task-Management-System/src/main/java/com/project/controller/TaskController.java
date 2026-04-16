package com.project.controller;

import com.project.dto.TaskDTO;
import com.project.entity.Task;
import com.project.service.TaskService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    // ✅ Create Task (USER + ADMIN)
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @PostMapping
    public Task createTask(@RequestBody TaskDTO dto) {
        return taskService.createTask(dto);
    }

    // ✅ Get logged-in user tasks
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping
    public List<Task> getMyTasks() {
        return taskService.getMyTasks();
    }

    // Optional ADMIN: get all tasks
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public List<Task> getAllTasks() {
        return taskService.getMyTasks();
    }
}