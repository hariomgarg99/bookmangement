package com.stationary.bookmanagement.util;

import com.stationary.bookmanagement.dto.BookDto;
import com.stationary.bookmanagement.dto.BookTypeEnum;


public class BookTest {

    public static BookDto getBook() {
        BookDto bookDto = new BookDto.BookDtoBuilder()
                .id(1L)
                .name("JAVA")
                .author("Hariom")
                .description("this book is for learn basic java concept")
                .isbn("13443")
                .price(105.00)
                .type(BookTypeEnum.JAVA.name()).build();
        return bookDto;
    }
}
