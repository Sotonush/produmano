package com.example.produmano.repository;

import com.example.produmano.entity.Task;
import com.example.produmano.enums.TaskPriority;
import com.example.produmano.enums.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    Optional<Task> findById(Long id);

    List<Task> findByStatus(TaskStatus taskStatus);
    List<Task> findByClientId(Long clientId);
    List<Task> findByPriority(TaskPriority taskPriority);
    List<Task> findByMainEmployee(String mainEmployee);
    @Query("SELECT t FROM Task t WHERE t.endDate BETWEEN :startDate AND :endDate")
    List<Task> findByEndDateBetween(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

}
