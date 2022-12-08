package com.task.dronetask.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig{
    //create openApi bean
    @Bean
    public OpenAPI openAPI(){
        //link to documentation https://springdoc.org/#migrating-from-springfox check migrating from spring fox
        //we are trying to configure the swagger to carry our own api details
        return new OpenAPI()
                .info(new Info()
                        .title("Drone API")
                        .description("API to manage drone activities")
                        .version("v1.0"));
    }
}
