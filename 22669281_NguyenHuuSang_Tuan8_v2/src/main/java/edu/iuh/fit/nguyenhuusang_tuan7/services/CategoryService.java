package edu.iuh.fit.nguyenhuusang_tuan7.services;

import edu.iuh.fit.nguyenhuusang_tuan7.entities.Category;
import edu.iuh.fit.nguyenhuusang_tuan7.reposities.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @Dự án: 22669281_NguyenHuuSang_Tuan7
 * @Class: CategoryService
 * @Tạo vào ngày: 10/20/2025
 * @Tác giả: Nguyen Huu Sang
 */

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    // Lấy tất cả các danh mục
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    // Lấy danh mục theo ID
    public Category getCategoryById(Integer id) {
        return categoryRepository.findById(id).orElse(null);
    }

    // Thêm hoặc cập nhật danh mục
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    // Xóa danh mục
    public void deleteCategory(Integer id) {
        categoryRepository.deleteById(id);
    }

    // Method to fetch all categories
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
}