package miniproject.carrotmarket1.repository;

import miniproject.carrotmarket1.dto.Category;

import java.util.List;

public interface CategoryRepository {
    // 전체 카테고리 조회
    List<Category> findAll();
    List<Category> selectByCategoryId(Long id);
    Category findById(Long categoryId);

}
