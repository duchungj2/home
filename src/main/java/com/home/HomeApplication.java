package com.home;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.home.entity.RoleEntity;
import com.home.entity.UserEntity;
import com.home.repository.RoleRepository;

@SpringBootApplication
public class HomeApplication implements CommandLineRunner {
	
	@Autowired
	private RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(HomeApplication.class, args);
	}
	
	@Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

	@Override
	public void run(String... args) throws Exception {
//		initRole();
	}

//	public void initRole() {
//		RoleEntity roleAdmin = new RoleEntity();
//		roleAdmin.setName("Admin");
//		RoleEntity roleUser = new RoleEntity();
//		roleUser.setName("User");
//		
//		roleRepository.saveAll(List.of(roleAdmin, roleUser));
//	}

}
