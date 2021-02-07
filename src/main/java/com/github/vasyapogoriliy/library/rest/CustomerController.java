package com.github.vasyapogoriliy.library.rest;

import com.github.vasyapogoriliy.library.models.Customer;
import com.github.vasyapogoriliy.library.service.BookService;
import com.github.vasyapogoriliy.library.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;
    private final BookService bookService;

    public CustomerController(CustomerService customerService, BookService bookService) {
        this.customerService = customerService;
        this.bookService = bookService;
    }

    @GetMapping("")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerService.getAll();

        if (customers.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable("id") Long id) {
        if (id == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Customer customer = customerService.getById(id);

        if (customer == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<Customer> saveCustomer(@RequestBody @Valid Customer customer) {
        if (customer == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        customerService.save(customer);

        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable("id") Long id,
                                                   @RequestBody @Valid Customer customer) {
        if (customer == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        if(!customerService.update(customer, id)) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        //customerService.update(customer, id);

        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable("id") Long id) {
        if (customerService.getById(id) == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        customerService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{customerId}/take/{bookId}")
    public ResponseEntity<Customer> takeBook(@PathVariable("customerId") Long customerId,
                                             @PathVariable("bookId") Long bookId) {
        if (customerService.getById(customerId) == null || bookService.getById(bookId) == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        if (!customerService.takeBook(customerId, bookId)) return new ResponseEntity<>(HttpStatus.IM_USED);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{customerId}/return/{bookId}")
    public ResponseEntity<Customer> returnBook(@PathVariable("customerId") Long customerId,
                                               @PathVariable("bookId") Long bookId) {
        if (customerService.getById(customerId) == null || bookService.getById(bookId) == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        if (!customerService.returnBook(customerId, bookId)) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
