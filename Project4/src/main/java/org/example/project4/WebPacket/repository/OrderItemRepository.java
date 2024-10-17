package org.example.project4.WebPacket.repository;

import org.example.project4.WebPacket.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}