package com.n2.iService;

import com.n2.domainModel.KhachHang;
import com.n2.domainModel.KhuyenMai;
import com.n2.iRepository.KhachHangRepository;
import java.sql.SQLException;
import java.util.ArrayList;

public class KhachHangService {
    private KhachHangRepository khachHangRepository = new KhachHangRepository();
    
    public ArrayList<KhachHang> getListKhachHang() {
        return khachHangRepository.getListKhachHang();
    }
    
    public int themKH(KhachHang khachHang) {
        return khachHangRepository.themKH(khachHang);
    }
    
    public int countKH() throws SQLException {
        return khachHangRepository.countKH();
    }
    
    public int suaKH(KhachHang khachHang) {
        return khachHangRepository.suaKH(khachHang);
    }
    
    public int doiTTKH(KhachHang khachHang) {
        return khachHangRepository.doiTTKH(khachHang);
    }
    
    public ArrayList<KhachHang> searchKH(String tenKH) {
        return khachHangRepository.searchKH(tenKH);
    }
    
    
}
