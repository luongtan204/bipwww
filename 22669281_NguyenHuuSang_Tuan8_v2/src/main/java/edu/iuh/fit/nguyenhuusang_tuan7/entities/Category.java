package edu.iuh.fit.nguyenhuusang_tuan7.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


/**
 * @Dự án: 22669281_NguyenHuuSang_Tuan7
 * @Class: Category
 * @Tạo vào ngày: 10/13/2025
 * @Tác giả: Nguyen Huu Sang
 */

@Entity
@Table(name = "category")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "products")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Product> products;
}