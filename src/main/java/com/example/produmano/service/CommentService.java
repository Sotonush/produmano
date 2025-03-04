package com.example.produmano.service;

import com.example.produmano.entity.Comment;
import com.example.produmano.entity.Employee;
import com.example.produmano.entity.Task;
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

    public Comment createComment(Employee employee, Task task, String text, CommentType type, Long parentCommentId, CommentPriority priority) {
        Comment comment = Comment.builder()
                .employee(employee)
                .task(task)
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

    public List<Comment> getCommentsByTask(Task task) {
        return commentRepository.findByTask(task);
    }

    public List<Comment> getCommentsByEmployee(Employee employee) {
        return commentRepository.findByEmployee(employee);
    }

    @Transactional
    public Comment updateComment(Long id, String newText, CommentPriority newPriority) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Комментарий не найден"));
        comment.setText(newText);
        comment.setPriority(newPriority);
        return comment;
    }

    public void deleteComment(Long id) {
        if (!commentRepository.existsById(id)) {
            throw new RuntimeException("Комментарий не найден");
        }
        commentRepository.deleteById(id);
    }

    public List<Comment> getCommentsSortedByPriority(CommentPriority priority) {
        return commentRepository.findByPriorityOrderByCreatedAtDesc(priority);
    }

    public List<Comment> getCommentSortedByType(CommentType type) {
        return commentRepository.findByTypeOrderByCreatedAtDesc(type);
    }
}
