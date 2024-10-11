/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.n2.iRepository;

import com.n2.domainModel.HoaDon;
import com.n2.domainModel.KhuyenMai;
import com.n2.domainModel.SanPham;
import com.n2.util.DBConnection;
import com.n2.util.JdbcHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author PC
 */
public class KhuyenMaiRepository {

    private Connection connection = JdbcHelper.openDbConnection();
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private String sql = null;

    public ArrayList<KhuyenMai> getListKhuyenMai() {
        String sql_getListKhuyenMai = "SELECT ID, MAKM, TENKM, MUCGIAMGIA, DIEUKIEN, THOIGIANBATDAU, THOIGIANKETTHUC, TRANGTHAIKM, SOLUONG\n"
                + "FROM KHUYENMAI";

        ArrayList<KhuyenMai> listKhuyenMai = new ArrayList<>(); // Tôi quen gọi cả thằng package ra, b có thể chỉ cần gọi tên nó là Class: Animal

        try (Connection connection = JdbcHelper.openDbConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql_getListKhuyenMai)) {

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idKM = resultSet.getInt(1);
                String maKM = resultSet.getString(2);
                String tenKM = resultSet.getString(3);
                float mucGiamGia = resultSet.getFloat(4);
                float dieuKien = resultSet.getFloat(5);
                Date thoiGianBatDau = resultSet.getDate(6);
                Date thoiGianKetThuc = resultSet.getDate(7);
                int trangThaiKM = resultSet.getInt(8);
                int soLuong = resultSet.getInt(9);

                KhuyenMai khuyenMai = new KhuyenMai(idKM, maKM, tenKM, mucGiamGia, dieuKien, thoiGianBatDau, thoiGianKetThuc, trangThaiKM, soLuong, dieuKien, thoiGianBatDau, idKM, tenKM);

                listKhuyenMai.add(khuyenMai);
            }
            return listKhuyenMai;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<KhuyenMai> getListSP() {
        String sql_getListSP = "SELECT \n"
                + "	KM.ID,\n"
                + "	HD.IDKM,\n"
                + "	HD.MAHD,\n"
                + "    KM.TENKM, \n"
                + "    KM.MUCGIAMGIA, \n"
                + "    KM.DIEUKIEN, \n"
                + "    HD.TONGTIEN,\n"
                + "    HD.NGAYTHANHTOAN\n"
                + "FROM \n"
                + "    HOADON HD\n"
                + "JOIN \n"
                + "    KHUYENMAI KM ON HD.IDKM = KM.ID";

        ArrayList<KhuyenMai> listSP = new ArrayList<>(); // Tôi quen gọi cả thằng package ra, b có thể chỉ cần gọi tên nó là Class: Animal

        try (Connection connection = JdbcHelper.openDbConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql_getListSP)) {

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                int idKM = resultSet.getInt(2);
                String tenHD = resultSet.getString(3);
                String tenKM = resultSet.getString(4);
                float mucGiamGia = resultSet.getFloat(5);
                float dieuKien = resultSet.getFloat(6);
                float tongTien = resultSet.getFloat(7);
                Date ngayThanhToan = resultSet.getDate(8);

                KhuyenMai khuyenMai = new KhuyenMai(idKM, tenHD, tenKM, mucGiamGia, dieuKien, ngayThanhToan, ngayThanhToan, idKM, idKM, tongTien, ngayThanhToan, id, tenHD);
                listSP.add(khuyenMai);
            }
            return listSP;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int themKM(KhuyenMai khuyenMai) {
        String sql_addKM = "INSERT INTO KHUYENMAI (MAKM, TENKM, MUCGIAMGIA, DIEUKIEN, THOIGIANBATDAU, THOIGIANKETTHUC, TRANGTHAIKM, SOLUONG) VALUES \n"
                + "(?,?,?,?,?,?,?,?);";
        try {
//            connection = DBConnect.getConnection();
            preparedStatement = connection.prepareStatement(sql_addKM);
            preparedStatement.setString(1, khuyenMai.getMaKM());
            preparedStatement.setString(2, khuyenMai.getTenKM());
            preparedStatement.setFloat(3, khuyenMai.getMucGiamGia());
            preparedStatement.setFloat(4, khuyenMai.getDieuKien());
            preparedStatement.setDate(5, new java.sql.Date(khuyenMai.getThoiGianBatDau().getTime()));
            preparedStatement.setDate(6, new java.sql.Date(khuyenMai.getThoiGianKetThuc().getTime()));
            preparedStatement.setInt(7, khuyenMai.getTrangThaiKM());
            preparedStatement.setInt(8, khuyenMai.getSoLuong());
            return preparedStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int countKM() throws SQLException {
        String sql = "SELECT COUNT(*) FROM KHUYENMAI";
        int soLuongKhuyenMai = 0;
        ResultSet rs = DBConnection.getDataFromQuery(sql);

        if (rs.next()) {
            soLuongKhuyenMai = rs.getInt(1);
        }
        return soLuongKhuyenMai;
    }

    public int suaKM(KhuyenMai khuyenMai) {
        String sql_suaKM = "UPDATE KHUYENMAI\n"
                + "SET TENKM = ?, MUCGIAMGIA = ?, DIEUKIEN = ?, THOIGIANBATDAU = ?, THOIGIANKETTHUC = ?, TRANGTHAIKM = ?, SOLUONG = ?\n"
                + "WHERE ID = ?";
        try {
            preparedStatement = connection.prepareStatement(sql_suaKM);
            preparedStatement.setString(1, khuyenMai.getTenKM());
            preparedStatement.setFloat(2, khuyenMai.getMucGiamGia());
            preparedStatement.setFloat(3, khuyenMai.getDieuKien());
            preparedStatement.setDate(4, new java.sql.Date(khuyenMai.getThoiGianBatDau().getTime()));
            preparedStatement.setDate(5, new java.sql.Date(khuyenMai.getThoiGianKetThuc().getTime()));
            preparedStatement.setInt(6, khuyenMai.getTrangThaiKM());
            preparedStatement.setInt(7, khuyenMai.getSoLuong());
            preparedStatement.setInt(8, khuyenMai.getIdKM());
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Ghi lại lỗi
            return 0;
        }
    }
}
