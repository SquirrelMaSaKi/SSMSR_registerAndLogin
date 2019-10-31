package com.rj.service;

import com.rj.pojo.User;

public interface UserService {
    User findByUsername(String username);
    void add(User user);
}
