package miniproject.carrotmarket1.service;

import lombok.extern.slf4j.Slf4j;
import miniproject.carrotmarket1.dto.User;
import miniproject.carrotmarket1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.info("🔎 loadUserByUsername() 호출됨 → email=" + email);
        User user = userRepository.findByEmail(email);

        if (user == null) {
            log.info("❌ 인증 실패: 사용자를 찾을 수 없음 → email=" + email);
            throw new UsernameNotFoundException("해당 이메일을 찾을 수 없습니다: " + email);
        }

        log.info("✅ 인증 성공: 사용자 정보 로드됨 → email=" + email);
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                new ArrayList<>()
        );
    }
}
