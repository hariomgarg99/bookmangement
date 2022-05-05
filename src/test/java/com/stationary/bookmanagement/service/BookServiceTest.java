package com.stationary.bookmanagement.service;

import com.stationary.bookmanagement.config.DtoMapperConfig;
import com.stationary.bookmanagement.dto.BookDto;
import com.stationary.bookmanagement.dto.BookTypeEnum;
import com.stationary.bookmanagement.entity.Book;
import com.stationary.bookmanagement.repository.BooksRepository;
import com.stationary.bookmanagement.util.BookTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.modelmapper.ModelMapper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class BookServiceTest {

    private final Long id = 1234L;
    private final int price = 250;

    @Mock
    private BooksRepository bookRepository;

    @Mock
    private DtoMapperConfig dtoMapperConfig;

    @Mock
    ModelMapper modelMapper;

    @InjectMocks
    private BookService bookService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddNewBook() {
        BookDto bookDto = BookTest.getBook();
        Book book = new Book(1L,BookTypeEnum.JAVA.name(),"Hariom","this book is for learn basic java concept",
                BookTypeEnum.JAVA.name(),78.4,"134234");
        Mockito.when(dtoMapperConfig.modelMapper()).thenReturn(modelMapper);
        Mockito.when(bookRepository.findById(id)).thenReturn(Optional.empty());
        Mockito.when(bookRepository.save(book)).thenReturn(book);
        Mockito.when(dtoMapperConfig.modelMapper().map(bookDto, Book.class)).thenReturn(book);
         bookService.saveBooks(bookDto);
        Mockito.verify(bookRepository).save(book);
    }

    @Test
    public void testGetAllBooks() {
        Book book = new Book(1L,BookTypeEnum.JAVA.name(),"Hariom","this book is for learn basic java concept",
                "Tech",78.4,"134234");
        List<Book> bookList = new ArrayList<>();
        bookList.add(book);
        BookDto bookDto = BookTest.getBook();
        Mockito.when(dtoMapperConfig.modelMapper()).thenReturn(modelMapper);
        Mockito.when(bookRepository.findAll()).thenReturn(bookList);
        Mockito.when(dtoMapperConfig.modelMapper().map(book, BookDto.class)).thenReturn(bookDto);
        List<Book> actualBookDto = bookService.getBooks();
        Assertions.assertEquals(bookList, actualBookDto);
    }

    @Test
    public void testUpdateBook() {
        BookDto bookDto = BookTest.getBook();
        Book book = new Book(1L,BookTypeEnum.JAVA.name(),"Hariom","this book is for learn basic java concept",
                "Tech",78.4,"134234");
        Mockito.when(dtoMapperConfig.modelMapper()).thenReturn(modelMapper);
        Mockito.when(dtoMapperConfig.modelMapper().map(bookDto, Book.class)).thenReturn(book);
        Book book1 = new Book(1L,BookTypeEnum.JAVA.name(),"Hariom","this book is for learn basic java concept",
                "Tech",78.4,"134234");
        Mockito.when(bookRepository.findById(id)).thenReturn(Optional.of(book1));
        bookRepository.save(book);
        bookService.update(id, bookDto);
        Mockito.verify(bookRepository).save(book);
    }

    @Test
    public void testDeleteBook() {
        Book book = new Book(1L,BookTypeEnum.JAVA.name(),"Hariom","this book is for learn basic java concept",
                "Tech",89.5,"134234");
        Mockito.when(bookRepository.findById(id)).thenReturn(Optional.of(book));
        bookRepository.deleteById(id);
        bookService.delete(id);
        Mockito.verify(bookRepository).deleteById(id);
    }

    @Test void getTotalPriceTestWithDiscount(){
        Book book = new Book(1L,BookTypeEnum.JAVA.name(),"Hariom","this book is for learn basic java concept",
                "FICTION",60.0,"134234");
        Book book1 = new Book(1L,BookTypeEnum.JAVA.name(),"Hariom","this book is for learn basic java concept",
                "JAVA",90.0,"134234");
        List<Long> bookIdsList = Arrays.asList(1L,2L,4L);
        Mockito.when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        Mockito.when(bookRepository.findById(2L)).thenReturn(Optional.of(book1));
        Double price = bookService.getTotalPrice(bookIdsList,"ABC");
        Assertions.assertEquals(price,132.00);
    }

    @Test void getTotalPriceTestWithoutDiscount(){
        Book book = new Book(1L,BookTypeEnum.JAVA.name(),"Hariom","this book is for learn basic java concept",
                "FICTION",60.0,"134234");
        Book book1 = new Book(1L,BookTypeEnum.JAVA.name(),"Hariom","this book is for learn basic java concept",
                "JAVA",90.0,"134234");
        List<Long> bookIdsList = Arrays.asList(1L,2L,4L);
        Mockito.when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        Mockito.when(bookRepository.findById(2L)).thenReturn(Optional.of(book1));
        Double price = bookService.getTotalPrice(bookIdsList,"");
        Assertions.assertEquals(price,150.00);
    }



}
