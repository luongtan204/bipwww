package edu.iuh.fit.nguyenhuusang_tuan7.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Dự án: 22669281_NguyenHuuSang_Tuan7
 * @Class: Product
 * @Tạo vào ngày: 10/13/2025
 * @Tác giả: Nguyen Huu Sang
 */

@Entity
@Table(name = "product")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"comments", "orderLines"})
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private BigDecimal price;
    private boolean inStock;
    private String image;
    private Integer quantity;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Orderline> orderLines;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Comment> comments;


}