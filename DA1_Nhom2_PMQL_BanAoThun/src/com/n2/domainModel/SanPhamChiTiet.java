package com.n2.domainModel;

import com.n2.viewModel.ChatLieuViewModel;
import com.n2.viewModel.KichCoViewModel;
import com.n2.viewModel.MauSacViewModel;
import com.n2.viewModel.ThuongHieuViewModel;

public class SanPhamChiTiet {

    private int ID;
    private int idMS;
    private int idCL;
    private int idTH;
    private int idKC;
    private int idSP;
    private String maSPCT;
    private String nguoiTao;
    private int soLuongTon;
    private String moTa;
    private int trangThaiSPCT;
    private float donGia;

    public SanPhamChiTiet() {
    }

    public SanPhamChiTiet(int ID, int idMS, int idCL, int idTH, int idKC, int idSP, String maSPCT, String nguoiTao, int soLuongTon, String moTa, int trangThaiSPCT, float donGia) {
        this.ID = ID;
        this.idMS = idMS;
        this.idCL = idCL;
        this.idTH = idTH;
        this.idKC = idKC;
        this.idSP = idSP;
        this.maSPCT = maSPCT;
        this.nguoiTao = nguoiTao;
        this.soLuongTon = soLuongTon;
        this.moTa = moTa;
        this.trangThaiSPCT = trangThaiSPCT;
        this.donGia = donGia;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getIdMS() {
        return idMS;
    }

    public void setIdMS(int idMS) {
        this.idMS = idMS;
    }

    public int getIdCL() {
        return idCL;
    }

    public void setIdCL(int idCL) {
        this.idCL = idCL;
    }

    public int getIdTH() {
        return idTH;
    }

    public void setIdTH(int idTH) {
        this.idTH = idTH;
    }

    public int getIdKC() {
        return idKC;
    }

    public void setIdKC(int idKC) {
        this.idKC = idKC;
    }

    public int getIdSP() {
        return idSP;
    }

    public void setIdSP(int idSP) {
        this.idSP = idSP;
    }

    public String getMaSPCT() {
        return maSPCT;
    }

    public void setMaSPCT(String maSPCT) {
        this.maSPCT = maSPCT;
    }

    public String getNguoiTao() {
        return nguoiTao;
    }

    public void setNguoiTao(String nguoiTao) {
        this.nguoiTao = nguoiTao;
    }

    public int getSoLuongTon() {
        return soLuongTon;
    }

    public void setSoLuongTon(int soLuongTon) {
        this.soLuongTon = soLuongTon;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public int getTrangThaiSPCT() {
        return trangThaiSPCT;
    }

    public void setTrangThaiSPCT(int trangThaiSPCT) {
        this.trangThaiSPCT = trangThaiSPCT;
    }

    public float getDonGia() {
        return donGia;
    }

    public void setDonGia(float donGia) {
        this.donGia = donGia;
    }

}
