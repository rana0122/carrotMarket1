package miniproject.carrotmarket1.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import miniproject.carrotmarket1.dto.User;
import miniproject.carrotmarket1.service.KakaoService;
import miniproject.carrotmarket1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Controller
@Slf4j
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final KakaoService kakaoService;

    //profile upload folder
    @Value("${file.upload-dir}")
    private String uploadDir;

    @Autowired
    public UserController(UserService userService, AuthenticationManager authenticationManager,
                          PasswordEncoder passwordEncoder, KakaoService kakaoService) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.kakaoService = kakaoService;
    }

    //========================로그인(위치정보 수집)===============================//
    @PostMapping("/login")
    public String login(
            @RequestParam String email,
            @RequestParam String password,
            HttpSession session) {
        try {
            //  Spring Security를 사용하여 사용자 인증
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password)
            );
            // 인증 성공 시 SecurityContext에 저장 (세션 유지)
            SecurityContextHolder.getContext().setAuthentication(authentication);
            //  사용자 정보 가져오기
            User user = userService.findByEmail(email);
            //  위치 정보 업데이트
            if (user != null) {
                if ("N".equals(user.getLockedYn())) {

                    //  세션에 사용자 정보 저장
                    session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());
                    session.setAttribute("loggedInUser", user);
                    return "redirect:/products?firstLogin=true"; // 최초 로그인 여부 전달
                } else if ("Y".equals(user.getLockedYn())) {
                    return "redirect:/products?accountLocked=true";
                }
            }
            return "redirect:/products?loginError=true";
        } catch (AuthenticationException e) {
            return "redirect:/products?loginError=true";
        }
    }
    @GetMapping("/kakaoLogin")
    public String kakaoLogin(
            @RequestParam String code,
            HttpSession session) throws JsonProcessingException {
        // 1. 카카오 로그인 처리
        User user = kakaoService.kakaoLogin(code);

        if (user != null) {
            if ("N".equals(user.getLockedYn())) {

                // SecurityContext에 인증 정보 설정
                Authentication authentication = new UsernamePasswordAuthenticationToken
                        (user.getEmail(), null, Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
                SecurityContextHolder.getContext().setAuthentication(authentication);

                session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());
                session.setAttribute("loggedInUser", user); // 세션에 사용자 정보 저장
                session.setAttribute("kakaoAccessToken", user.getKakaoAccessToken()); // Access Token 저장
                return "redirect:/products?firstLogin=true"; // 최초 로그인 여부 전달
            } else if ("Y".equals(user.getLockedYn())) {
                return "redirect:/products?accountLocked=true"; // 정지된 계정 상태 전달
            }
        }
        // 로그인 실패 시
        return "redirect:/products?loginError=true";

    }
    //로그아웃
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        String accessToken = (String) session.getAttribute("kakaoAccessToken");
        if (accessToken != null) {
//            kakaoService.unlink(accessToken); // 카카오 연결 해제
            kakaoService.kakaoLogout(accessToken); // 연결 해제
        }
        SecurityContextHolder.clearContext();
        session.invalidate(); // 세션 무효화

        Cookie cookie = new Cookie("JSESSIONID", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);

        return "redirect:/products";
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
        boolean exists = userService.findByEmail(email) != null;
        Map<String, Boolean> response = new HashMap<>();
        response.put("exists", exists);
        return response;
    }
    //=============== 프로필=================//
    //프로필 수정하기.
    @GetMapping("/edit-profile")
    public String editProfile(Model model, HttpSession session) {
        User user = userService.getLoggedInUser(session);
        model.addAttribute("user", user);
        return "user/edit-profile";
    }
    // 프로필 업데이트
    @PostMapping("/edit-profile")
    public String updateProfile(@ModelAttribute User user,
                                @RequestParam(required = false) String currentPassword,
                                @RequestParam(required = false) MultipartFile profileImageFile,
                                HttpSession session) {
        try {
            // 기존 사용자의 ID, group 설정
            user.setId(userService.getLoggedInUser(session).getId());
            user.setUserGroup(userService.getLoggedInUser(session).getUserGroup());

            User loggedInUser = userService.getLoggedInUser(session);
            String beforeLocation = userService.findByEmail(loggedInUser.getEmail()).getLocation();

            // 새 비밀번호가 입력되지 않은 경우 현재 비밀번호로 유지
            if (!"KAKAO".equals(loggedInUser.getUserGroup())) {
                if (user.getPassword() == null || user.getPassword().isEmpty()) {
                    user.setPassword(loggedInUser.getPassword());
                }else{ // 새 비밀번호가 입력되었다면, 암호화
                    user.setPassword(passwordEncoder.encode(user.getPassword()));
                }
            }

            // 프로필 이미지와 기타 정보 업데이트
            userService.saveOrUpdateUser(user, profileImageFile);

            // 세션에 업데이트된 사용자 정보 저장
            session.setAttribute("loggedInUser", user);
            return "redirect:/"; // 프로필 페이지로 리디렉션

        } catch (IOException e) {
            e.printStackTrace();
            return "redirect:/edit-profile?error=true";
        }
    }

    @PostMapping("/check-password")
    @ResponseBody
    public boolean checkPassword(@RequestParam("currentPassword") String currentPassword, HttpSession session) {
        User loggedInUser = userService.getLoggedInUser(session);
        // 카카오 로그인 유저는 비밀번호 검증 없이 true 반환
        if ("KAKAO".equals(loggedInUser.getUserGroup())) {
            return true;
        }
        // 사용자가 입력한 평문 비밀번호(currentPassword)와 DB에 저장된 암호화된 비밀번호 비교
        return loggedInUser != null && passwordEncoder.matches(currentPassword, loggedInUser.getPassword());
    }


}
