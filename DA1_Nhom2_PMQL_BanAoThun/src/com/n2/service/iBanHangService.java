package com.n2.service;

import com.n2.domainModel.HoaDon;
import com.n2.domainModel.HoaDonChiTiet;
import com.n2.domainModel.SanPham;
import com.n2.domainModel.SanPhamChiTiet;
import java.util.List;

public interface iBanHangService {

    List<SanPhamChiTiet> getAllSP();

    List<HoaDon> getAllHD();

    String insertHD(HoaDon hd);

    String findByIdKH(String idKH);

    String findByIdNV(String idNV);

    void deleteHD(String maHD);

    List<HoaDonChiTiet> getHDCT(String maHDCT);

    List<SanPham> getSanPhamGH(String maHD);

}
