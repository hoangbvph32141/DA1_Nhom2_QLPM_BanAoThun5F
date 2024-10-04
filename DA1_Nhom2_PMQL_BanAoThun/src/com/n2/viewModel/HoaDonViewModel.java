/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.n2.viewModel;

import java.util.Date;

/**
 *
 * @author vxkie
 */
public class HoaDonViewModel {
    private int ID;
    private String tenKH;
    private String tenNV;
    private String maHD;
    private Date ngayTao;
    private String lyDoNghi;
    private Date ngayThanhToan;
    private float donGiaSauGiam;
    private float tongTien;
    private int trangThai;

    public HoaDonViewModel() {
    }

    public HoaDonViewModel(int ID, String tenKH, String tenNV, String maHD, Date ngayTao, String lyDoNghi, Date ngayThanhToan, float donGiaSauGiam, float tongTien, int trangThai) {
        this.ID = ID;
        this.tenKH = tenKH;
        this.tenNV = tenNV;
        this.maHD = maHD;
        this.ngayTao = ngayTao;
        this.lyDoNghi = lyDoNghi;
        this.ngayThanhToan = ngayThanhToan;
        this.donGiaSauGiam = donGiaSauGiam;
        this.tongTien = tongTien;
        this.trangThai = trangThai;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public String getLyDoNghi() {
        return lyDoNghi;
    }

    public void setLyDoNghi(String lyDoNghi) {
        this.lyDoNghi = lyDoNghi;
    }

    public Date getNgayThanhToan() {
        return ngayThanhToan;
    }

    public void setNgayThanhToan(Date ngayThanhToan) {
        this.ngayThanhToan = ngayThanhToan;
    }

    public float getDonGiaSauGiam() {
        return donGiaSauGiam;
    }

    public void setDonGiaSauGiam(float donGiaSauGiam) {
        this.donGiaSauGiam = donGiaSauGiam;
    }

    public float getTongTien() {
        return tongTien;
    }

    public void setTongTien(float tongTien) {
        this.tongTien = tongTien;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }
    
}
