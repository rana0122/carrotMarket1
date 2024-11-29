package miniproject.carrotmarket1.service;

import miniproject.carrotmarket1.entity.Category;
import miniproject.carrotmarket1.repository.CategoryRepository;
import miniproject.carrotmarket1.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    public Category findById(Long categoryId) {
        return categoryRepository.findById(categoryId);
    }

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public List<Category> selectByCategoryId(Long id) {
        return categoryRepository.selectByCategoryId(id);
    }
}
