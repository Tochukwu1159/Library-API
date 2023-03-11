package com.codewithchristian.libraryapi.service.implementation;

import com.codewithchristian.libraryapi.exceptions.BadRequestException;
import com.codewithchristian.libraryapi.exceptions.ResourceNotFoundException;
import com.codewithchristian.libraryapi.repositories.BookCategoryRepository;
import com.codewithchristian.libraryapi.repositories.BookRepository;
import com.codewithchristian.libraryapi.service.BookService;
import com.codewithchristian.libraryapi.models.Book;
import com.codewithchristian.libraryapi.models.BookCategory;
import com.codewithchristian.libraryapi.payload.request.BookRequest;
import com.codewithchristian.libraryapi.payload.response.ApiResponse;
import com.codewithchristian.libraryapi.payload.response.BookResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookCategoryRepository bookCategoryRepository;



    @Override
    public List<BookResponse> getAllBooks() {
        List<Book> books =  bookRepository.findAll();
        return getBookResponseList(books);
    }

    @Override
    public List<BookResponse> getBooksByCategory(Long categoryId) {
        BookCategory bookCategory = bookCategoryRepository.findById(categoryId)
                .orElseThrow(()-> {
                    throw new ResourceNotFoundException("Category Does Not Exist");
                });

        List<Book> books = bookRepository.findAllByCategory(bookCategory);
        return getBookResponseList(books);

    }

    private List<BookResponse> getBookResponseList(List<Book> books) {
        List<BookResponse> bookResponses = new ArrayList<>();

        for (Book book : books) {
            BookResponse bookResponse = new BookResponse(book.getId(), book.getBookTitle(), book.getBookAuthor(), book.getQuantity(),book.getCategory().getCategory());
            bookResponses.add(bookResponse);
        }

        return bookResponses;
    }

    @Override
    public BookResponse getBook(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() ->{
            throw new ResourceNotFoundException("Book does not exist");
        });
        return new BookResponse(book.getId(), book.getBookTitle(), book.getBookAuthor(), book.getQuantity(),book.getCategory().getCategory());
    }

    @Override
    public BookResponse addBook(BookRequest bookRequest) {
        List<Book> books = bookRepository.findAll();
        BookCategory bookCategory = bookCategoryRepository.findById(bookRequest.getCategoryId())
                .orElseThrow(()-> {
                    throw new ResourceNotFoundException("Category Does Not Exist");
                });

        books.stream().filter(book -> book.getBookTitle().equals(bookRequest.getBookTitle()))
                .forEach(book -> {
                    throw new BadRequestException("book with name already exists");
        });

        Book book = new Book();
        return getBookResponse(bookRequest, book, bookCategory);
    }

    @Override
    public BookResponse updateBook(Long id, BookRequest newBookRequest) {

        List<Book> books = bookRepository.findAll();
        Book book = bookRepository.findById(id)
                .orElseThrow(()->{
                    throw new ResourceNotFoundException("This book does not exist");
                });

        BookCategory bookCategory = bookCategoryRepository.findById(newBookRequest.getCategoryId())
                .orElseThrow(()-> {
                    throw new ResourceNotFoundException("Category Does Not Exist");
                });

        books.stream().filter(aBook -> book.getBookTitle().equals(newBookRequest.getBookTitle()))
                .forEach(aBook -> book.setQuantity(book.getQuantity()+newBookRequest.getQuantity()));

        return getBookResponse(newBookRequest, book, bookCategory);
    }

    @Override
    public ApiResponse deleteBook(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(()->{
                    throw new ResourceNotFoundException("This book does not exist");
                });
        bookRepository.delete(book);
        return new ApiResponse(Boolean.TRUE, "You successfully deleted this book");
    }

    private BookResponse getBookResponse(BookRequest newBookRequest, Book book, BookCategory bookCategory) {
        book.setBookTitle(newBookRequest.getBookTitle());
        book.setBookAuthor(newBookRequest.getBookAuthor());
        book.setQuantity(book.getQuantity() + newBookRequest.getQuantity());
        book.setCategory(bookCategory);

        Book updatedBook = bookRepository.save(book);

        return new BookResponse(
                        updatedBook.getId(),updatedBook.getBookTitle(),
                updatedBook.getBookAuthor(),updatedBook.getQuantity(),updatedBook.getCategory().getCategory() );

    }
}
