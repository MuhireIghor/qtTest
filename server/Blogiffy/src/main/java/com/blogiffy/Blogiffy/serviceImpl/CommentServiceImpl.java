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
@RequiredArgsConstructor
public class CommentServiceImpl implements ICommentService {
    private static final Logger logger = LoggerFactory.getLogger(CommentServiceImpl.class);
    private final IUserService userService;
    private final IUserRepository userRepository;
    private final IBlogRepository blogRepository;
    private final ICommentRepository commentRepository;


    @Override
    public Comment addComment(UUID blogId, CreateCommentDto comment) {
        User author = userService.getLoggedInUser();

        Blog blogToComment = blogRepository.findById(blogId).orElseThrow(() -> new NotFoundException("Blog not found"));
        logger.info("Adding comment to blog: {}", blogId);

        Comment newComment = new Comment();
        newComment.setAuthor(author);
        newComment.setContent(comment.getContent());
        newComment.setBlog(blogToComment);
        blogToComment.getComments().add(newComment);
        logger.info("Comment added to blog's comment list");

        blogRepository.save(blogToComment);
        commentRepository.save(newComment);
        return newComment;

    }


    @Override
    public List<Comment> getAllCommentsByBlogId(UUID blogId) {
        System.out.println("Invocation is not working");
        try {
            boolean isBlogPresent = blogRepository.findById(blogId).isPresent();

            Optional<Blog> blog = blogRepository.findById(blogId);
            if (blog.isEmpty()) {
                System.out.println("Blog not found =============");
                return null;

            }
            System.out.println("Blog found ============="+blog.get());
            return commentRepository.findAllByBlog(blog.get());


        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            ExceptionUtils.handleControllerExceptions(e);
            return null;
        }


    }

    @Override
    public Comment getCommentById(UUID id) {
        try {

            return commentRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Comment with id %s is not found!", id)));
        } catch (NotFoundException e) {
            ExceptionUtils.handleControllerExceptions(e);
            return null;
        }
    }


    @Override
    public Comment updateComment(UUID commentId, UpdateCommentDto comment) {
        Comment oldComment = commentRepository.findById(commentId).orElseThrow(() -> new NotFoundException(String.format("Comment with id %s is not found!", commentId)));
        oldComment.setContent(comment.getContent());
        return commentRepository.save(oldComment);


    }

    @Override
    public void deleteComment(UUID commentId) {
        Comment oldComment = commentRepository.findById(commentId).orElseThrow(() -> new NotFoundException(String.format("Comment with id %s is not found!", commentId)));
        commentRepository.deleteById(commentId);


    }
}
