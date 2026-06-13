package com.abhishek.enterprise_inventory_system.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.info(new Info().title("EnterPrise Inventory System")
						.version("1.0")
						.description("Spring Boot Backend project"));
	}
}
