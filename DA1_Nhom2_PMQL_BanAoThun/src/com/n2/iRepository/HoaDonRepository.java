package com.n2.iRepository;

import com.n2.domainModel.HoaDon;
import com.n2.util.DBConnection;
import com.n2.util.JdbcHelper;
import com.n2.viewModel.HoaDonViewModel;
import com.sun.jdi.connect.spi.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HoaDonRepository {

    public List<HoaDonViewModel> getAllHDD() {
        List<HoaDonViewModel> listHD = new ArrayList<>();
        String sql = "SELECT        dbo.HOADON.ID,  dbo.HOADON.MAHD,   dbo.NHANVIEN.TENNV, dbo.HOADON.NGAYTAO,dbo.HOADON.NGAYTHANHTOAN,  dbo.HOADON.TRANGTHAIHD, dbo.HOADON.TONGTIEN, dbo.HOADON.DONGIASAUGIAM \n"
                + "FROM            dbo.HOADON INNER JOIN\n"
                + "                         dbo.NHANVIEN ON dbo.HOADON.IDNV = dbo.NHANVIEN.ID\n"
                + "order by dbo.HOADON.MaHD desc";
        try {
            ResultSet rs = DBConnection.getDataFromQuery(sql);
            while (rs.next()) {
                HoaDonViewModel hd = new HoaDonViewModel();
                hd.setID(rs.getInt(1));
                hd.setMaHD(rs.getString(2));
                hd.setTenNV(rs.getString(3));
                hd.setNgayTao(rs.getDate(4));
                hd.setNgayThanhToan(rs.getDate(5));
                hd.setTrangThai(rs.getInt(6));
                hd.setTongTien(rs.getFloat(7));
                hd.setDonGiaSauGiam(rs.getFloat(8));
                listHD.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listHD;
    }

    public List<HoaDonViewModel> getHoaDonsByDateRange(Date startDate, Date endDate) throws SQLException {
        List<HoaDonViewModel> listHD = new ArrayList<>();

        String sql = "SELECT * FROM HoaDon WHERE NgayTao BETWEEN ? AND ?";
        try (java.sql.Connection con = JdbcHelper.openDbConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDate(1, (java.sql.Date) startDate);
            ps.setDate(2, (java.sql.Date) endDate);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    HoaDonViewModel hd = new HoaDonViewModel();
                    hd.setID(rs.getInt("ID"));
                    hd.setMaHD(rs.getString("MaHD"));
                    hd.setTenNV(rs.getString("TenNV"));
                    hd.setNgayTao(rs.getDate("NgayTao"));
                    hd.setNgayThanhToan(rs.getDate("NgayThanhToan"));
                    hd.setTrangThai(rs.getInt("TrangThai"));
                    hd.setTongTien(rs.getFloat("TongTien"));
                    hd.setDonGiaSauGiam(rs.getFloat("DonGiaSauGiam"));
                    listHD.add(hd);
                }
            }
        }
        return listHD;
    }
    
public List<HoaDonViewModel> getHDfindByMaHD(String maHD) throws SQLException {
    List<HoaDonViewModel> listHD = new ArrayList<>();
    String sql = "SELECT dbo.HOADON.ID, dbo.HOADON.MAHD, dbo.NHANVIEN.TENNV, dbo.HOADON.NGAYTAO, " +
                 "dbo.HOADON.NGAYTHANHTOAN, dbo.HOADON.TRANGTHAIHD, dbo.HOADON.TONGTIEN, dbo.HOADON.DONGIASAUGIAM " +
                 "FROM dbo.HOADON INNER JOIN dbo.NHANVIEN ON dbo.HOADON.IDNV = dbo.NHANVIEN.ID WHERE MAHD = ?";

    try (java.sql.Connection con = JdbcHelper.openDbConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
        // Gán giá trị cho tham số
        ps.setString(1, maHD);
        
        // Thực thi truy vấn
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                HoaDonViewModel hd = new HoaDonViewModel();
                hd.setID(rs.getInt(1));
                hd.setMaHD(rs.getString(2));
                hd.setTenNV(rs.getString(3));
                hd.setNgayTao(rs.getDate(4));
                hd.setNgayThanhToan(rs.getDate(5));
                hd.setTrangThai(rs.getInt(6));
                hd.setTongTien(rs.getFloat(7));
                hd.setDonGiaSauGiam(rs.getFloat(8));
                listHD.add(hd);
                
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    return listHD;
}
}
