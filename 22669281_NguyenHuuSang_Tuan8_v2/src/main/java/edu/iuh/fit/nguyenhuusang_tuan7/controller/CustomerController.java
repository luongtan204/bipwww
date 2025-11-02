package edu.iuh.fit.nguyenhuusang_tuan7.controller;

import edu.iuh.fit.nguyenhuusang_tuan7.entities.Customer;
import edu.iuh.fit.nguyenhuusang_tuan7.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

 import java.util.List;

/**
 * @Dự án: 22669281_NguyenHuuSang_Tuan7
 * @Class: CustomerController
 * @Tạo vào ngày: 10/13/2025
 * @Tác giả: Nguyen Huu Sang
 */

@Controller
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    // Hiển thị danh sách khách hàng
    @GetMapping
    public String listCustomers(Model model) {
        List<Customer> customers = customerService.getAllCustomers();
        model.addAttribute("customers", customers);
        System.out.println(customers);
        return "customer/customers";
    }

    // Chi tiết khách hàng
    @GetMapping("/detail/{id}")
    public String viewDetail(@PathVariable Integer id, Model model) {
        Customer customer = customerService.getCustomerById(id);
        model.addAttribute("customer", customer);
        return "customer/customer-detail";
    }

    // Thêm khách hàng
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "customer/customer-form";
    }

    @PostMapping("/add")
    public String addCustomer(@ModelAttribute Customer customer) {
        customerService.saveCustomer(customer);
        return "redirect:/customers";
    }

    // Sửa khách hàng
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        Customer customer = customerService.getCustomerById(id);
        model.addAttribute("customer", customer);
        return "customer/customer-form";
    }

    @PostMapping("/edit/{id}")
    public String editCustomer(@PathVariable Integer id, @ModelAttribute Customer customer) {
        customer.setId(id);
        customerService.saveCustomer(customer);
        return "redirect:/customers";
    }

    // Xóa khách hàng
    @GetMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable Integer id) {
        customerService.deleteCustomer(id);
        return "redirect:/customers";
    }

    // Tìm kiếm khách hàng
    @GetMapping("/search")
    public String searchCustomers(@RequestParam String name, Model model) {
        List<Customer> customers = customerService.searchByName(name);
        model.addAttribute("customers", customers);
        model.addAttribute("keyword", name);
        return "customer/customers";
    }
}