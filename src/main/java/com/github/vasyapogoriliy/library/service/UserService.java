package com.github.vasyapogoriliy.library.service;

import com.github.vasyapogoriliy.library.models.User;
import com.github.vasyapogoriliy.library.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    IUserRepository userRepository;

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getById(Long id) {
        return userRepository.getOne(id);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void update(User updatedUser, Long id) {
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
