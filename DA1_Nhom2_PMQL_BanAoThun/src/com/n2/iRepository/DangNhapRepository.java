/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.n2.iRepository;

import com.n2.domainModel.NhanVien;
import com.n2.util.JdbcHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author PC
 */
public class DangNhapRepository {

    private Connection connection = JdbcHelper.openDbConnection();
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private String sql = null;

    public NhanVien checkLogin(String sdt, String matKhau) {
    String query = "SELECT ID, TENNV, CHUCVU, SDT, MATKHAU, TRANGTHAINV FROM NHANVIEN WHERE SDT = ? AND MATKHAU = ?";
    try (PreparedStatement stmt = connection.prepareStatement(query)) {
        stmt.setString(1, sdt);
        stmt.setString(2, matKhau);
        ResultSet rs = stmt.executeQuery();
        
        if (rs.next()) { // Nếu có kết quả, người dùng tồn tại
            NhanVien nhanVien = new NhanVien();
            nhanVien.setIdNV(rs.getInt("ID"));
            nhanVien.setTenNV(rs.getString("TENNV"));
            nhanVien.setChucVu(rs.getInt("CHUCVU"));
            nhanVien.setSdt(rs.getString("SDT"));
            nhanVien.setMatKhau(rs.getString("MATKHAU"));
            nhanVien.setTrangThai(rs.getInt("TRANGTHAINV"));
            return nhanVien; // Trả về đối tượng nhân viên
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null; // Không tìm thấy nhân viên
}
    
    
}
