package com.n2.iService;

import com.n2.domainModel.SanPhamChiTiet;
import com.n2.iRepository.BanHangRepository;
import com.n2.service.iSanPhamService;
import com.n2.util.DBConnect;
import com.n2.viewModel.SanPhamViewModel;
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

    public ArrayList<SanPhamViewModel> getAll() {
        // lấy tất cả dl từ bảng mylove trong sql server
        // đổ vào list
        sql = "SELECT ID,MASP,TENSP,TRANGTHAISP FROM SANPHAM";

        ArrayList list_SP = new ArrayList<>();
        try {
            // Kết nối thành công
            con = DBConnect.getConnection();
            pr = con.prepareStatement(sql);
            rs = pr.executeQuery();
            while (rs.next()) { // chạy từ đầu đến cuối tập rs
                int iD = rs.getInt(1);
                String maSP = rs.getString(2);
                String tenSP = rs.getString(3);
                int trangThaiSP = rs.getInt(4);
                SanPhamViewModel ml = new SanPhamViewModel(iD, maSP, tenSP, trangThaiSP);
                list_SP.add(ml);
            }// đóng white
            return list_SP;

        } catch (Exception e) {
            // Kết nối lỗi
            e.printStackTrace(); // In ra các lỗi
            return null;
        }
    }

}
