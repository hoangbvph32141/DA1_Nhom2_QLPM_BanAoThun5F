/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.n2.domainModel;

import java.util.Date;

/**
 *
 * @author PC
 */
public class KhuyenMai {
    private int idKM;
    private String maKM;
    private String tenKM;
    private float mucGiamGia;
    private float dieuKien;
    private Date thoiGianBatDau;
    private Date thoiGianKetThuc;
    private int trangThaiKM;
    private int soLuong;
    private float tongTien;
    private Date ngayThanhToan;
    private int id;
    private String tenHD;

    public KhuyenMai() {
    }

    public KhuyenMai(int idKM, String maKM, String tenKM, float mucGiamGia, float dieuKien, Date thoiGianBatDau, Date thoiGianKetThuc, int trangThaiKM, int soLuong, float tongTien, Date ngayThanhToan, int id, String tenHD) {
        this.idKM = idKM;
        this.maKM = maKM;
        this.tenKM = tenKM;
        this.mucGiamGia = mucGiamGia;
        this.dieuKien = dieuKien;
        this.thoiGianBatDau = thoiGianBatDau;
        this.thoiGianKetThuc = thoiGianKetThuc;
        this.trangThaiKM = trangThaiKM;
        this.soLuong = soLuong;
        this.tongTien = tongTien;
        this.ngayThanhToan = ngayThanhToan;
        this.id = id;
        this.tenHD = tenHD;
    }

    public int getIdKM() {
        return idKM;
    }

    public void setIdKM(int idKM) {
        this.idKM = idKM;
    }

    public String getMaKM() {
        return maKM;
    }

    public void setMaKM(String maKM) {
        this.maKM = maKM;
    }

    public String getTenKM() {
        return tenKM;
    }

    public void setTenKM(String tenKM) {
        this.tenKM = tenKM;
    }

    public float getMucGiamGia() {
        return mucGiamGia;
    }

    public void setMucGiamGia(float mucGiamGia) {
        this.mucGiamGia = mucGiamGia;
    }

    public float getDieuKien() {
        return dieuKien;
    }

    public void setDieuKien(float dieuKien) {
        this.dieuKien = dieuKien;
    }

    public Date getThoiGianBatDau() {
        return thoiGianBatDau;
    }

    public void setThoiGianBatDau(Date thoiGianBatDau) {
        this.thoiGianBatDau = thoiGianBatDau;
    }

    public Date getThoiGianKetThuc() {
        return thoiGianKetThuc;
    }

    public void setThoiGianKetThuc(Date thoiGianKetThuc) {
        this.thoiGianKetThuc = thoiGianKetThuc;
    }

    public int getTrangThaiKM() {
        return trangThaiKM;
    }

    public void setTrangThaiKM(int trangThaiKM) {
        this.trangThaiKM = trangThaiKM;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public float getTongTien() {
        return tongTien;
    }

    public void setTongTien(float tongTien) {
        this.tongTien = tongTien;
    }

    public Date getNgayThanhToan() {
        return ngayThanhToan;
    }

    public void setNgayThanhToan(Date ngayThanhToan) {
        this.ngayThanhToan = ngayThanhToan;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenHD() {
        return tenHD;
    }

    public void setTenHD(String tenHD) {
        this.tenHD = tenHD;
    }

    
}
