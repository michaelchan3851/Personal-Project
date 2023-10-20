package com.integrated.demousermanagementsystem.config;

import org.flywaydb.core.Flyway;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseMigrationRunner implements CommandLineRunner {

    private final Flyway flyway;

    public DatabaseMigrationRunner(Flyway flyway) {
        this.flyway = flyway;
    }

    @Override
    public void run(String... args) {
        flyway.migrate();
    }
}