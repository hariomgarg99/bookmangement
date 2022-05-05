package com.stationary.bookmanagement.controller;

import com.stationary.bookmanagement.dto.BookDto;
import com.stationary.bookmanagement.dto.BookTypeEnum;
import com.stationary.bookmanagement.entity.Book;
import com.stationary.bookmanagement.service.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
@Validated
@Api(tags = "hello")
public class BooksController {

    @Autowired
    BookService bookService;

    @PostMapping
    @ApiOperation(value = "sfdf", nickname = "hello")
    public ResponseEntity saveBooks(@Valid @RequestBody BookDto book) {
        try {
            BookTypeEnum.valueOf(book.getType());
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Type enum is not match");
        }
        bookService.saveBooks(book);
        return ResponseEntity.status(HttpStatus.OK).body("Book has been saved successfully");

    }

    @GetMapping
    public ResponseEntity getBookList() {
        List<Book> books = bookService.getBooks();
        if(books.isEmpty()){
            return ResponseEntity.ok().body("No Book found");
        }
        return ResponseEntity.ok().body(books);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") Long bookId, @Valid @RequestBody BookDto book) {
        try {
            BookTypeEnum.valueOf(book.getType());
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Type enum is not match");
        }
        Optional<Book> entity = bookService.get(bookId);
        if (entity.isPresent()) {
            bookService.update(bookId, book);
            return ResponseEntity.ok().body("Book has been updated successfully.");
        } else {
            return ResponseEntity.ok().body("Book id is not found");
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") long id) {
        Optional<Book> book = bookService.get(id);
        return book.isPresent() ? ResponseEntity.ok().body(book.get()) : ResponseEntity.notFound().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long bookId) {
        Optional<Book> book = bookService.get(bookId);
        if (book.isPresent()) {
            bookService.delete(bookId);
            return ResponseEntity.ok().body("Book has been deleted successfully.");
        } else {
            return ResponseEntity.ok().body("Book id is not found");
        }
    }

    @GetMapping("/total_price")
    public ResponseEntity getTotalPrice(@RequestParam("books") List<Long> bookIds,
                                        @RequestParam(value = "promotionCode",required = false) String promotionCode) {
        return ResponseEntity.ok().body("Total price is " + bookService.getTotalPrice(bookIds, promotionCode));
    }

}
