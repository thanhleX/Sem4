package org.example.project4.WebPacket.repository;

import org.example.project4.WebPacket.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}