package com.productservices;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProductServicesApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductServicesApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper(){

        return new ModelMapper();
    }

}
