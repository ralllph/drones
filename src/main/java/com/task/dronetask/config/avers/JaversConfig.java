package com.task.dronetask.config.avers;

import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JaversConfig {
    @Bean
    public Javers javers() {
        JaversBuilder javersBuilder = JaversBuilder.javers();
        // configure Javers instance
        return javersBuilder.build();
    }

}

