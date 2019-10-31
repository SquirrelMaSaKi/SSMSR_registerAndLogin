package com.rj.service.impl;

import com.rj.dao.UserDao;
import com.rj.pojo.User;
import com.rj.service.UserService;
import com.rj.utils.Interation;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public void add(User user) {
        String originPassword = user.getPassword();
        String salt = UUID.randomUUID().toString();
        String password = new Sha256Hash(originPassword,salt, Interation.COUNTS).toBase64();
        user.setPassword(password);
        user.setSalt(salt);
        userDao.add(user);
    }
}
