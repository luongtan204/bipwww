-- Tạo database
CREATE DATABASE IF NOT EXISTS shoppingdb CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE shoppingdb;

-- Tạo bảng Category
CREATE TABLE category (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL
);

-- Tạo bảng Product
CREATE TABLE product (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(200) NOT NULL,
    price DECIMAL(12,2) NOT NULL,
    in_stock BOOLEAN DEFAULT TRUE,
    quantity INT DEFAULT 0, -- Thêm cột quantity để lưu số lượng sản phẩm
    image VARCHAR(500), -- Thêm cột image để lưu đường dẫn ảnh
    category_id INT,
    FOREIGN KEY (category_id) REFERENCES category(id)
);

-- Tạo bảng Customer
CREATE TABLE customer (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(200) NOT NULL,
    customer_since DATE NOT NULL
);

-- Tạo bảng Users (đổi tên để tránh từ khóa)
CREATE TABLE users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    role VARCHAR(50) NOT NULL
);

-- Tạo bảng Orders
CREATE TABLE orders (
    id INT PRIMARY KEY AUTO_INCREMENT,
    date DATE NOT NULL,
    customer_id INT,
    FOREIGN KEY (customer_id) REFERENCES customer(id)
);

-- Tạo bảng OrderLine
CREATE TABLE orderline (
    id INT PRIMARY KEY AUTO_INCREMENT,
    order_id INT,
    product_id INT,
    amount INT NOT NULL,
    purchase_price DECIMAL(12,2) NOT NULL,
    FOREIGN KEY (order_id) REFERENCES orders(id),
    FOREIGN KEY (product_id) REFERENCES product(id)
);

-- Tạo bảng Comment
CREATE TABLE comment (
    id INT PRIMARY KEY AUTO_INCREMENT,
    product_id INT,
    text VARCHAR(500),
    FOREIGN KEY (product_id) REFERENCES product(id)
);

-- Dữ liệu mẫu cho category
INSERT INTO category (name) VALUES ('Electronics'), ('Books'), ('Clothing');

-- Dữ liệu mẫu cho product
INSERT INTO product (name, price, in_stock, quantity, image, category_id) VALUES
    ('Laptop Dell XPS 13', 22000000, TRUE, 10, 'images/laptop_dell_xps_13.jpg', 1),
    ('Smartphone iPhone 15', 27000000, TRUE, 15, 'images/iphone_15.jpg', 1),
    ('T-shirt Polo', 450000, TRUE, 30, 'images/tshirt_polo.jpg', 3),
    ('Book: Spring Boot in Action', 320000, TRUE, 25, 'images/spring_boot_in_action.jpg', 2),
    ('Book: Java Core', 400000, TRUE, 20, 'images/java_core.jpg', 2);

-- Dữ liệu mẫu cho customer
INSERT INTO customer (name, customer_since) VALUES
    ('Nguyen Van A', '2022-05-10'),
    ('Tran Thi B', '2023-01-20'),
    ('Pham Van C', '2021-11-15');

-- Dữ liệu mẫu cho users (quản trị hệ thống)
INSERT INTO users (username, password, role) VALUES
    ('admin', 'admin123', 'ADMIN'),
    ('user1', 'user1123', 'USER'),
    ('manager', 'manager123', 'MANAGER');

-- Dữ liệu mẫu cho orders
INSERT INTO orders (date, customer_id) VALUES
    ('2023-08-01', 1),
    ('2023-08-02', 2),
    ('2023-08-03', 3),
    ('2023-08-05', 1);

-- Dữ liệu mẫu cho orderline
INSERT INTO orderline (order_id, product_id, amount, purchase_price) VALUES
    (1, 1, 1, 22000000), -- Laptop Dell XPS 13
    (1, 4, 2, 320000),   -- Book: Spring Boot in Action
    (2, 2, 1, 27000000), -- iPhone 15
    (2, 3, 3, 450000),   -- T-shirt Polo
    (3, 5, 1, 400000),   -- Book: Java Core
    (4, 1, 1, 22000000); -- Laptop Dell XPS 13

-- Dữ liệu mẫu cho comment
INSERT INTO comment (product_id, text) VALUES
    (1, 'Laptop chạy rất mượt, hài lòng!'),
    (2, 'iPhone camera rất đẹp!'),
    (4, 'Sách hướng dẫn chi tiết, phù hợp cho người mới bắt đầu.'),
    (3, 'Áo Polo chất liệu tốt, mặc thoải mái');