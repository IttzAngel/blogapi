package com.homework.blogapi.service;

import com.homework.blogapi.models.Blog;
import com.homework.blogapi.models.Comments;
import com.homework.blogapi.repos.BlogRepo;
import com.homework.blogapi.repos.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private BlogRepo blogRepo;

    public void addComment(Long blogId, Comments comment){
        Blog blog = blogRepo.findById(blogId).orElse(null);
        comment.setBlog(blog);
        commentRepo.save(comment);
    }

    public List<Comments> getAllCommentsByBlogId(Long blogId){
        return commentRepo.findCommentsByBlogId(blogId);
    }

    public void updateComment(Long blogId, Long commentId, Comments comment){
        Blog blog = blogRepo.findById(blogId).orElse(null);
        Comments c = commentRepo.findById(commentId).orElse(null);
        if (c != null){
            c.setUser(comment.getUser());
            c.setComment(comment.getComment());
        }
        comment.setBlog(blog);
        commentRepo.save(comment);
    }

    public Optional<Comments> getCommentById(Long commentId){
        return commentRepo.findById(commentId);
    }

    public void deleteComment(Long commentId){
        commentRepo.deleteById(commentId);
    }
}
