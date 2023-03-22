package com.task.dronetask.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.sql.DataSource;

@Configuration
//property source points to where to pick the configurations
@PropertySource({"classpath:application.properties"})
@EnableJpaRepositories(
        basePackages = "com.task.dronetask.repository",
        entityManagerFactoryRef = "entityManagerFactory",
        transactionManagerRef = "transactionManager")
public class PersistenceDroneAutoConfiguration {
    @Primary
    @Bean
    @ConfigurationProperties(prefix="spring.datasource")
    public DataSource firstDroneDataSource() {
        return DataSourceBuilder.create().build();
    }

}
