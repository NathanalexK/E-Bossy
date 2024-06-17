package com.project.ebossy.util;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Files;
import java.nio.file.Paths;

@Configuration
public class UploadConfig {
    @Bean
    CommandLineRunner init() {
        return args -> {
            Files.createDirectories(Paths.get("uploads/image/eleve"));
        };
    }
}
