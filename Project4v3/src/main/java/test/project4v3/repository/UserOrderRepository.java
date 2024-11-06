package test.project4v3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import test.project4v3.dto.UserOrderDTO;
import test.project4v3.entity.Oders.OrderUser;
import test.project4v3.entity.User;


import java.util.List;

public interface UserOrderRepository extends JpaRepository<OrderUser, User> {
    List<UserOrderDTO> findByUserId(User userId);
}
