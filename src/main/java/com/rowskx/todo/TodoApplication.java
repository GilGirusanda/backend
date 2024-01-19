package com.rowskx.todo;

import java.util.Objects;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.rowskx.todo.models.Role;
import com.rowskx.todo.models.enums.ERole;
import com.rowskx.todo.repositories.RoleRepository;

@SpringBootApplication
public class TodoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoApplication.class, args);
	}

	@Bean
	CommandLineRunner myCommandLineRunner(RoleRepository roleRepository) {
		return (args) -> {

			if (!roleRepository.findByName(ERole.ROLE_USER).isPresent()) {
				System.out.println("Adding basic roles");
				roleRepository.save(new Role("ROLE_USER"));
			}
		};
	}
}
