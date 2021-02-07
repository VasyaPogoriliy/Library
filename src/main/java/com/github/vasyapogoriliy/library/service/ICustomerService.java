package com.github.vasyapogoriliy.library.service;

import com.github.vasyapogoriliy.library.models.Customer;

import java.util.List;

public interface ICustomerService {

    List<Customer> getAll();

    Customer getById(Long id);

    void save(Customer customer);

    boolean update(Customer customer, Long id);

    void delete(Long id);

    boolean takeBook(Long customerId, Long bookId);

    boolean returnBook(Long customerId, Long bookId);
}
