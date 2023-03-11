package com.codewithchristian.libraryapi.repositories;

import com.codewithchristian.libraryapi.models.BookCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookCategoryRepository extends JpaRepository<BookCategory, Long> {

}
