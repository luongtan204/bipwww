package com.fit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    // BCrypt encoder
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // In-memory users: CHAIRMAN & STUDENT
    @Bean
    public UserDetailsService userDetailsService(BCryptPasswordEncoder encoder) {
        var chairman = User.withUsername("chairman")
                .password(encoder.encode("123"))
                .roles("CHAIRMAN")
                .build();

        var student = User.withUsername("student")
                .password(encoder.encode("123"))
                .roles("STUDENT")
                .build();

        return new InMemoryUserDetailsManager(chairman, student);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        // Tài nguyên tĩnh & trang login mở tự do
                        .requestMatchers(
                                "/login",
                                "/css/**", "/js/**", "/images/**", "/webjars/**"
                        ).permitAll()

                        // Trang chủ - cả CHAIRMAN và STUDENT đều có thể xem
                        .requestMatchers("/").hasAnyRole("CHAIRMAN", "STUDENT")

                        // ===== QUYỀN XEM (STUDENT hoặc CHAIRMAN) =====
                        // Xem danh sách giảng viên và chi tiết
                        .requestMatchers(HttpMethod.GET, "/giangvien/**").hasAnyRole("CHAIRMAN", "STUDENT")

                        // ===== QUYỀN CHAIRMAN (Thêm đề tài) =====
                        // Add detai (GET form + POST submit)
                        .requestMatchers(HttpMethod.GET,  "/detai/add/**").hasRole("CHAIRMAN")
                        .requestMatchers(HttpMethod.POST, "/detai/save").hasRole("CHAIRMAN")

                        // Các request còn lại: yêu cầu đăng nhập
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        // Nếu bạn có trang login riêng thì mở dòng dưới và tạo template /login
                        // .loginPage("/login")
                        .defaultSuccessUrl("/", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout=true")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                );
        // CSRF: giữ mặc định bật để bảo vệ form
        return http.build();
    }
}