package com.io.amigos_sec.service;

import com.io.amigos_sec.domain.Role;
import com.io.amigos_sec.domain.User_App;

import java.util.List;

public interface UserService {
    User_App saveUser(User_App user);
    Role saveRole(Role role);
    List<User_App> getUsers();
    User_App getUser(String username);
    void addRoleToUser(String username, String roleName);
}
