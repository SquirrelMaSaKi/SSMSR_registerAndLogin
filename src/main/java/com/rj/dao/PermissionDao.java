package com.rj.dao;

import org.apache.ibatis.annotations.Param;

import java.util.Set;

public interface PermissionDao {
    Set<String> findByUsername(@Param("username") String username);
}
