package com.github.vasyapogoriliy.library.repository;

import com.github.vasyapogoriliy.library.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Long> {
}
