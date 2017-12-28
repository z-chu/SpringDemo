package com.zchu.friendbook.controller;

import com.zchu.friendbook.data.BookRepository;
import com.zchu.friendbook.service.BookService;
import com.zchu.friendbook.vo.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Transient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookRepository bookRepository;

    private final BookService bookService;

    @Autowired
    public BookController(BookRepository bookRepository, BookService bookService) {
        this.bookRepository = bookRepository;
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> listBook() {
        return bookRepository.findAll();
    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable("id") Long id) {
        return bookRepository.findOne(id);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable("id") Long id) {
        bookRepository.delete(id);
    }

    @PutMapping("/{id}")
    public Book putBook(@PathVariable("id") Long id, @RequestBody Book book) {
        book.setId(id);
        return bookRepository.save(book);
    }

    @PostMapping
    public Book addBook(@Valid @RequestBody Book book, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            System.out.println(bindingResult.getFieldError().getDefaultMessage());
            return null;
        }
        return bookRepository.save(book);
    }

    @PostMapping("/list")
    public List<Book> addBook(@RequestBody List<Book> book) {
       return bookService.insertBooks(book);
    }

}
