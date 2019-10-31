package com.rj.dao;

import com.rj.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserDao {
    User findByUsername(@Param("username") String username);
    void add(User user);
}
