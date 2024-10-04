
package com.n2.domainModel;

import java.util.Date;

public class HoaDonChiTiet {
     private int ID;
     private int idHD;
     private int idSPCT;
     private String maHDCT;
     private Date ngayTao;
     private float donGia;
     private float donGiaSauKhiGiam;
     private int soLuong;

    public HoaDonChiTiet() {
    }

    public HoaDonChiTiet(int ID, int idHD, int idSPCT, String maHDCT, Date ngayTao, float donGia, float donGiaSauKhiGiam, int soLuong) {
        this.ID = ID;
        this.idHD = idHD;
        this.idSPCT = idSPCT;
        this.maHDCT = maHDCT;
        this.ngayTao = ngayTao;
        this.donGia = donGia;
        this.donGiaSauKhiGiam = donGiaSauKhiGiam;
        this.soLuong = soLuong;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getIdHD() {
        return idHD;
    }

    public void setIdHD(int idHD) {
        this.idHD = idHD;
    }

    public int getIdSPCT() {
        return idSPCT;
    }

    public void setIdSPCT(int idSPCT) {
        this.idSPCT = idSPCT;
    }

    public String getMaHDCT() {
        return maHDCT;
    }

    public void setMaHDCT(String maHDCT) {
        this.maHDCT = maHDCT;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public float getDonGia() {
        return donGia;
    }

    public void setDonGia(float donGia) {
        this.donGia = donGia;
    }

    public float getDonGiaSauKhiGiam() {
        return donGiaSauKhiGiam;
    }

    public void setDonGiaSauKhiGiam(float donGiaSauKhiGiam) {
        this.donGiaSauKhiGiam = donGiaSauKhiGiam;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
     
     
}
