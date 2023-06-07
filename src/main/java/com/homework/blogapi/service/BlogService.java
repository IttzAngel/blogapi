package com.homework.blogapi.service;

import com.homework.blogapi.exceptions.ResourceNotFoundException;
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

    public Optional<Blog> findBlogById(Long blogId){
        verifyBlog(blogId);
        return blogRepo.findById(blogId);
    }

    public void updateBlog(Long blogId, Blog blog){
        verifyBlog(blogId);
        blog.setId(blogId);
        blogRepo.save(blog);
    }

    public void deleteBlog(Long blogId){
        verifyBlog(blogId);
        blogRepo.deleteById(blogId);
    }

    public Blog findBlogsByAuthor(String author){
        return blogRepo.findByAuthor(author);
    }

    protected void verifyBlog(Long blogId) throws ResourceNotFoundException {
        Optional<Blog> blog = blogRepo.findById(blogId);
        if (blog.isEmpty()){
            throw new ResourceNotFoundException("The blog with id " + blogId + " does not exist :(");
        }
    }
}
