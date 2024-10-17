package org.example.project4.WebPacket.Domain.repository;

import org.example.project4.WebPacket.Domain.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}