package miniproject.carrotmarket1.repository.MySQL;

import miniproject.carrotmarket1.dao.MySQL.ProductDAO;
import miniproject.carrotmarket1.entity.Product;
import miniproject.carrotmarket1.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MySQLProductRepository implements ProductRepository {

    private final ProductDAO productDAO;

    @Autowired
    public MySQLProductRepository(ProductDAO productDAO) {
        this.productDAO = productDAO;
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
    //게시글 전체 목록 조회
    public Page<Product> findAll(Pageable pageable) {
        List<Product> products = productDAO.findAll(pageable.getPageSize(), (int) pageable.getOffset());
        long totalCount = 0;
        if (!products.isEmpty()) {
            totalCount = products.get(0).getTotalCount();
        }else{
            return Page.empty();
        }

        return new PageImpl<>(products, pageable, totalCount);
    }

    //판매중인 상품에 대한 게시글 목록 조회
    public Page<Product> findAvailableItems(Pageable pageable) {
        List<Product> products = productDAO.findAvailableItems(pageable.getPageSize(), (int) pageable.getOffset());
        long totalCount = 0;
        if (!products.isEmpty()) {
            totalCount = products.get(0).getTotalCount();
        }else{
            return Page.empty();
        }

        return new PageImpl<>(products, pageable, totalCount);
    }

    public Page<Product> findByCategoryId(Long categoryId, Pageable pageable) {
        List<Product> products =  productDAO.findByCategoryId(categoryId,pageable.getPageSize(), (int) pageable.getOffset());
        long totalCount = 0;
        if (!products.isEmpty()) {
            totalCount = products.get(0).getTotalCount();
        }else{
            return Page.empty();
        }

        return new PageImpl<>(products, pageable, totalCount);

    }

    public Page<Product> findAvailableByCategoryId(Long categoryId, Pageable pageable) {
        List<Product> products =  productDAO.findAvailableByCategoryId(categoryId,pageable.getPageSize(), (int) pageable.getOffset());
        long totalCount = 0;
        if (!products.isEmpty()) {
            totalCount = products.get(0).getTotalCount();
        }else{
            return Page.empty();
        }

        return new PageImpl<>(products, pageable, totalCount);

    }
    @Override
    public Page<Product> findAllByTitleContainingIgnoreCase(String keyword, Pageable pageable) {
        List<Product> products =  productDAO.findAllByTitleContainingIgnoreCase(keyword,pageable.getPageSize(), (int) pageable.getOffset());
        long totalCount = 0;
        if (!products.isEmpty()) {
            totalCount = products.get(0).getTotalCount();
        }else{
            return Page.empty();
        }

        return new PageImpl<>(products, pageable, totalCount);

    }


    @Override
    public Page<Product> findAvailableByTitleContainingIgnoreCase(String keyword, Pageable pageable) {
        List<Product> products =  productDAO.findAvailableByTitleContainingIgnoreCase(keyword,pageable.getPageSize(), (int) pageable.getOffset());
        long totalCount = 0;
        if (!products.isEmpty()) {
            totalCount = products.get(0).getTotalCount();
        }else{
            return Page.empty();
        }

        return new PageImpl<>(products, pageable, totalCount);

    }

    @Override
    public Page<Product> findByCategoryAndTitleContainingIgnoreCase(Long categoryId, String keyword, Pageable pageable) {
        List<Product> products =  productDAO.findByCategoryAndTitleContainingIgnoreCase(categoryId, keyword,pageable.getPageSize(), (int) pageable.getOffset());
        long totalCount = 0;
        if (!products.isEmpty()) {
            totalCount = products.get(0).getTotalCount();
        }else{
            return Page.empty();
        }

        return new PageImpl<>(products, pageable, totalCount);

    }

    @Override
    public Page<Product> findProductsWithinRadiusByCategoryAndKeyword(double latitude, double longitude, double radiusKm, Long categoryId, String keyword, Pageable pageable) {
        List<Product> products =  productDAO.findProductsWithinRadiusByCategoryAndKeyword(latitude, longitude, radiusKm, categoryId, keyword,pageable.getPageSize(), (int) pageable.getOffset());
        long totalCount = 0;
        if (!products.isEmpty()) {
            totalCount = products.get(0).getTotalCount();
        }else{
            return Page.empty();
        }

        return new PageImpl<>(products, pageable, totalCount);

    }

    @Override
    public Page<Product> findAvailableProductsWithinRadiusByCategory(double latitude, double longitude, double radiusKm, Long categoryId, Pageable pageable) {
        List<Product> products =  productDAO.findAvailableProductsWithinRadiusByCategory(latitude, longitude, radiusKm, categoryId,pageable.getPageSize(), (int) pageable.getOffset());
        long totalCount = 0;
        if (!products.isEmpty()) {
            totalCount = products.get(0).getTotalCount();
        }else{
            return Page.empty();
        }
        return new PageImpl<>(products, pageable, totalCount);

    }

    @Override
    public Page<Product> findAvailableProductsWithinRadius(double latitude, double longitude, double radiusKm, Pageable pageable) {
        List<Product> products =  productDAO.findAvailableProductsWithinRadius(latitude, longitude, radiusKm,pageable.getPageSize(), (int) pageable.getOffset());
        long totalCount = 0;
        if (!products.isEmpty()) {
            totalCount = products.get(0).getTotalCount();
        }else{
            return Page.empty();
        }
        return new PageImpl<>(products, pageable, totalCount);

    }

    @Override
    public Page<Product> findProductsWithinRadiusByKeyword(double latitude, double longitude, double radiusKm, String keyword, Pageable pageable) {
        List<Product> products =  productDAO.findProductsWithinRadiusByKeyword(latitude, longitude, radiusKm, keyword,pageable.getPageSize(), (int) pageable.getOffset());
        long totalCount = 0;
        if (!products.isEmpty()) {
            totalCount = products.get(0).getTotalCount();
        }else{
            return Page.empty();
        }

        return new PageImpl<>(products, pageable, totalCount);

    }
    @Override
    public Page<Product> findProductsWithinRadiusByCategory(double latitude, double longitude, double radiusKm, Long categoryId, Pageable pageable) {
        List<Product> products =  productDAO.findProductsWithinRadiusByCategory(latitude, longitude, radiusKm, categoryId,pageable.getPageSize(), (int) pageable.getOffset());
        long totalCount = 0;
        if (!products.isEmpty()) {
            totalCount = products.get(0).getTotalCount();
        }else{
            return Page.empty();
        }

        return new PageImpl<>(products, pageable, totalCount);

    }

    @Override
    public Page<Product> findAvailableProductsWithinRadiusByKeyword(double latitude, double longitude, double radiusKm, String keyword, Pageable pageable) {
        List<Product> products =  productDAO.findAvailableProductsWithinRadiusByKeyword(latitude, longitude, radiusKm, keyword,pageable.getPageSize(), (int) pageable.getOffset());
        long totalCount = 0;
        if (!products.isEmpty()) {
            totalCount = products.get(0).getTotalCount();
        }else{
            return Page.empty();
        }

        return new PageImpl<>(products, pageable, totalCount);

    }

    @Override
    public Page<Product> findProductsWithinRadius(double latitude, double longitude, double radiusKm, Pageable pageable) {
        List<Product> products =  productDAO.findProductsWithinRadius(latitude, longitude, radiusKm,pageable.getPageSize(), (int) pageable.getOffset());
        long totalCount = 0;
        if (!products.isEmpty()) {
            totalCount = products.get(0).getTotalCount();
        }else{
            return Page.empty();
        }
        return new PageImpl<>(products, pageable, totalCount);

    }


}
