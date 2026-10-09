package com.jetbrains.kabelo.photos.clone.repository;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import com.jetbrains.kabelo.photos.clone.model.User;

public interface UserRepository
        extends CrudRepository<User, Long> {

         
    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);
}