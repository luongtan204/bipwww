package edu.iuh.fit.nguyenhuusang_tuan7.controller;

import edu.iuh.fit.nguyenhuusang_tuan7.entities.*;
import edu.iuh.fit.nguyenhuusang_tuan7.services.CustomerService;
import edu.iuh.fit.nguyenhuusang_tuan7.services.OrderLineService;
import edu.iuh.fit.nguyenhuusang_tuan7.services.OrderService;
import edu.iuh.fit.nguyenhuusang_tuan7.services.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderLineService orderLineService;

    // Lấy giỏ hàng từ session
    private CartBean getCart(HttpSession session) {
        CartBean cart = (CartBean) session.getAttribute("cart");
        if (cart == null) {
            cart = new CartBean();
            session.setAttribute("cart", cart);
        }
        return cart;
    }

    // Hiển thị danh sách hóa đơn
    @GetMapping
    public String listOrders(Model model) {
        List<Order> orders = orderService.getAllOrders();
        model.addAttribute("orders", orders);
        return "order/orders";
    }

    // Xem chi tiết hóa đơn
    @GetMapping("/detail/{id}")
    public String viewDetailOrder(@PathVariable Integer id, Model model) {
        List<Orderline> orderLines = orderLineService.findOrderLineById(id);
//        System.out.println("orderLines: " + orderLines);
        model.addAttribute("orderLines",orderLines);
        return "order/order-detail";
    }

    // Hiển thị giỏ hàng
    @GetMapping("/cart")
    public String viewCart(HttpSession session, Model model) {
        CartBean cart = getCart(session);
        model.addAttribute("cart", cart);
        model.addAttribute("products", productService.getAllProducts());
        return "order/cart";
    }

    // Thêm sản phẩm vào giỏ hàng
    @PostMapping("/cart/add")
    public String addToCart(@RequestParam Integer productId,
                            @RequestParam(defaultValue = "1") int quantity,
                            HttpSession session) {
        Product product = productService.getProductById(productId);
        if (product != null) {
            CartBean cart = getCart(session);
            cart.addProduct(product, quantity);
        }
        return "redirect:/orders/cart";
    }

    // Cập nhật số lượng trong giỏ hàng
    @PostMapping("/cart/update")
    public String updateCart(@RequestParam Integer productId,
                             @RequestParam int quantity,
                             HttpSession session) {
        CartBean cart = getCart(session);
        cart.updateQuantity(productId, quantity);
        return "redirect:/orders/cart";
    }

    // Xóa sản phẩm khỏi giỏ hàng
    @GetMapping("/cart/remove/{productId}")
    public String removeFromCart(@PathVariable Integer productId, HttpSession session) {
        CartBean cart = getCart(session);
        cart.removeProduct(productId);
        return "redirect:/orders/cart";
    }

    // Xóa toàn bộ giỏ hàng
    @GetMapping("/cart/clear")
    public String clearCart(HttpSession session) {
        CartBean cart = getCart(session);
        cart.clear();
        return "redirect:/orders/cart";
    }

    // Checkout - hiển thị trang thanh toán
    @GetMapping("/checkout")
    public String showCheckout(HttpSession session, Model model) {
        CartBean cart = getCart(session);
        if (cart.isEmpty()) {
            return "redirect:/orders/cart";
        }
        model.addAttribute("cart", cart);
        model.addAttribute("customers", customerService.getAllCustomers());
        return "order/checkout";
    }

    // Xử lý checkout - tạo đơn hàng từ giỏ hàng
    @PostMapping("/checkout")
    public String processCheckout(@RequestParam Integer customerId, HttpSession session) {
        CartBean cart = getCart(session);

        if (cart.isEmpty()) {
            return "redirect:/orders/cart";
        }

        // Tạo Order mới
        Order order = new Order();
        order.setDate(LocalDate.now()); // Sử dụng LocalDate.now()

        // Lấy customer từ customerId
        Customer customer = customerService.getCustomerById(customerId);
        order.setCustomer(customer);

        // Chuyển CartBean thành OrderLines
        List<Orderline> orderLines = new ArrayList<>();
        for (CartItemBean cartItem : cart.getItems()) {
            Orderline line = new Orderline();
            line.setOrder(order);
            line.setProduct(cartItem.getProduct());
            line.setAmount(cartItem.getQuantity());
            line.setPurchasePrice(cartItem.getSubtotal());
            orderLines.add(line);
        }

        order.setOrderLines(orderLines);
        orderService.saveOrder(order);

        // Xóa giỏ hàng sau khi đặt hàng
        cart.clear();

        return "redirect:/orders/detail/" + order.getId();
    }

    // Xóa hóa đơn
    @GetMapping("/delete/{id}")
    public String deleteOrder(@PathVariable Integer id) {
        orderService.deleteOrder(id);
        return "redirect:/orders";
    }

    // Tìm kiếm hóa đơn theo mã khách hàng
    @GetMapping("/search/customer")
    public String searchByCustomer(@RequestParam Integer customerId, Model model) {
        List<Order> orders = orderService.getOrdersByCustomerId(customerId);
        model.addAttribute("orders", orders);
        model.addAttribute("customerId", customerId);
        return "order/orders";
    }

    // Tìm kiếm hóa đơn theo ngày
    @GetMapping("/search")
    public String searchByDate(@RequestParam String date, Model model) {
        try {
            // Chuyển date string sang LocalDate
            LocalDate searchDate = LocalDate.parse(date);
            List<Order> orders = orderService.getOrdersByDate(searchDate);
            model.addAttribute("orders", orders);
            model.addAttribute("keyword", date);
        } catch (Exception e) {
            model.addAttribute("orders", orderService.getAllOrders());
        }
        return "order/orders";
    }
}