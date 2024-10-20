package com.n2.iRepository;

import com.n2.domainModel.HoaDon;
import com.n2.util.DBConnection;
import com.n2.util.JdbcHelper;
import com.n2.viewModel.HoaDonChiTietViewModel;
import com.n2.viewModel.HoaDonViewModel;
import com.n2.viewModel.SanPhamChiTietViewModel;
import com.sun.jdi.connect.spi.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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


public List<HoaDonViewModel> getHoaDonsByDateRangeAndTrangThai(String startDateStr, String endDateStr, int trangThai, String maHD) throws SQLException, ParseException {
    List<HoaDonViewModel> listHD = new ArrayList<>();

    String sql = "SELECT dbo.HOADON.ID, dbo.HOADON.MAHD, dbo.NHANVIEN.TENNV, dbo.HOADON.NGAYTAO, "
            + "dbo.HOADON.NGAYTHANHTOAN, dbo.HOADON.TRANGTHAIHD, dbo.HOADON.TONGTIEN, dbo.HOADON.DONGIASAUGIAM "
            + "FROM dbo.HOADON INNER JOIN dbo.NHANVIEN ON dbo.HOADON.IDNV = dbo.NHANVIEN.ID "
            + "WHERE NgayTao BETWEEN ? AND ? AND TRANGTHAIHD = ? AND (? IS NULL OR MAHD = ?)";

    try (java.sql.Connection con = JdbcHelper.openDbConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
        // Chuyển đổi từ String sang java.sql.Date
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.sql.Date startDate = new java.sql.Date(dateFormat.parse(startDateStr).getTime());
        java.sql.Date endDate = new java.sql.Date(dateFormat.parse(endDateStr).getTime());

        ps.setDate(1, startDate);
        ps.setDate(2, endDate);
        ps.setInt(3, trangThai);
        ps.setString(4, maHD);
        ps.setString(5, maHD); 

        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                HoaDonViewModel hd = new HoaDonViewModel();
                hd.setID(rs.getInt("ID"));
                hd.setMaHD(rs.getString("MaHD"));
                hd.setTenNV(rs.getString("TenNV"));
                hd.setNgayTao(rs.getDate("NgayTao"));
                hd.setNgayThanhToan(rs.getDate("NgayThanhToan"));
                hd.setTrangThai(rs.getInt("TRANGTHAIHD")); 
                hd.setTongTien(rs.getFloat("TongTien"));
                hd.setDonGiaSauGiam(rs.getFloat("DonGiaSauGiam"));
                listHD.add(hd);
            }
        }
    }
        // Xử lý lỗi chuyển đổi ngày
    return listHD;
}


    public List<HoaDonViewModel> getHDfindByMaHD(String maHD) throws SQLException {
        List<HoaDonViewModel> listHD = new ArrayList<>();
        String sql = "SELECT dbo.HOADON.ID, dbo.HOADON.MAHD, dbo.NHANVIEN.TENNV, dbo.HOADON.NGAYTAO, "
                + "dbo.HOADON.NGAYTHANHTOAN, dbo.HOADON.TRANGTHAIHD, dbo.HOADON.TONGTIEN, dbo.HOADON.DONGIASAUGIAM "
                + "FROM dbo.HOADON INNER JOIN dbo.NHANVIEN ON dbo.HOADON.IDNV = dbo.NHANVIEN.ID WHERE MAHD = ?";

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

    public List<HoaDonViewModel> getHDfindHDTT(int tt) throws SQLException {
        List<HoaDonViewModel> listHD = new ArrayList<>();
        String sql = "SELECT dbo.HOADON.ID, dbo.HOADON.MAHD, dbo.NHANVIEN.TENNV, dbo.HOADON.NGAYTAO, "
                + "dbo.HOADON.NGAYTHANHTOAN, dbo.HOADON.TRANGTHAIHD, dbo.HOADON.TONGTIEN, dbo.HOADON.DONGIASAUGIAM "
                + "FROM dbo.HOADON INNER JOIN dbo.NHANVIEN ON dbo.HOADON.IDNV = dbo.NHANVIEN.ID WHERE TRANGTHAIHD = 1";

        try (java.sql.Connection con = JdbcHelper.openDbConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

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

    public List<HoaDonViewModel> getHDfindHDCTT() throws SQLException {
        List<HoaDonViewModel> listHD = new ArrayList<>();
        String sql = "SELECT dbo.HOADON.ID, dbo.HOADON.MAHD, dbo.NHANVIEN.TENNV, dbo.HOADON.NGAYTAO, "
                + "dbo.HOADON.NGAYTHANHTOAN, dbo.HOADON.TRANGTHAIHD, dbo.HOADON.TONGTIEN, dbo.HOADON.DONGIASAUGIAM "
                + "FROM dbo.HOADON INNER JOIN dbo.NHANVIEN ON dbo.HOADON.IDNV = dbo.NHANVIEN.ID WHERE TRANGTHAIHD = 0";

        try (java.sql.Connection con = JdbcHelper.openDbConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

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

    public List<HoaDonViewModel> getHDfindHDHuy() throws SQLException {
        List<HoaDonViewModel> listHD = new ArrayList<>();
        String sql = "SELECT dbo.HOADON.ID, dbo.HOADON.MAHD, dbo.NHANVIEN.TENNV, dbo.HOADON.NGAYTAO, "
                + "dbo.HOADON.NGAYTHANHTOAN, dbo.HOADON.TRANGTHAIHD, dbo.HOADON.TONGTIEN, dbo.HOADON.DONGIASAUGIAM "
                + "FROM dbo.HOADON INNER JOIN dbo.NHANVIEN ON dbo.HOADON.IDNV = dbo.NHANVIEN.ID WHERE TRANGTHAIHD = 2";

        try (java.sql.Connection con = JdbcHelper.openDbConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

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
    public int getIdHDByMaHD(String maHD) throws SQLException {
        String sql = "select ID from HOADON where MAHD = ?";
        int idHD = 0;
        ResultSet rs = DBConnection.getDataFromQuery(sql, maHD);

        if (rs.next()) {
            idHD = rs.getInt(1);
        }

        return idHD;
    }
    public int getIdSPCTByMaSPCT(String maSPCT) throws SQLException {
        String sql = "select ID from SANPHAMCHITIET where maSPCT = ?";
        int idSPCT = 0;
        ResultSet rs = DBConnection.getDataFromQuery(sql, maSPCT);

        if (rs.next()) {
            idSPCT = rs.getInt(1);
        }

        return idSPCT;
    }

     public List<HoaDonChiTietViewModel> getAllHDCT(int id) {
        List<HoaDonChiTietViewModel> list = new ArrayList<>();
        String sql = "SELECT dbo.SANPHAMCHITIET.MASPCT, dbo.SANPHAM.TENSP, dbo.HOADONCT.SOLUONG, "
                + "dbo.HOADONCT.DONGIA, dbo.KICHCO.TENKC, dbo.CHATLIEU.TENCL "
                + "FROM dbo.SANPHAMCHITIET "
                + "INNER JOIN dbo.HOADONCT ON dbo.SANPHAMCHITIET.ID = dbo.HOADONCT.IDSPCT "
                + "INNER JOIN dbo.KICHCO ON dbo.SANPHAMCHITIET.IDKC = dbo.KICHCO.ID "
                + "INNER JOIN dbo.SANPHAM ON dbo.SANPHAMCHITIET.IDSP = dbo.SANPHAM.ID "
                + "INNER JOIN dbo.CHATLIEU ON dbo.CHATLIEU.ID = dbo.SANPHAMCHITIET.IDCL "
                + "WHERE IDHD = ?";
        try {
            ResultSet rs = DBConnection.getDataFromQuery(sql, id);
            while (rs.next()) {
                HoaDonChiTietViewModel hdct = new HoaDonChiTietViewModel();
                hdct.setMaSP(rs.getString("MASPCT"));
                hdct.setTenSP(rs.getString("TENSP"));
                hdct.setSoLuong(rs.getInt("SOLUONG"));
                hdct.setDonGia(rs.getFloat("DONGIA"));
                hdct.setTenKC(rs.getString("TENKC"));
                hdct.setTenCL(rs.getString("TENCL"));
                list.add(hdct);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
}
