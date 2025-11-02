package edu.iuh.fit.nguyenhuusang_tuan7.services;

import edu.iuh.fit.nguyenhuusang_tuan7.entities.Customer;
import edu.iuh.fit.nguyenhuusang_tuan7.entities.Order;
import edu.iuh.fit.nguyenhuusang_tuan7.reposities.CustomerRepository;
import edu.iuh.fit.nguyenhuusang_tuan7.reposities.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @Dự án: 22669281_NguyenHuuSang_Tuan7
 * @Class: CustomerService
 * @Tạo vào ngày: 10/13/2025
 * @Tác giả: Nguyen Huu Sang
 */


@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private OrderRepository orderRepository;

    // CRUD
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Integer id) {
        return customerRepository.findById(id).orElse(null);
    }

    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public void deleteCustomer(Integer id) {
        // lấy danh sách Orders liên quan đến Customer
        List<Order> orders = orderRepository.findByCustomer_Id(id);

        // đặt customer của mỗi order thành null
        for (Order order :orders) {
            order.setCustomer(null);
            orderRepository.save(order); // lưu lại sau khi sửa
        }
        customerRepository.deleteById(id);
    }


    // Tìm kiếm khách theo tên (LIKE)
    public List<Customer> searchByName(String name) {
        return customerRepository.findByNameContainingIgnoreCase(name);
    }


}