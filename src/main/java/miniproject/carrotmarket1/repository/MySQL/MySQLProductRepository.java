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
}
