package edu.iuh.fit.nguyenhuusang_tuan7.entities;

import jakarta.persistence.*;
import lombok.*;


/**
 * @Dự án: 22669281_NguyenHuuSang_Tuan7
 * @Class: User
 * @Tạo vào ngày: 10/13/2025
 * @Tác giả: Nguyen Huu Sang
 */

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    private String role;
}