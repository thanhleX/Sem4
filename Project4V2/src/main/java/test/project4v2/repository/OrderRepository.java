package test.project4v2.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import test.project4v2.entity.Oders.OrderEntity;
import test.project4v2.entity.User;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    OrderEntity findByUserId(User userId);
}