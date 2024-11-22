package miniproject.carrotmarket1.dao.MySQL;

import miniproject.carrotmarket1.entity.Category;
import miniproject.carrotmarket1.entity.ChatRoom;
import miniproject.carrotmarket1.entity.Product;
import miniproject.carrotmarket1.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ChatRoomDAO {
    @Insert("INSERT INTO chat_room (product_id, buyer_id, seller_id, created_at) VALUES (#{productId}, #{buyerId}, #{sellerId}, NOW())")
    void insertChatRoom(ChatRoom chatRoom);

    @Select("SELECT * FROM chat_room WHERE product_id = #{productId} AND buyer_id = #{buyerId}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "buyerId", column = "buyer_id"),
            @Result(property = "sellerId", column = "seller_id"),
            @Result(property = "productId", column = "product_id"),
            @Result(property = "buyer", column = "buyer_id", javaType = User.class,
                    one = @One(select = "miniproject.carrotmarket1.dao.MySQL.UserDAO.selectById")),
            @Result(property = "seller", column = "seller_id", javaType = User.class,
                    one = @One(select = "miniproject.carrotmarket1.dao.MySQL.UserDAO.selectById")),
            @Result(property = "product", column = "product_id", javaType = Product.class,
                    one = @One(select = "miniproject.carrotmarket1.dao.MySQL.ProductDAO.findById"))
    })
    Optional<ChatRoom> findByProductIdAndBuyerId(@Param("productId") Long productId, @Param("buyerId") Long buyerId);

    @Select("SELECT * FROM chat_room WHERE id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "buyerId", column = "buyer_id"),
            @Result(property = "sellerId", column = "seller_id"),
            @Result(property = "productId", column = "product_id"),
            @Result(property = "buyer", column = "buyer_id", javaType = User.class,
                    one = @One(select = "miniproject.carrotmarket1.dao.MySQL.UserDAO.selectById")),
            @Result(property = "seller", column = "seller_id", javaType = User.class,
                    one = @One(select = "miniproject.carrotmarket1.dao.MySQL.UserDAO.selectById")),
            @Result(property = "product", column = "product_id", javaType = Product.class,
                    one = @One(select = "miniproject.carrotmarket1.dao.MySQL.ProductDAO.findById"))
    })
    Optional<ChatRoom> findById(@Param("id") Long id);

    @Select("SELECT * FROM chat_room WHERE buyer_id = #{userId} OR seller_id = #{userId}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "buyerId", column = "buyer_id"),
            @Result(property = "sellerId", column = "seller_id"),
            @Result(property = "productId", column = "product_id"),
            @Result(property = "buyer", column = "buyer_id", javaType = User.class,
                    one = @One(select = "miniproject.carrotmarket1.dao.MySQL.UserDAO.selectById")),
            @Result(property = "seller", column = "seller_id", javaType = User.class,
                    one = @One(select = "miniproject.carrotmarket1.dao.MySQL.UserDAO.selectById")),
            @Result(property = "product", column = "product_id", javaType = Product.class,
                    one = @One(select = "miniproject.carrotmarket1.dao.MySQL.ProductDAO.findById"))
    })
    List<ChatRoom> findAllByUser(@Param("userId")Long id);
}
