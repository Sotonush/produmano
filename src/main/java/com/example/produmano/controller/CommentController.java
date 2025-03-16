package com.example.produmano.controller;

import com.example.produmano.entity.Comment;
import com.example.produmano.entity.Employee;
import com.example.produmano.entity.Task;
import com.example.produmano.enums.CommentPriority;
import com.example.produmano.enums.CommentType;
import com.example.produmano.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/create")
    public ResponseEntity<Comment> createComment(
            @RequestParam Employee employee,
            @RequestParam Task task,
            @RequestParam String text,
            @RequestParam CommentType type,
            @RequestParam(required = false) Long parentCommentId,
            @RequestParam CommentPriority priority) {
        Comment comment = commentService.createComment(employee, task, text, type, parentCommentId, priority);
        return ResponseEntity.ok(comment);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable Long id) {
        Optional<Comment> comment = commentService.getCommentById(id);
        return comment.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/entity/{entityId}")
    public ResponseEntity<List<Comment>> getCommentsByEntityId(@PathVariable Task task) {
        return ResponseEntity.ok(commentService.getCommentsByTask(task));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Comment>> getCommentsByUserId(@PathVariable Employee employee) {
        return ResponseEntity.ok(commentService.getCommentsByEmployee(employee));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Comment> updateComment(
            @PathVariable Long id,
            @RequestParam String newText,
            @RequestParam CommentPriority newPriority) {
        return ResponseEntity.ok(commentService.updateComment(id, newText, newPriority));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/sorted/priority")
    public ResponseEntity<List<Comment>> getCommentsSortedByPriority(@RequestParam CommentPriority priority) {
        return ResponseEntity.ok(commentService.getCommentsSortedByPriority(priority));
    }

    @GetMapping("/sorted/type")
    public ResponseEntity<List<Comment>> getCommentSortedByType(@RequestParam CommentType type) {
        return ResponseEntity.ok(commentService.getCommentSortedByType(type));
    }
}
