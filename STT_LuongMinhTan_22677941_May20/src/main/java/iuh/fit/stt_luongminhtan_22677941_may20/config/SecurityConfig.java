package iuh.fit.stt_luongminhtan_22677941_may20.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
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

    // In-memory users: ADMIN & EMPLOYEE
    @Bean
    public UserDetailsService userDetailsService(BCryptPasswordEncoder encoder) {
        var admin = User.withUsername("admin")
                .password(encoder.encode("123"))
                .roles("ADMIN")
                .build();

        var employee = User.withUsername("employee")
                .password(encoder.encode("111"))
                .roles("EMPLOYEE")
                .build();

        return new InMemoryUserDetailsManager(admin, employee);
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

                        // Trang chủ có thể chuyển hướng về /busroute
                        .requestMatchers("/").authenticated()

                        // ===== QUYỀN XEM (EMPLOYEE hoặc ADMIN) =====
                        // Trang danh sách và chi tiết
                        .requestMatchers(HttpMethod.GET, "/busroute").hasAnyRole("EMPLOYEE", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/busroute/*").hasAnyRole("EMPLOYEE", "ADMIN")

                        // ===== QUYỀN ADMIN (Thêm/Sửa/Xóa) =====
                        // Add (GET form + POST submit)
                        .requestMatchers(HttpMethod.GET,  "/busroute/add").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/busroute/add").hasRole("ADMIN")

                        // Edit (GET form + POST submit)
                        .requestMatchers(HttpMethod.GET,  "/busroute/*/edit").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/busroute/*/edit").hasRole("ADMIN")

                        // Delete (POST)
                        .requestMatchers(HttpMethod.POST, "/busroute/*/delete").hasRole("ADMIN")

                        // Các request còn lại: yêu cầu đăng nhập
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        // Nếu bạn có trang login riêng thì mở dòng dưới và tạo template /login
                        // .loginPage("/login")
                        .defaultSuccessUrl("/busroute", true)
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
