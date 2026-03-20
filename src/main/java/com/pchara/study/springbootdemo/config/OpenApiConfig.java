package com.pchara.study.springbootdemo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class OpenApiConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/docs").setViewName("/docs/index.html");
        registry.addViewController("/docs/").setViewName("/docs/index.html");
    }

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Spring Boot Demo API")
                        .description("Minimal Spring Boot REST API with PostgreSQL persistence")
                        .version("0.0.1")
                        .contact(new Contact()
                                .name("pchara")
                                .url("https://github.com/pchara")));
    }
}
