package com.codewithchristian.libraryapi.service;

import com.codewithchristian.libraryapi.payload.response.ApiResponse;
import com.codewithchristian.libraryapi.payload.response.BookCategoryResponse;
import com.codewithchristian.libraryapi.models.BookCategory;

import java.util.List;

public interface BookCategoryService {
    List<BookCategoryResponse> getAllCategories();
    BookCategoryResponse getCategory(Long id);
    BookCategoryResponse addCategory(BookCategory category);
    BookCategoryResponse updateCategory(Long id, BookCategory newCategory);
    ApiResponse deleteCategory(Long id);
}
