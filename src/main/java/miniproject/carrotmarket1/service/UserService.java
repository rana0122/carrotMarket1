package miniproject.carrotmarket1.service;

import jakarta.servlet.http.HttpSession;
import miniproject.carrotmarket1.dto.User;
import miniproject.carrotmarket1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${file.upload-dir}") // application.properties의 값을 주입
    private String uploadDir;
    private static final long MAX_FILE_SIZE = 20 * 1024 * 1024; // 20MB (바이트 단위)


    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    //로그인 시 패스워드 확인
    public User authenticate(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return user;
        }
        return null;
    }

    //로그인 시 사용자 위치 정보  update
    public void updateUserLocation(Long userId, Double latitude, Double longitude, String location) {
        userRepository.updateLocation(userId, latitude, longitude, location);
    }

    //프로필 수정시 사용
    public User getLoggedInUser(HttpSession session) {
        // 세션에서 로그인된 사용자 정보 가져오기
        return (User) session.getAttribute("loggedInUser");
    }

    //프로필 생성 및 업데이트
    public void saveOrUpdateUser(User user, MultipartFile profileImageFile) throws IOException {
        // 기존 사용자 조회
        User existingUser = userRepository.findByEmail(user.getEmail());

        if (existingUser == null) {
            //  신규 회원인 경우
            if (profileImageFile != null && !profileImageFile.isEmpty()) {
                //  일반 회원가입 시 이미지 저장
                String fileName = saveProfileImage(profileImageFile, user);
                user.setProfileImage(fileName);
            } else if ("KAKAO".equals(user.getUserGroup())
                    && user.getProfileImage() != null && !user.getProfileImage().isEmpty()) {
                //  카카오 로그인 시 URL 프로필 이미지 저장
                user.setProfileImage(user.getProfileImage());
            }

            //  사용자 그룹 설정 (카카오 로그인인지 일반 로그인인지)
            if (user.getUserGroup() == null || user.getUserGroup().isEmpty()) {
                user.setUserGroup("GENERAL"); // 기본값
            }

            //  비밀번호 암호화 (카카오 로그인 사용자는 제외) 신규는 평문
            if (user.getPassword() != null && !user.getPassword().isEmpty()
                    && !user.getUserGroup().equals("KAKAO")) {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
            }

            userRepository.insertUser(user); // 신규 사용자 추가
        } else {
            //  기존 사용자 업데이트
            user.setId(existingUser.getId()); // 기존 ID 유지

            if (profileImageFile != null && !profileImageFile.isEmpty()) {
                //  일반 회원가입 시 새 이미지 저장
                String fileName = saveProfileImage(profileImageFile, user);
                user.setProfileImage(fileName);
            } else if (user.getProfileImage() != null && !user.getProfileImage().isEmpty())
            {
                //  카카오 로그인 시 프로필 이미지 URL 유지 (기존 이미지 덮어쓰지 않음)
                existingUser.setProfileImage(user.getProfileImage());
            } else {
                //  새 이미지가 없으면 기존 이미지 유지
                user.setProfileImage(existingUser.getProfileImage());
            }

            userRepository.updateUser(user); // 기존 사용자 업데이트
        }
    }


    // 프로필 이미지 저장 메소드
    private String saveProfileImage(MultipartFile profileImageFile, User user) throws IOException {

        if (profileImageFile.getSize() > MAX_FILE_SIZE) {
            throw new IllegalArgumentException("파일 크기가 20MB를 초과합니다. 더 작은 이미지를 업로드해주세요.");
        }
        Path uploadPath = Paths.get(uploadDir).toAbsolutePath().normalize();

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }


        String fileExtension = profileImageFile.getOriginalFilename()
                .substring(profileImageFile.getOriginalFilename().lastIndexOf("."));
        String fileName = user.getEmail() + fileExtension;

        Path filePath = uploadPath.resolve(fileName);

        // 동일한 이름의 파일이 있으면 삭제
        if (Files.exists(filePath)) {
            Files.delete(filePath);
        }

        // 파일 저장
        profileImageFile.transferTo(filePath.toFile());

        return "/profileImages/" + fileName; // 저장된 파일 경로 반환
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // USER 조회 By Id
    public User findById(Long id) {
        return userRepository.selectById(id);
    }
}
