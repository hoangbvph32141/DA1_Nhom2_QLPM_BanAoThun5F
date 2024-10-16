package com.n2.iService;

import com.n2.domainModel.SanPhamChiTiet;
import com.n2.iRepository.BanHangRepository;
import com.n2.service.iSanPhamService;
import com.n2.util.DBConnect;
import com.n2.viewModel.ChatLieuViewModel;
import com.n2.viewModel.KichCoViewModel;
import com.n2.viewModel.MauSacViewModel;
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

    // Select bảng Sản Phẩm
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

    // Select bảng DS Sản Phẩm
    public ArrayList<SanPhamChiTietViewModel> getAllSPCT() {
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
                SanPhamChiTietViewModel ml = new SanPhamChiTietViewModel();
                list_SP.add(ml);
            }
            return list_SP;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
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

}
