package com.dheeraj.blogapp.Model.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogRequestDto {
    private String title;
    private String content;
    private String author;
}
