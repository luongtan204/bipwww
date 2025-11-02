package edu.iuh.fit.nguyenhuusang_tuan7.controller;

import edu.iuh.fit.nguyenhuusang_tuan7.entities.Comment;
import edu.iuh.fit.nguyenhuusang_tuan7.entities.Product;
import edu.iuh.fit.nguyenhuusang_tuan7.services.CommentService;
import edu.iuh.fit.nguyenhuusang_tuan7.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @Dự án: 22669281_NguyenHuuSang_Tuan7
 * @Class: CommentController
 * @Tạo vào ngày: 10/13/2025
 * @Tác giả: Nguyen Huu Sang
 */


@Controller
@RequestMapping("/products/{productId}/comments")
public class CommentController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CommentService commentService;

    // Hiển thị form thêm comment
    @GetMapping("/add")
    public String showCommentForm(@PathVariable Integer productId, Model model) {
        Product product = productService.getProductById(productId);
        model.addAttribute("product", product);
        model.addAttribute("comment", new Comment());
        return "product/comment-form";
    }

    // Xử lý thêm comment
    @PostMapping("/add")
    public String addComment(@PathVariable Integer productId, @ModelAttribute Comment comment) {
        Product product = productService.getProductById(productId);
        comment.setProduct(product);
        commentService.saveComment(comment);
        return "redirect:/products/detail/" + productId;
    }
}