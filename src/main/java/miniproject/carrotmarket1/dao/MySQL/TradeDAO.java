package miniproject.carrotmarket1.dao.MySQL;

import miniproject.carrotmarket1.entity.Category;
import miniproject.carrotmarket1.entity.Product;
import miniproject.carrotmarket1.entity.Trade;
import miniproject.carrotmarket1.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TradeDAO {
    /* 사용자id로 작성한 거래한 조회*/
    @Select("SELECT * FROM product p JOIN trade t ON p.user_id = t.buyer_id WHERE t.buyer_id = #{buyerId}")
    List<Product> findByBuyerId(Long buyerId);

}
