package com.dheeraj.blogapp.Controller;

import com.dheeraj.blogapp.Model.Blog;
import com.dheeraj.blogapp.Model.Dtos.BlogRequestDto;
import com.dheeraj.blogapp.Model.Dtos.BlogResponseDto;
import com.dheeraj.blogapp.Service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/blogs")
@RequiredArgsConstructor
public class BlogController {

    private final BlogService blogService;

    @PostMapping
    public ResponseEntity<BlogResponseDto> addBlog(@RequestBody BlogRequestDto blogRequestDto){
        return ResponseEntity.ok(blogService.addBlog(blogRequestDto));
    }

    @GetMapping
    public ResponseEntity<Map<String,Object>> getAllBlogs(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        Page<BlogResponseDto> blogPage = blogService.getAllBlogs(page, size);

        Map<String, Object> response = new HashMap<>();
        response.put("content", blogPage.getContent());
        response.put("totalElements", blogPage.getTotalElements());
        response.put("totalPages", blogPage.getTotalPages());
        response.put("pageNumber", blogPage.getNumber());
        response.put("pageSize", blogPage.getSize());
        response.put("first", blogPage.isFirst());
        response.put("last", blogPage.isLast());

        return ResponseEntity.ok(response);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<BlogResponseDto>> getBlogById(@PathVariable Long id){
        return ResponseEntity.ok(blogService.getBlogById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BlogResponseDto> updateBlog(@PathVariable Long id, @RequestBody BlogRequestDto updatedBlogDto){
        return ResponseEntity.ok(blogService.updateBlog(id,updatedBlogDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBlog(@PathVariable Long id){
        blogService.deleteBlog(id);
        return ResponseEntity.noContent().build();
    }
}
