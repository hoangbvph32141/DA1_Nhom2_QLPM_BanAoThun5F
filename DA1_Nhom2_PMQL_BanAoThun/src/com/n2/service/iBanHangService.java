package com.n2.service;

import com.n2.domainModel.HoaDon;
import com.n2.domainModel.HoaDonChiTiet;
import com.n2.domainModel.SanPham;
import com.n2.domainModel.SanPhamChiTiet;
import com.n2.viewModel.HoaDonChiTietViewModel;
import com.n2.viewModel.HoaDonViewModel;
import com.n2.viewModel.SanPhamChiTietViewModel;
import java.util.List;

public interface iBanHangService {

    List<SanPhamChiTiet> getAllSP();
     List<SanPhamChiTietViewModel> getAllSPCT();

    List<HoaDon> getAllHD();
    
    List<HoaDonViewModel> getAllHDD();
    
    List<HoaDonChiTietViewModel>getAllHDCT(int id);

    
    String insertHD(HoaDon hd);

    String findByIdKH(String idKH);

    String findByIdNV(String idNV);

    void deleteHD(String maHD);

    List<HoaDonChiTiet> getHDCT(String maHDCT);

    List<SanPham> getSanPhamGH(String maHD);

}
