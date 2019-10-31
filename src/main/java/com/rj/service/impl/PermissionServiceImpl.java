package com.rj.service.impl;

import com.rj.dao.PermissionDao;
import com.rj.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionDao permissionDao;
    @Override
    public Set<String> findByUsername(String username) {
        return permissionDao.findByUsername(username);
    }
}
