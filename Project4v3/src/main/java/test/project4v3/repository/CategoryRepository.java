package test.project4v3.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import test.project4v3.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}