package com.example.produmano.controller;

import com.example.produmano.entity.Task;
import com.example.produmano.enums.TaskPriority;
import com.example.produmano.enums.TaskStatus;
import com.example.produmano.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Task>> getAllTask() {
        List<Task> tasks = taskService.getAllTasks();
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Task>> getTaskById(@PathVariable Long id) {
        try{
            Optional<Task> task = taskService.getTaskById(id);
            return ResponseEntity.ok(task);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task updateTask){
        Task task = taskService.updateTask(id, updateTask);
        return ResponseEntity.ok(task);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id){
        boolean deleted = taskService.deleteTask(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/search/client")
    public ResponseEntity<List<Task>> findTaskByClient(@RequestParam Long id) {
        List<Task> tasks = taskService.findTaskByClient(id);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/search/priority")
    public ResponseEntity<List<Task>> findByPriority(@RequestParam TaskPriority taskPriority) {
        List<Task> tasks = taskService.findByPriority(taskPriority);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/search/employee")
    public ResponseEntity<List<Task>> findByMainEmployee(@RequestParam String employee) {
        List<Task> tasks = taskService.findByMainEmployee(employee);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/search/status")
    public ResponseEntity<List<Task>> findByStatus(@RequestParam TaskStatus status) {
        List<Task> tasks = taskService.findByStatus(status);
        return ResponseEntity.ok(tasks);
    }


}
//Создание, удаление, обновление, найти задачу по клиенту,
//Найти по приеритету,найти по обязанному, найти по статусу. Найти по айди