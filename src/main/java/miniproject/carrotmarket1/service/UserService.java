package miniproject.carrotmarket1.service;

import jakarta.servlet.http.HttpSession;
import miniproject.carrotmarket1.dao.UserDAO;
import miniproject.carrotmarket1.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserDAO userDAO;

    @Value("${file.upload-dir}") // application.properties의 값을 주입
    private String uploadDir;

    @Autowired
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    //로그인 시 패스워드 확인
    public User authenticate(String email, String password) {
        User user = userDAO.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
    //로그인 시 사용자 위치 정보  update
    public void updateUserLocation(Long userId, Double latitude, Double longitude, String location) {
        userDAO.updateLocation(userId, latitude, longitude, location);
    }
    //프로필 수정시 사용
    public User getLoggedInUser(HttpSession session) {
        // 세션에서 로그인된 사용자 정보 가져오기
        return (User) session.getAttribute("loggedInUser");
    }
}
