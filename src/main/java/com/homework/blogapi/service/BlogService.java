package com.homework.blogapi.service;

import com.homework.blogapi.models.Blog;
import com.homework.blogapi.repos.BlogRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BlogService {

    @Autowired
    BlogRepo blogRepo;

    public void postBlog(Blog blog){
        blogRepo.save(blog);
    }

    public Iterable<Blog> getAllBlogs(){
        return blogRepo.findAll();
    }

    public Optional<Blog> findBlogById(Long id){
        return blogRepo.findById(id);
    }

    public void updateBlog(Long id, Blog blog){
        blog.setId(id);
        blogRepo.save(blog);
    }

    public void deleteBlog(Long id){
        blogRepo.deleteById(id);
    }

    public Blog findBlogsByAuthor(String author){
        return blogRepo.findByAuthor(author);
    }
}
