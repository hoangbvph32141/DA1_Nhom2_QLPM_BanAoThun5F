package com.n2.iRepository;

import com.n2.domainModel.KhachHang;
import com.n2.util.DBConnection;
import com.n2.util.JdbcHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class KhachHangRepository {

    private Connection connection = JdbcHelper.openDbConnection();
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private String sql = null;

    public ArrayList<KhachHang> getListKhachHang() {
        String sql_getListKhachHang = "SELECT ID, MAKH, TENKH, NGAYSINH, GIOITINH, SDT, DIACHI, TRANGTHAIKH\n"
                + "FROM KHACHHANG";

        ArrayList<KhachHang> listKhachHang = new ArrayList<>();

        try (Connection connection = JdbcHelper.openDbConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql_getListKhachHang)) {

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idKH = resultSet.getInt(1);
                String maKH = resultSet.getString(2);
                String tenKH = resultSet.getString(3);
                Date ngaySinh = resultSet.getDate(4);
                int gioiTinh = resultSet.getInt(5);
                String sdt = resultSet.getString(6);
                String diaChi = resultSet.getString(7);
                int trangThaiKH = resultSet.getInt(8);
                KhachHang khachHang = new KhachHang(idKH, maKH, tenKH, ngaySinh, gioiTinh, sdt, diaChi, trangThaiKH);

                listKhachHang.add(khachHang);
            }
            return listKhachHang;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int themKH(KhachHang khachHang) {
        String sql_addKH = "INSERT INTO KHACHHANG(MAKH, TENKH, NGAYSINH, GIOITINH, SDT, DIACHI, TRANGTHAIKH) VALUES (?,?,?,?,?,?,?);";
        try {
//            connection = DBConnect.getConnection();
            preparedStatement = connection.prepareStatement(sql_addKH);
            preparedStatement.setString(1, khachHang.getMaKH());
            preparedStatement.setString(2, khachHang.getTenKH());
            preparedStatement.setDate(3, new java.sql.Date(khachHang.getNgaySinh().getTime()));
            preparedStatement.setInt(4, khachHang.getGioiTinh());
            preparedStatement.setString(5, khachHang.getSdt());
            preparedStatement.setString(6, khachHang.getDiaChi());
            preparedStatement.setInt(7, khachHang.getTrangThaiKH());

            return preparedStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int countKH() throws SQLException {
        String sql = "SELECT COUNT(*) FROM KHACHHANG";
        int soLuongKhachHang = 0;
        ResultSet rs = DBConnection.getDataFromQuery(sql);

        if (rs.next()) {
            soLuongKhachHang = rs.getInt(1);
        }
        return soLuongKhachHang;
    }

    public int suaKH(KhachHang khachHang) {
        String sql_suaKH = "UPDATE KHACHHANG\n"
                + "SET TENKH = ?, NGAYSINH = ?, GIOITINH = ?, SDT = ?, DIACHI = ?, TRANGTHAIKH = ?\n"
                + "WHERE ID = ?";
        try {
            preparedStatement = connection.prepareStatement(sql_suaKH);
            preparedStatement.setString(1, khachHang.getTenKH());
            preparedStatement.setDate(2, new java.sql.Date(khachHang.getNgaySinh().getTime()));
            preparedStatement.setInt(3, khachHang.getGioiTinh());
            preparedStatement.setString(4, khachHang.getSdt());
            preparedStatement.setString(5, khachHang.getDiaChi());
            preparedStatement.setInt(6, khachHang.getTrangThaiKH());
            preparedStatement.setInt(7, khachHang.getIdKH());

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Ghi lại lỗi
            return 0;
        }
    }

    public int doiTTKH(KhachHang khachHang) {
        String sql_doiTTKH = "UPDATE KHACHHANG\n"
                + "SET TRANGTHAIKH = ?\n"
                + "WHERE ID = ?";
        try {
            preparedStatement = connection.prepareStatement(sql_doiTTKH);
            preparedStatement.setInt(1, khachHang.getTrangThaiKH());
            preparedStatement.setInt(2, khachHang.getIdKH());

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Ghi lại lỗi
            return 0;
        }
    }

    public ArrayList<KhachHang> searchKH(String tenKH) {
        ArrayList<KhachHang> listSearchKH = new ArrayList<>();

        String sql_searchKM = "SELECT ID, MAKH, TENKH, NGAYSINH, GIOITINH, SDT, DIACHI, TRANGTHAIKH\n"
                + "FROM KHACHHANG \n"
                + "WHERE TENKH LIKE ?";
        try (Connection connection = JdbcHelper.openDbConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql_searchKM)) {

            preparedStatement.setString(1, "%" + tenKH + "%");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idKH = resultSet.getInt(1);
                String maKH = resultSet.getString(2);
                tenKH = resultSet.getString(3);
                Date ngaySinh = resultSet.getDate(4);
                int gioiTinh = resultSet.getInt(5);
                String sdt = resultSet.getString(6);
                String diaChi = resultSet.getString(7);
                int trangThaiKH = resultSet.getInt(8);
                KhachHang khachHang = new KhachHang(idKH, maKH, tenKH, ngaySinh, gioiTinh, sdt, diaChi, trangThaiKH);

                listSearchKH.add(khachHang);
            }
            return listSearchKH;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
