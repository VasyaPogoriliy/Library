package com.github.vasyapogoriliy.library.repository;

import com.github.vasyapogoriliy.library.models.Book;
import com.github.vasyapogoriliy.library.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Long> {
}
