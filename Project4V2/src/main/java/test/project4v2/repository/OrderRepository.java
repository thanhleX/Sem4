package test.project4v2.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import test.project4v2.entity.Oders.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}