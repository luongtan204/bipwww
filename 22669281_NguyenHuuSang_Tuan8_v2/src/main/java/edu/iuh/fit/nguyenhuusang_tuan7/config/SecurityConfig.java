//package edu.iuh.fit.nguyenhuusang_tuan7.config;
//
///**
// * @Dự án: 22669281_NguyenHuuSang_Tuan7
// * @Class: SecurityConfig
// * @Tạo vào ngày: 10/20/2025
// * @Tác giả: Nguyen Huu Sang
// */
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableMethodSecurity(prePostEnabled = true)
//public class SecurityConfig {
//
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public InMemoryUserDetailsManager userDetailsService() {
//        PasswordEncoder encoder = passwordEncoder();
//
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password(encoder.encode("123"))
//                .roles("ADMIN")
//                .build();
//
//        UserDetails customer = User.builder()
//                .username("customer")
//                .password(encoder.encode("111"))
//                .roles("CUSTOMER")
//                .build();
//
//        UserDetails guest = User.builder()
//                .username("guest")
//                .password(encoder.encode("guest"))
//                .roles("GUEST")
//                .build();
//
//        return new InMemoryUserDetailsManager(admin, customer, guest);
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests(auth -> auth
//                        // Quy tắc mở cho các trang static resources
//                        .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()
//                        // Phân quyền cho các chức năng
//                        .requestMatchers("/products", "/products/detail/**").hasAnyRole("CUSTOMER", "ADMIN", "GUEST")
//                        .requestMatchers("/products/add", "/products/edit/**", "/products/update/**").hasRole("ADMIN")
//                        .requestMatchers("/orders", "/orders/detail/**", "/orders/add").hasRole("ADMIN")
//                        .requestMatchers("/customers/**", "/customers/detail/**", "/customers/add").hasRole("ADMIN")
//                        // Các request còn lại yêu cầu xác thực
//                        .anyRequest().authenticated()
//                )
//                // Sử dụng form đăng nhập mặc định của Spring Security
//                .formLogin(form -> form
//                        .defaultSuccessUrl("/home")
//                        .permitAll()
//                )
//                .logout(logout -> logout
//                        .logoutUrl("/logout")
//                        .logoutSuccessUrl("/login?logout=true")
//                        .invalidateHttpSession(true)
//                        .deleteCookies("JSESSIONID")
//                        .permitAll()
//                );
//
//        return http.build();
//    }
//}

package edu.iuh.fit.nguyenhuusang_tuan7.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        PasswordEncoder encoder = passwordEncoder();

        UserDetails admin = User.builder()
                .username("admin")
                .password(encoder.encode("123"))
                .roles("ADMIN")
                .build();

        UserDetails customer = User.builder()
                .username("customer")
                .password(encoder.encode("111"))
                .roles("CUSTOMER")
                .build();

        UserDetails guest = User.builder()
                .username("guest")
                .password(encoder.encode("guest"))
                .roles("GUEST")
                .build();

        return new InMemoryUserDetailsManager(admin, customer, guest);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        // Static resources
                        .requestMatchers("/css/**", "/js/**", "/images/**", "/uploads/**").permitAll()

                        // Public pages
                        .requestMatchers("/", "/home", "/login").permitAll()

                        // Product pages - accessible by all authenticated users
                        .requestMatchers("/products", "/products/detail/**", "/products/search").hasAnyRole("CUSTOMER", "ADMIN", "GUEST")

                        // Product management - ADMIN only
                        .requestMatchers("/products/add", "/products/edit/**", "/products/delete/**").hasRole("ADMIN")

                        // Cart - accessible by CUSTOMER and ADMIN
                        .requestMatchers("/orders/cart/**", "/orders/checkout").hasAnyRole("CUSTOMER", "ADMIN")

                        // Order management - ADMIN only
                        .requestMatchers("/orders/**").hasRole("ADMIN")

                        // Customer management - ADMIN only
                        .requestMatchers("/customers/**").hasRole("ADMIN")

                        // All other requests require authentication
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .defaultSuccessUrl("/products", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout=true")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                );

        return http.build();
    }
}