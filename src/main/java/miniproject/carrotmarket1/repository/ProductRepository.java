package miniproject.carrotmarket1.repository;

import miniproject.carrotmarket1.entity.Product;

import java.util.List;

public interface ProductRepository {
    // 게시글 전체 목록 조회
    List<Product> findAll();

    // 판매중인 상품에 대한 게시글 목록 조회
    List<Product> findAvailableItems();

    //xml 연동 테스트
    List<Product> findProductsByConditions(Long category);

    //ID로 상품 상세 조회
    Product findById(Long productId);

    //게시글 생성
    void insertProduct(Product product);
    
    //게시글 수정
    void updateProduct(Product existingProduct);

    List<Product> findByCategoryId(Long categoryId);

    List<Product> findAvailableByCategoryId(Long categoryId);

    List<Product> findAllByTitleContainingIgnoreCase(String keyword);

    List<Product> findAvailableByTitleContainingIgnoreCase(String keyword);

    List<Product> findByCategoryAndTitleContainingIgnoreCase(Long categoryId, String keyword);
}
