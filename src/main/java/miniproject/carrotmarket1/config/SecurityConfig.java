package miniproject.carrotmarket1.config;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import miniproject.carrotmarket1.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.SecurityContextHolderFilter;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.ALWAYS) // 세션 유지 설정
                )
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/register", "/check-email", "/get-address").permitAll()
                        .requestMatchers("/", "/products").permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterAfter(new SecurityContextPersistenceFilter(), SecurityContextHolderFilter.class) // SecurityContext 강제 유지
//                .formLogin(form-> form.disable())
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout")) //  GET 요청으로 로그아웃 가능하게 설정
                        .logoutSuccessUrl("/products") //  로그아웃 후 이동할 페이지
                        .invalidateHttpSession(true) //  세션 무효화
                        .clearAuthentication(true) //  SecurityContext 초기화
                        .permitAll()
                );

        return http.build();
    }
    @PostConstruct
    public void init() {
        SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}