package miniproject.carrotmarket1.service;

import miniproject.carrotmarket1.dao.ProductDAO;
import miniproject.carrotmarket1.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductDAO productDAO;

   @Autowired
    public ProductService(ProductDAO productDAO) {
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
}
