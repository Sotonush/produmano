package com.example.produmano.repository;

import com.example.produmano.entity.Comment;
import com.example.produmano.enums.CommentPriority;
import com.example.produmano.enums.CommentType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByEntityId(Long entityId);
    List<Comment> findByUserId(Long userId);
    List<Comment> findAllByPriorityOrderByPriorityDesc(CommentPriority priority);
    List<Comment> findByTypeOrderByCreatedAtDesc(CommentType type);
}
