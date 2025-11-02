package edu.iuh.fit.nguyenhuusang_tuan7.reposities;

import edu.iuh.fit.nguyenhuusang_tuan7.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * @Dự án: 22669281_NguyenHuuSang_Tuan7
 * @Interface: CustomerRepository
 * @Tạo vào ngày: 10/13/2025
 * @Tác giả: Nguyen Huu Sang
 */

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    // Tìm kiếm khách theo tên (LIKE)
    List<Customer> findByNameContainingIgnoreCase(String name);
}