package com.n2.domainModel;

import java.util.Date;

public class KhachHang {
    private int idKH;
    private String maKH;
    private String tenKH;
    private Date ngaySinh;
    private int gioiTinh;
    private String sdt;
    private String diaChi;
    private int trangThaiKH;

    public KhachHang() {
    }

    public KhachHang(int idKH, String maKH, String tenKH, Date ngaySinh, int gioiTinh, String sdt, String diaChi, int trangThaiKH) {
        this.idKH = idKH;
        this.maKH = maKH;
        this.tenKH = tenKH;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.sdt = sdt;
        this.diaChi = diaChi;
        this.trangThaiKH = trangThaiKH;
    }

    public int getIdKH() {
        return idKH;
    }

    public void setIdKH(int idKH) {
        this.idKH = idKH;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public int getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(int gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public int getTrangThaiKH() {
        return trangThaiKH;
    }

    public void setTrangThaiKH(int trangThaiKH) {
        this.trangThaiKH = trangThaiKH;
    }

    
}
