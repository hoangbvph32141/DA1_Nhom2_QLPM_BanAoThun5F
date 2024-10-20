/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.n2.iService;

import com.n2.domainModel.KhuyenMai;
import com.n2.iRepository.KhuyenMaiRepository;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author PC
 */
public class KhuyenMaiService {
    private KhuyenMaiRepository khuyenMaiRepository = new KhuyenMaiRepository();
    
    public ArrayList<KhuyenMai> getListKhuyenMai() {
        return khuyenMaiRepository.getListKhuyenMai();
    }
    
    public int themKM(KhuyenMai khuyenMai) {
        return khuyenMaiRepository.themKM(khuyenMai);
    }
    
    public int countKM() throws SQLException {
        return khuyenMaiRepository.countKM();
    }
    
    public int suaKM(KhuyenMai khuyenMai) {
        return khuyenMaiRepository.suaKM(khuyenMai);
    }
    
    public ArrayList<KhuyenMai> getListSP() {
        return khuyenMaiRepository.getListSP();
    }
    
    public int doiTTKM(KhuyenMai khuyenMai) {
        return khuyenMaiRepository.doiTTKM(khuyenMai);
    }
    
    public ArrayList<KhuyenMai> searchKM(String tenKM) {
        return khuyenMaiRepository.searchKM(tenKM);
    }
}
