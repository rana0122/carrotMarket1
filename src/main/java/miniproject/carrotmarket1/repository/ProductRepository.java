package miniproject.carrotmarket1.repository;

import miniproject.carrotmarket1.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductRepository {


    //xml 연동 테스트
    List<Product> findProductsByConditions(Long category);

    //ID로 상품 상세 조회
    Product findById(Long productId);

    //게시글 생성
    void insertProduct(Product product);
    
    //게시글 수정
    void updateProduct(Product existingProduct);



    // 게시글 전체 목록 조회
    Page<Product> findAll(Pageable pageable);
    // 판매중인 상품에 대한 게시글 목록 조회
    Page<Product> findAvailableItems(Pageable pageable);
    Page<Product> findByCategoryId(Long categoryId, Pageable pageable);
    Page<Product> findAvailableByCategoryId(Long categoryId, Pageable pageable);
    Page<Product> findAllByTitleContainingIgnoreCase(String keyword, Pageable pageable);
    Page<Product> findAvailableByTitleContainingIgnoreCase(String keyword, Pageable pageable);
    Page<Product> findByCategoryAndTitleContainingIgnoreCase(Long categoryId, String keyword, Pageable pageable);
    Page<Product> findProductsWithinRadiusByCategory(double latitude, double longitude, double radiusKm, Long categoryId, Pageable pageable);
    Page<Product> findAvailableProductsWithinRadiusByKeyword(double latitude, double longitude, double radiusKm, String keyword, Pageable pageable);
    Page<Product> findProductsWithinRadius(double latitude, double longitude, double radiusKm, Pageable pageable);
    Page<Product> findProductsWithinRadiusByCategoryAndKeyword(double latitude, double longitude, double radiusKm, Long categoryId, String keyword, Pageable pageable);
    Page<Product> findAvailableProductsWithinRadiusByCategory(double latitude, double longitude, double radiusKm, Long categoryId, Pageable pageable);
    Page<Product> findAvailableProductsWithinRadius(double latitude, double longitude, double radiusKm, Pageable pageable);
    Page<Product> findProductsWithinRadiusByKeyword(double latitude, double longitude, double radiusKm, String keyword, Pageable pageable);

}
