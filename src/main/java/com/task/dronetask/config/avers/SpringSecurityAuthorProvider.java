package com.task.dronetask.config.avers;

import com.task.dronetask.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.javers.spring.auditable.AuthorProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

@Configuration
@Slf4j
public class SpringSecurityAuthorProvider  implements AuthorProvider{

    @Override
    public String provide() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            log.info("Authentication object: {}", authentication);
            if (authentication != null){
                Object principal = authentication.getPrincipal();
                if (principal instanceof User){
                    return authentication.getName();
                } else if (principal instanceof String) {
                    return (String) principal;
                }
                else if (principal instanceof UserDto) {
                    UserDto currentUserDto = (UserDto) principal;
                    return currentUserDto.getUserName();
                } else if (principal instanceof com.task.dronetask.entity.User) {
                    com.task.dronetask.entity.User currentUser = (com.task.dronetask.entity.User) principal;
                    return currentUser.getUserName();
                }
            }
            return "SYSTEM";
        } catch (Exception ex) {
            log.error("Error getting current user", ex);
            return "SYSTEM";
        }
    }
}
