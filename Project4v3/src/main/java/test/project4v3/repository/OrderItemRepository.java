package test.project4v3.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import test.project4v3.entity.Oders.OrderItems;


public interface OrderItemRepository extends JpaRepository<OrderItems, Long> {
}