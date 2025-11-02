package edu.iuh.fit.nguyenhuusang_tuan7.entities;

import jakarta.persistence.*;
import lombok.*;


/**
 * @Dự án: 22669281_NguyenHuuSang_Tuan7
 * @Class: Comment
 * @Tạo vào ngày: 10/13/2025
 * @Tác giả: Nguyen Huu Sang
 */



@Entity
@Table(name = "comment")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(length = 500)
    private String text;
}