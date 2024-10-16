﻿CREATE DATABASE DA1_NHOM2
GO

USE DA1_NHOM2
GO

CREATE TABLE CHUCVU(
	   ID INT PRIMARY KEY IDENTITY(1,1),
	   MACV VARCHAR(20),
	   TENCV NVARCHAR(50)
);
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
	   IDCV INT,

	   FOREIGN KEY (IDCV) REFERENCES CHUCVU(ID)
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
	   DIACHI NVARCHAR(MAX)
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

-- INSERT INTO CHUCVU
INSERT INTO CHUCVU (MACV,TENCV) VALUES
('CV01',N'Quản lý'),
('CV02',N'Nhân viên');
GO

-- INSERT INTO NHANVIEN
INSERT INTO NHANVIEN (MANV,TENNV,GIOITINH,NGAYSINH,CCCD,DIACHI,SDT,EMAIL,MATKHAU,TRANGTHAINV,IDCV) VALUES
('NV01',N'Vũ Xuân Kiên',1,'2003-12-16','001203030101',N'Hà Nội','0335613548','kienvxph27136@fpt.edu.vn','123',1,2),
('NV02',N'Dư Văn An',0,'2000-01-20','001203987617',N'Thạch Thất','0963464048','andvph27911@fpt.edu.vn','123',0,2),
('NV03',N'Bùi Vũ Hoàng',0,'2004-06-14','001298768787',N'Hải Dương','0965416951','hoangbvph32141@fpt.edu.vn','123',1,1),
('NV04',N'Ngọc Quốc Thiện',1,'2003-05-11','001202098789',N'Đông Anh','0937521482','thiennqph35476@fpt.edu.vn','123',1,2),
('NV05',N'Bùi Văn Hiếu',1,'2002-06-22','001202098789',N'Nghệ An','0977546452','hieubvph35580@fpt.edu.vn','123',0,2);
GO

-- INSERT INTO MAUSAC
INSERT INTO MAUSAC (MAMS,TENMS,TRANGTHAIMS) VALUES
('MS01',N'Xám',1),
('MS02',N'Đen',1),
('MS03',N'Trắng',1),
('MS04',N'Xanh',0),
('MS05',N'Hồng',1);
GO

-- INSERT INTO CHATLIEU
INSERT INTO CHATLIEU (MACL,TENCL,TRANGTHAICL) VALUES
('CL01',N'Kaki',1),
('CL02',N'Cotton',1),
('CL03',N'Polyester',1),
('CL04',N'Denim',1),
('CL05',N'Nano',0);
GO

-- INSERT INTO THUONGHIEU
INSERT INTO THUONGHIEU(MATH,TENTH,TRANGTHAITH) VALUES
('TH01',N'Denimst',1),
('TH02',N'Coolmate',1),
('TH03',N'Hussio',1),
('TH04',N'Pattern',1),
('TH05',N'SSStutter',1);
GO

-- INSERT INTO KICHCO
INSERT INTO KICHCO (MAKC,TENKC,TRANGTHAIKC) VALUES
('KC01',N'M',1),
('KC02',N'L',1),
('KC03',N'XL',1),
('KC04',N'XXL',1),
('KC05',N'XXXL',0);
GO

-- INSERT INTO SANPHAM
INSERT INTO SANPHAM (MASP,TENSP,TRANGTHAISP) VALUES
('SP01',N'Áo thun cổ tròn',1),
('SP02',N'Áo thun cổ bẻ (polo)',1),
('SP03',N'Áo thun dài tay',1),
('SP04',N'Áo thun thể thao',1),
('SP05',N'Áo thun in logo hoặc thương hiệu',1);
GO

-- INSERT INTO SANPHAMCHITIET
INSERT INTO SANPHAMCHITIET (MASPCT,IDMS,IDCL,IDTH,IDKC,IDSP,NGUOITAO,SOLUONGTON,MOTA,TRANGTHAISPCT,DONGIA) VALUES
('SPCT01',1,1,1,1,1,N'Bùi Vũ Hoàng',100,N'chất liệu thoáng mát',1,800000),
('SPCT02',2,2,2,2,2,N'Bùi Vũ Hoàng',200,N'sang trọng, lịch lãm',1,700000),
('SPCT03',3,3,3,3,3,N'Bùi Vũ Hoàng',300,N'trẻ trung, năng động',1,700000);
GO

-- INSERT INTO KHACHHANG
INSERT INTO KHACHHANG (MAKH,TENKH,NGAYSINH,GIOITINH,SDT,DIACHI) VALUES
('KH01',N'Nguyễn Cầu Giấy','2000-12-09',1,'0987878787',N'Sài Gòn'),
('KH02',N'Trần Thanh Xuân','1997-08-09',1,'0987678787',N'Hà Nội'),
('KH03',N'Lê Ba Đình','2003-11-03',0,'0347878787',N'Hà Nội');
GO

-- INSERT INTO KHUYENMAI
INSERT INTO KHUYENMAI (MAKM, TENKM, MUCGIAMGIA, THOIGIANBATDAU, THOIGIANKETTHUC, TRANGTHAIKM, SOLUONG) VALUES 
('KM01',N'Sale 11/11/2021',30,'2021-11-11','2021-12-12',0,500),
('KM02',N'Sale 12/12/2021',10,'2021-12-12','2021-12-13',0,200),
('KM03',N'Sale 04/04/2024',20,'2024-04-04','2024-04-05',1,100);
GO

-- INSERT INTO KHUYENMAICHITIET
INSERT INTO KHUYENMAICHITIET (MAKMCT, IDKM, TRANGTHAIKMCT) VALUES
('KMCT01',1,1),
('KMCT02',2,0);
GO

-- INSERT INTO HOADON
INSERT INTO HOADON (IDKH, IDNV, IDKM, MAHD, NGAYTAO, LYDOHUY, NGAYTHANHTOAN, DONGIASAUGIAM, TONGTIEN, TRANGTHAIHD) VALUES
(1, 1, 1, 'HD01', '2024-01-01', NULL, '2024-01-02', 100000, 150000, 1),
(2, 2, 2, 'HD02', '2024-02-01', NULL, '2024-02-02', 200000, 250000, 1),
(3, 3, 3, 'HD03', '2024-03-01', NULL, '2024-03-02', 300000, 350000, 0);
GO

-- INSERT INTO HOADONCT
INSERT INTO HOADONCT (IDHD, IDSPCT, MAHDCT, NGAYTAO, DONGIA, DONGIASAUKHIGIAM, SOLUONG) VALUES
(1, 1, 'HDCT01', '2024-01-01', 800000, 700000, 2),
(2, 2, 'HDCT02', '2024-02-01', 700000, 600000, 1),
(3, 3, 'HDCT03', '2024-03-01', 700000, 650000, 3);
GO



-- SELECT FROM
SELECT*FROM CHUCVU
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

SELECT ID, FROM SANPHAM

