package com.task.dronetask.repository;

import com.task.dronetask.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository  extends CrudRepository<User, Long> {
    //find user by username this executes an sql statement
    //note that the entity property name we wish to find  is userName and the column name must be user_name. sql interpretes column name with underscore as camel case
    Optional<User> findByUserName(String username);
}
