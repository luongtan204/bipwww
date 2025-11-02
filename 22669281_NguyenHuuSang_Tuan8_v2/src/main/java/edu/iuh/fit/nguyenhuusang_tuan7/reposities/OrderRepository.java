package edu.iuh.fit.nguyenhuusang_tuan7.reposities;

import edu.iuh.fit.nguyenhuusang_tuan7.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

/**
 * @Dự án: 22669281_NguyenHuuSang_Tuan7
 * @Class: OrderReposity
 * @Tạo vào ngày: 10/13/2025
 * @Tác giả: Nguyen Huu Sang
 */

public interface OrderRepository extends JpaRepository<Order, Integer> {
    // Tìm kiếm hóa đơn theo tên khách hoặc mã khách (giả sử có trường customerId)
    List<Order> findByCustomer_Id(Integer customerId);

    // Tìm kiếm hóa đơn theo ngày
    List<Order> findByDate(LocalDate date);
}