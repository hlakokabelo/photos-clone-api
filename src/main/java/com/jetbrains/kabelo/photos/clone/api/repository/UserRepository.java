package com.jetbrains.kabelo.photos.clone.api.repository;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import com.jetbrains.kabelo.photos.clone.api.model.User;

public interface UserRepository
        extends CrudRepository<User, Long> {

    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);
}