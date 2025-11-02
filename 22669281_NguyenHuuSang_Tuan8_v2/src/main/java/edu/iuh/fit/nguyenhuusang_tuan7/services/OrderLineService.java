package edu.iuh.fit.nguyenhuusang_tuan7.services;

import edu.iuh.fit.nguyenhuusang_tuan7.entities.Orderline;
import edu.iuh.fit.nguyenhuusang_tuan7.reposities.OrderLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderLineService {
    @Autowired
    private OrderLineRepository orderLineRepository;

    // CRUD
    public List<Orderline> getAllOrderLines() {
        return orderLineRepository.findAll();
    }

    public Orderline getOrderLineById(Integer id) {
        return orderLineRepository.findById(id).orElse(null);
    }

    public Orderline saveOrderLine(Orderline orderLine) {
        return orderLineRepository.save(orderLine);
    }

    public void deleteOrderLine(Integer id) {
        orderLineRepository.deleteById(id);
    }

    // Lấy các orderline theo order
    public List<Orderline> getOrderLinesByOrderId(Integer orderId) {
        return orderLineRepository.findByOrder_Id(orderId);
    }

    // Lấy các orderline theo product
    public List<Orderline> getOrderLinesByProductId(Integer productId) {
        return orderLineRepository.findByProduct_Id(productId);
    }

    public List<Orderline> findOrderLineById(Integer id) {
        return orderLineRepository.findByOrder_Id(id);
    }
}