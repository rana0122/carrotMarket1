package miniproject.carrotmarket1.repository.MySQL;


import miniproject.carrotmarket1.dao.MySQL.ProductImageDAO;
import miniproject.carrotmarket1.entity.ProductImage;
import miniproject.carrotmarket1.repository.ProductImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository

public class MySQLProductImageRepository implements ProductImageRepository {
    private final ProductImageDAO productImageDAO ;

    @Autowired
    public MySQLProductImageRepository(ProductImageDAO productImageDAO) {
        this.productImageDAO = productImageDAO;
    }

    public void insertProductImage(ProductImage productImage){
       productImageDAO.insertProductImage(productImage);
    }

    public void updateProductImage(ProductImage productImage){

        productImageDAO.updateProductImage(productImage);

    }

}
