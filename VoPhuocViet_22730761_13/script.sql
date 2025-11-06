-- Create database
CREATE DATABASE IF NOT EXISTS detai_db;
USE detai_db;

-- Create GIANGVIEN table
CREATE TABLE IF NOT EXISTS GIANGVIEN (
    MAGV VARCHAR(50) PRIMARY KEY,
    TENGV VARCHAR(100) NOT NULL,
    LINHVUCNGHIENCUU VARCHAR(200),
    EMAIL VARCHAR(100) NOT NULL UNIQUE
);

-- Create DETAI table
CREATE TABLE IF NOT EXISTS DETAI (
    MADETAI VARCHAR(50) PRIMARY KEY,
    TENDETAI VARCHAR(200) NOT NULL,
    NAM INT NOT NULL,
    MOTADETAI TEXT,
    MADANGKY VARCHAR(50),
    MAGV VARCHAR(50) NOT NULL,
    FOREIGN KEY (MAGV) REFERENCES GIANGVIEN(MAGV)
);

-- Insert sample data for GIANGVIEN
INSERT INTO GIANGVIEN (MAGV, TENGV, LINHVUCNGHIENCUU, EMAIL) VALUES
('GV001', 'Nguyễn Văn A', 'Trí tuệ nhân tạo', 'nguyenvana@fit.edu.vn'),
('GV002', 'Trần Thị B', 'Khoa học dữ liệu', 'tranthib@fit.edu.vn'),
('GV003', 'Lê Văn C', 'An ninh mạng', 'levanc@fit.edu.vn');

-- Insert sample data for DETAI
INSERT INTO DETAI (MADETAI, TENDETAI, NAM, MOTADETAI, MADANGKY, MAGV) VALUES
('DT001', 'Xây dựng hệ thống quản lý sinh viên', 2024, 'Hệ thống quản lý thông tin sinh viên sử dụng Spring Boot', NULL, 'GV001'),
('DT002', 'Phân tích dữ liệu lớn với Hadoop', 2024, 'Nghiên cứu và ứng dụng Hadoop trong phân tích dữ liệu', NULL, 'GV002'),
('DT003', 'Hệ thống phát hiện xâm nhập mạng', 2024, 'Xây dựng hệ thống IDS sử dụng machine learning', NULL, 'GV003');

