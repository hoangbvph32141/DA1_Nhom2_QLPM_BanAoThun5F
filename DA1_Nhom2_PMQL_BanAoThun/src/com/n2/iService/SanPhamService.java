package com.n2.iService;

import com.n2.domainModel.SanPhamChiTiet;
import com.n2.iRepository.BanHangRepository;
import com.n2.service.iSanPhamService;
import com.n2.util.DBConnect;
import com.n2.viewModel.ChatLieuViewModel;
import com.n2.viewModel.KichCoViewModel;
import com.n2.viewModel.MauSacViewModel;
import com.n2.viewModel.SanPhamCTViewModel;
import com.n2.viewModel.SanPhamChiTietViewModel;
import com.n2.viewModel.SanPhamViewModel;
import com.n2.viewModel.ThuongHieuViewModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SanPhamService implements iSanPhamService {

    private Connection con = null;       // Biến kết nối với csdl
    private PreparedStatement pr = null; // Chuẩn bị thực hiện lệnh
    private ResultSet rs = null;         // Tập kết quả truy vấn select
    private String sql = null;           // Câu lệnh sql

    public SanPhamService() {
        con = DBConnect.getConnection();
    }

    // bảng Sản Phẩm
    public ArrayList<SanPhamViewModel> getAllSP() {
        sql = "SELECT ID,MASP,TENSP,TRANGTHAISP FROM SANPHAM";

        ArrayList list_SP = new ArrayList<>();
        try {
            con = DBConnect.getConnection();
            pr = con.prepareStatement(sql);
            rs = pr.executeQuery();
            while (rs.next()) {
                int iD = rs.getInt(1);
                String maSP = rs.getString(2);
                String tenSP = rs.getString(3);
                int trangThaiSP = rs.getInt(4);
                SanPhamViewModel ml = new SanPhamViewModel(iD, maSP, tenSP, trangThaiSP);
                list_SP.add(ml);
            }
            return list_SP;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int themSP(SanPhamViewModel m) {
        sql = "insert into SANPHAM(MASP,TENSP,TRANGTHAISP) values(?,?,?)";
        try {
            con = DBConnect.getConnection();
            pr = con.prepareStatement(sql);
            pr.setObject(1, m.getMaSP());
            pr.setObject(2, m.getTenSP());
            pr.setObject(3, m.getTrangThaiSP());
            return pr.executeUpdate(); // thêm sửa xoá
        } catch (Exception e) {
            // thêm thất bại
            e.printStackTrace();
            return 0;
        }
    }

    public int suaSP(int id, SanPhamViewModel m) {
        sql = "update SANPHAM set MASP=?,TENSP=?,TRANGTHAISP=? where id=?";

        try {
            pr = con.prepareStatement(sql);
            pr.setObject(1, m.getMaSP());
            pr.setObject(2, m.getTenSP());
            pr.setObject(3, m.getTrangThaiSP());
            pr.setObject(4, id);
            return pr.executeUpdate();
        } catch (Exception e) { // không sửa được
            return 0;
        }
    }

    public int xoaSP(int id) {
        sql = "delete from SANPHAM where id =?";
        try {
            con = DBConnect.getConnection();
            pr = con.prepareStatement(sql);
            pr.setObject(1, id);
            return pr.executeUpdate();

        } catch (Exception e) {
            return 0;
        }
    }

    // Sản Phẩm Chi Tiết
    public ArrayList<SanPhamCTViewModel> getAllSPCT() {
        sql = "SELECT IDMS,IDCL,IDTH,IDKC,IDSP,MASPCT,NGUOITAO,SOLUONGTON,MOTA,TRANGTHAISPCT,DONGIA FROM SANPHAMCHITIET";

        ArrayList list_SPCT = new ArrayList<>();
        try {
            con = DBConnect.getConnection();
            pr = con.prepareStatement(sql);
            rs = pr.executeQuery();
            while (rs.next()) {
                int idMS = rs.getInt(1);
                int idCL = rs.getInt(2);
                int idTH = rs.getInt(3);
                int idKC = rs.getInt(4);
                int idSP = rs.getInt(5);
                String maSPCT = rs.getString(6);
                String nguoiTao = rs.getString(7);
                int soLuongTon = rs.getInt(8);
                String moTa = rs.getString(9);
                int trangThaiSPCT = rs.getInt(10);
                float donGia = rs.getFloat(11);
                SanPhamCTViewModel ml = new SanPhamCTViewModel(idMS, idCL, idTH, idKC, idSP, maSPCT, nguoiTao, soLuongTon, moTa, trangThaiSPCT, donGia);
                list_SPCT.add(ml);
            }
            return list_SPCT;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int themSPCT(SanPhamCTViewModel m) {
        sql = "insert into SANPHAMCHITIET(IDMS,IDCL,IDTH,IDKC,IDSP,MASPCT,NGUOITAO,SOLUONGTON,MOTA,TRANGTHAISPCT,DONGIA) values(?,?,?,?,?,?,?,?,?,?,?)";
        try {
            con = DBConnect.getConnection();
            pr = con.prepareStatement(sql);
            pr.setInt(1, m.getIdMS());
            pr.setInt(2, m.getIdCL());
            pr.setInt(3, m.getIdTH());
            pr.setInt(4, m.getIdKC());
            pr.setInt(5, m.getIdSP());
            pr.setString(6, m.getMaSPCT());
            pr.setString(7, m.getNguoiTao());
            pr.setInt(8, m.getSoLuongTon());
            pr.setString(9, m.getMoTa());
            pr.setInt(10, m.getTrangThaiSPCT());
            pr.setFloat(11, m.getDonGia());
            return pr.executeUpdate(); // thêm sửa xoá
        } catch (Exception e) {
            // thêm thất bại
            e.printStackTrace();
            return 0;
        }
    }

    public int suaSPCT(int id, SanPhamCTViewModel m) {
        sql = "update SANPHAMCHITIET set IDMS=?,IDCL=?,IDTH=?,IDKC=?,IDSP=?,MASPCT=?,NGUOITAO=?,SOLUONGTON=?,MOTA=?,TRANGTHAISPCT=?,DONGIA=? where id=?";

        try {
            pr = con.prepareStatement(sql);
            pr.setObject(1, m.getIdMS());
            pr.setObject(2, m.getIdCL());
            pr.setObject(3, m.getIdTH());
            pr.setObject(4, m.getIdKC());
            pr.setObject(5, m.getIdSP());
            pr.setObject(6, m.getMaSPCT());
            pr.setObject(7, m.getNguoiTao());
            pr.setObject(8, m.getSoLuongTon());
            pr.setObject(9, m.getMoTa());
            pr.setObject(10, m.getTrangThaiSPCT());
            pr.setObject(11, m.getDonGia());
            pr.setObject(12, id);
            return pr.executeUpdate();
        } catch (Exception e) { // không sửa được
            return 0;
        }
    }

    public int xoaSPCT(int id) {
        sql = "delete from SANPHAMCHITIET where id =?";
        try {
            con = DBConnect.getConnection();
            pr = con.prepareStatement(sql);
            pr.setObject(1, id);
            return pr.executeUpdate();

        } catch (Exception e) {
            return 0;
        }
    }

    // Phương thức để lấy sản phẩm theo ID
    public SanPhamViewModel getSanPhamById(int id) {
        SanPhamViewModel spvm = null;
        sql = "SELECT * FROM SANPHAM WHERE ID = ?";

        try {
            con = DBConnect.getConnection();
            pr = con.prepareStatement(sql);
            rs = pr.executeQuery();
            pr.setInt(1, id);

            if (rs.next()) {
                spvm = new SanPhamViewModel();
                // Giả định các trường tương ứng
                spvm.setID(rs.getInt("ID"));
                // Thêm các thuộc tính khác nếu cần
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return spvm;
    }

    // Select bảng Thuộc Tính Màu Sắc
    public ArrayList<MauSacViewModel> getAllMS() {
        sql = "SELECT ID,MAMS,TENMS,TRANGTHAIMS FROM MAUSAC";

        ArrayList list_MS = new ArrayList<>();
        try {
            con = DBConnect.getConnection();
            pr = con.prepareStatement(sql);
            rs = pr.executeQuery();
            while (rs.next()) {
                int iD = rs.getInt(1);
                String maMS = rs.getString(2);
                String tenMS = rs.getString(3);
                int trangThaiMS = rs.getInt(4);
                MauSacViewModel ml = new MauSacViewModel(iD, maMS, tenMS, trangThaiMS);
                list_MS.add(ml);
            }
            return list_MS;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int themTTMS(MauSacViewModel m) {
        sql = "insert into MAUSAC(MAMS,TENMS,TRANGTHAIMS) values(?,?,?)";
        try {
            con = DBConnect.getConnection();
            pr = con.prepareStatement(sql);
            pr.setObject(1, m.getMaMS());
            pr.setObject(2, m.getTenMS());
            pr.setObject(3, m.getTrangThaiMS());
            return pr.executeUpdate(); // thêm sửa xoá
        } catch (Exception e) {
            // thêm thất bại
            e.printStackTrace();
            return 0;
        }
    }

    public int suaTTMS(int id, MauSacViewModel m) {
        sql = "update MAUSAC set MAMS=?,TENMS=?,TRANGTHAIMS=? where id=?";

        try {
            pr = con.prepareStatement(sql);
            pr.setObject(1, m.getMaMS());
            pr.setObject(2, m.getTenMS());
            pr.setObject(3, m.getTrangThaiMS());
            pr.setObject(4, id);
            return pr.executeUpdate();
        } catch (Exception e) { // không sửa được
            return 0;
        }
    }

    // Select bảng Thuộc Tính Chất Liệu
    public ArrayList<ChatLieuViewModel> getAllCL() {
        sql = "SELECT ID,MACL,TENCL,TRANGTHAICL FROM CHATLIEU";

        ArrayList list_CL = new ArrayList<>();
        try {
            con = DBConnect.getConnection();
            pr = con.prepareStatement(sql);
            rs = pr.executeQuery();
            while (rs.next()) {
                int iD = rs.getInt(1);
                String maCL = rs.getString(2);
                String tenCL = rs.getString(3);
                int trangThaiCL = rs.getInt(4);
                ChatLieuViewModel ml = new ChatLieuViewModel(iD, maCL, tenCL, trangThaiCL);
                list_CL.add(ml);
            }
            return list_CL;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int themTTCL(ChatLieuViewModel m) {
        sql = "insert into CHATLIEU(MACL,TENCL,TRANGTHAICL) values(?,?,?)";
        try {
            con = DBConnect.getConnection();
            pr = con.prepareStatement(sql);
            pr.setObject(1, m.getMaCL());
            pr.setObject(2, m.getTenCL());
            pr.setObject(3, m.getTrangThaiMS()); // ở viewmodel đặt đặt là trạng thái MS => đây là sai hãy sửa
            return pr.executeUpdate();
        } catch (Exception e) {
            // thêm thất bại
            e.printStackTrace();
            return 0;
        }
    }

    public int suaTTCL(int id, ChatLieuViewModel m) {
        sql = "update CHATLIEU set MACL=?,TENCL=?,TRANGTHAICL=? where id=?";

        try {
            pr = con.prepareStatement(sql);
            pr.setObject(1, m.getMaCL());
            pr.setObject(2, m.getTenCL());
            pr.setObject(3, m.getTrangThaiMS());
            pr.setObject(4, id);
            return pr.executeUpdate();
        } catch (Exception e) { // không sửa được
            return 0;
        }
    }

    // Select bảng Thuộc Tính Thương Hiệu
    public ArrayList<ThuongHieuViewModel> getAllTH() {
        sql = "SELECT ID,MATH,TENTH,TRANGTHAITH FROM THUONGHIEU";

        ArrayList list_TH = new ArrayList<>();
        try {
            con = DBConnect.getConnection();
            pr = con.prepareStatement(sql);
            rs = pr.executeQuery();
            while (rs.next()) {
                int iD = rs.getInt(1);
                String maTH = rs.getString(2);
                String tenTH = rs.getString(3);
                int trangThaiTH = rs.getInt(4);
                ThuongHieuViewModel ml = new ThuongHieuViewModel(iD, maTH, tenTH, trangThaiTH);
                list_TH.add(ml);
            }
            return list_TH;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int themTTTH(ThuongHieuViewModel m) {
        sql = "insert into THUONGHIEU(MATH,TENTH,TRANGTHAITH) values(?,?,?)";
        try {
            con = DBConnect.getConnection();
            pr = con.prepareStatement(sql);
            pr.setObject(1, m.getMaTH());
            pr.setObject(2, m.getTenTH());
            pr.setObject(3, m.getTrangThaiTH());
            return pr.executeUpdate();
        } catch (Exception e) {
            // thêm thất bại
            e.printStackTrace();
            return 0;
        }
    }

    public int suaTTTH(int id, ThuongHieuViewModel m) {
        sql = "update THUONGHIEU set MATH=?,TENTH=?,TRANGTHAITH=? where id=?";

        try {
            pr = con.prepareStatement(sql);
            pr.setObject(1, m.getMaTH());
            pr.setObject(2, m.getTenTH());
            pr.setObject(3, m.getTrangThaiTH());
            pr.setObject(4, id);
            return pr.executeUpdate();
        } catch (Exception e) { // không sửa được
            return 0;
        }
    }

    // Select bảng Thuộc Tính Kích Cỡ
    public ArrayList<KichCoViewModel> getAllKC() {
        sql = "SELECT ID,MAKC,TENKC,TRANGTHAIKC FROM KICHCO";

        ArrayList list_TH = new ArrayList<>();
        try {
            con = DBConnect.getConnection();
            pr = con.prepareStatement(sql);
            rs = pr.executeQuery();
            while (rs.next()) {
                int iD = rs.getInt(1);
                String maKC = rs.getString(2);
                String tenKC = rs.getString(3);
                int trangThaiKC = rs.getInt(4);
                KichCoViewModel ml = new KichCoViewModel(iD, maKC, tenKC, trangThaiKC);
                list_TH.add(ml);
            }
            return list_TH;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int themTTKC(KichCoViewModel m) {
        sql = "insert into KICHCO(MAKC,TENKC,TRANGTHAIKC) values(?,?,?)";
        try {
            con = DBConnect.getConnection();
            pr = con.prepareStatement(sql);
            pr.setObject(1, m.getMaKC());
            pr.setObject(2, m.getTenKC());
            pr.setObject(3, m.getTrangThaiKC());
            return pr.executeUpdate();
        } catch (Exception e) {
            // thêm thất bại
            e.printStackTrace();
            return 0;
        }
    }

    public int suaTTKC(int id, KichCoViewModel m) {
        sql = "update KICHCO set MAKC=?,TENKC=?,TRANGTHAIKC=? where id=?";

        try {
            pr = con.prepareStatement(sql);
            pr.setObject(1, m.getMaKC());
            pr.setObject(2, m.getTenKC());
            pr.setObject(3, m.getTrangThaiKC());
            pr.setObject(4, id);
            return pr.executeUpdate();
        } catch (Exception e) { // không sửa được
            return 0;
        }
    }

}
