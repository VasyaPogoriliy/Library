package com.github.vasyapogoriliy.library.repository;

import com.github.vasyapogoriliy.library.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Long> {

}
