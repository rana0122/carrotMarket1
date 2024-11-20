package miniproject.carrotmarket1.dao;

import miniproject.carrotmarket1.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserDAO {


    @Insert("INSERT INTO user (username, password, email, location, latitude, longitude, profile_image, created_at, user_group, radius_km) " +
            "VALUES (#{username}, #{password}, #{email}, #{location}, #{latitude}, #{longitude}, #{profileImage}, #{createdAt}, #{userGroup}, #{radiusKm})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertUser(User user);

    @Select("SELECT * FROM user WHERE id = #{id}")
    User selectById(Long id);

    @Select("SELECT * FROM user WHERE email = #{email}")
    User findByEmail(String email);

    @Select("SELECT * FROM user")
    List<User> selectAllUsers();

    @Update("UPDATE user SET username = #{username}, password = #{password}, email = #{email}, " +
            "location = #{location}, latitude = #{latitude}, longitude = #{longitude}, " +
            "profile_image = #{profileImage}, user_group = #{userGroup}, radius_km=#{radiusKm} WHERE id = #{id}")
    void updateUser(User user);

    @Delete("DELETE FROM user WHERE id = #{id}")
    void deleteUser(Long id);

    @Update("UPDATE user SET location = #{location}, latitude = #{latitude}, longitude = #{longitude} WHERE id = #{userId}")
    void updateLocation(@Param("userId") Long userId,
                        @Param("latitude") Double latitude,
                        @Param("longitude") Double longitude,
                        @Param("location") String location);


}
