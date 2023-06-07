package com.homework.blogapi.controllers;

import com.homework.blogapi.models.Comments;
import com.homework.blogapi.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/blogs/{blogId}/comments")
    public ResponseEntity<Void> postComment(@PathVariable Long blogId, @RequestBody Comments comment){
        commentService.addComment(blogId, comment);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/blogs/{blogId}/comments")
    public ResponseEntity<List<Comments>> getAllCommentsById(@PathVariable Long blogId){
        return new ResponseEntity<>(commentService.getAllCommentsByBlogId(blogId), HttpStatus.OK);
    }

    @PutMapping("/blogs/{blogId}/comments/{commentId}")
    public ResponseEntity<Void> updateComment(@PathVariable Long blogId, @PathVariable Long commentId, @RequestBody Comments comment){
        commentService.updateComment(blogId, commentId, comment);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/comments/{commentId}")
    public ResponseEntity<Optional<Comments>> getCommentById(@PathVariable Long commentId){
        return new ResponseEntity<>(commentService.getCommentById(commentId), HttpStatus.OK);
    }

    @DeleteMapping("comments/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId){
        commentService.deleteComment(commentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
