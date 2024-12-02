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

    //반경내 게시글 조회
    @Override
    public Page<Product> findProductsWithinRadius(Double latitude, Double longitude, Double radiusKm,
                                                  Long categoryId, String status, String keyword, Pageable pageable) {
        List<Product> products = productDAO.findProductsWithinRadius(latitude, longitude, radiusKm, categoryId, status, keyword,
                                                                        pageable.getPageSize(), (int) pageable.getOffset());
        long totalCount = 0;
        if (!products.isEmpty()) {
            totalCount = products.get(0).getTotalCount();
        }else{
            return Page.empty();
        }

        return new PageImpl<>(products, pageable, totalCount);
    }

    //게시글 조회(로그인 안한 경우)
    @Override
    public Page<Product> findProductsByConditions(Long categoryId, String status, String keyword, Pageable pageable) {
        List<Product> products = productDAO.findProductsByConditions(categoryId, status, keyword,pageable.getPageSize(), (int) pageable.getOffset());
        long totalCount = 0;
        if (!products.isEmpty()) {
            totalCount = products.get(0).getTotalCount();
        }else{
            return Page.empty();
        }

        return new PageImpl<>(products, pageable, totalCount);
    }

    @Override
    public void updateReservationStatus(Long productId, String status) {
        productDAO.updateReservationStatus(productId,status);
    }

}
