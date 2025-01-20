package miniproject.carrotmarket1.repository;

import miniproject.carrotmarket1.dto.User;

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
