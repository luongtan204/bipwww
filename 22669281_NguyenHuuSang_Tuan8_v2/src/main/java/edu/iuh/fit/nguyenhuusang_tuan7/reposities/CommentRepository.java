package edu.iuh.fit.nguyenhuusang_tuan7.reposities;

import edu.iuh.fit.nguyenhuusang_tuan7.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


/**
 * @Dự án: 22669281_NguyenHuuSang_Tuan7
 * @Interface: CommentRepository
 * @Tạo vào ngày: 10/13/2025
 * @Tác giả: Nguyen Huu Sang
 */

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findByProduct_Id(Integer productId);
}