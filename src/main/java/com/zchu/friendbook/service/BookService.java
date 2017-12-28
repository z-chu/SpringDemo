package com.zchu.friendbook.service;

import com.zchu.friendbook.data.BookRepository;
import com.zchu.friendbook.vo.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> insertBooks(List<Book> books) {
        if (books != null && books.size() > 0) {
            return bookRepository.save(books);
        }
        return Collections.emptyList();
    }
}
