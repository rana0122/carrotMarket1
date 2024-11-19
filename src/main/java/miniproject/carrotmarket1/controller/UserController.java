package miniproject.carrotmarket1.controller;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpSession;
import miniproject.carrotmarket1.entity.User;
import miniproject.carrotmarket1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {

    private final UserService userService;
    private final ServletContext servletContext;

    // application.properties에 설정된 API 키를 가져옴
    @Value("${google.api.key}")
    private String apiKey;
    // kakao 주소 api JavaScript 키 가져오기
    @Value("${kakao.api.javascript.key}")
    private String kakaoJavascriptKey;
    //profile upload folder
    @Value("${file.upload-dir}")
    private String uploadDir;

    @Autowired
    public UserController(UserService userService, ServletContext servletContext) {
        this.userService = userService;
        this.servletContext = servletContext;
    }

    //========================로그인(위치정보 수집)===============================//
    @PostMapping("/login")
    public String login(
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam(required = false) Double latitude,
            @RequestParam(required = false) Double longitude,
            @RequestParam(required = false) String location,
            HttpSession session) {
        // 회원 인증
        User user = userService.authenticate(email, password);
        if (user != null) {
            // 위치 정보 업데이트
            if (latitude != null && longitude != null && location != null) {
                userService.updateUserLocation(user.getId(), latitude, longitude, location);
            }
            session.setAttribute("loggedInUser", user); // 세션에 사용자 정보 저장
            return "redirect:/products";
        } else {
            return "redirect:/products?loginError=true";
        }
    }
    //로그아웃
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/products";
    }
    @GetMapping("/get-address")
    @ResponseBody
    public String getAddress(@RequestParam double latitude, @RequestParam double longitude) {
        String url = "https://maps.googleapis.com/maps/api/geocode/json?latlng=" + latitude + "," + longitude + "&key=" + apiKey+ "&language=ko";
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, String.class);

    }
    //입력받은 주소로 위도,경도 갱신하기.
    @GetMapping("/get-latlng")
    @ResponseBody
    public String getLatLng(@RequestParam("address") String address) {
        String url = "https://maps.googleapis.com/maps/api/geocode/json?address=" + address + "&key=" + apiKey;
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);
        return response; // API의 JSON 응답을 클라이언트에 반환
    }

    //=================회원가입==========================//
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "user/register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user,
                               @RequestParam("profileImageFile") MultipartFile profileImageFile,
                               HttpSession session) throws IOException {
        //중복되는 코드....service 단 saveOrUpdateUser 에서 동일하게 수행.
//        if (!profileImageFile.isEmpty()) {
//            String filePath = saveProfileImage(profileImageFile, user);
//            if (filePath == null) {
//                return "register";
//            }
//            user.setProfileImage(filePath);
//        }

//        userService.save(user);
//        userService.saveOrUpdateUser(user, profileImageFile);
//        session.setAttribute("loggedInUser", user);
//
//        return "redirect:/board/list";

        try {
            userService.saveOrUpdateUser(user, profileImageFile);
            session.setAttribute("loggedInUser", user);
            return "redirect:/"; // 회원가입 완료 후 로그인 페이지로 리디렉션
        } catch (IOException e) {
            e.printStackTrace();
            return "redirect:/register?error=true";
        }
    }
    //이메일 중복가입 체크
    @PostMapping("/check-email")
    @ResponseBody
    public Map<String, Boolean> checkEmail(@RequestParam("email") String email) {
        boolean exists = userService.emailExists(email);
        Map<String, Boolean> response = new HashMap<>();
        response.put("exists", exists);
        return response;
    }

    @PostMapping("/check-password")
    @ResponseBody
    public boolean checkPassword(@RequestParam("currentPassword") String currentPassword, HttpSession session) {
        User loggedInUser = userService.getLoggedInUser(session);
        return loggedInUser != null && loggedInUser.getPassword().equals(currentPassword);
    }
}
