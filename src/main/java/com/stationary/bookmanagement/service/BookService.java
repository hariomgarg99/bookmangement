package com.stationary.bookmanagement.service;

import com.stationary.bookmanagement.config.DtoMapperConfig;
import com.stationary.bookmanagement.dto.BookDto;
import com.stationary.bookmanagement.entity.Book;
import com.stationary.bookmanagement.repository.BooksRepository;
import com.stationary.bookmanagement.dto.BookTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class BookService {

    @Autowired
    BooksRepository booksRepository;

    @Autowired
    DtoMapperConfig dtoMapperConfig;

    public Long saveBooks(BookDto bookDto) {
        if (bookDto.getId() != null) {
            Optional<Book> entity = booksRepository.findById(bookDto.getId());
            entity.ifPresent(book1 -> {
                throw new RuntimeException("Book id is already present");
            });
        }
        Book book = dtoMapperConfig.modelMapper().map(bookDto, Book.class);
        Book savedBook = booksRepository.save(book);
        return savedBook.getId();
    }

    public List<Book> getBooks() {
        return booksRepository.findAll();
    }

    public void update(long bookId, BookDto bookDto) {
        Book entity = booksRepository.findById(bookId).get();
        entity.setAuthor(bookDto.getAuthor());
        entity.setDescription(bookDto.getDescription());
        entity.setName(bookDto.getName());
        entity.setPrice(bookDto.getPrice());
        entity.setType(bookDto.getType());
        entity.setIsbn(bookDto.getIsbn());
        booksRepository.save(entity);
    }

    public Optional<Book> get(long id) {
        return booksRepository.findById(id);
    }

    public void delete(long id) {
        Book book = booksRepository.findById(id).get();
        booksRepository.delete(book);
    }

    public Double getTotalPrice(List<Long> bookIds, String promotionCode) {
        AtomicReference<Double> totalPrice = new AtomicReference<>(0.0);
        bookIds.stream().forEach(id -> {
            booksRepository.findById(id).ifPresent(book -> {
                if (null != promotionCode && !promotionCode.isEmpty()) {
                    totalPrice.updateAndGet(price -> price + (book.getPrice() - getDiscount(book)));
                } else {
                    totalPrice.updateAndGet(price -> price + book.getPrice());
                }

            });
        });
        return totalPrice.get();
    }

    private double getDiscount(Book book) {
        Integer  discountRange =  BookTypeEnum.valueOf(book.getType()).getDiscount();
        return  (book.getPrice() * discountRange)/100;
    }
}
