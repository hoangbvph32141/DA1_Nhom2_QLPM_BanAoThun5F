/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.n2.iService;

import DTOSanPhamThongKe.SanPhamDTO;
import com.n2.domainModel.DoanhThu;
import com.n2.iRepository.ThongKeRepository;
import java.util.List;

/**
 *
 * @author admin
 */
public class ThongKeService {
    
    private ThongKeRepository tkr = new ThongKeRepository();
    
    public DoanhThu getByMAndY(String m, String y){
        return tkr.getDoanhThuByThang(m,y);
    }
    
    public List<SanPhamDTO> getByDate(String bd, String kt){
        return tkr.getByDate(bd, kt);
    }
    
    public List<SanPhamDTO> searchByDate(String bd, String kt,String key){
        return tkr.searchByDate(bd, kt, key);
    }
    
    public List<DoanhThu> getOneDate(String bt, String kt){
        return tkr.getDoanhThuByNgay(bt, bt);
    }
}
