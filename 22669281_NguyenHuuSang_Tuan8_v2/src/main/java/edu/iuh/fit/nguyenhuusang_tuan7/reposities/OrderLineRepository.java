package edu.iuh.fit.nguyenhuusang_tuan7.reposities;

import edu.iuh.fit.nguyenhuusang_tuan7.entities.Orderline;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * @Dự án: 22669281_NguyenHuuSang_Tuan7
 * @Interface: OrderLineRepository
 * @Tạo vào ngày: 10/13/2025
 * @Tác giả: Nguyen Huu Sang
 */

public interface OrderLineRepository extends JpaRepository<Orderline, Integer> {
    // Lấy các orderline theo order
    List<Orderline> findByOrder_Id(Integer orderId);

    // Lấy các orderline theo product
    List<Orderline> findByProduct_Id(Integer productId);

}