package com.example.produmano.controller;

import com.example.produmano.entity.Task;
import com.example.produmano.enums.TaskPriority;
import com.example.produmano.enums.TaskStatus;
import com.example.produmano.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "*")
public class TaskController {
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('MANAGER') or hasRole('ADMIN')")
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskService.getAllTasks();

        if (tasks.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('MANAGER') or hasRole('ADMIN')")
    public ResponseEntity<?> getTaskById(@PathVariable Long id) {
        Task task = taskService.getTaskById(id)
                .orElse(null);

        if (task == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Задача с ID " + id + " не найдена.");
        }

        return ResponseEntity.ok(task);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('MANAGER') or hasRole('ADMIN')")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task updateTask){
        Task task = taskService.updateTask(id, updateTask);
        return ResponseEntity.ok(task);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('MANAGER') or hasRole('ADMIN')")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        boolean deleted = taskService.deleteTask(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/search/client")
    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('MANAGER') or hasRole('ADMIN')")
    public ResponseEntity<List<Task>> findTaskByClient(@RequestParam Long id) {
        List<Task> tasks = taskService.findTaskByClient(id);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/search/priority")
    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('MANAGER') or hasRole('ADMIN')")
    public ResponseEntity<List<Task>> findByPriority(@RequestParam TaskPriority taskPriority) {
        List<Task> tasks = taskService.findByPriority(taskPriority);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/search/employee")
    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('MANAGER') or hasRole('ADMIN')")
    public ResponseEntity<List<Task>> findByMainEmployee(@RequestParam String employee) {
        List<Task> tasks = taskService.findByMainEmployee(employee);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/search/status")
    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('MANAGER') or hasRole('ADMIN')")
    public ResponseEntity<List<Task>> findByStatus(@RequestParam TaskStatus status) {
        List<Task> tasks = taskService.findByStatus(status);
        return ResponseEntity.ok(tasks);
    }
    @PostMapping("/createTask")
    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('MANAGER') or hasRole('ADMIN')")
    public ResponseEntity<Task> createTask(@Valid @RequestBody Task task) {
        Task createdTask = taskService.createTask(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
    }
}
//надо проверить