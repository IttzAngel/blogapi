package com.homework.blogapi.repos;

import com.homework.blogapi.models.Comments;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepo extends CrudRepository<Comments, Long> {

    List<Comments> findCommentsByBlogId(Long blogId);

}
