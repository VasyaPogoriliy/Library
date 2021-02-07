package com.github.vasyapogoriliy.library.service;

import com.github.vasyapogoriliy.library.models.Book;

import java.util.List;

public interface IBookService {

    List<Book> getAll();

    Book getById(Long id);

    void save(Book book);

    boolean update(Book book, Long id);

    void delete(Long id);

}
