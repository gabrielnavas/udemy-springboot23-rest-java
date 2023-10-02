package com.api.controllers.helpers;

import com.api.controllers.responses.ResponseBook;
import com.api.models.Book;

import java.util.List;

public class BookToDto {
    public static ResponseBook toResponseBook(Book book) {
        return new ResponseBook(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getPrice(),
                book.getLaunchData()
        );
    }

    public static List<ResponseBook> toResponseListBook(List<Book> books) {
        return books.stream().map(BookToDto::toResponseBook).toList();
    }
}
