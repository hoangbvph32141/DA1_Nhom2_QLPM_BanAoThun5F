/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.n2.iService;

import com.n2.domainModel.NhanVien;
import com.n2.iRepository.DangNhapRepository;

/**
 *
 * @author PC
 */
public class DangNhapService {
    DangNhapRepository dangNhapRepository = new DangNhapRepository();
    
    public NhanVien checkLogin(String sdt, String matKhau) {
        return dangNhapRepository.checkLogin(sdt, matKhau);
    }
}
