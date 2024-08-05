package com.blogiffy.Blogiffy.serviceImpl;

import com.blogiffy.Blogiffy.dto.request.CreateBlogDto;
import com.blogiffy.Blogiffy.dto.request.UpdateBlogDto;
import com.blogiffy.Blogiffy.exceptions.NotFoundException;
import com.blogiffy.Blogiffy.models.Blog;
import com.blogiffy.Blogiffy.models.User;
import com.blogiffy.Blogiffy.repositories.IBlogRepository;
import com.blogiffy.Blogiffy.services.IBlogService;
import com.blogiffy.Blogiffy.services.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor // Automatically generates a constructor with required arguments
public class BlogServiceImpl implements IBlogService {
    private final IBlogRepository repository; // Injecting the blog repository
    private final IUserService userService; // Injecting the user service

    @Override
    public Blog createBlog(CreateBlogDto blog) {
        User author = userService.getLoggedInUser(); // Get the logged-in user

        Blog newBlog = new Blog(); // Create a new blog object

        // Set the blog's title, author, and content
        newBlog.setTitle(blog.getTitle());
        newBlog.setAuthor(author);
        newBlog.setContent(blog.getContent());

        // Save the new blog to the repository and return it
        return repository.save(newBlog);
    }

    @Override
    public Blog updateBlog(UUID blogId, UpdateBlogDto blog) {
        // Find the blog by its ID or throw an exception if not found
        Blog oldBlog = repository.findById(blogId).orElseThrow(() -> new NotFoundException(String.format("Blog with id %s not found", blogId.toString())));

        // Update the blog's title and content
        oldBlog.setTitle(blog.getTitle());
        oldBlog.setContent(blog.getContent());

        // Save the updated blog to the repository and return it
        return repository.save(oldBlog);
    }

    @Override
    public void deleteBlog(UUID blogId) {
        // Check if the blog exists by its ID
        Boolean isBlogExistent = repository.existsById(blogId);

        // If the blog doesn't exist, throw an exception
        if (!isBlogExistent) {
            throw new NotFoundException(String.format("Blog with id %s not found", blogId.toString()));
        }

        // Delete the blog by its ID
        repository.deleteById(blogId);
    }

    @Override
    public Blog getBlogById(UUID blogId) {
        // Find the blog by its ID or throw an exception if not found
        return repository.findById(blogId).orElseThrow(() -> new NotFoundException(String.format("Blog with id %s not found", blogId.toString())));
    }

    @Override
    public List<Blog> getAllBlogs() {
        // Retrieve all blogs from the repository
        return repository.findAll();
    }
}
