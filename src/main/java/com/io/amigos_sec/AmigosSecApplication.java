package com.io.amigos_sec;

import com.io.amigos_sec.domain.Role;
import com.io.amigos_sec.domain.User_App;
import com.io.amigos_sec.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class AmigosSecApplication {

	public static void main(String[] args) {
		SpringApplication.run(AmigosSecApplication.class, args);
	}
/*
	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
	@Bean
	CommandLineRunner run(UserService userService){
		return args -> {
			userService.saveRole(new Role(null, "ROLE_USER"));
			userService.saveRole(new Role(null, "ROLE_MANAGER"));
			userService.saveRole(new Role(null, "ROLE_ADMIN"));
			userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));


			userService.saveUser(new User_App(null, "oussama", "ouss", "123", new ArrayList<>()));

			userService.addRoleToUser("ouss", "ROLE_USER");


		};
	}
 */
}
