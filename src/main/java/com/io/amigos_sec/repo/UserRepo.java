package com.io.amigos_sec.repo;

import com.io.amigos_sec.domain.User_App;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepo extends JpaRepository<User_App, Long> {
    User_App findByUsername(String username);
}
