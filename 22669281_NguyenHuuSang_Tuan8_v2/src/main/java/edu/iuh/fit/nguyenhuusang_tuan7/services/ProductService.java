package edu.iuh.fit.nguyenhuusang_tuan7.services;

import edu.iuh.fit.nguyenhuusang_tuan7.entities.Product;
import edu.iuh.fit.nguyenhuusang_tuan7.reposities.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @Dự án: 22669281_NguyenHuuSang_Tuan7
 * @Class: ProductService
 * @Tạo vào ngày: 10/13/2025
 * @Tác giả: Nguyen Huu Sang
 */



@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    // CRUD
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Integer id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);
    }

    // Tìm kiếm sản phẩm theo tên
    public List<Product> searchByName(String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }
}