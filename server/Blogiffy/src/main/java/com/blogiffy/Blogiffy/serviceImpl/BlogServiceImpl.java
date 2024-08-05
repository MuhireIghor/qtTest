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
@RequiredArgsConstructor

public class BlogServiceImpl implements IBlogService {
    private final IBlogRepository repository;
    private final IUserService userService;

    @Override
    public Blog createBlog(CreateBlogDto blog) {
        User author = userService.getLoggedInUser();

        Blog newBlog = new Blog();

        newBlog.setTitle(blog.getTitle());
        newBlog.setAuthor(author);
        newBlog.setContent(blog.getContent());
        return repository.save(newBlog);

    }

    @Override
    public Blog updateBlog(UUID blogId, UpdateBlogDto blog) {
        Blog oldBlog = repository.findById(blogId).orElseThrow(() -> new NotFoundException(String.format("Blog with id %s not found", blogId.toString())));
        oldBlog.setTitle(blog.getTitle());
        oldBlog.setContent(blog.getContent());
        return repository.save(oldBlog);
    }

    @Override
    public void deleteBlog(UUID blogId) {
        Boolean isBlogExistent = repository.existsById(blogId);
        if (!isBlogExistent) {
            throw new NotFoundException(String.format("Blog with id %s not found", blogId.toString()));
        }
        repository.deleteById(blogId);
    }

    @Override
    public Blog getBlogById(UUID blogId) {
        return repository.findById(blogId).orElseThrow(() -> new NotFoundException(String.format("Blog with id %s not found", blogId.toString())));
    }

    @Override
    public List<Blog> getAllBlogs() {
        return repository.findAll();
    }
}
