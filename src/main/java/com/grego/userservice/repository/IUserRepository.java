package com.grego.userservice.repository;

import com.grego.userservice.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.Optional;
import java.util.UUID;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

    public User findUserById(UUID id);

    public boolean existsByEmail(String email);

    Optional<User> findUserByEmail(String email);



    @Modifying
    @Query(value = "UPDATE users SET users.token = ?2 WHERE users.email = ?1" , nativeQuery = true)
    public void updateUserTokenByEmail(String email, String token);

}
