package com.api.modules.book.controllers.helpers;

import com.api.modules.book.controllers.responses.ResponseBook;
import com.api.modules.book.models.Book;

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
