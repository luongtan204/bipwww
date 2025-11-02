package edu.iuh.fit.nguyenhuusang_tuan7.services;

import edu.iuh.fit.nguyenhuusang_tuan7.entities.Order;
import edu.iuh.fit.nguyenhuusang_tuan7.reposities.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;


/**
 * @Dự án: 22669281_NguyenHuuSang_Tuan7
 * @Class: OrderService
 * @Tạo vào ngày: 10/13/2025
 * @Tác giả: Nguyen Huu Sang
 */



@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    // CRUD
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Integer id) {
        return orderRepository.findById(id).orElse(null);
    }

    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    public void deleteOrder(Integer id) {
        orderRepository.deleteById(id);
    }

    // Tìm hóa đơn theo customerId
    public List<Order> getOrdersByCustomerId(Integer customerId) {
        return orderRepository.findByCustomer_Id(customerId);
    }

    // Tìm hóa đơn theo ngày
    public List<Order> getOrdersByDate(LocalDate date) {
        return orderRepository.findByDate(date);
    }
}