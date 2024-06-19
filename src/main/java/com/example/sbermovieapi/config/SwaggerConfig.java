package com.example.sbermovieapi.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title="Movie REST API", version = "0.1",
        description = "Spring boot 3.3, h2database, springdoc"))
public class SwaggerConfig {
}
