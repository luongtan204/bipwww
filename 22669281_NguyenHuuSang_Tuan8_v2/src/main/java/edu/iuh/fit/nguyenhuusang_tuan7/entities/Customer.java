package edu.iuh.fit.nguyenhuusang_tuan7.entities;

import java.time.LocalDate;
import jakarta.persistence.*;
import lombok.*;
import java.util.Calendar;
import java.util.List;
/**
 * @Dự án: 22669281_NguyenHuuSang_Tuan7
 * @Class: Customer
 * @Tạo vào ngày: 10/13/2025
 * @Tác giả: Nguyen Huu Sang
 */

@Entity
@Table(name = "customer")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "orders")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private LocalDate customerSince;

    @OneToMany(mappedBy = "customer")
    private List<Order> orders;
}

