package com.stationary.bookmanagement.repository;

import com.stationary.bookmanagement.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BooksRepository extends JpaRepository<Book,Long> {
}
