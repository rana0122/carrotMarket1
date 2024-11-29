package miniproject.carrotmarket1.dao.MySQL;

import miniproject.carrotmarket1.entity.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CategoryDAO {
    @Select("SELECT * FROM category WHERE id = #{id}")
    Category selectById(Long id);

    @Select("SELECT * FROM category")
    List<Category> findAll();

    @Select("select * from product where category.id = #{id}")
    List<Category> selectByCategoryId(Long id);
    // findById 메서드 추가 (이미 selectById가 있으므로 동일한 기능)
    @Select("SELECT * FROM category WHERE id = #{id}")
    Category findById(Long id);
}
