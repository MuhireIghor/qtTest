package com.blogiffy.Blogiffy.controllers;

import com.blogiffy.Blogiffy.dto.request.CreateCommentDto;
import com.blogiffy.Blogiffy.dto.request.UpdateCommentDto;
import com.blogiffy.Blogiffy.dto.response.ApiResponse;
import com.blogiffy.Blogiffy.services.ICommentService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/comments")
@RequiredArgsConstructor
public class CommentController {
    private final ICommentService commentService;

    @Operation(summary = "Create new Comment", description = "Api Endpoint for creating a new comment")
    @PostMapping("/create-comment")
    public ResponseEntity<ApiResponse> createComment(@RequestBody  CreateCommentDto comment,@RequestParam(name = "blogId") UUID blogId) {
        return ResponseEntity.ok(new ApiResponse(true, "Comment created successfully", commentService.addComment(blogId,comment)));
    }

    @Operation(summary = "Update single comment", description = "Api Endpoint for updating a single comment")
    @PutMapping("/update-comment/{commentId}")
    public ResponseEntity<ApiResponse> updateComment(@PathVariable UUID commentId, @RequestBody UpdateCommentDto comment) {
        return ResponseEntity.ok(new ApiResponse(true, "Comment updated successfully", commentService.updateComment(commentId, comment)));
    }

    @Operation(summary = "Get All comments by Blog Id ", description = "Api Endpoint for getting All comments by BlogId")
    @GetMapping("/all-comments/by-blog/{blogId}")
    public ResponseEntity<ApiResponse> getAllCommentsByBlog(@PathVariable UUID blogId) {
        return ResponseEntity.ok(new ApiResponse(true, "Comments fetched successfully", commentService.getAllCommentsByBlogId(blogId)));
    }

    @Operation(summary = "Get Single Comment ", description = "Api Endpoint for deleting a comment by BlogId")
    @DeleteMapping("/delete-comment/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable UUID commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.ok(new ApiResponse(true, "Comment deleted successfully", null));
    }


}
