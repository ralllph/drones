package com.task.dronetask.security.manager;

import com.task.dronetask.security.filter.AuthenticationFilter;
import com.task.dronetask.security.filter.CheckExceptionFilter;
import com.task.dronetask.security.filter.JWTAuthorizationFilter;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.Check;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@AllArgsConstructor
public class SecurityConfig {
    private CustomAuthenticationManager authenticationManager;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        //create new authentication filter
        AuthenticationFilter authenticationFilter = new AuthenticationFilter(authenticationManager);
        // we want /authenticate to be processed by Auth filter
        authenticationFilter.setFilterProcessesUrl("/authenticate");
        http//means any http request goes through this chain
                .headers().frameOptions().disable() // This allows h2 console which runs on html frames work well
                .and()
                .csrf().disable()
                .authorizeHttpRequests()//who has access to what request based on authorization
                .antMatchers(HttpMethod.POST, SecurityConstants.REGISTER_PATH).permitAll()// Here we allow register with post request without authentication
                .antMatchers("/h2/**").permitAll() // allows you view h2 database without having to authenticate
                .antMatchers("/v3/**").permitAll()//allow any request to /v3/api-docs
                .antMatchers("/swagger-ui/**").permitAll() //allow request to /swagger-ui
                .anyRequest().authenticated()//any request made must be authenticated
                .and()
                .addFilterBefore(new CheckExceptionFilter(), AuthenticationFilter.class)//here we say this custom created filter should run before the authentication filter. Go to authentication filter after
                .addFilter(authenticationFilter) // requests routed to /authenticate n would be handled by authentication filter
                .addFilterAfter(new JWTAuthorizationFilter(), AuthenticationFilter.class) //thi is saying the jwtauthorization filter should come after the authentication filter class
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);//prevents postman from using sessions on request
        //builds object then registers it as a bean
        return http.build();
    }
}
