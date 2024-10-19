package test.project4v2.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import test.project4v2.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(String username);

    User findByUsername(String username);
}