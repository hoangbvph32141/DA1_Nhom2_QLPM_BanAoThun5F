﻿CREATE DATABASE DA1_NHOM2
GO

USE DA1_NHOM2
GO

CREATE TABLE NHANVIEN(
	   ID INT PRIMARY KEY IDENTITY(1,1),
	   MANV VARCHAR(20),
	   TENNV NVARCHAR(50),
	   GIOITINH INT,
	   NGAYSINH DATE,
	   CCCD VARCHAR(13),
	   DIACHI NVARCHAR(MAX),
	   SDT VARCHAR(12),
	   EMAIL VARCHAR(50),
	   MATKHAU VARCHAR(50),
	   TRANGTHAINV INT,
	   CHUCVU INT -- 1 NV | 2 QL
);
GO

CREATE TABLE MAUSAC(
	   ID INT PRIMARY KEY IDENTITY(1,1),
	   MAMS VARCHAR(20),
	   TENMS NVARCHAR(50),
	   TRANGTHAIMS INT
);
GO

CREATE TABLE CHATLIEU(
	   ID INT PRIMARY KEY IDENTITY(1,1),
	   MACL VARCHAR(20),
	   TENCL NVARCHAR(50),
	   TRANGTHAICL INT
);
GO

CREATE TABLE THUONGHIEU(
	   ID INT PRIMARY KEY IDENTITY(1,1),
	   MATH VARCHAR(20),
	   TENTH NVARCHAR(50),
	   TRANGTHAITH INT
);
GO

CREATE TABLE KICHCO(
	   ID INT PRIMARY KEY IDENTITY(1,1),
	   MAKC VARCHAR(20),
	   TENKC NVARCHAR(50),
	   TRANGTHAIKC INT
);
GO

CREATE TABLE SANPHAM(
	   ID INT PRIMARY KEY IDENTITY(1,1),
	   MASP VARCHAR(20),
	   TENSP NVARCHAR(50),
	   TRANGTHAISP INT
);
GO

CREATE TABLE SANPHAMCHITIET(
	   ID INT PRIMARY KEY IDENTITY(1,1),
	   IDMS INT,
	   IDCL INT,
	   IDTH INT,
	   IDKC INT,
	   IDSP INT,
	   MASPCT VARCHAR(20),
	   NGUOITAO NVARCHAR(50),
	   SOLUONGTON INT,
	   MOTA NVARCHAR(MAX),
	   TRANGTHAISPCT INT,
	   DONGIA FLOAT,

	   FOREIGN KEY (IDMS) REFERENCES MAUSAC(ID),
	   FOREIGN KEY (IDCL) REFERENCES CHATLIEU(ID),
	   FOREIGN KEY (IDTH) REFERENCES THUONGHIEU(ID),
	   FOREIGN KEY (IDKC) REFERENCES KICHCO(ID),
	   FOREIGN KEY (IDSP) REFERENCES SANPHAM(ID)
);
GO

CREATE TABLE KHACHHANG(
	   ID INT PRIMARY KEY IDENTITY(1,1),
	   MAKH VARCHAR(20),
	   TENKH NVARCHAR(50),
	   NGAYSINH DATE,
	   GIOITINH BIT,
	   SDT VARCHAR(12),
	   DIACHI NVARCHAR(MAX),
	   TRANGTHAIKH INT
);
GO

CREATE TABLE KHUYENMAI(
	   ID INT PRIMARY KEY IDENTITY(1,1),
	   MAKM VARCHAR(20),
	   TENKM NVARCHAR(50),
	   MUCGIAMGIA FLOAT,
	   DIEUKIEN FLOAT,
	   THOIGIANBATDAU DATE,
	   THOIGIANKETTHUC DATE,
	   TRANGTHAIKM INT,
	   SOLUONG INT,
);
GO

CREATE TABLE KHUYENMAICHITIET(
	   ID INT PRIMARY KEY IDENTITY(1,1),
	   IDKM INT,
	   MAKMCT VARCHAR(20),
	   TRANGTHAIKMCT INT,

	   FOREIGN KEY (IDKM) REFERENCES KHUYENMAI(ID)
);
GO

CREATE TABLE HOADON(
	   ID INT PRIMARY KEY IDENTITY(1,1),
	   IDKH INT,
	   IDKM INT,
	   IDNV INT,
	   MAHD VARCHAR(20),
	   NGAYTAO DATETIME,
	   LYDOHUY NVARCHAR(MAX),
	   NGAYTHANHTOAN DATE,
	   DONGIASAUGIAM FLOAT,
	   TONGTIEN FLOAT,
	   TRANGTHAIHD INT,

	   FOREIGN KEY (IDKH) REFERENCES KHACHHANG(ID),
	   FOREIGN KEY (IDKM) REFERENCES KHUYENMAI(ID),
	   FOREIGN KEY (IDNV) REFERENCES NHANVIEN(ID)
);
GO

CREATE TABLE HOADONCT(
	   ID INT PRIMARY KEY IDENTITY(1,1),
	   IDHD INT,
	   IDSPCT INT,
	   MAHDCT VARCHAR(20),
	   NGAYTAO DATETIME,
	   DONGIA FLOAT,
	   DONGIASAUKHIGIAM FLOAT,
	   SOLUONG INT,

	   FOREIGN KEY (IDHD) REFERENCES HOADON(ID),
	   FOREIGN KEY (IDSPCT) REFERENCES SANPHAMCHITIET(ID)
);
GO

-- INSERT INTO NHANVIEN
INSERT INTO NHANVIEN (MANV,TENNV,GIOITINH,NGAYSINH,CCCD,DIACHI,SDT,EMAIL,MATKHAU,TRANGTHAINV,CHUCVU) VALUES --1 NV | 2 QL
('NV01',N'Vũ Xuân Kiên',1,'2003-12-16','001203030101',N'Hà Nội','0335613548','kienvxph27136@fpt.edu.vn','123',1,2),
('NV02',N'Bùi Vũ Hoàng',0,'2004-06-14','001298768787',N'Hải Dương','0965416951','hoangbvph32141@fpt.edu.vn','123',1,1),
('NV03',N'Ngọc Quốc Thiện',1,'2003-05-11','001202098789',N'Đông Anh','0937521482','thiennqph35476@fpt.edu.vn','123',1,2),
('NV04',N'Bùi Văn Hiếu',1,'2002-06-22','001204037486',N'Hà Nội','0965138987','hieubvph35580@fpt.edu.vn','456',0,2),
('NV05', N'Trần Thanh Tùng', 1, '1998-09-18', '001201234567', N'Hải Phòng', '0348765432', 'tungtt123@fpt.edu.vn', 'abc123', 0, 1),
('NV06', N'Phạm Thị Lan', 0, '1995-03-25', '001209876543', N'Quảng Ninh', '0321456789', 'lanpt@fpt.edu.vn', 'xyz123', 0, 2),
('NV07', N'Lê Minh Hùng', 1, '1999-11-12', '001204567890', N'Thái Nguyên', '0912345678', 'hunglm@fpt.edu.vn', 'pass123', 0, 2),
('NV08', N'Nguyễn Thị Mai', 0, '2001-04-08', '001207891234', N'Nam Định', '0356789123', 'maint@fpt.edu.vn', '321abc', 0, 1),
('NV09', N'Hoàng Văn Sơn', 1, '2000-01-30', '001203214567', N'Hà Nam', '0387654321', 'sonhv@fpt.edu.vn', 'son123', 0, 2),
('NV10', N'Trương Thanh Hà', 0, '1996-02-14', '001206543210', N'Nghệ An', '0923415678', 'hatt@fpt.edu.vn', 'qwe123', 0, 1),
('NV11', N'Đỗ Quang Vinh', 1, '1997-07-05', '001205678945', N'TP Hồ Chí Minh', '0378912345', 'vinhdq@fpt.edu.vn', 'v12345', 0, 2),
('NV12', N'Vũ Thị Hương', 0, '1998-08-21', '001203456789', N'Bắc Ninh', '0945612347', 'huongvt@fpt.edu.vn', 'huong123', 0, 2),
('NV13', N'Phạm Văn Khoa', 1, '2003-02-28', '001209654321', N'Hà Tĩnh', '0367543982', 'khoapv@fpt.edu.vn', 'khoa123', 0, 1),
('NV14', N'Lê Thị Thu', 0, '2002-12-10', '001203245789', N'Tuyên Quang', '0954123678', 'thult@fpt.edu.vn', 'thu123', 0, 2),
('NV15', N'Nguyễn Văn Tú', 1, '1994-10-15', '001201112233', N'Hòa Bình', '0398765432', 'tungv@fpt.edu.vn', 'tu12345', 0, 1);
GO




-- INSERT INTO MAUSAC
INSERT INTO MAUSAC (MAMS,TENMS,TRANGTHAIMS) VALUES
('MS01',N'Xám',1),
('MS02',N'Đen',1),
('MS03',N'Trắng',1),
('MS04',N'Xanh',0),
('MS05',N'Hồng',1),
('MS06', N'Đỏ', 1),
('MS07', N'Vàng', 1),
('MS08', N'Tím', 0),
('MS09', N'Xanh Lá', 1),
('MS10', N'Cam', 1),
('MS11', N'Nâu', 0),
('MS12', N'Bạc', 1),
('MS13', N'Xanh Dương', 1),
('MS14', N'Xanh Ngọc', 0),
('MS15', N'Be Nhạt', 1);
GO

-- INSERT INTO CHATLIEU
INSERT INTO CHATLIEU (MACL,TENCL,TRANGTHAICL) VALUES
('CL01',N'Kaki',1),
('CL02',N'Cotton',1),
('CL03',N'Polyester',1),
('CL04',N'Denim',1),
('CL05',N'Nano',0),
('CL06', N'Lanh', 1),
('CL07', N'Spandex', 1),
('CL08', N'Tencel', 1),
('CL09', N'Viscose', 1),
('CL10', N'Modal', 1),
('CL11', N'Acrylic', 0),
('CL12', N'Bamboo', 1),
('CL13', N'Nhung', 0),
('CL14', N'Chiffon', 0),
('CL15', N'Len', 0);
GO

-- INSERT INTO THUONGHIEU
INSERT INTO THUONGHIEU(MATH,TENTH,TRANGTHAITH) VALUES
('TH01',N'Denimst',1),
('TH02',N'Coolmate',1),
('TH03',N'Hussio',1),
('TH04',N'Pattern',1),
('TH05',N'SSStutter',1),
('TH06', N'Uniqlo', 1),
('TH07', N'Zara', 1),
('TH08', N'H&M', 1),
('TH09', N'Adidas', 1),
('TH10', N'Nike', 1),
('TH11', N'Levis', 1),
('TH12', N'Gap', 0),
('TH13', N'Puma', 1),
('TH14', N'Under Armour', 1),
('TH15', N'Tommy Hilfiger', 1);
GO

-- INSERT INTO KICHCO
INSERT INTO KICHCO (MAKC,TENKC,TRANGTHAIKC) VALUES
('KC01',N'M',1),
('KC02',N'L',1),
('KC03',N'XL',1),
('KC04',N'XXL',1),
('KC05',N'XXXL',0),
('KC06', N'S', 1),     
('KC07', N'XS', 1),    
('KC08', N'XXS', 1),   
('KC09', N'4XL', 0),   
('KC10', N'5XL', 0);   
GO

-- INSERT INTO SANPHAM
INSERT INTO SANPHAM (MASP,TENSP,TRANGTHAISP) VALUES
('SP01', N'Áo thun cổ tròn', 1),
('SP02', N'Áo thun polo', 1),
('SP03', N'Áo thun dài tay', 1),
('SP04', N'Áo thun thể thao', 1),
('SP05', N'Áo thun in logo hoặc thương hiệu', 1),
('SP06', N'Áo thun cổ tròn nam', 1),
('SP07', N'Áo thun polo nam', 1),
('SP08', N'Áo thun tay lỡ nam', 1),
('SP09', N'Áo thun in hình nam', 1),
('SP10', N'Áo thun thể thao nam', 1),
('SP11', N'Áo thun oversize nam', 1),
('SP12', N'Áo thun kẻ sọc nam', 1),
('SP13', N'Áo thun len nam', 1),
('SP14', N'Áo thun mùa hè nam', 1),
('SP15', N'Áo thun in slogan nam', 1);
GO

-- INSERT INTO SANPHAMCHITIET
INSERT INTO SANPHAMCHITIET (MASPCT,IDMS,IDCL,IDTH,IDKC,IDSP,NGUOITAO,SOLUONGTON,MOTA,TRANGTHAISPCT,DONGIA) VALUES
('SPCT01', 1, 1, 1, 1, 1, N'Bùi Vũ Hoàng', 100, N'Chất liệu thoáng mát', 1, 800000),
('SPCT02', 2, 2, 2, 2, 2, N'Bùi Vũ Hoàng', 200, N'Sang trọng, lịch lãm', 1, 700000),
('SPCT03', 3, 3, 3, 3, 3, N'Bùi Vũ Hoàng', 300, N'Trẻ trung, năng động', 1, 700000),
('SPCT04', 1, 1, 1, 1, 4, N'Bùi Vũ Hoàng', 150, N'Thể thao, thoải mái', 1, 750000),  -- Chi tiết cho áo thun thể thao
('SPCT05', 1, 1, 1, 1, 5, N'Bùi Vũ Hoàng', 120, N'In logo thương hiệu, phong cách', 1, 650000),  -- Chi tiết cho áo thun in logo
('SPCT06', 1, 1, 1, 1, 6, N'Bùi Vũ Hoàng', 90, N'Kiểu dáng oversize, phong cách hiện đại', 1, 800000),  -- Chi tiết cho áo thun oversize
('SPCT07', 1, 1, 1, 1, 7, N'Bùi Vũ Hoàng', 80, N'Tay lỡ, thoáng mát', 1, 700000),  -- Chi tiết cho áo thun tay lỡ
('SPCT08', 1, 1, 1, 1, 8, N'Bùi Vũ Hoàng', 70, N'In hình thể thao, trẻ trung', 1, 600000),  -- Chi tiết cho áo thun in hình
('SPCT09', 1, 1, 1, 1, 9, N'Bùi Vũ Hoàng', 60, N'Chất liệu thấm hút mồ hôi, năng động', 1, 750000),  -- Chi tiết cho áo thun thể thao nam
('SPCT10', 1, 1, 1, 1, 10, N'Bùi Vũ Hoàng', 50, N'Mùa hè, thoáng mát', 1, 680000),  -- Chi tiết cho áo thun mùa hè
('SPCT11', 1, 1, 1, 1, 11, N'Bùi Vũ Hoàng', 40, N'Chất liệu len, giữ ấm', 1, 900000),  -- Chi tiết cho áo thun len
('SPCT12', 1, 1, 1, 1, 12, N'Bùi Vũ Hoàng', 30, N'Kẻ sọc, phong cách trẻ trung', 1, 700000),  -- Chi tiết cho áo thun kẻ sọc
('SPCT13', 1, 1, 1, 1, 13, N'Bùi Vũ Hoàng', 20, N'In slogan, cá tính', 1, 650000),  -- Chi tiết cho áo thun in slogan
('SPCT14', 1, 1, 1, 1, 14, N'Bùi Vũ Hoàng', 15, N'Chất liệu bền, dễ bảo quản', 1, 720000),  -- Chi tiết cho áo thun cổ tròn
('SPCT15', 1, 1, 1, 1, 15, N'Bùi Vũ Hoàng', 10, N'Phong cách cổ điển, dễ phối đồ', 1, 700000);  -- Chi tiết cho áo thun polo
GO

-- INSERT INTO KHACHHANG
INSERT INTO KHACHHANG (MAKH,TENKH,NGAYSINH,GIOITINH,SDT,DIACHI, TRANGTHAIKH) VALUES
('KH01', N'Phạm Minh Tuấn', '1995-05-20', 1, '0912345678', N'Đà Nẵng', 1),
('KH02', N'Nguyễn Văn A', '1992-02-15', 1, '0909876543', N'Hải Phòng', 1),
('KH03', N'Trần Thị B', '1998-04-25', 0, '0987654321', N'Vũng Tàu', 1),
('KH04', N'Lê Minh Khôi', '1996-01-10', 1, '0976543210', N'Nha Trang', 1),
('KH05', N'Nguyễn Thị Hằng', '1999-06-30', 0, '0945678910', N'Cần Thơ', 1),
('KH06', N'Nguyễn Văn Nam', '2001-03-15', 1, '0934567890', N'Thuận An', 1),
('KH07', N'Trần Văn D', '1988-09-05', 1, '0954321789', N'Biên Hòa', 1),
('KH08', N'Lê Thị Hoa', '2002-12-01', 0, '0913456789', N'Hà Tĩnh', 1),
('KH09', N'Nguyễn Quốc C', '1990-11-30', 1, '0923456789', N'Sóc Trăng', 1),
('KH10', N'Trần Hoài Nam', '2000-08-20', 1, '0965432178', N'Vĩnh Long', 1),
('KH11', N'Lê Ngọc Anh', '1997-07-07', 0, '0988765432', N'Tiền Giang', 1),
('KH12', N'Nguyễn Hải Đăng', '2004-05-25', 1, '0934567890', N'Thái Nguyên', 1),
('KH13', N'Trần Thị Nhung', '1995-04-18', 0, '0912345678', N'Hà Giang', 1),
('KH14', N'Lê Văn Tú', '2002-09-12', 1, '0945678912', N'Ninh Bình', 1),
('KH15', N'Nguyễn Văn T', '1998-06-05', 1, '0976543212', N'Lâm Đồng', 1);
GO

-- INSERT INTO KHUYENMAI
INSERT INTO KHUYENMAI (MAKM, TENKM, MUCGIAMGIA, THOIGIANBATDAU, THOIGIANKETTHUC, TRANGTHAIKM, SOLUONG) VALUES 
('KM01', N'Sale Tết Nguyên Đán 2024', 15, '2024-01-15', '2024-02-15', 1, 300),
('KM02', N'Sale Giáng Sinh 2024', 25, '2024-12-01', '2024-12-25', 1, 400),
('KM03', N'Sale Mừng Quốc Khánh', 20, '2024-08-30', '2024-09-05', 1, 250),
('KM04', N'Sale Hè 2024', 15, '2024-06-01', '2024-06-30', 1, 350),
('KM05', N'Sale Black Friday', 50, '2024-11-29', '2024-11-30', 1, 150),
('KM06', N'Sale Ngày Phụ Nữ', 10, '2024-03-08', '2024-03-09', 1, 500),
('KM07', N'Sale Sinh Nhật Cửa Hàng', 30, '2024-07-01', '2024-07-10', 1, 200),
('KM08', N'Sale Mùa Thu', 20, '2024-09-01', '2024-09-30', 1, 300),
('KM09', N'Sale Khai Trương', 25, '2024-10-01', '2024-10-31', 1, 100),
('KM10', N'Sale Back to School', 15, '2024-08-01', '2024-08-31', 1, 250),
('KM11', N'Sale Tháng 11', 10, '2024-11-01', '2024-11-30', 1, 400),
('KM12', N'Sale Tặng Quà', 5, '2024-05-01', '2024-05-31', 1, 300),
('KM13', N'Sale Cuối Năm', 20, '2024-12-26', '2024-12-31', 1, 200),
('KM14', N'Sale Mùa Đông', 30, '2024-12-01', '2025-01-15', 1, 150),
('KM15', N'Sale Hàng Tháng', 10, '2024-10-01', '2024-10-31', 1, 100);
GO

-- INSERT INTO KHUYENMAICHITIET
INSERT INTO KHUYENMAICHITIET (MAKMCT, IDKM, TRANGTHAIKMCT) VALUES
('KMCT01', 1, 1),
('KMCT02', 2, 1),
('KMCT03', 3, 1),
('KMCT04', 4, 0),
('KMCT05', 5, 0),
('KMCT06', 6, 1),
('KMCT07', 7, 1),
('KMCT08', 8, 1),
('KMCT09', 9, 1),
('KMCT10', 10, 1),
('KMCT11', 11, 1),
('KMCT12', 12, 1),
('KMCT13', 13, 1),
('KMCT14', 14, 1),
('KMCT15', 15, 1);
GO

-- INSERT INTO HOADON
INSERT INTO HOADON (IDKH, IDNV, IDKM, MAHD, NGAYTAO, LYDOHUY, NGAYTHANHTOAN, DONGIASAUGIAM, TONGTIEN, TRANGTHAIHD) VALUES
(1, 1, 1, 'HD01', '2024-04-01', NULL, '2024-04-01', 100000, 150000, 1),
(2, 2, 1, 'HD02', '2024-05-01', NULL, '2024-05-01', 200000, 250000, 1),
(3, 3, 2, 'HD03', '2024-06-01', NULL, '2024-06-01', 300000, 350000, 1),
(1, 1, 3, 'HD04', '2024-07-01', NULL, '2024-07-01', 400000, 450000, 1),
(2, 2, 1, 'HD05', '2024-08-01', NULL, '2024-08-01', 150000, 200000, 1),
(3, 3, 2, 'HD06', '2024-09-01', NULL, '2024-09-01', 250000, 300000, 1),
(1, 1, 3, 'HD07', '2024-10-01', NULL, '2024-10-01', 350000, 400000, 1),
(2, 2, 1, 'HD08', '2024-11-01', NULL, '2024-11-01', 120000, 170000, 1),
(3, 3, 2, 'HD09', '2024-12-01', NULL, '2024-12-01', 220000, 270000, 1),
(1, 1, 3, 'HD10', '2024-01-15', NULL, '2024-01-15', 320000, 370000, 1),
(2, 2, 1, 'HD11', '2024-02-15', NULL, '2024-02-15', 180000, 230000, 1),
(3, 3, 2, 'HD12', '2024-03-15', NULL, '2024-03-15', 260000, 310000, 1),
(1, 1, 3, 'HD13', '2024-04-15', NULL, '2024-04-15', 360000, 410000, 1),
(2, 2, 1, 'HD14', '2024-05-15', NULL, '2024-05-15', 140000, 190000, 1),
(3, 3, 2, 'HD15', '2024-06-15', NULL, '2024-06-15', 240000, 290000, 1),
(1, 1, 3, 'HD16', '2024-07-15', NULL, '2024-07-15', 340000, 390000, 1),
(1, 1, 1, 'HD17', '2024-01-01', NULL, '2024-01-01', 120000, 180000, 1),
(2, 2, 1, 'HD18', '2024-02-01', NULL, '2024-02-01', 150000, 200000, 1),
(3, 3, 2, 'HD19', '2024-03-01', NULL, '2024-03-01', 170000, 230000, 1),
(1, 1, 3, 'HD20', '2024-04-01', NULL, '2024-04-01', 140000, 190000, 1),
(2, 2, 1, 'HD21', '2024-05-01', NULL, '2024-05-01', 160000, 220000, 1),
(3, 3, 2, 'HD22', '2024-06-01', NULL, '2024-06-01', 180000, 240000, 1),
(1, 1, 3, 'HD23', '2024-07-01', NULL, '2024-07-01', 200000, 260000, 1),
(2, 2, 1, 'HD24', '2024-08-01', NULL, '2024-08-01', 220000, 280000, 1),
(3, 3, 2, 'HD25', '2024-09-01', NULL, '2024-09-01', 240000, 300000, 1),
(1, 1, 3, 'HD26', '2024-10-01', NULL, '2024-10-01', 260000, 320000, 1),
(2, 2, 1, 'HD27', '2024-11-01', NULL, '2024-11-01', 280000, 340000, 1),
(3, 3, 2, 'HD28', '2024-12-01', NULL, '2024-12-01', 300000, 360000, 1),
(1, 1, 3, 'HD29', '2024-01-15', NULL, '2024-01-15', 320000, 380000, 1),
(2, 2, 1, 'HD30', '2024-02-15', NULL, '2024-02-15', 340000, 400000, 1),
(3, 3, 2, 'HD31', '2024-03-15', NULL, '2024-03-15', 360000, 420000, 1),
(1, 1, 3, 'HD32', '2024-04-15', NULL, '2024-04-15', 380000, 440000, 1);
GO

-- INSERT INTO HOADONCT
INSERT INTO HOADONCT (IDHD, IDSPCT, MAHDCT, NGAYTAO, DONGIA, DONGIASAUKHIGIAM, SOLUONG) VALUES
(1, 1, 'HDCT01', '2024-04-01', 100000, 90000, 2),
(2, 2, 'HDCT02', '2024-04-01', 150000, 135000, 1),
(3, 3, 'HDCT03', '2024-04-01', 200000, 180000, 1),
(4, 1, 'HDCT04', '2024-05-01', 200000, 180000, 3),
(5, 2, 'HDCT05', '2024-05-01', 250000, 225000, 2),
(6, 3, 'HDCT06', '2024-05-01', 300000, 270000, 1),
(7, 1, 'HDCT07', '2024-06-01', 300000, 270000, 2),
(8, 2, 'HDCT08', '2024-06-01', 350000, 315000, 2),
(9, 3, 'HDCT09', '2024-06-01', 400000, 360000, 3),
(10, 1, 'HDCT10', '2024-07-01', 400000, 360000, 1),
(11, 2, 'HDCT11', '2024-07-01', 450000, 405000, 2),
(12, 3, 'HDCT12', '2024-07-01', 500000, 450000, 3),
(13, 1, 'HDCT13', '2024-08-01', 150000, 135000, 4),
(14, 2, 'HDCT14', '2024-08-01', 200000, 180000, 2),
(15, 3, 'HDCT15', '2024-08-01', 250000, 225000, 1),
(16, 1, 'HDCT16', '2024-09-01', 250000, 225000, 3),
(17, 2, 'HDCT17', '2024-09-01', 300000, 270000, 1),
(18, 3, 'HDCT18', '2024-09-01', 350000, 315000, 2),
(19, 1, 'HDCT19', '2024-10-01', 350000, 315000, 1),
(20, 2, 'HDCT20', '2024-10-01', 400000, 360000, 2),
(21, 3, 'HDCT21', '2024-10-01', 450000, 405000, 3),
(22, 1, 'HDCT22', '2024-11-01', 120000, 110000, 1),
(23, 2, 'HDCT23', '2024-11-01', 180000, 165000, 2),
(24, 3, 'HDCT24', '2024-11-01', 220000, 200000, 1),
(25, 1, 'HDCT25', '2024-12-01', 220000, 200000, 3),
(26, 2, 'HDCT26', '2024-12-01', 270000, 243000, 2),
(27, 3, 'HDCT27', '2024-12-01', 320000, 288000, 1),
(28, 1, 'HDCT28', '2024-01-15', 320000, 288000, 1),
(29, 2, 'HDCT29', '2024-01-15', 370000, 333000, 2),
(30, 3, 'HDCT30', '2024-01-15', 420000, 378000, 3),
(31, 1, 'HDCT31', '2024-02-15', 180000, 162000, 1),
(32, 2, 'HDCT32', '2024-02-15', 230000, 207000, 2);
GO



-- SELECT FROM
SELECT*FROM NHANVIEN
SELECT*FROM MAUSAC
SELECT*FROM CHATLIEU
SELECT*FROM THUONGHIEU
SELECT*FROM KICHCO
SELECT*FROM SANPHAM
SELECT*FROM SANPHAMCHITIET
SELECT*FROM KHACHHANG
SELECT*FROM KHUYENMAI
SELECT*FROM KHUYENMAICHITIET
SELECT*FROM HOADON
SELECT*FROM HOADONCT

