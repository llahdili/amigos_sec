package com.io.amigos_sec.service;

import com.io.amigos_sec.domain.Role;
import com.io.amigos_sec.domain.User_App;
import com.io.amigos_sec.repo.RoleRepo;
import com.io.amigos_sec.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor @Transactional @Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {
    private final RoleRepo roleRepo;
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User_App user = userRepo.findByUsername(username);
        if (username==null){
            log.error("Username not found ! ");
            throw new UsernameNotFoundException("Username not found");
        }else {
            log.info("Username found in the database! {}", username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
         return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
    @Override
    public User_App saveUser(User_App user) {
        log.info("******************* saving User {} *******************" , user.getUsername());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("******************* saving role {} *******************", role.getName());
        return roleRepo.save(role);
    }

    @Override
    public List<User_App> getUsers() {
        log.info("******************* finding all the Users  *******************" );
        return userRepo.findAll();
    }

    @Override
    public User_App getUser(String username) {
        log.info("******************* finding one User {} *******************", username);
        return userRepo.findByUsername(username);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("******************* Giving the role {} to the username {} *******************", " # "+roleName+ " #", " # "+ username +" #");
        User_App user= userRepo.findByUsername(username);
        Role role = roleRepo.findByName(roleName);
        //we user the @Transactional annotation so this will auto save in DB!
        user.getRoles().add(role);
    }


}
