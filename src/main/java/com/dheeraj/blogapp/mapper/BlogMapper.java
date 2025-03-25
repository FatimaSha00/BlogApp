package com.dheeraj.blogapp.mapper;

import com.dheeraj.blogapp.Model.Blog;
import com.dheeraj.blogapp.Model.Dtos.BlogRequestDto;
import com.dheeraj.blogapp.Model.Dtos.BlogResponseDto;
import org.springframework.stereotype.Component;

@Component
public class BlogMapper {

    public BlogResponseDto toDto(Blog blog){
        BlogResponseDto dto = new BlogResponseDto();
        dto.setId(blog.getId());
        dto.setTitle(blog.getTitle());
        dto.setContent(blog.getContent());
        dto.setAuthor(blog.getAuthor());
        dto.setCreatedAt(blog.getCreatedAt());
        return dto;
    }

    public Blog toEntity(BlogRequestDto dto){
        Blog blog = new Blog();
        blog.setTitle(dto.getTitle());
        blog.setContent(dto.getContent());
        blog.setAuthor(dto.getAuthor());
        return blog;
    }
}
