package com.n2.iService;

import com.n2.domainModel.NhanVien;
import com.n2.iRepository.NhanVienRepository;
import java.sql.SQLException;
import java.util.ArrayList;

public class NhanVienService {
    NhanVienRepository nhanVienRepository = new NhanVienRepository();
    
    public ArrayList<NhanVien> getListNhanVien() {
        return nhanVienRepository.getListNhanVien();
    }
    
    public ArrayList<NhanVien> searchNV(String tenNV) {
        return nhanVienRepository.searchNV(tenNV);
    }
    
    public int themNV(NhanVien nhanVien) {
        return nhanVienRepository.themNV(nhanVien);
    }
    
    public int countNV() throws SQLException {
        return nhanVienRepository.countNV();
    }
    
    public int suaNV(NhanVien nhanVien) {
        return nhanVienRepository.suaNV(nhanVien);
    }
    
    public int doiTTNV(NhanVien nhanVien) {
        return nhanVienRepository.doiTTNV(nhanVien);
    }
    
    public boolean checkSdtExists(String sdt) throws SQLException {
        return nhanVienRepository.checkSdtExists(sdt);
    }
    
    public boolean checkCccdExists(String cccd) throws SQLException {
        return nhanVienRepository.checkCccdExists(cccd);
    }
    
    public boolean checkEmailExists(String email) throws SQLException {
        return nhanVienRepository.checkEmailExists(email);
    }
}
