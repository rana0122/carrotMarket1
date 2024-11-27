package miniproject.carrotmarket1.dao.MySQL;

import miniproject.carrotmarket1.entity.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CategoryDAO {
    @Select("SELECT * FROM category WHERE id = #{id}")
    Category selectById(Long id);
}
