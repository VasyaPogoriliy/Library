package com.github.vasyapogoriliy.library.rest;

import com.github.vasyapogoriliy.library.models.Book;
import com.github.vasyapogoriliy.library.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("")
    public ResponseEntity<List<Book>> getAllUsers() {
        List<Book> books = bookService.getAll();

        if (books.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getUser(@PathVariable("id") Long id) {
        if (id == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Book book = bookService.getById(id);

        if (book == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<Book> saveUser(@RequestBody @Valid Book book) {
        if (book == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        bookService.save(book);

        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Book> updateUser(@PathVariable("id") Long id,
                                           @RequestBody @Valid Book book) {
        if (book == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        if(!bookService.update(book, id)) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Book> deleteUser(@PathVariable("id") Long id) {
        if (bookService.getById(id) == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        bookService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
