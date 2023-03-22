package com.task.dronetask.config;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {
    @Bean
    public SpringLiquibase dbLiquibase(DataSource secondDataSource) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(secondDataSource);
        liquibase.setChangeLog("/classpath:db/changelog/changelog.xml");
        liquibase.setContexts("drone_v1");
        return liquibase;
    }
}
