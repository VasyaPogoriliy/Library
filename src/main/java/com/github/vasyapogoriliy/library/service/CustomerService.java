package com.github.vasyapogoriliy.library.service;

import com.github.vasyapogoriliy.library.models.Book;
import com.github.vasyapogoriliy.library.models.Customer;
import com.github.vasyapogoriliy.library.repository.IBookRepository;
import com.github.vasyapogoriliy.library.repository.ICustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService implements ICustomerService {

    private final ICustomerRepository customerRepository;
    private final IBookRepository bookRepository;

    public CustomerService(ICustomerRepository customerRepository, IBookRepository bookRepository) {
        this.customerRepository = customerRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public boolean update(Customer customer, Long id) {
        if (customerRepository.existsById(id)) {
            customer.setId(id);
            customerRepository.save(customer);
            return true;
        }
        return false;
    }

    @Override
    public void delete(Long id) {
        Customer customer = customerRepository.getOne(id);
        List<Book> customerBooks = customer.getBooks();
        List<Book> allBooks = bookRepository.findAll();

        for (Book book : allBooks) {
            for (Book customerBook : customerBooks) {
                if (book.getId().equals(customerBook.getId())) {
                    customerBook.setTaken(false);
                    customerBook.setCustomer(null);
                    customerBooks.remove(customerBook.getId());
                }
            }
        }

        customerRepository.deleteById(id);
    }

    @Override
    public boolean takeBook(Long customerId, Long bookId) {
        Customer customer = customerRepository.getOne(customerId);
        Book newBook = bookRepository.getOne(bookId);

        if (newBook.isTaken()) {
            return false;
        }

        List<Book> books = customer.getBooks();
        books.add(newBook);

        newBook.setTaken(true);
        newBook.setCustomer(customer);
        customer.setBooks(books);
        customerRepository.save(customer);
        bookRepository.save(newBook);

        return true;
    }

    @Override
    public boolean returnBook(Long customerId, Long bookId) {
        Customer customer = customerRepository.getOne(customerId);
        Book bookToRemove = bookRepository.getOne(bookId);

        if (!bookToRemove.isTaken()) {
            return false;
        }

        List<Book> books = customer.getBooks();
        books.remove(bookToRemove);

        bookToRemove.setTaken(false);
        bookToRemove.setCustomer(null);
        customer.setBooks(books);
        customerRepository.save(customer);
        bookRepository.save(bookToRemove);

        return true;
    }

}
