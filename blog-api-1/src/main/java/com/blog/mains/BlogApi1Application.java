package com.blog.mains;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
//import io.swagger.v3.oas.annotations.tags.Tag;


@SpringBootApplication
@OpenAPIDefinition(
		info = @Info
		(
					title = "Blogging Api",
					description = "A comprehensive API for managing blog posts, users, and comments",
					
					contact = @Contact
					(
							name = "Manish Sharma",
							url = "localhost:8080/swagger-ui.html",
							email = "Mani.shiv21@gmail.com"
					),
					license = @License
					(
							name = "Term & Condition available",
							url = "https://github.com/ms5977?tab=repositories"
					)
					
		),
//					servers = @Server
//					(
////							url = "http://localhost:8080",
//							description = "Local Server"
//					),
//					tags = {
//							@Tag(name = "user",description = "Operation Related to User"),
//							@Tag(name = "Public urls" ,description = "For creating a user and Its a Public URL annyone can access for register themseleves")
//					}
				security = @SecurityRequirement(name = "Blogging Api Security")
					
	
		)


@SecurityScheme
(
		name = "Blogging Api Security",
		in = SecuritySchemeIn.HEADER,
		type = SecuritySchemeType.HTTP,
		scheme = "Bearer",
		bearerFormat = "jwt"
		
)

public class BlogApi1Application  implements CommandLineRunner {
	
	@Autowired
	PasswordEncoder passwordEncoder;
	public static void main(String[] args) {
		SpringApplication.run(BlogApi1Application.class, args);
	}
	@Bean
	 ModelMapper modelMapper()
	{
		return new ModelMapper();
	}
	@Override
	public void run(String... args) throws Exception {
			System.out.println(this.passwordEncoder.encode("Rajesh@123"));
			System.out.println(this.passwordEncoder.encode("Vikram@123"));
//			roleService.savedRoles();
			
		
	}
	

}
