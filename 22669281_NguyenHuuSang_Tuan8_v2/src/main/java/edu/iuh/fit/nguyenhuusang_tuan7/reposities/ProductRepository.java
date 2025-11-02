package edu.iuh.fit.nguyenhuusang_tuan7.reposities;


import edu.iuh.fit.nguyenhuusang_tuan7.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * @Dự án: 22669281_NguyenHuuSang_Tuan7
 * @Interface: ProductRepository
 * @Tạo vào ngày: 10/13/2025
 * @Tác giả: Nguyen Huu Sang
 */

public interface ProductRepository extends JpaRepository<Product, Integer> {
    // Tìm kiếm sản phẩm theo tên (LIKE)
    List<Product> findByNameContainingIgnoreCase(String name);
}