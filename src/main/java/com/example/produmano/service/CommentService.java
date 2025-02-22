package com.example.produmano.service;

import com.example.produmano.entity.Comment;
import com.example.produmano.enums.CommentPriority;
import com.example.produmano.enums.CommentType;
import com.example.produmano.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public Comment createComment(Long userId, Long entityId, String text, CommentType type, Long parentCommentId, CommentPriority priority) {
        Comment comment = Comment.builder()
                .userId(userId)
                .entityId(entityId)
                .text(text)
                .createdAt(LocalDateTime.now())
                .type(type)
                .parentCommentId(parentCommentId)
                .priority(priority)
                .build();
        return commentRepository.save(comment);
    }

    public Optional<Comment> getCommentById(Long id) {
        return commentRepository.findById(id);
    }

    public List<Comment> getCommentsByEntityId(Long entityId) {
        return commentRepository.findByEntityId(entityId);
    }

    public List<Comment> getCommentsByUserId(Long userId) {
        return commentRepository.findByUserId(userId);
    }

    @Transactional
    public Comment updateComment(Long id, String newText, CommentPriority newPriority) {
        return commentRepository.findById(id).map(comment -> {
            comment.setText(newText);
            comment.setPriority(newPriority);
            return commentRepository.save(comment);
        }).orElseThrow(() -> new RuntimeException("Комментарий не найден"));
    }

    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }

    public List<Comment> getCommentsSortedByPriority(CommentPriority priority) {
        return commentRepository.findAllByPriorityOrderByPriorityDesc(priority);
    }

    public List<Comment> getCommentSortedByType(CommentType type) {
        return commentRepository.findByTypeOrderByCreatedAtDesc(type);
    }
}