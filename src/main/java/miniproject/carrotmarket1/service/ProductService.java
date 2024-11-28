package miniproject.carrotmarket1.service;

import miniproject.carrotmarket1.dao.MySQL.ProductDAO;
import miniproject.carrotmarket1.entity.Product;
import miniproject.carrotmarket1.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

   @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    //게시글 전체 목록 조회
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    //판매중인 상품에 대한 게시글 목록 조회
    public List<Product> findAvailableItems() {
        return productRepository.findAvailableItems();
    }

    //xml 연동 테스트
    public  List<Product> findAvailableItemsByCategory(Long category) {
       return  productRepository.findProductsByConditions(category);
    }

    //ID로 상품 상세 조회
    public Product findItemById(Long id){
        return productRepository.findById(id);
    }
}
