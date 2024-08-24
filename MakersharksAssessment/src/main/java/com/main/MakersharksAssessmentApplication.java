package com.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info
		(
					title = "Maker Sharks Task Api",
					description = "A comprehensive API for Suppliers",
					
					contact = @Contact
					(
							name = "Manish Sharma",
							url = "https://www.linkedin.com/in/manish-sharma-6ba2b024a/",
							email = "Mani.shiv21@gmail.com"
					),
					license = @License
					(
							name = "Term & Condition available",
							url = "https://github.com/ms5977?tab=repositories"
					)
					
					
		),
		security = @SecurityRequirement(name = "MakerShark Authentication")
)
@SecurityScheme(
		name = "MakerShark Authentication",
		type = SecuritySchemeType.HTTP,
		scheme = "basic"
	)
public class MakersharksAssessmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(MakersharksAssessmentApplication.class, args);
	}

}
