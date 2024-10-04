/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.n2.viewModel;

/**
 *
 * @author vxkie
 */
public class SanPhamChiTietViewModel {
    private int ID;
    private String tenMS;
    private String tenCL;
    private String tenTH;
    private String tenKC;
    private String tenSP;
    private String maSPCT;
    private String nguoiTao;
    private int soLuongTon;
    private String moTa;
    private int trangThaiSPCT;
    private float donGia;

    public SanPhamChiTietViewModel() {
    }

    public SanPhamChiTietViewModel(int ID, String tenMS, String tenCL, String tenTH, String tenKC, String tenSP, String maSPCT, String nguoiTao, int soLuongTon, String moTa, int trangThaiSPCT, float donGia) {
        this.ID = ID;
        this.tenMS = tenMS;
        this.tenCL = tenCL;
        this.tenTH = tenTH;
        this.tenKC = tenKC;
        this.tenSP = tenSP;
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

    public String getTenMS() {
        return tenMS;
    }

    public void setTenMS(String tenMS) {
        this.tenMS = tenMS;
    }

    public String getTenCL() {
        return tenCL;
    }

    public void setTenCL(String tenCL) {
        this.tenCL = tenCL;
    }

    public String getTenTH() {
        return tenTH;
    }

    public void setTenTH(String tenTH) {
        this.tenTH = tenTH;
    }

    public String getTenKC() {
        return tenKC;
    }

    public void setTenKC(String tenKC) {
        this.tenKC = tenKC;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
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
