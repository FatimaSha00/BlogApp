package com.dheeraj.blogapp.Model.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagedResponse<T> {
    private List<T> content;
    private  int pageNumber;
    private int pageSize;
    private int totalElements;
    private int totalPages;
    private boolean last;
}
