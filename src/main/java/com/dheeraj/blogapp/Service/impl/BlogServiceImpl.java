package com.dheeraj.blogapp.Service.impl;

import com.dheeraj.blogapp.Model.Blog;
import com.dheeraj.blogapp.Model.Dtos.BlogRequestDto;
import com.dheeraj.blogapp.Model.Dtos.BlogResponseDto;
import com.dheeraj.blogapp.Repository.BlogRepository;
import com.dheeraj.blogapp.Service.BlogService;
import com.dheeraj.blogapp.mapper.BlogMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BlogServiceImpl implements BlogService {

    private final BlogRepository blogRepository;

    private final BlogMapper blogMapper;


    @Override
    public BlogResponseDto addBlog(BlogRequestDto blogRequestDto) {
        Blog blog = blogMapper.toEntity(blogRequestDto);
        Blog savedBlog = blogRepository.save(blog);
        return blogMapper.toDto(savedBlog);
    }

    @Override
    public Page<BlogResponseDto> getAllBlogs(int page, int size) {
        return blogRepository.findAll(PageRequest.of(page, size)).map(blogMapper::toDto);
    }

    @Override
    public Optional<BlogResponseDto> getBlogById(Long id) {
        return blogRepository.findById(id).map(blogMapper::toDto);
    }

    @Override
    public BlogResponseDto updateBlog(Long id, BlogRequestDto updatedBlogDto) {
        return blogRepository.findById(id).map(blog -> {
            blog.setTitle(updatedBlogDto.getTitle());
            blog.setContent(updatedBlogDto.getContent());
            blog.setAuthor(updatedBlogDto.getAuthor());
            Blog updatedBlog = blogRepository.save(blog);
            return blogMapper.toDto(updatedBlog);
        }).orElseThrow(() -> new RuntimeException("Blog not found"));
    }

    @Override
    public void deleteBlog(Long id) {
        blogRepository.deleteById(id);
    }
}
