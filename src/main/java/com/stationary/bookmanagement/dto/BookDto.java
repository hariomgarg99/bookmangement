package com.stationary.bookmanagement.dto;



import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class BookDto {

    private Long id;
    @NotEmpty(message = "name must not be empty")
    private String name;
    @Size(min = 20,max = 100)
    @NotEmpty(message = "description name must not be empty")
    private String description;
    @NotEmpty(message = "author name must not be empty")
    private String author;
    @NotEmpty(message = "type must not be empty")
    private String type;
    private Double price;
    @NotEmpty(message = "isbn must not be empty")
    @Size(min = 5,max = 5)
    private String isbn;

    private BookDto(BookDtoBuilder bookDtoBuilder){
       this.id = bookDtoBuilder.id;
       this.name = bookDtoBuilder.name;
       this.description = bookDtoBuilder.description;
       this.author = bookDtoBuilder.author;
       this.type = bookDtoBuilder.type;
       this.price = bookDtoBuilder.price;
       this.isbn = bookDtoBuilder.isbn;
    }

    public BookDto() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getAuthor() {
        return author;
    }

    public String getType() {
        return type;
    }

    public Double getPrice() {
        return price;
    }

    public String getIsbn() {
        return isbn;
    }

    @Override
    public String toString() {
        return "BookDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", author='" + author + '\'' +
                ", type='" + type + '\'' +
                ", price=" + price +
                ", isbn='" + isbn + '\'' +
                '}';
    }

    public static class BookDtoBuilder
    {

        private Long id;
        private String name;
        private String description;
        private String author;
        private String type;
        private Double price;
        private String isbn;

        public BookDtoBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public BookDtoBuilder name(String name) {
            this.name = name;
            return this;
        }

        public BookDtoBuilder description(String description) {
            this.description = description;
            return this;
        }

        public BookDtoBuilder author(String author) {
            this.author = author;
            return this;
        }

        public BookDtoBuilder type(String type) {
            this.type = type;
            return this;
        }

        public BookDtoBuilder price(Double price) {
            this.price = price;
            return this;
        }

        public BookDtoBuilder isbn(String isbn) {
            this.isbn = isbn;
            return this;
        }

        public BookDto build(){
            BookDto bookDto = new BookDto(this);
           return bookDto;
        }
    }
}
