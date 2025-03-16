package com.example.produmano.repository;

import com.example.produmano.entity.Comment;
import com.example.produmano.entity.Employee;
import com.example.produmano.entity.Task;
import com.example.produmano.enums.CommentPriority;
import com.example.produmano.enums.CommentType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByEmployee(Employee employee);
    List<Comment> findByPriorityOrderByCreatedAtDesc(CommentPriority priority);
    List<Comment> findByTypeOrderByCreatedAtDesc(CommentType type);

    List<Comment> findByTask(Task task);
}
