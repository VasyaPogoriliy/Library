package com.github.vasyapogoriliy.library.repository;

import com.github.vasyapogoriliy.library.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBookRepository extends JpaRepository<Book, Long> {
}
