package com.dheeraj.blogapp.Service;

import com.dheeraj.blogapp.Model.Dtos.BlogRequestDto;
import com.dheeraj.blogapp.Model.Dtos.BlogResponseDto;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface BlogService {
    BlogResponseDto addBlog(BlogRequestDto blogRequestDto);
    Page<BlogResponseDto> getAllBlogs(int page, int size);
    Optional<BlogResponseDto> getBlogById(Long id);
    BlogResponseDto updateBlog(Long id, BlogRequestDto updatedBlogDto);
    void deleteBlog(Long id);
}
