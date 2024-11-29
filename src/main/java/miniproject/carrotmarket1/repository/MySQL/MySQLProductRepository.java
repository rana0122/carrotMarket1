package miniproject.carrotmarket1.repository.MySQL;

import miniproject.carrotmarket1.dao.MySQL.ProductDAO;
import miniproject.carrotmarket1.entity.Product;
import miniproject.carrotmarket1.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MySQLProductRepository implements ProductRepository {

    private final ProductDAO productDAO;

    @Autowired
    public MySQLProductRepository(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    //게시글 전체 목록 조회
    public List<Product> findAll() {

        return productDAO.findAll();
    }

    //판매중인 상품에 대한 게시글 목록 조회
    public List<Product> findAvailableItems() {

        return productDAO.findAvailableItems();
    }

    //xml 연동 테스트
    @Override
    public List<Product> findProductsByConditions(Long category) {
        return productDAO.findProductsByConditions(category);
    }

    //ID로 상품 상세 조회
    @Override
    public Product findById(Long productId) {
        return productDAO.findById(productId);
    }

    //게시글 저장
    @Override
    public void insertProduct(Product product) {
        productDAO.insertProduct(product);
    }

    //게시글 수정
    @Override
    public void updateProduct(Product product) {
        productDAO.updateProduct(product);
    }

    public List<Product> findByCategoryId(Long categoryId) {
        return productDAO.findByCategoryId(categoryId);
    }

    public List<Product> findAvailableByCategoryId(Long categoryId) {
        return productDAO.findAvailableByCategoryId(categoryId);
    }
    @Override
    public List<Product> findAllByTitleContainingIgnoreCase(String keyword) {
        return productDAO.findAllByTitleContainingIgnoreCase(keyword);
    }

    @Override
    public List<Product> findAvailableByTitleContainingIgnoreCase(String keyword) {
        return productDAO.findAvailableByTitleContainingIgnoreCase(keyword);
    }

    @Override
    public List<Product> findByCategoryAndTitleContainingIgnoreCase(Long categoryId, String keyword) {
        return productDAO.findByCategoryAndTitleContainingIgnoreCase(categoryId, keyword);
    }

    @Override
    public List<Product> findProductsWithinRadiusByCategoryAndKeyword(double latitude, double longitude, double radiusKm, Long categoryId, String keyword) {
        return productDAO.findProductsWithinRadiusByCategoryAndKeyword(latitude, longitude, radiusKm, categoryId, keyword);
    }

    @Override
    public List<Product> findAvailableProductsWithinRadiusByCategory(double latitude, double longitude, double radiusKm, Long categoryId) {
        return productDAO.findAvailableProductsWithinRadiusByCategory(latitude, longitude, radiusKm, categoryId);
    }

    @Override
    public List<Product> findAvailableProductsWithinRadius(double latitude, double longitude, double radiusKm) {
        return productDAO.findAvailableProductsWithinRadius(latitude, longitude, radiusKm);
    }

    @Override
    public List<Product> findProductsWithinRadiusByKeyword(double latitude, double longitude, double radiusKm, String keyword) {
        return productDAO.findProductsWithinRadiusByKeyword(latitude, longitude, radiusKm, keyword);
    }
    @Override
    public List<Product> findProductsWithinRadiusByCategory(double latitude, double longitude, double radiusKm, Long categoryId) {
        return productDAO.findProductsWithinRadiusByCategory(latitude, longitude, radiusKm, categoryId);
    }

    @Override
    public List<Product> findAvailableProductsWithinRadiusByKeyword(double latitude, double longitude, double radiusKm, String keyword) {
        return productDAO.findAvailableProductsWithinRadiusByKeyword(latitude, longitude, radiusKm, keyword);
    }

    @Override
    public List<Product> findProductsWithinRadius(double latitude, double longitude, double radiusKm) {
        return productDAO.findProductsWithinRadius(latitude, longitude, radiusKm);
    }


}
