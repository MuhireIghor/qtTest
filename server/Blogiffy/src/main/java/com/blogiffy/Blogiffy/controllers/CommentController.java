package com.blogiffy.Blogiffy.controllers;

import com.blogiffy.Blogiffy.dto.request.CreateCommentDto;
import com.blogiffy.Blogiffy.dto.request.UpdateCommentDto;
import com.blogiffy.Blogiffy.dto.response.ApiResponse;
import com.blogiffy.Blogiffy.services.ICommentService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

//Comment controller end point
@RestController
@RequestMapping("/api/v1/comments")
@RequiredArgsConstructor
@CrossOrigin
public class CommentController {
    private static final Logger logger = LoggerFactory.getLogger(CommentController.class);

    private final ICommentService commentService;

    //end point for creating a new end point
    @Operation(summary = "Create new Comment", description = "Api Endpoint for creating a new comment")
    @PostMapping("/create-comment")
    public ResponseEntity<ApiResponse> createComment(@RequestBody CreateCommentDto comment, @RequestParam(name = "blogId") UUID blogId) {
        logger.info("Creating a new comment");
        return ResponseEntity.ok(new ApiResponse(true, "Comment created successfully", commentService.addComment(blogId, comment)));
    }

    //end point for updating an existing end point
    @Operation(summary = "Update single comment", description = "Api Endpoint for updating a single comment")
    @PutMapping("/update-comment/{commentId}")
    public ResponseEntity<ApiResponse> updateComment(@PathVariable UUID commentId, @RequestBody UpdateCommentDto comment) {
        logger.info("Updating a comment");
        return ResponseEntity.ok(new ApiResponse(true, "Comment updated successfully", commentService.updateComment(commentId, comment)));
    }

    //end point for getting all comments related to a certain blog
    @Operation(summary = "Get All comments by Blog Id ", description = "Api Endpoint for getting All comments by BlogId")
    @GetMapping("/all-comments/by-blog/{blogId}")
    public ResponseEntity<ApiResponse> getAllCommentsByBlog(@PathVariable UUID blogId) {
        logger.info("Get All comments by Blog Id");
        return ResponseEntity.ok(new ApiResponse(true, "Comments fetched successfully", commentService.getAllCommentsByBlogId(blogId)));
    }
    //end point for deleting a single comment

    @Operation(summary = "Get Single Comment ", description = "Api Endpoint for deleting a comment by BlogId")
    @DeleteMapping("/delete-comment/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable UUID commentId) {
        logger.info("Deleting a comment");
        commentService.deleteComment(commentId);
        return ResponseEntity.ok(new ApiResponse(true, "Comment deleted successfully", null));
    }


}
