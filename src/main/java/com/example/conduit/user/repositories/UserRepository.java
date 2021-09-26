package com.example.conduit.user.repositories;

import com.example.conduit.user.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByProfileUsername(@Param("username") String username);
    User findUserByEmail(@Param("email") String email);
}
