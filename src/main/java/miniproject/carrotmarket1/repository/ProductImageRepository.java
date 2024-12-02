package miniproject.carrotmarket1.repository;

import miniproject.carrotmarket1.entity.ProductImage;

public interface ProductImageRepository {
    //상품이미지 업로드
    void insertProductImage(ProductImage productImage);

    //상품이미지 업데이트
    void updateProductImage(ProductImage productImage);

    //상품이미지 조회
    ProductImage findById(Long id);

    //상품이미지 삭제
    void deleteById(Long id);

    // 상품의 id를 기준으로 이미지 삭제
    void deleteByProductId(Long productId);
}
