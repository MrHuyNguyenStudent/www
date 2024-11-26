CREATE DATABASE ProjectTourtai_khoan CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE ProjectTour;
CREATE TABLE IF NOT EXISTS tai_khoan (
    id INT AUTO_INCREMENT PRIMARY KEY,      -- Mã tài khoản
    username VARCHAR(255) NOT NULL UNIQUE,  -- Tên đăng nhập (username)
    password VARCHAR(255) NOT NULL,         -- Mật khẩu (password)
    roles VARCHAR(255) NOT NULL             -- Quyền (roles) - ví dụ: 'USER', 'ADMIN'
);

CREATE TABLE IF NOT EXISTS khach_hang (
    id INT AUTO_INCREMENT PRIMARY KEY,          -- Mã khách hàng
    ho_ten VARCHAR(255) NOT NULL,                -- Họ tên khách hàng
    so_dien_thoai VARCHAR(15) NOT NULL,          -- Số điện thoại
    email VARCHAR(100) NOT NULL UNIQUE,          -- Email
    dia_chi TEXT,                               -- Địa chỉ
    id_tai_khoan INT NOT NULL,                  -- Mã tài khoản (liên kết với bảng tai_khoan)
    FOREIGN KEY (id_tai_khoan) REFERENCES tai_khoan(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS nhan_vien (
    id INT AUTO_INCREMENT PRIMARY KEY,          -- Mã nhân viên
    ho_ten VARCHAR(255) NOT NULL,                -- Họ tên nhân viên
    so_dien_thoai VARCHAR(15) NOT NULL,          -- Số điện thoại
    email VARCHAR(100) NOT NULL UNIQUE,          -- Email
    dia_chi TEXT,                               -- Địa chỉ
    id_tai_khoan INT NOT NULL,                  -- Mã tài khoản (liên kết với bảng tai_khoan)
    FOREIGN KEY (id_tai_khoan) REFERENCES tai_khoan(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS khuyen_mai (
    id INT AUTO_INCREMENT PRIMARY KEY,         -- Mã khuyến mãi
    ma_khuyen_mai VARCHAR(50) NOT NULL UNIQUE, -- Mã khuyến mãi
    mo_ta TEXT,                                -- Mô tả khuyến mãi
    gia_tri DOUBLE NOT NULL                    -- Giá trị khuyến mãi (giảm giá)
);

CREATE TABLE IF NOT EXISTS tour (
    id INT AUTO_INCREMENT PRIMARY KEY,        -- Mã tour
    ten_tour VARCHAR(255) NOT NULL,            -- Tên tour
    mo_ta TEXT,                               -- Mô tả tour
    gia DOUBLE,                               -- Giá tour
    hinh_anh VARCHAR(255)                     -- Hình ảnh tour
);

CREATE TABLE IF NOT EXISTS hoa_don (
    id INT AUTO_INCREMENT PRIMARY KEY,        -- Mã hóa đơn
    ma_khach_hang INT,                        -- Mã khách hàng (liên kết bảng khach_hang)
    ma_nhan_vien INT,                         -- Mã nhân viên (liên kết bảng nhan_vien)
    ngay_lap DATE NOT NULL,                   -- Ngày lập hóa đơn
    tong_tien DECIMAL(15, 2) DEFAULT 0.0,     -- Tổng tiền hóa đơn
    FOREIGN KEY (ma_khach_hang) REFERENCES khach_hang(id) ON DELETE SET NULL,
    FOREIGN KEY (ma_nhan_vien) REFERENCES nhan_vien(id) ON DELETE SET NULL
);

-- Bảng chi tiết hóa đơn (Mỗi hóa đơn có thể có nhiều tour)
CREATE TABLE IF NOT EXISTS chi_tiet_hoa_don (
    id INT AUTO_INCREMENT PRIMARY KEY,        -- Mã chi tiết hóa đơn
    hoa_don_id INT NOT NULL,                  -- Mã hóa đơn (liên kết bảng hoa_don)
    tour_id INT NOT NULL,                     -- Mã tour (liên kết bảng tour)
    so_luong INT NOT NULL DEFAULT 1,          -- Số lượng tour
    don_gia DECIMAL(15, 2) NOT NULL,          -- Đơn giá mỗi tour
    ma_khuyen_mai INT,                        -- Mã khuyến mãi (liên kết bảng khuyen_mai)
    FOREIGN KEY (hoa_don_id) REFERENCES hoa_don(id) ON DELETE CASCADE,
    FOREIGN KEY (tour_id) REFERENCES tour(id) ON DELETE CASCADE,
    FOREIGN KEY (ma_khuyen_mai) REFERENCES khuyen_mai(id) ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS dia_diem (
    id INT AUTO_INCREMENT PRIMARY KEY,       -- Mã địa điểm
    ten_dia_diem VARCHAR(255) NOT NULL,      -- Tên địa điểm
    dia_chi TEXT,                            -- Địa chỉ
    hinh_anh VARCHAR(255)                    -- Hình ảnh địa điểm
);

CREATE TABLE IF NOT EXISTS tour_dia_diem (
    tour_id INT NOT NULL,                     -- Mã tour
    dia_diem_id INT NOT NULL,                 -- Mã địa điểm
    PRIMARY KEY (tour_id, dia_diem_id),       -- Khóa chính gồm tour_id và dia_diem_id
    FOREIGN KEY (tour_id) REFERENCES tour(id) ON DELETE CASCADE,
    FOREIGN KEY (dia_diem_id) REFERENCES dia_diem(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS gio_hang (
    id INT AUTO_INCREMENT PRIMARY KEY,         -- Mã giỏ hàng
    id_khach_hang INT NOT NULL,                -- Mã khách hàng (liên kết với bảng khach_hang)
    tong_tien DOUBLE NOT NULL,                 -- Tổng tiền giỏ hàng
    FOREIGN KEY (id_khach_hang) REFERENCES khach_hang(id) ON DELETE CASCADE
);

-- Now, create chi_tiet_gio_hang after gio_hang is created
CREATE TABLE IF NOT EXISTS chi_tiet_gio_hang (
    id INT AUTO_INCREMENT PRIMARY KEY,         -- Mã chi tiết giỏ hàng
    gio_hang_id INT NOT NULL,                  -- Mã giỏ hàng (liên kết với bảng gio_hang)
    tour_id INT NOT NULL,                      -- Mã tour (liên kết với bảng tour)
    so_luong INT NOT NULL,                     -- Số lượng tour
    don_gia DOUBLE NOT NULL,                   -- Đơn giá của tour
    FOREIGN KEY (gio_hang_id) REFERENCES gio_hang(id) ON DELETE CASCADE,
    FOREIGN KEY (tour_id) REFERENCES tour(id) ON DELETE CASCADE
);
