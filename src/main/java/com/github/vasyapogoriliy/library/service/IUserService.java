package com.github.vasyapogoriliy.library.service;

import com.github.vasyapogoriliy.library.models.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IUserService {

    List<User> getAll();

    User getById(Long id);

    void save(User user);

    @Modifying
    @Query("UPDATE User user SET user=?1 WHERE user.id=?2")
    void update(User user, Long id);

    void delete(Long id);


}
