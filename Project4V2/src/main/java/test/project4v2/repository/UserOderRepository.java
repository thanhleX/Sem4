package test.project4v2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import test.project4v2.dto.UserOrderDTO;

import java.util.List;

public interface UserOderRepository extends JpaRepository<UserOrderDTO, Long> {
    List<UserOrderDTO> findByUserId(Long userId);
}
