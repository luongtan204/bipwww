package fit.se.lan1.controllers;

import fit.se.lan1.entities.Product;
import fit.se.lan1.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.time.LocalDate.*;

@Controller
@RequestMapping("/product")
public class ProductController {
    // Cấu hình thư mục upload
    @Value("${app.upload.dir:src/main/resources/static/uploads/products/}")
    private String uploadDir;

    @Autowired
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping
    public String getAllProduct(Model model ){
        List<Product> list=productService.findAll();
        model.addAttribute("products",list);
        return "product/list";
    }
    @GetMapping("/{id}")
    public String showProduct(@PathVariable String id, Model model ){
        Product list=productService.findById(id).orElse(null);
        model.addAttribute("products",list);
        return "product/productdetail";
    }
    @GetMapping("/add")
    public String showAddForm(Model model) {
       model.addAttribute("product",new Product());
        return "product/add";
    }
    @PostMapping("/save")
    @PreAuthorize("hasRole('ADMIN')")
    public String saveProduct(@ModelAttribute Product product, @RequestParam("file")MultipartFile file) throws IOException {
        product.setCreateAt(Date.valueOf(LocalDate.now()));

        // 2. Xử lý upload file
        if (file != null && !file.isEmpty()) {
            String imagePath = saveFile(file);
            product.setImage(imagePath);
        }

        // 3. Lưu product
        productService.add(product);

        return "redirect:/product";
    }
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") String id, Model model) {
        Product product = productService.findById(id).orElse(null);
        if (product == null) {
            return "redirect:/product";
        }
        model.addAttribute("product", product);

        return "product/edit";
    }

    // Cập nhật sản phẩm - CHỈ ADMIN
    @PostMapping("/update")

    public String updateProduct(@ModelAttribute("product") Product product,
                                @RequestParam("file") MultipartFile file) throws IOException {
        // Thư mục uploads ở project root
        String uploadDir = System.getProperty("user.dir") + File.separator + "uploads";
//        String uploadDir = System.getProperty("user.dir")
//                + File.separator + "src"
//                + File.separator + "main"
//                + File.separator + "resources"
//                + File.separator + "uploads";
        File uploadFolder = new File(uploadDir);
        if (!uploadFolder.exists()) {
            uploadFolder.mkdirs();
        }

        if (!file.isEmpty()) {
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            File dest = new File(uploadFolder, fileName);
            file.transferTo(dest);
            product.setImage(fileName);
        } else {
            // Giữ ảnh cũ nếu không upload ảnh mới
            Product existing = productService.findById(product.getId()).orElse(null);
            if (existing != null) {
                product.setImage(existing.getImage());
            }
        }
        productService.add(product);
        return "redirect:/product";
    }

    // Xóa sản phẩm - CHỈ ADMIN
    @GetMapping("/delete/{id}")

    public String deleteProduct(@PathVariable("id") String id, Model model) {
        Product product = productService.findById(id).orElse(null);
        if (product == null) {
            return "redirect:/product";
        }

        productService.delete(id);
        return "redirect:/product";
    }

    // Method lưu file đơn giản
    private String saveFile(MultipartFile file) throws IOException {
        // Thư mục lưu file
        String uploadDir = "src/main/resources/static/uploads/products/";

        // Tạo thư mục nếu chưa có
        Path path = Paths.get(uploadDir);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }

        // Tạo tên file unique
        String originalName = file.getOriginalFilename();
        String extension = originalName.substring(originalName.lastIndexOf("."));
        String fileName = UUID.randomUUID().toString() + extension;

        // Lưu file
        Path filePath = path.resolve(fileName);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        // Trả về đường dẫn web
        return "/uploads/products/" + fileName;
    }
}
