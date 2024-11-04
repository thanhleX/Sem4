package test.project4v2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import test.project4v2.dto.UserOrderDTO;
import test.project4v2.entity.Oders.OrderUser;
import test.project4v2.entity.User;

import java.util.List;

public interface UserOrderRepository extends JpaRepository<OrderUser, User> {
    List<UserOrderDTO> findByUserId(User userId);
}
