package com.enigma.demospringboot.service;

import com.enigma.demospringboot.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    List<User> findAll();
    Optional<User> findById(Integer id);
    void updateById(User user);
    User create(User user);
}