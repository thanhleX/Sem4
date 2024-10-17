package org.example.project4.WebPacket.repository;

import org.example.project4.WebPacket.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}