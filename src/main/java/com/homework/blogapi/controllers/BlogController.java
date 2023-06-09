package com.homework.blogapi.controllers;

import com.homework.blogapi.models.Blog;
import com.homework.blogapi.service.BlogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class BlogController {

    private static final Logger logger = LoggerFactory.getLogger(BlogController.class);

    @Autowired
    BlogService blogService;

    @PostMapping("/blogs")
    public ResponseEntity<Void> postBlog(@RequestBody Blog blog){
        blogService.postBlog(blog);
        logger.info("Post created successfully");
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/blogs")
    public ResponseEntity<?> getAllOrGetBlogByAuthor(@RequestParam(value = "author", required = false) String author){
        Blog blog = blogService.findBlogsByAuthor(author);
        if(author != null){
            return new ResponseEntity<>(blog, HttpStatus.OK);
        }
        return new ResponseEntity<>(blogService.getAllBlogs(), HttpStatus.OK);
    }

    @PutMapping("/blogs/{blogId}")
    public ResponseEntity<Void> updateBlog(@PathVariable Long blogId, @RequestBody Blog blog){
        blogService.updateBlog(blogId, blog);
        logger.info("Post updated successfully");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/blogs/{blogId}")
    public Optional<Blog> getBlogById(@PathVariable Long blogId){
        return blogService.findBlogById(blogId);
    }

    @DeleteMapping("/blogs/{blogId}")
    public ResponseEntity<Void> deleteBlog(@PathVariable Long blogId){
        blogService.deleteBlog(blogId);
        logger.info("Post deleted successfully");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
