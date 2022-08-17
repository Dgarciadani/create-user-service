package com.grego.userservice.repository;

import com.grego.userservice.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

    public User findUserById(UUID id);

    public boolean existsByEmail(String email);

    Optional<User> findUserByEmail(String email);

}
