package com.blogiffy.Blogiffy.services;

import com.blogiffy.Blogiffy.dto.request.CreateCommentDto;
import com.blogiffy.Blogiffy.dto.request.UpdateCommentDto;
import com.blogiffy.Blogiffy.models.Comment;

import java.util.List;
import java.util.UUID;

public interface ICommentService {
    Comment addComment(UUID blogId,CreateCommentDto comment);
    List<Comment> getAllCommentsByBlogId(UUID blogId);
    Comment getCommentById(UUID id);
    Comment updateComment(UUID commentId, UpdateCommentDto comment);
    void deleteComment(UUID commentId);
}
