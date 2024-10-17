package org.example.project4.WebPacket.Domain.repository;

import org.example.project4.WebPacket.Domain.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}