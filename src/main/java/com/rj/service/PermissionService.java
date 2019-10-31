package com.rj.service;

import java.util.Set;

public interface PermissionService {
    Set<String> findByUsername(String username);
}
