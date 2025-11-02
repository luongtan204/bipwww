package edu.iuh.fit.nguyenhuusang_tuan7.entities;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
/**
 * @Dự án: 22669281_NguyenHuuSang_Tuan7
 * @Class: Orderline
 * @Tạo vào ngày: 10/13/2025
 * @Tác giả: Nguyen Huu Sang
 */


@Entity
@Table(name = "orderline")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Orderline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer amount;
    private BigDecimal purchasePrice;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;


}