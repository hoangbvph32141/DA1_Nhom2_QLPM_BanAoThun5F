package com.n2.iRepository;

import com.n2.domainModel.NhanVien;
import com.n2.util.DBConnection;
import com.n2.util.JdbcHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class NhanVienRepository {

    private Connection connection = JdbcHelper.openDbConnection();
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private String sql = null;

    public ArrayList<NhanVien> getListNhanVien() {
        String sql_getListNhanVien = "SELECT ID, MANV, TENNV, GIOITINH, NGAYSINH, CCCD, DIACHI, SDT, EMAIL, MATKHAU, TRANGTHAINV, CHUCVU\n"
                + "FROM NHANVIEN";

        ArrayList<NhanVien> listNhanVien = new ArrayList<>();

        try (Connection connection = JdbcHelper.openDbConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql_getListNhanVien)) {

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idNV = resultSet.getInt(1);
                String maNV = resultSet.getString(2);
                String tenNV = resultSet.getString(3);
                int gioiTinh = resultSet.getInt(4);
                Date ngaySinh = resultSet.getDate(5);
                String cccd = resultSet.getString(6);
                String diaChi = resultSet.getString(7);
                String sdt = resultSet.getString(8);
                String email = resultSet.getString(9);
                String matKhau = resultSet.getString(10);
                int trangThai = resultSet.getInt(11);
                int chucVu = resultSet.getInt(12);
                NhanVien nhanVien = new NhanVien(idNV, maNV, tenNV, gioiTinh, ngaySinh, cccd, diaChi, sdt, email, matKhau, trangThai, chucVu);

                listNhanVien.add(nhanVien);
            }
            return listNhanVien;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<NhanVien> searchNV(String tenNV) {
        ArrayList<NhanVien> listSearchNV = new ArrayList<>();

        String sql_searchNV = "SELECT ID, MANV, TENNV, GIOITINH, NGAYSINH, CCCD, DIACHI, SDT, EMAIL, MATKHAU, TRANGTHAINV, CHUCVU\n"
                + "FROM NHANVIEN WHERE TENNV LIKE ?";
        try (Connection connection = JdbcHelper.openDbConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql_searchNV)) {

            preparedStatement.setString(1, "%" + tenNV + "%");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idNV = resultSet.getInt(1);
                String maNV = resultSet.getString(2);
                tenNV = resultSet.getString(3);
                int gioiTinh = resultSet.getInt(4);
                Date ngaySinh = resultSet.getDate(5);
                String cccd = resultSet.getString(6);
                String diaChi = resultSet.getString(7);
                String sdt = resultSet.getString(8);
                String email = resultSet.getString(9);
                String matKhau = resultSet.getString(10);
                int trangThai = resultSet.getInt(11);
                int chucVu = resultSet.getInt(12);
                NhanVien nhanVien = new NhanVien(idNV, maNV, tenNV, gioiTinh, ngaySinh, cccd, diaChi, sdt, email, matKhau, trangThai, chucVu);

                listSearchNV.add(nhanVien);
            }
            return listSearchNV;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int countNV() throws SQLException {
        String sql = "SELECT COUNT(*) FROM NHANVIEN";
        int soLuongNhanVien = 0;
        ResultSet rs = DBConnection.getDataFromQuery(sql);

        if (rs.next()) {
            soLuongNhanVien = rs.getInt(1);
        }
        return soLuongNhanVien;
    }

    public int themNV(NhanVien nhanVien) {
        String sql = "INSERT INTO NHANVIEN(MANV, TENNV, GIOITINH, NGAYSINH, CCCD, DIACHI, SDT, EMAIL, MATKHAU, TRANGTHAINV, CHUCVU) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, nhanVien.getMaNV());
            preparedStatement.setString(2, nhanVien.getTenNV());
            preparedStatement.setInt(3, nhanVien.getGioiTinh());
            preparedStatement.setDate(4, new java.sql.Date(nhanVien.getNgaySinh().getTime()));
            preparedStatement.setString(5, nhanVien.getCccd());
            preparedStatement.setString(6, nhanVien.getDiaChi());
            preparedStatement.setString(7, nhanVien.getSdt());
            preparedStatement.setString(8, nhanVien.getEmail());
            preparedStatement.setString(9, nhanVien.getMatKhau());
            preparedStatement.setInt(10, nhanVien.getTrangThai());
            preparedStatement.setInt(11, nhanVien.getChucVu());

            return preparedStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int suaNV(NhanVien nhanVien) {
        String sql_suaNV = "UPDATE NHANVIEN\n"
                + "SET TENNV = ?, GIOITINH = ?, NGAYSINH = ?, CCCD = ?, DIACHI = ?, SDT = ?, EMAIL = ?, MATKHAU = ?, TRANGTHAINV = ?, CHUCVU = ?\n"
                + "WHERE ID = ?";
        try {
            preparedStatement = connection.prepareStatement(sql_suaNV);
            preparedStatement.setString(1, nhanVien.getTenNV());
            preparedStatement.setInt(2, nhanVien.getGioiTinh());
            preparedStatement.setDate(3, new java.sql.Date(nhanVien.getNgaySinh().getTime()));
            preparedStatement.setString(4, nhanVien.getCccd());
            preparedStatement.setString(5, nhanVien.getDiaChi());
            preparedStatement.setString(6, nhanVien.getSdt());
            preparedStatement.setString(7, nhanVien.getEmail());
            preparedStatement.setString(8, nhanVien.getMatKhau());
            preparedStatement.setInt(9, nhanVien.getTrangThai());
            preparedStatement.setInt(10, nhanVien.getChucVu());
            preparedStatement.setInt(11, nhanVien.getIdNV());

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Ghi lại lỗi
            return 0;
        }
    }

    public int doiTTNV(NhanVien nhanVien) {
        String sql_doiTTNV = "UPDATE NHANVIEN\n"
                + "SET TRANGTHAINV = ?\n"
                + "WHERE ID = ?";
        try {
            preparedStatement = connection.prepareStatement(sql_doiTTNV);
            preparedStatement.setInt(1, nhanVien.getTrangThai());
            preparedStatement.setInt(2, nhanVien.getIdNV());

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Ghi lại lỗi
            return 0;
        }
    }

    public boolean checkSdtExists(String sdt) throws SQLException {
        String query = "SELECT COUNT(*) FROM NHANVIEN WHERE SDT = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, sdt);
        ResultSet rs = stmt.executeQuery();
        rs.next();
        return rs.getInt(1) > 0; // Nếu COUNT(*) > 0 nghĩa là đã có SDT này
    }

    public boolean checkCccdExists(String cccd) throws SQLException {
        String query = "SELECT COUNT(*) FROM NHANVIEN WHERE CCCD = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, cccd);
        ResultSet rs = stmt.executeQuery();
        rs.next();
        return rs.getInt(1) > 0; // Nếu COUNT(*) > 0 nghĩa là đã có CCCD này
    }
    
    public boolean checkEmailExists(String email) throws SQLException {
        String query = "SELECT COUNT(*) FROM NHANVIEN WHERE EMAIL = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, email);
        ResultSet rs = stmt.executeQuery();
        rs.next();
        return rs.getInt(1) > 0; // Nếu COUNT(*) > 0 nghĩa là đã có CCCD này
    }
}
