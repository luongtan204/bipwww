package edu.iuh.fit.nguyenhuusang_tuan7.services;

/**
 * @Dự án: 22669281_NguyenHuuSang_Tuan7
 * @Class: CommentService
 * @Tạo vào ngày: 10/13/2025
 * @Tác giả: Nguyen Huu Sang
 */



import edu.iuh.fit.nguyenhuusang_tuan7.entities.Comment;
import edu.iuh.fit.nguyenhuusang_tuan7.reposities.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> getCommentsByProductId(Integer productId) {
        return commentRepository.findByProduct_Id(productId);
    }

    public Comment saveComment(Comment comment) {
        return commentRepository.save(comment);
    }
}