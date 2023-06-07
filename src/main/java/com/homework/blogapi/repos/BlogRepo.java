package com.homework.blogapi.repos;

import com.homework.blogapi.models.Blog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepo extends CrudRepository<Blog, Long> {

    Blog findByAuthor(String author);

}
