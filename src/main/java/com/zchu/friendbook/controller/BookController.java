package com.zchu.friendbook.controller;

import com.zchu.friendbook.dao.BookRepository;
import com.zchu.friendbook.service.BookService;
import com.zchu.friendbook.domain.Book;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.ws.http.HTTPException;
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

    @ApiOperation(value="获取书籍列表", notes="")
    @GetMapping
    public List<Book> listBook() {
        return bookRepository.findAll();
    }

    @ApiOperation(value="获取书籍详细信息", notes="根据url的id来获取用户详细信息")
    @ApiImplicitParam(name = "id", value = "书籍ID", required = true, dataType = "Long")
    @GetMapping("/{id}")
    public Book getBook(@PathVariable("id") Long id) {
        return bookRepository.findOne(id);
    }


    @ApiOperation(value="删除书籍", notes="根据url的id来删除信息")
    @ApiImplicitParam(name = "id", value = "书籍ID", required = true, dataType = "Long")
    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable("id") Long id) {
        bookRepository.delete(id);
    }

    @PutMapping("/{id}")
    public Book putBook(@PathVariable("id") Long id, @RequestBody Book book) {
        book.setId(id);
        return bookRepository.save(book);
    }

    @ApiOperation(value="创建书籍", notes="根据Book对象创建书籍")
    @ApiImplicitParam(name = "book", value = "用户详细实体book", required = true, dataType = "Book")
    @PostMapping
    public Book addBook(@Valid @RequestBody Book book, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            System.out.println(bindingResult.getFieldError().getDefaultMessage());
            throw new HTTPException(400);
        }
        return bookRepository.save(book);
    }

    @PostMapping("/list")
    public List<Book> addBook(@RequestBody List<Book> book) {
       return bookService.insertBooks(book);
    }

}
