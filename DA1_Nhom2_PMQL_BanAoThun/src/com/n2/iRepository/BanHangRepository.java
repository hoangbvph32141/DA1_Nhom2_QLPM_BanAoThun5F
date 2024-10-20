package com.n2.iRepository;

import com.n2.domainModel.HoaDon;
import com.n2.domainModel.HoaDonChiTiet;
import com.n2.domainModel.KhuyenMai;
import com.n2.domainModel.SanPham;
import com.n2.domainModel.SanPhamChiTiet;
import com.n2.repository.iBanHangRepository;
import com.n2.util.DBConnection;
import com.n2.util.JdbcHelper;
import com.n2.viewModel.HoaDonChiTietViewModel;
import com.n2.viewModel.HoaDonViewModel;
import com.n2.viewModel.SanPhamChiTietViewModel;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vxkie
 */
public class BanHangRepository implements iBanHangRepository {

    @Override
    public String insertHD(HoaDon hd) {
        String sql = "insert into HOADON (MAHD, IDNV, NGAYTAO, TRANGTHAIHD, IDKH) values(?,?,?,?,?)";
        try (Connection con = JdbcHelper.openDbConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, hd.getMaHD());
            ps.setObject(2, hd.getIDNV());
            ps.setObject(3, hd.getNgayTao());
            ps.setObject(4, hd.getTrangThai());
            ps.setObject(5, hd.getIDKH());
            if (ps.executeUpdate() > 0) {
                return "Thêm hóa đơn thành công";
            }
        } catch (Exception e) {
            System.out.println("lỗi");
            e.printStackTrace();
        }

        return "Thêm hóa đơn thất bại";
    }

    public int countHD() throws SQLException {
        String sql = "select count(*) from hoadon";
        int soLuongHoaDon = 0;
        ResultSet rs = DBConnection.getDataFromQuery(sql);

        if (rs.next()) {
            soLuongHoaDon = rs.getInt(1);
        }

        return soLuongHoaDon;
    }

    @Override
    public List<HoaDonChiTiet> getGioHang(String idHD) {
        List<HoaDonChiTiet> list = new ArrayList<>();
        String sql = "select * from HOADONCT where IDHD = ?";
        try {
            JdbcHelper.query(sql, idHD);
        } catch (SQLException ex) {
            Logger.getLogger(BanHangRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public List<SanPhamChiTietViewModel> getAllSPCT() {
        List<SanPhamChiTietViewModel> listSPCT = new ArrayList<>();
        String sql = "SELECT \n"
                + "    dbo.SANPHAMCHITIET.ID, \n"
                + "    dbo.CHATLIEU.TENCL, \n"
                + "    dbo.KICHCO.TENKC, \n"
                + "    dbo.THUONGHIEU.TENTH, \n"
                + "    dbo.MAUSAC.TENMS, \n"
                + "    dbo.SANPHAMCHITIET.SOLUONGTON, \n"
                + "    dbo.SANPHAMCHITIET.DONGIA, \n"
                + "    dbo.SANPHAM.TENSP, \n"
                + "    dbo.SANPHAMCHITIET.TRANGTHAISPCT,\n"
                + "    dbo.SANPHAMCHITIET.MASPCT\n"
                + "FROM \n"
                + "    dbo.SANPHAMCHITIET \n"
                + "INNER JOIN \n"
                + "    dbo.KICHCO ON dbo.SANPHAMCHITIET.IDKC = dbo.KICHCO.ID \n"
                + "INNER JOIN \n"
                + "    dbo.MAUSAC ON dbo.SANPHAMCHITIET.IDMS = dbo.MAUSAC.ID \n"
                + "INNER JOIN \n"
                + "    dbo.SANPHAM ON dbo.SANPHAMCHITIET.IDSP = dbo.SANPHAM.ID \n"
                + "INNER JOIN \n"
                + "    dbo.CHATLIEU ON dbo.SANPHAMCHITIET.IDCL = dbo.CHATLIEU.ID \n"
                + "INNER JOIN \n"
                + "    dbo.THUONGHIEU ON dbo.SANPHAMCHITIET.IDTH = dbo.THUONGHIEU.ID;";
        try {
            ResultSet rs = DBConnection.getDataFromQuery(sql);
            while (rs.next()) {
                SanPhamChiTietViewModel spct = new SanPhamChiTietViewModel();
                spct.setID(rs.getInt(1));
                spct.setTenCL(rs.getString(2));
                spct.setTenKC(rs.getString(3));
                spct.setTenTH(rs.getString(4));
                spct.setTenMS(rs.getString(5));
                spct.setSoLuongTon(rs.getInt(6));
                spct.setDonGia(rs.getFloat(7));
                spct.setTenSP(rs.getString(8));
                spct.setTrangThaiSPCT(rs.getInt(9));
                spct.setMaSPCT(rs.getString(10));
                listSPCT.add(spct);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listSPCT;
    }

    @Override
    public List<HoaDonViewModel> getAllHDD() {
        List<HoaDonViewModel> listHD = new ArrayList<>();
        String sql = "SELECT        dbo.HOADON.ID,  dbo.HOADON.MAHD,   dbo.NHANVIEN.TENNV, dbo.HOADON.NGAYTAO,dbo.HOADON.NGAYTHANHTOAN,  dbo.HOADON.TRANGTHAIHD \n"
                + "FROM            dbo.HOADON INNER JOIN\n"
                + "                         dbo.NHANVIEN ON dbo.HOADON.IDNV = dbo.NHANVIEN.ID\n"
                + "						 where TRANGTHAIHD = 0"
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
                listHD.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listHD;
    }

    @Override
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

    public int getIdHDByMaHD(String maHD) throws SQLException {
        String sql = "select ID from HOADON where MAHD = ?";
        int idHD = 0;
        ResultSet rs = DBConnection.getDataFromQuery(sql, maHD);

        if (rs.next()) {
            idHD = rs.getInt(1);
        }

        return idHD;
    }

    public int countHDCT() throws SQLException {
        String sql = "select count(*) from hoadonct";
        int soLuongHoaDonct = 0;
        ResultSet rs = DBConnection.getDataFromQuery(sql);

        if (rs.next()) {
            soLuongHoaDonct = rs.getInt(1);
        }

        return soLuongHoaDonct;
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

    public String insertHDCT(HoaDonChiTiet hdct) {
        String sql = "insert into HOADONCT (IDHD, IDSPCT, MAHDCT, SOLUONG, DONGIA) values(?,?,?,?,?)";
        try (Connection con = JdbcHelper.openDbConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, hdct.getIdHD());
            ps.setObject(2, hdct.getIdSPCT());
            ps.setObject(3, hdct.getMaHDCT());
            ps.setObject(4, hdct.getSoLuong());
            ps.setObject(5, hdct.getDonGia());
            if (ps.executeUpdate() > 0) {
                return "Thêm hóa đơn thành công";
            }
        } catch (Exception e) {
            System.out.println("lỗi");
            e.printStackTrace();
        }

        return "Thêm hóa đơn thất bại";
    }

    public String updateSPCT(SanPhamChiTiet spct) {
        String sql = "update SANPHAMCHITIET set SOLUONGTON = ? where ID = ?";
        try (Connection con = JdbcHelper.openDbConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, spct.getSoLuongTon());
            ps.setObject(2, spct.getID());
            if (ps.executeUpdate() > 0) {
                return "Thêm hóa đơn thành công";
            }
        } catch (Exception e) {
            System.out.println("lỗi");
            e.printStackTrace();
        }

        return "Thêm hóa đơn thất bại";
    }
    public int findSLbyIdSPCT(int idSPCT) throws SQLException {
        String sql = "select SOLUONGTON from SANPHAMCHITIET where id = ?";
        int sl = 0;
        ResultSet rs = DBConnection.getDataFromQuery(sql, idSPCT);

        if (rs.next()) {
            sl = rs.getInt(1);
        }

        return sl;
    }
    public String deleteSPCT(HoaDonChiTiet hdct) {
        String sql = "delete from HOADONCT where IDHD = ? and IDSPCT = ?";
        try (Connection con = JdbcHelper.openDbConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, hdct.getIdHD());
            ps.setObject(2, hdct.getIdSPCT());
            if (ps.executeUpdate() > 0) {
                return "thành công";
            }
        } catch (Exception e) {
            System.out.println("lỗi");
            e.printStackTrace();
        }

        return "Thất bại";
    }

    public String updateHoaDonCT(HoaDonChiTiet hdct) {
        String sql = "update HoaDonCT set SOLUONG  = ? where IDHD = ? and IDSPCT = ?";
        try (Connection con = JdbcHelper.openDbConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, hdct.getSoLuong());
            ps.setObject(2, hdct.getIdHD());
            ps.setObject(3, hdct.getIdSPCT());  // Sửa tham số thứ 3 thành vị trí đúng

            if (ps.executeUpdate() > 0) {
                return "thành công";
            }
        } catch (Exception e) {
            System.out.println("lỗi");
            e.printStackTrace();
        }

        return "thất bại";
    }

    @Override
    public String findByIdKH(String idKH) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String findByIdNV(String idNV) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public String huyHD(String idHD) {
        HoaDon hd = new HoaDon();
        String sql = "update HoaDon set TrangThaiHD = 2 where ID = ?";
        try (Connection con = JdbcHelper.openDbConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, idHD);

            if (ps.executeUpdate() > 0) {
                return "thành công";
            }
        } catch (Exception e) {
            System.out.println("lỗi");
            e.printStackTrace();
        }

        return "thất bại";
    }

    public String thanhToan(HoaDon hd) {
        
        String sql = "update HoaDon set TRANGTHAIHD = 1, IDKM = ?,TONGTIEN = ?, NGAYTHANHTOAN = ?, DONGIASAUGIAM = ? where id = ?";
        try (Connection con = JdbcHelper.openDbConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setObject(1, hd.getIDKM());
            ps.setObject(2, hd.getTongTien());
            ps.setObject(3, hd.getNgayThanhToan());
            ps.setObject(4, hd.getDonGiaSauGiam());
            ps.setObject(5, hd.getID());
            if (ps.executeUpdate() > 0) {
                return "thành công";
            }
        } catch (Exception e) {
            System.out.println("lỗi");
            e.printStackTrace();
        }

        return "thất bại";
    }
    public int getIdKMbyMaKM(String maKM) throws SQLException {
        String sql = "select ID from KHUYENMAI where maKM = ?";
        int idSPCT = 0;
        ResultSet rs = DBConnection.getDataFromQuery(sql, maKM);

        if (rs.next()) {
            idSPCT = rs.getInt(1);
        }

        return idSPCT;
    }
    public KhuyenMai getKhuyenMaiByMaKH(String maKM) {
    KhuyenMai km = null; // Khởi tạo là null
    String sql = "SELECT TENKM, MUCGIAMGIA FROM KHUYENMAI WHERE MAKM = ?";
    try {
        ResultSet rs = DBConnection.getDataFromQuery(sql, maKM);
        if (rs.next()) { // Kiểm tra xem có kết quả hay không
            km = new KhuyenMai(); // Khởi tạo đối tượng chỉ khi có dữ liệu
            km.setTenKM(rs.getString(1));
            km.setMucGiamGia(rs.getFloat(2));
        } else {
            // Xử lý khi không có kết quả (nếu cần)
            System.out.println("Không tìm thấy khuyến mãi với mã: " + maKM);
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return km; // Trả về null nếu không tìm thấy
}

    @Override
    public List<HoaDonChiTiet> getHDCT(String maHDCT) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<SanPham> getSanPhamGH(String maHD) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<SanPhamChiTiet> getAllSP() {
        return null;
    }

    @Override
    public List<HoaDon> getAllHD() {
        return null;
    }

    @Override
    public void deleteHD(String maHD) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
