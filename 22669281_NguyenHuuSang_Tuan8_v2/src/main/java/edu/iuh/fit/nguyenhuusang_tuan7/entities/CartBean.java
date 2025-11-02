package edu.iuh.fit.nguyenhuusang_tuan7.entities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @Dự án: 22669281_NguyenHuuSang_Tuan7
 * @Class: CartBean
 * @Tạo vào ngày: 10/27/2025
 * @Tác giả: Nguyen Huu Sang
 */
public class CartBean {
    private List<CartItemBean> items;

    public CartBean() {
        items = new ArrayList<>();
    }

    public CartBean(List<CartItemBean> items) {
        this.items = items;
    }

    public List<CartItemBean> getItems() {
        return items;
    }

    public void setItems(List<CartItemBean> items) {
        this.items = items;
    }

    /**
     * Thêm sản phẩm vào giỏ hàng
     * @param product Sản phẩm cần thêm
     * @param quantity Số lượng
     */
    public void addProduct(Product product, int quantity) {
        // Kiểm tra xem sản phẩm đã có trong giỏ hàng chưa
        for (CartItemBean item : items) {
            if (item.getProduct().getId().equals(product.getId())) {
                // Nếu có rồi thì tăng số lượng
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }
        // Nếu chưa có thì thêm mới
        items.add(new CartItemBean(product, quantity));
    }

    /**
     * Xóa sản phẩm khỏi giỏ hàng
     * @param productId ID của sản phẩm cần xóa
     */
    public void removeProduct(Integer productId) {
        items.removeIf(item -> item.getProduct().getId().equals(productId));
    }

    /**
     * Cập nhật số lượng sản phẩm trong giỏ hàng
     * @param productId ID của sản phẩm
     * @param quantity Số lượng mới
     */
    public void updateQuantity(Integer productId, int quantity) {
        for (CartItemBean item : items) {
            if (item.getProduct().getId().equals(productId)) {
                if (quantity > 0) {
                    item.setQuantity(quantity);
                } else {
                    // Nếu số lượng <= 0 thì xóa sản phẩm khỏi giỏ hàng
                    removeProduct(productId);
                }
                return;
            }
        }
    }

    /**
     * Tính tổng tiền của giỏ hàng
     * @return Tổng tiền
     */
    public BigDecimal getTotal() {
        BigDecimal total = BigDecimal.ZERO;
        for (CartItemBean item : items) {
            total = total.add(item.getSubtotal());
        }
        return total;
    }

    /**
     * Xóa toàn bộ giỏ hàng
     */
    public void clear() {
        items.clear();
    }

    /**
     * Kiểm tra giỏ hàng có rỗng không
     * @return true nếu giỏ hàng rỗng, false nếu có sản phẩm
     */
    public boolean isEmpty() {
        return items.isEmpty();
    }

    /**
     * Lấy tổng số sản phẩm trong giỏ hàng
     * @return Tổng số sản phẩm
     */
    public int getTotalItems() {
        int total = 0;
        for (CartItemBean item : items) {
            total += item.getQuantity();
        }
        return total;
    }

    /**
     * Tìm CartItem theo productId
     * @param productId ID sản phẩm
     * @return CartItemBean hoặc null nếu không tìm thấy
     */
    public CartItemBean getItemByProductId(Integer productId) {
        for (CartItemBean item : items) {
            if (item.getProduct().getId().equals(productId)) {
                return item;
            }
        }
        return null;
    }
}