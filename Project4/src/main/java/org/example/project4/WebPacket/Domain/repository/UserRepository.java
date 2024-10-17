package org.example.project4.WebPacket.Domain.repository;

import org.example.project4.WebPacket.Domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(String username);

    User findByUsername(String username);
}