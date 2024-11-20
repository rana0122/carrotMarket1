package miniproject.carrotmarket1.repository.MySQL;

import jakarta.servlet.http.HttpSession;
import miniproject.carrotmarket1.dao.MySQL.UserDAO;
import miniproject.carrotmarket1.entity.User;
import miniproject.carrotmarket1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Repository
public class MySQLUserRepository implements UserRepository {

    private final UserDAO userDAO;
    @Autowired
    public MySQLUserRepository(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public void insertUser(User user) {
        userDAO.insertUser(user);
    }

    @Override
    public User selectById(Long id) {
        return userDAO.selectById(id);
    }

    @Override
    public User findByEmail(String email) {
        return userDAO.findByEmail(email);
    }

    @Override
    public List<User> selectAllUsers() {
        return userDAO.selectAllUsers();
    }

    @Override
    public void updateUser(User user) {
        userDAO.updateUser(user);
    }

    @Override
    public void deleteUser(Long id) {
        userDAO.deleteUser(id);
    }

    @Override
    public void updateLocation(Long userId, Double latitude, Double longitude, String location) {
        userDAO.updateLocation(userId, latitude, longitude, location);
    }
}
