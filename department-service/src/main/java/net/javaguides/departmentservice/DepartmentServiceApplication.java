package net.javaguides.departmentservice;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@OpenAPIDefinition(
        info = @Info(
                title = "Department Service REST APIs",
                description = "Department Service REST APIs Documentation",
                version = "v1.0",
                contact = @Contact(
                        name = "Andrew",
                        email = "anhnguyen.052003@gmail.com",
                        url = "https://www.andrewnguyen.vn"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "https://www.andrewnguyen.vn"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "Department-Service Doc",
                url = "https://www.andrewnguyen.vn"
        )
)


@SpringBootApplication

public class DepartmentServiceApplication {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    public static void main(String[] args) {
        SpringApplication.run(DepartmentServiceApplication.class, args);
    }

}
