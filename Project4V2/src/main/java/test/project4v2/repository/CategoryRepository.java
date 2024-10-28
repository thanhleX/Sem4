package test.project4v2.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import test.project4v2.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}