package com.codewithchristian.libraryapi.service.implementation;

import com.codewithchristian.libraryapi.exceptions.BadRequestException;
import com.codewithchristian.libraryapi.exceptions.ResourceNotFoundException;
import com.codewithchristian.libraryapi.repositories.BookCategoryRepository;
import com.codewithchristian.libraryapi.models.BookCategory;
import com.codewithchristian.libraryapi.payload.response.ApiResponse;
import com.codewithchristian.libraryapi.payload.response.BookCategoryResponse;
import com.codewithchristian.libraryapi.service.BookCategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class BookCategoryServiceImpl implements BookCategoryService {

    private final BookCategoryRepository bookCategoryRepository;

    @Override
    public List<BookCategoryResponse> getAllCategories() {
        List<BookCategory> bookCategories =  bookCategoryRepository.findAll();
        List<BookCategoryResponse>  bookCategoryResponses= new ArrayList<>();

        for (BookCategory bookCategory : bookCategories) {
            BookCategoryResponse bookCategoryResponse = new BookCategoryResponse(bookCategory.getId(),bookCategory.getCategory());
            bookCategoryResponses.add(bookCategoryResponse);
        }
        return bookCategoryResponses;
    }

    @Override
    public BookCategoryResponse getCategory(Long id) {
        BookCategory bookCategory =  bookCategoryRepository.findById(id)
                .orElseThrow(()-> {
                    throw new ResourceNotFoundException("Category Does Not Exist");
                });
        return new BookCategoryResponse(bookCategory.getId(), bookCategory.getCategory());

    }

    @Override
    public BookCategoryResponse addCategory(BookCategory category) {
        List<BookCategory> bookCategories = bookCategoryRepository.findAll();

        bookCategories.stream()
                .filter(bookCategory -> bookCategory.getCategory().equals(category.getCategory()))
                .forEach(bookCategory -> {
                    throw new BadRequestException("category with name already exists");
        });
        BookCategory bookCategory = bookCategoryRepository.save(category);
        return new BookCategoryResponse(bookCategory.getId(), bookCategory.getCategory());

    }

    @Override
    public BookCategoryResponse updateCategory(Long id, BookCategory newCategory) {
        BookCategory bookCategory = bookCategoryRepository.findById(id)
                .orElseThrow(()-> {
                    throw new ResourceNotFoundException("Category Does Not Exist");
                });
        bookCategory.setCategory(newCategory.getCategory());

        BookCategory updatedBookCategory = bookCategoryRepository.save(bookCategory);
        return new BookCategoryResponse(updatedBookCategory.getId(), updatedBookCategory.getCategory());
    }

    @Override
    public ApiResponse deleteCategory(Long id) {
        BookCategory bookCategory = bookCategoryRepository.findById(id)
                .orElseThrow(()-> {
                    throw new ResourceNotFoundException("Category Does Not Exist");
                });
        bookCategoryRepository.delete(bookCategory);
        return new ApiResponse(Boolean.TRUE, "You successfully deleted this category");
    }
}
