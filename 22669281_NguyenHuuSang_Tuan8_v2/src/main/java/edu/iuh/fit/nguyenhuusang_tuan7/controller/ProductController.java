package edu.iuh.fit.nguyenhuusang_tuan7.controller;

import edu.iuh.fit.nguyenhuusang_tuan7.entities.CartBean;
import edu.iuh.fit.nguyenhuusang_tuan7.entities.Product;
import edu.iuh.fit.nguyenhuusang_tuan7.services.CategoryService;
import edu.iuh.fit.nguyenhuusang_tuan7.services.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    // Đường dẫn thư mục upload được cấu hình trong application.properties
    @Value("${file.upload-dir:uploads}")
    private String uploadDir;


    // Lấy giỏ hàng từ session
    private CartBean getCart(HttpSession session) {
        CartBean cart = (CartBean) session.getAttribute("cart");
        if (cart == null) {
            cart = new CartBean();
            session.setAttribute("cart", cart);
        }
        return cart;
    }

    // Hiển thị toàn bộ sản phẩm
    @GetMapping
    public String listProducts(Model model, HttpSession session) {
        List<Product> products = productService.getAllProducts();
        CartBean cart = getCart(session);
        model.addAttribute("products", products);
        model.addAttribute("cart", cart);
        return "product/products";
    }

    // Chi tiết sản phẩm
    @GetMapping("/detail/{id}")
    public String viewDetail(@PathVariable Integer id, Model model, HttpSession session) {
        Product product = productService.getProductById(id);
        CartBean cart = getCart(session);
        model.addAttribute("product", product);
        model.addAttribute("cart", cart);
        return "product/product-detail";
    }

    // Hiển thị form thêm sản phẩm
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryService.findAll());
        return "product/product-form";
    }

    // Lưu sản phẩm mới
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    public String addProduct(@ModelAttribute Product product,
                             @RequestParam("fileImage") MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            // Sử dụng cấu hình uploadDir từ application.properties
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path uploadPath = Paths.get(uploadDir);
            Files.createDirectories(uploadPath); // Tạo thư mục nếu chưa tồn tại
            file.transferTo(uploadPath.resolve(fileName)); // Lưu file vào thư mục cấu hình
            product.setImage(fileName); // Lưu tên file vào database
        }
        productService.saveProduct(product);
        return "redirect:/products";
    }


    // Hiển thị form chỉnh sửa sản phẩm
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        model.addAttribute("categories", categoryService.findAll());
        return "product/product-form";
    }

    // Cập nhật sản phẩm
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/edit/{id}")
    public String editProduct(@PathVariable Integer id, @ModelAttribute Product product) {
        product.setId(id);
        productService.saveProduct(product);
        return "redirect:/products";
    }

    // Xóa sản phẩm
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Integer id) {
        productService.deleteProduct(id);
        return "redirect:/products";
    }

    // Tìm kiếm sản phẩm
    @GetMapping("/search")
    public String searchProducts(@RequestParam String name, Model model, HttpSession session) {
        List<Product> products = productService.searchByName(name);
        CartBean cart = getCart(session);
        model.addAttribute("products", products);
        model.addAttribute("keyword", name);
        model.addAttribute("cart", cart);
        return "product/products";
    }
}