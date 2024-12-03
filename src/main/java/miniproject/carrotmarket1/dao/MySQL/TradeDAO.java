package miniproject.carrotmarket1.dao.MySQL;

import miniproject.carrotmarket1.entity.Category;
import miniproject.carrotmarket1.entity.Product;
import miniproject.carrotmarket1.entity.Trade;
import miniproject.carrotmarket1.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

// 1. TradeDAO.java
@Mapper
public interface TradeDAO {
    // 기존 메서드 유지
    @Select("SELECT * FROM product p " +
            "JOIN trade t ON p.id = t.product_id " +
            "WHERE t.status = 'SOLD' " +
            "AND t.buyer_id = #{buyerId}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "categoryId", column = "category_id"),
            @Result(property = "user", column = "user_id", javaType = User.class,
                    one = @One(select = "miniproject.carrotmarket1.dao.MySQL.UserDAO.selectById")),
            @Result(property = "category", column = "category_id", javaType = Category.class,
                    one = @One(select = "miniproject.carrotmarket1.dao.MySQL.CategoryDAO.selectById")), // 경로 수정
            @Result(property = "images", column = "id", javaType = List.class,
                    many = @Many(select = "miniproject.carrotmarket1.dao.MySQL.ProductDAO.selectProductImagesByProductId"))
    })
    List<Product> findByBuyerId(Long buyerId);

    // 새로운 메서드 추가
    @Select("SELECT * FROM trade WHERE product_id = #{productId}")
    Trade findByProductId(Long productId);

    @Insert("INSERT INTO trade (buyer_id, product_id, trade_date, status) " +
            "VALUES (#{buyerId}, #{productId}, #{tradeDate}, #{status})")
    void createTrade(Trade trade);

    @Update("UPDATE trade SET status = #{status} WHERE id = #{id}")
    void updateTrade(Trade trade);

    @Delete("DELETE FROM trade WHERE id = #{id}")
    void deleteTrade(Long id);
}
