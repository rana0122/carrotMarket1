package miniproject.carrotmarket1.dao.MySQL;

import miniproject.carrotmarket1.entity.ProductImage;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ProductImageDAO {

    @Insert("INSERT INTO product_image (product_id, image_url, uploaded_at) " +
            "VALUES (#{productId}, #{imageUrl}, #{uploadedAt})")
    @Options(useGeneratedKeys = true, keyProperty = "id")  // id 자동 생성 활성화
    void insertProductImage(ProductImage productImage);

    @Update("UPDATE product_image SET image_url = #{imageUrl} WHERE id = #{id}")
    void updateProductImage(ProductImage productImage);
}
