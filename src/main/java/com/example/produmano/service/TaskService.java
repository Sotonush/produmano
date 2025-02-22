package com.example.produmano.service;

import com.example.produmano.entity.Client;
import com.example.produmano.entity.Task;
import com.example.produmano.enums.TaskPriority;
import com.example.produmano.enums.TaskStatus;
import com.example.produmano.repository.ClientRepository;
import com.example.produmano.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final ClientRepository clientRepository;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    @Transactional
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    @Transactional
    public Task updateTask(Long id, Task updatedTask) {
        return taskRepository.findById(id)
                .map(existingTask -> {
                    if (updatedTask.getClient() != null) {
                        Client client = clientRepository.findById(updatedTask.getClient().getId())
                                .orElseThrow(() -> new RuntimeException("Client not found with id " + updatedTask.getClient().getId()));
                        existingTask.setClient(client);
                    }
                    if (updatedTask.getDescription() != null) {
                        existingTask.setDescription(updatedTask.getDescription());
                    }
                    if (updatedTask.getStartDate() != null) {
                        existingTask.setStartDate(updatedTask.getStartDate());
                    }
                    if (updatedTask.getEndDate() != null) {
                        existingTask.setEndDate(updatedTask.getEndDate());
                    }
                    if (updatedTask.getStatus() != null) {
                        existingTask.setStatus(updatedTask.getStatus());
                    }
                    if (updatedTask.getPriority() != null) {
                        existingTask.setPriority(updatedTask.getPriority());
                    }
                    if (updatedTask.getMainEmployee() != null) {
                        existingTask.setMainEmployee(updatedTask.getMainEmployee());
                    }
                    return taskRepository.save(existingTask);
                })
                .orElseThrow(() -> new RuntimeException("Task not found with id " + id));
    }

    @Transactional
    public boolean deleteTask(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new RuntimeException("Task not found with id " + id);
        }
        taskRepository.deleteById(id);
        return false;
    }

    public List<Task> findTaskByClient(Long id){
        return taskRepository.findByClientId(id);
    }

    public List<Task> findByPriority(TaskPriority taskPriority) {
        return taskRepository.findByPriority(taskPriority);
    }

    public List<Task> findByMainEmployee(String employee) {
        return taskRepository.findByMainEmployee(employee);
    }
    public List<Task> findByStatus(TaskStatus status) {
        return taskRepository.findByStatus(status);
    }
}
