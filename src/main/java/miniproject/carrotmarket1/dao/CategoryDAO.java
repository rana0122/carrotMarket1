package miniproject.carrotmarket1.dao;

import miniproject.carrotmarket1.entity.Category;
import org.apache.ibatis.annotations.Select;

public interface CategoryDAO {
    @Select("SELECT * FROM category WHERE id = #{id}")
    Category selectById(Long id);
}
