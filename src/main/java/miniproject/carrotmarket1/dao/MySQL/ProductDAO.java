package miniproject.carrotmarket1.dao.MySQL;

import miniproject.carrotmarket1.entity.Category;
import miniproject.carrotmarket1.entity.Product;
import miniproject.carrotmarket1.entity.ProductImage;
import miniproject.carrotmarket1.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProductDAO {

    //게시글 생성
    @Insert("INSERT INTO product (title, description, price, location, latitude, longitude, " +
            "category_id, created_at, user_id, status) " +
            "VALUES (#{title}, #{description}, #{price}, #{location}, #{latitude}, #{longitude}, " +
            "#{categoryId}, #{createdAt}, #{userId}, #{status})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertProduct(Product product);

    //게시글 생성시 이미지 업로드
    @Insert("INSERT INTO product_image (product_id, image_url, uploaded_at) " +
            "VALUES (#{product_id}, #{image_url}, #{uploaded_at})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertProductImage(ProductImage productImage);


    // 판매중인 상품에 대한 게시글 목록 조회
    @Select("SELECT * FROM product WHERE status = 'AVAILABLE'")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "user", column = "user_id", javaType = User.class,
                    one = @One(select = "miniproject.carrotmarket1.dao.MySQL.UserDAO.selectById")),
            @Result(property = "category", column = "category_id", javaType = Category.class,
                    one = @One(select = "miniproject.carrotmarket1.dao.MySQL.CategoryDAO.selectById")), // 경로 수정
            @Result(property = "images", column = "id", javaType = List.class,
                    many = @Many(select = "selectProductImagesByProductId"))
    })
    List<Product> findAvailableItems();

    //모든 상품에 대한 게시글 목록 조회
    @Select("SELECT * FROM product")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "user", column = "user_id", javaType = User.class,
                    one = @One(select = "miniproject.carrotmarket1.dao.MySQL.UserDAO.selectById")),
            @Result(property = "category", column = "category_id", javaType = Category.class,
                    one = @One(select = "miniproject.carrotmarket1.dao.MySQL.CategoryDAO.selectById")), // 경로 수정
            @Result(property = "images", column = "id", javaType = List.class,
                    many = @Many(select = "selectProductImagesByProductId"))
    })
    List<Product> findAll();

    //게시글 상세조회
    @Select("SELECT * FROM product WHERE id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "user", column = "user_id", javaType = User.class,
                    one = @One(select = "miniproject.carrotmarket1.dao.MySQL.UserDAO.selectById")),
            @Result(property = "category", column = "category_id", javaType = Category.class,
                    one = @One(select = "miniproject.carrotmarket1.dao.MySQL.CategoryDAO.selectById")), // 경로 수정
            @Result(property = "images", column = "id", javaType = List.class,
                    many = @Many(select = "selectProductImagesByProductId"))
    })
    Product findById(Long id);


    // Product ID에 의해 ProductImage들을 조회하는 메소드
    @Select("SELECT * FROM product_image WHERE product_id = #{id}")
    List<ProductImage> selectProductImagesByProductId(Long id);

    //xml 연동 테스트
    List<Product> findProductsByConditions( @Param("category") Long category);

    //게시글 수정
    @Update("UPDATE product SET title = #{title}, description = #{description}, " +
            "price = #{price}, category_id = #{categoryId} WHERE id = #{id}")
    void updateProduct(Product product);
}
