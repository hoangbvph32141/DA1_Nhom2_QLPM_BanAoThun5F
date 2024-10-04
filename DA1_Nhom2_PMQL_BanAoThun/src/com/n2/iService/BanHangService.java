package com.n2.iService;

import com.n2.domainModel.HoaDon;
import com.n2.domainModel.HoaDonChiTiet;
import com.n2.domainModel.SanPham;
import com.n2.domainModel.SanPhamChiTiet;
import com.n2.iRepository.BanHangRepository;
import com.n2.service.iBanHangService;
import com.n2.viewModel.HoaDonChiTietViewModel;
import com.n2.viewModel.HoaDonViewModel;
import com.n2.viewModel.SanPhamChiTietViewModel;
import java.sql.SQLException;
import java.util.List;

public class BanHangService implements iBanHangService {

    private BanHangRepository banHangRepository = new BanHangRepository();

    @Override
    public List<SanPhamChiTiet> getAllSP() {
        return banHangRepository.getAllSP();
    }

    @Override
    public List<HoaDon> getAllHD() {
        return banHangRepository.getAllHD();
    }

    @Override
    public String insertHD(HoaDon hd) {
        return banHangRepository.insertHD(hd);
    }

    public int countHD() throws SQLException {
        return banHangRepository.countHD();
    }

    @Override
    public List<SanPhamChiTietViewModel> getAllSPCT() {
        return banHangRepository.getAllSPCT();
    }

    @Override
    public List<HoaDonViewModel> getAllHDD() {
        return banHangRepository.getAllHDD();
    }

    @Override
    public List<HoaDonChiTietViewModel> getAllHDCT(int id) {
        return banHangRepository.getAllHDCT(id);
    }

    public int getIdHDByMaHD(String maHD) throws SQLException {
        return banHangRepository.getIdHDByMaHD(maHD);
    }

    public int getIdSPCTByMaSPCT(String maSPCT) throws SQLException {
        return banHangRepository.getIdSPCTByMaSPCT(maSPCT);
    }

    public String insertHDCT(HoaDonChiTiet hdct) {
        return banHangRepository.insertHDCT(hdct);
    }

    public int countHDCT() throws SQLException {
        return banHangRepository.countHDCT();
    }

    public String updateSPCT(SanPhamChiTiet spct) {
        return banHangRepository.updateSPCT(spct);
    }

    public String deleteSPCT(HoaDonChiTiet hdct) {
        return banHangRepository.deleteSPCT(hdct);
    }

    public String updateHoaDonCT(HoaDonChiTiet hdct) {
        return banHangRepository.updateHoaDonCT(hdct);
    }
    public String huyHD(String idHD) {
        return banHangRepository.huyHD(idHD);
    }
    @Override
    public String findByIdKH(String idKH) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String findByIdNV(String idNV) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteHD(String maHD) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<HoaDonChiTiet> getHDCT(String maHDCT) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<SanPham> getSanPhamGH(String maHD) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
