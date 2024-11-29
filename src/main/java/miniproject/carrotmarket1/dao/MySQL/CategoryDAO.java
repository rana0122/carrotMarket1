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
}
