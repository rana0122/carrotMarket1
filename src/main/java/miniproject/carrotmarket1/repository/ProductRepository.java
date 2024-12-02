package miniproject.carrotmarket1.repository;

import miniproject.carrotmarket1.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductRepository {


    //ID로 상품 상세 조회
    Product findById(Long productId);

    //게시글 생성
    void insertProduct(Product product);
    
    //게시글 수정
    void updateProduct(Product existingProduct);

    //반경내 게시글 조회
    Page<Product> findProductsWithinRadius(Double latitude, Double longitude, Double radiusKm, Long categoryId, String status, String keyword, Pageable pageable);

    //게시글 조회(로그인 안한 경우)
    Page<Product> findProductsByConditions(Long categoryId, String status, String keyword, Pageable pageable);

    //채팅에서 상품 거래 상태 변경
    void updateReservationStatus(Long productId, String status);
}
