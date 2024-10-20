/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.n2.iRepository;

import DTOSanPhamThongKe.SanPhamDTO;
import com.n2.domainModel.DoanhThu;
import com.n2.repository.iThongKeRepository;
import com.n2.util.DBConnect;
import com.n2.util.JdbcHelper;
import com.sun.jdi.connect.spi.Connection;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;



/**
 *
 * @author admin
 */
public class ThongKeRepository implements iThongKeRepository{
    
     @Override
    public DoanhThu getDoanhThuByThang(String st, String end) {
     String sql = "SELECT SUM(TONGTIEN) AS TongDoanhThu " +
                     "FROM HOADON " +
                     "WHERE TRANGTHAIHD = 1 " +  // Chỉ thống kê hóa đơn đã thanh toán
                     "AND NGAYTHANHTOAN >= ? " + 
                     "AND NGAYTHANHTOAN <= ?;";

        DoanhThu doanhThu = new DoanhThu();
        try {
            ResultSet rs = JdbcHelper.query(sql, st,end);
            while (rs.next()) {
                doanhThu.setTongDoanhThu(rs.getFloat("TongDoanhThu"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return doanhThu;
    }
    public List<DoanhThu> getDoanhThuByNgay(String bt, String kt) {
        String sql = "SELECT \n"
                + "FORMAT(NGAYTHANHTOAN, 'yyyy-MM-dd') AS Thang,\n"
                + "SUM(TONGTIEN) AS DoanhThu\n"
                + "FROM \n"
                + "HOADON\n"
                + "WHERE \n"
                + "NGAYTHANHTOAN BETWEEN ? AND ?\n"
                + "AND TRANGTHAIHD = 1 \n "
                + "GROUP BY \n"
                + "FORMAT(NGAYTHANHTOAN, 'yyyy-MM-dd')\n"
                + "ORDER BY \n"
                + "Thang;";
        List<DoanhThu> list = new ArrayList<>();

        try {
            ResultSet rs = JdbcHelper.query(sql, bt, kt);
           
            while (rs.next()) {
                DoanhThu doanhThu = new DoanhThu();
                System.out.println("đây là rs");
                
                doanhThu.setTongDoanhThu(rs.getFloat("DoanhThu"));
                doanhThu.setDate(rs.getString("Thang"));
                list.add(doanhThu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public List<SanPhamDTO> getByDate(String bd, String kt){
       String sql = "SELECT " +
             "    SANPHAM.MASP as ma, " +
             "    SANPHAM.TENSP as ten, " +
             "    SUM(HOADONCT.SOLUONG) AS SoLuong, " +
             "    SUM(HOADONCT.DONGIA * HOADONCT.SOLUONG) AS DoanhThu " +
             "FROM " +
             "    HOADON " +
             "JOIN " +
             "    HOADONCT ON HOADON.ID = HOADONCT.IDHD " +
             "JOIN " +
             "    SANPHAMCHITIET ON HOADONCT.IDSPCT = SANPHAMCHITIET.ID " +
             "JOIN " +
             "    SANPHAM ON SANPHAMCHITIET.IDSP = SANPHAM.ID " +
             "WHERE " +
             "    HOADON.TRANGTHAIHD = 1 AND " +
             "    HOADON.NGAYTHANHTOAN BETWEEN ? AND ? " + // Ngày bắt đầu và ngày kết thúc
             "GROUP BY " +
             "    SANPHAM.MASP, SANPHAM.TENSP " + // Nhóm theo mã và tên sản phẩm
             "ORDER BY " +
             "    DoanhThu DESC;"; // Sắp xếp theo doanh thu giảm dần
       List<SanPhamDTO> sanPhamDTOs = new ArrayList<>();
         try {
            ResultSet rs = JdbcHelper.query(sql, bd,kt);
            while (rs.next()) {
                SanPhamDTO sp = new SanPhamDTO();
                sp.setMa(rs.getString("ma"));
                sp.setTen(rs.getString("ten"));
                sp.setDoanhThu(rs.getFloat("DoanhThu"));
                sp.setSoLuong(rs.getInt("SoLuong"));
                sanPhamDTOs.add(sp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sanPhamDTOs;         


    }
    
    public List<SanPhamDTO> searchByDate(String bd, String kt, String key){
       String sql = "SELECT " +
             "    SANPHAM.MASP AS ma, " +
             "    SANPHAM.TENSP AS ten, " +
             "    SUM(HOADONCT.SOLUONG) AS SoLuong, " +
             "    SUM(HOADONCT.DONGIA * HOADONCT.SOLUONG) AS DoanhThu " +
             "FROM " +
             "    HOADON " +
             "JOIN " +
             "    HOADONCT ON HOADON.ID = HOADONCT.IDHD " +
             "JOIN " +
             "    SANPHAMCHITIET ON HOADONCT.IDSPCT = SANPHAMCHITIET.ID " +
             "JOIN " +
             "    SANPHAM ON SANPHAMCHITIET.IDSP = SANPHAM.ID " +
             "WHERE " +
             "    HOADON.TRANGTHAIHD = 1 AND " +
             "    HOADON.NGAYTHANHTOAN BETWEEN ? AND ? " +
             "    AND (SANPHAM.TENSP LIKE ? " +  // Sửa từ 'ten' thành 'SANPHAM.TENSP'
             "    OR SANPHAM.MASP = ? )" +  // Sửa từ 'ma' thành 'SANPHAM.MASP'
             "GROUP BY " +
             "    SANPHAM.MASP, SANPHAM.TENSP " + // Nhóm theo mã và tên sản phẩm
             "ORDER BY " +
             "    DoanhThu DESC;"; // Sắp xếp theo doanh thu giảm dần

       List<SanPhamDTO> sanPhamDTOs = new ArrayList<>();
         try {
            ResultSet rs = JdbcHelper.query(sql, bd,kt,"%" + key + "%",key );
            while (rs.next()) {
                SanPhamDTO sp = new SanPhamDTO();
                sp.setMa(rs.getString("ma"));
                sp.setTen(rs.getString("ten"));
                sp.setDoanhThu(rs.getFloat("DoanhThu"));
                sp.setSoLuong(rs.getInt("SoLuong"));
                sanPhamDTOs.add(sp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sanPhamDTOs;         
    }
}
