package com.fazo.esm.service;

import com.fazo.esm.entity.User;

import java.util.List;

public interface UserService {
    User findByUsername(String username);

    List<User> findAll();

    void delete(int id);
}
