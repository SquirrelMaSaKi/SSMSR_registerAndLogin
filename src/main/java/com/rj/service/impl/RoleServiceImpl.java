package com.rj.service.impl;

import com.rj.dao.RoleDao;
import com.rj.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service("roleService")
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;
    @Override
    public Set<String> findByUsername(String username) {
        return roleDao.findByUsername(username);
    }
}
