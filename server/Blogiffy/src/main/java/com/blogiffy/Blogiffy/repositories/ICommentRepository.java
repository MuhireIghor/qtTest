package com.blogiffy.Blogiffy.repositories;

import com.blogiffy.Blogiffy.models.Blog;
import com.blogiffy.Blogiffy.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ICommentRepository extends JpaRepository<Comment, UUID> {
    List<Comment> findAllByBlog(Blog blog);
}
