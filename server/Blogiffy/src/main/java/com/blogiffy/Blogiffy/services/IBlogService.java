package com.blogiffy.Blogiffy.services;

import com.blogiffy.Blogiffy.dto.request.CreateBlogDto;
import com.blogiffy.Blogiffy.dto.request.UpdateBlogDto;
import com.blogiffy.Blogiffy.models.Blog;

import java.util.List;
import java.util.UUID;

public interface IBlogService {
     Blog createBlog(CreateBlogDto blog);
     Blog updateBlog(UUID blogId, UpdateBlogDto blog);
     void deleteBlog(UUID blogId);
     Blog getBlogById(UUID blogId);
     List<Blog> getAllBlogs();

}
