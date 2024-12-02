package miniproject.carrotmarket1.dao.MySQL;

import miniproject.carrotmarket1.entity.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TradeDAO {
    /* 사용자id로 구매한 거래한 조회*/
    @Select("SELECT * FROM product p " +
            "JOIN trade t ON p.id = t.product_id " +
            "WHERE t.status = 'SOLD' " +
            "AND t.buyer_id = #{buyerId}")
    List<Product> findByBuyerId(Long buyerId);



}
