package com.laurabailie.transactionservice;

import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;

@Configuration
public class FlywayConfig {

    @Bean(initMethod = "migrate")
    public Flyway flyway(DataSource dataSource) {
        return Flyway.configure()
                .dataSource(dataSource)
                .locations("classpath:db/migration")  // your path
                .baselineOnMigrate(true)
                .validateOnMigrate(false)  // optional for first run
                .outOfOrder(true)  // optional for dev testing
                .load();
    }
}