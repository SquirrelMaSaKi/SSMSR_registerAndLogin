package com.rj.service;

import java.util.Set;

public interface RoleService {
    Set<String> findByUsername(String username);
}
