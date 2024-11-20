package miniproject.carrotmarket1.repository;

import jakarta.servlet.http.HttpSession;
import miniproject.carrotmarket1.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface UserRepository {

    void insertUser(User user);

    User selectById(Long id);

    User findByEmail(String email);

    List<User> selectAllUsers();

    void updateUser(User user);

    void deleteUser(Long id);

    void updateLocation(Long userId, Double latitude, Double longitude, String location);

}
