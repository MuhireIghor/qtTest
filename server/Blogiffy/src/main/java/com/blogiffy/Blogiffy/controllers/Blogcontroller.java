package com.blogiffy.Blogiffy.controllers;

import com.blogiffy.Blogiffy.dto.request.CreateBlogDto;
import com.blogiffy.Blogiffy.dto.request.UpdateBlogDto;
import com.blogiffy.Blogiffy.dto.response.ApiResponse;
import com.blogiffy.Blogiffy.services.IBlogService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

//Blog controller
@RestController
@RequestMapping("/api/v1/blogs")
@RequiredArgsConstructor
@CrossOrigin
public class Blogcontroller {
    private final IBlogService blogService;
    private static final Logger logger = LoggerFactory.getLogger(Blogcontroller.class);
    //end point for retrieving all blogs

    @Operation(summary = "Get all blogs", description = "Api Endpoint for getting all created blogs")

    @GetMapping("/all-blogs")

    public ResponseEntity<ApiResponse> getAllBlogs() {
        logger.info("Get all blogs");
        return ResponseEntity.ok(
                new ApiResponse(true, "Blogs Fetched successfully", blogService.getAllBlogs())
        );
    }

        //end point for getting a single blog that can be accessible by both the authorised and unauthorised people
        @Operation(summary = "Get Single Blog by Id", description = "Api Endpoint for getting a single Blog by Id")
        @GetMapping("/read-blog/{blogId}")
        public ResponseEntity<ApiResponse> getBlogById(@PathVariable(value = "blogId") UUID blogId) {
            return ResponseEntity.ok(new ApiResponse(true, "Blog fetched successfully", blogService.getBlogById(blogId)));
        }
    //end point for creating a new blog that is only accessible to the publisher

    @Operation(summary = "Blog Creation", description = "Api Endpoint for Creating a new Blog")
    @PreAuthorize("hasAuthority('PUBLISHER')")
    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createBlog(@RequestBody CreateBlogDto dto) {
        logger.info("Create blog");
        return ResponseEntity.ok(new ApiResponse(true, "Blog created successfully", blogService.createBlog(dto)));
    }




    //endpoint for updating a single blog
    @Operation(summary = "Update blog", description = "Api Endpoint for updating a single blog by id")
    @PreAuthorize("hasAuthority('PUBLISHER')")
    @PutMapping("/update/{blogId}")
    public ResponseEntity<ApiResponse> updateBlogById(@PathVariable(value = "blogId") UUID blogId, @RequestBody UpdateBlogDto dto) {
        logger.info("Update blog");
        return ResponseEntity.ok(new ApiResponse(true, "Blog updated successfully", blogService.updateBlog(blogId, dto)));
    }
        //end point for deleting a single blog that is only accessible by the publisher only

        @Operation(summary = "Delete a single Blog by Id", description = "Api Endpoint for deleting a single Blog by id")
        @PreAuthorize("hasAuthority('PUBLISHER')")
        @DeleteMapping("/{blogId}")
        public ResponseEntity<ApiResponse> deleteBlog(@PathVariable(value = "blogId") UUID blogId) {
            logger.info("Delete blog");
            blogService.deleteBlog(blogId);
            return ResponseEntity.ok(new ApiResponse(true, "Blog deleted successfully", null));
        }

}
