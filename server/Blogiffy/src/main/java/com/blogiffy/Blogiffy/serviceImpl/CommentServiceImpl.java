package com.blogiffy.Blogiffy.serviceImpl;

import com.blogiffy.Blogiffy.dto.request.CreateCommentDto;
import com.blogiffy.Blogiffy.dto.request.UpdateBlogDto;
import com.blogiffy.Blogiffy.dto.request.UpdateCommentDto;
import com.blogiffy.Blogiffy.exceptions.NotFoundException;
import com.blogiffy.Blogiffy.models.Blog;
import com.blogiffy.Blogiffy.models.Comment;
import com.blogiffy.Blogiffy.models.User;
import com.blogiffy.Blogiffy.repositories.ICommentRepository;
import com.blogiffy.Blogiffy.repositories.IBlogRepository;
import com.blogiffy.Blogiffy.repositories.IUserRepository;
import com.blogiffy.Blogiffy.services.ICommentService;
import com.blogiffy.Blogiffy.services.IUserService;
import com.blogiffy.Blogiffy.utils.ExceptionUtils;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor // Automatically generates a constructor with required arguments
public class CommentServiceImpl implements ICommentService {
    private static final Logger logger = LoggerFactory.getLogger(CommentServiceImpl.class); // Logger for the class
    private final IUserService userService; // Injecting the user service
    private final IUserRepository userRepository; // Injecting the user repository
    private final IBlogRepository blogRepository; // Injecting the blog repository
    private final ICommentRepository commentRepository; // Injecting the comment repository

    public
    @Override Comment addComment(UUID blogId, CreateCommentDto comment) {
        User author = userService.getLoggedInUser(); // Get the logged-in user

        // Find the blog to comment on or throw an exception if not found
        Blog blogToComment = blogRepository.findById(blogId).orElseThrow(() -> new NotFoundException("Blog not found"));
        logger.info("Adding comment to blog: {}", blogId);

        // Create a new comment object and set its fields
        Comment newComment = new Comment();
        newComment.setAuthor(author);
        newComment.setContent(comment.getContent());
        newComment.setBlog(blogToComment);

        // Add the new comment to the blog's comment list
        blogToComment.getComments().add(newComment);
        logger.info("Comment added to blog's comment list");

        // Save the blog and comment to their respective repositories
        blogRepository.save(blogToComment);
        commentRepository.save(newComment);
        return newComment;
    }

    @Override
    public List<Comment> getAllCommentsByBlogId(UUID blogId) {
        try {
            boolean isBlogPresent = blogRepository.findById(blogId).isPresent();

            // Find the blog by its ID
            Optional<Blog> blog = blogRepository.findById(blogId);
            return blog.map(commentRepository::findAllByBlog).orElse(null);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            // Handle exceptions using the utility class
            ExceptionUtils.handleControllerExceptions(e);
            return null;
        }
    }

    @Override
    public Comment getCommentById(UUID id) {
        try {
            // Find the comment by its ID or throw an exception if not found
            return commentRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Comment with id %s is not found!", id)));
        } catch (NotFoundException e) {
            // Handle exceptions using the utility class
            ExceptionUtils.handleControllerExceptions(e);
            return null;
        }
    }

    @Override
    public Comment updateComment(UUID commentId, UpdateCommentDto comment) {
        // Find the comment by its ID or throw an exception if not found
        Comment oldComment = commentRepository.findById(commentId).orElseThrow(() -> new NotFoundException(String.format("Comment with id %s is not found!", commentId)));

        // Update the comment's content
        oldComment.setContent(comment.getContent());

        // Save the updated comment to the repository and return it
        return commentRepository.save(oldComment);
    }

    @Override
    public void deleteComment(UUID commentId) {
        // Find the comment by its ID or throw an exception if not found
        Comment oldComment = commentRepository.findById(commentId).orElseThrow(() -> new NotFoundException(String.format("Comment with id %s is not found!", commentId)));

        // Delete the comment by its ID
        commentRepository.deleteById(commentId);
    }
}
